import com.example.demoJavafx.bucleDeControl.BucleDeControlProperties;
import com.example.demoJavafx.estructurasDeDatos.ArbolDeBusqueda.BST;
import com.example.demoJavafx.estudiante.Estudiante;
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

    private BucleDeControlProperties matrizModel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


    public void loadUserData(BucleDeControlProperties parametrosBucleDeControl) {
        this.matrizModel = parametrosBucleDeControl;
    }

    public void setStage(Stage s) {
        this.stage = s;
    }

    public void guardarButton() {
        matrizModel.commit();
    }

    public void cerrarButton() {
        stage.close();
    }

    public void cancelarButton() {
        matrizModel.rollback();
    }


    public void abrirPantallaFinal() {
        Stage stage = new Stage();
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pantalla-final.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("¡El juego ha finalizado! ");
            stage.setScene(scene);

            SplitPane split = new SplitPane();

            VBox vbox = new VBox();

            for (int j = 0; j < matrizModel.getFilas(); j++) {
                for (int i = 0; i < matrizModel.getColumnas(); i++) {
                    for (int k = 0; k < matrizModel.original.matriz[j][i].getListaIndividuo().getNumeroElementos(); k++) {
                        Estudiante ind = matrizModel.original.matriz[j][i].getListaIndividuo().getElemento(k).getData();
                        Label lab = new Label(ind.toString());
                        Button b = new Button("Ver árbol");
                        vbox.getChildren().addAll(lab, b);

                        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent e) {
                                DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(ind.getArbolGenealogico().raiz.getDato());
                                mostrarArboles(rootNode, ind.getArbolGenealogico());


                                JTree tree = new JTree(rootNode);

                                JScrollPane scrollPane = new JScrollPane(tree);

                                JFrame frame = new JFrame("Tree View Ind" + ind.getId());
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

    public void mostrarArboles(DefaultMutableTreeNode d, BST<Estudiante> ind) {


        if (ind.raiz.getDerecha() != null) {
            DefaultMutableTreeNode child1 = new DefaultMutableTreeNode(ind.raiz.getDerecha().getDato());
            d.add(child1);
            mostrarArboles(child1, ind.getSubArbolDcha());
        }
        if (ind.raiz.getIzquierda() != null) {
            DefaultMutableTreeNode child2 = new DefaultMutableTreeNode(ind.raiz.getIzquierda().getDato());
            d.add(child2);
            mostrarArboles(child2, ind.getSubArbolIzq());
        }

    }

}
