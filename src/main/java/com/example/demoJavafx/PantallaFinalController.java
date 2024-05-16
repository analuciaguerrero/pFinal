import com.example.demoJavafx.bucleDeControl.BucleDeControlProperties;
import com.example.demoJavafx.estructurasDeDatos.ArbolDeBusqueda.BST;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.tablero.Celda;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.net.URL;
import java.util.ResourceBundle;

public class PantallaFinalController implements Initializable {
    private Stage stage;

    private BucleDeControlProperties modelo;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


    public void loadUserData(BucleDeControlProperties parametrosBucleDeControl) {
        this.modelo = parametrosBucleDeControl;
    }

    public void setStage(Stage s) {
        this.stage = s;
    }

    public void guardarButton() {
        modelo.commit();
    }

    public void cerrarButton() {
        stage.close();
    }

    public void cancelarButton() {
        modelo.rollback();
    }


    public void abrirPantallaFinal() {
        Stage stage = new Stage();
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PantallaFinal.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("¡El juego ha terminado! ");
            stage.setScene(scene);
            SplitPane split = new SplitPane();
            VBox vbox = new VBox();
            for (int j = 0; j < modelo.getFilas(); j++) {
                for (int i = 0; i < modelo.getColumnas(); i++) {
                    Celda celda = modelo.getOriginal().getCelda(i, j);
                    for (int k = 0; k < celda.getListaEstudiantes().getNumeroElementos(); k++) {
                        Estudiante est = celda.getListaEstudiantes().getElemento(k).getData();
                        Label lab = new Label(est.toString());
                        Button b = new Button("Ver árbol");
                        vbox.getChildren().addAll(lab, b);
                        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent e) {
                                DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(est.getArbolGenealogico().raiz.getDato());
                                mostrarArboles(rootNode, est.getArbolGenealogico());
                                JTree tree = new JTree(rootNode);
                                JScrollPane scrollPane = new JScrollPane(tree);
                                JFrame frame = new JFrame("Tree View Est" + est.getId());
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.add(scrollPane);
                                frame.setSize(300, 300);
                                frame.setVisible(true);
                            }
                        };
                        b.setOnAction(event);
                    }
                }
            }
            ScrollPane scroll = new ScrollPane(vbox);
            split.getItems().add(scroll);
            split.setOrientation(Orientation.HORIZONTAL);
            Scene scene1 = new Scene(split, 800, 600);
            stage.setScene(scene1);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarArboles(DefaultMutableTreeNode d, BST<Estudiante> est) {
        if (est.raiz.getDerecha() != null) {
            DefaultMutableTreeNode child1 = new DefaultMutableTreeNode(est.raiz.getDerecha().getDato());
            d.add(child1);
            mostrarArboles(child1, est.getSubArbolDcha());
        }
        if (est.raiz.getIzquierda() != null) {
            DefaultMutableTreeNode child2 = new DefaultMutableTreeNode(est.raiz.getIzquierda().getDato());
            d.add(child2);
            mostrarArboles(child2, est.getSubArbolIzq());
        }

    }

}
