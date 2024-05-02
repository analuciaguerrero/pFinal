package com.example.demojavafx;


import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import com.example.demojavafx.usuario.Jugador;


/**
 * @author César Munuera Pérez y Sergio Salmerón González
 */
/**
 * Esta clase muestra un menú, donde nos podremos registrar o acceder para el juego
 */
public class Menu2EntradaController extends javax.swing.JFrame {

    /**
     * Creamos el HashMap con todos los usuarios que han jugado
     */
    public static HashMap<String, Jugador> jugadores = new HashMap<>();

    /**
     * Atributo de clase jugador
     */
    public static Jugador jugador;

    /**
     * @return Obtenemos el jugador deseado
     */
    public static Jugador getJugador() {
        return jugador;
    }

    /**
     * @return Jugadores registrados en el juego
     */
    public static HashMap<String, Jugador> getJugadores() {
        return jugadores;
    }

    //public String dni;
    FileOutputStream ranking;

    /**
     * Creates new form MenuEntradaController
     */
    public Menu2EntradaController() {
        loadRanking();
        initComponents();
        this.getContentPane().setBackground(Color.white);
    }

    /**
     * Método encargado de cargar el ranking del archivo .dat
     */
    public void loadRanking() {
        FileInputStream fileRanking; //En esta y la siguinete, declaramos el nombre de las variables
        ObjectInputStream streamRanking;
        try {
            fileRanking = new FileInputStream("ranking.dat"); //Obtenemos el fichero ranking y lo pasamos a fileranking
            streamRanking = new ObjectInputStream(fileRanking);//Pasamos el fileranking a objeto, para poder escribir en él
            jugadores = (HashMap) streamRanking.readObject(); //Pasamos ése objeto al HashMap jugadores
        } catch (FileNotFoundException ex) {//Si no se ha conseguido cargar con éxito
            Logger.getLogger(Menu2EntradaController.class.getName()).log(Level.SEVERE, null, ex);//Excepción por no haber encontrado el archivo
        } catch (IOException ex) {
            Logger.getLogger(Menu2EntradaController.class.getName()).log(Level.SEVERE, null, ex);//Excepción obligatoria
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu2EntradaController.class.getName()).log(Level.SEVERE, null, ex);//Excepción obligatoria
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelNombre = new javax.swing.JLabel();
        jLabelDNI = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldDNI = new javax.swing.JTextField();
        jButtonContinuar = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();
        jButtonVolver = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelNombre.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelNombre.setText("NOMBRE");

        jLabelDNI.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelDNI.setText("DNI");

        jButtonContinuar.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonContinuar.setText("¡A jugar!");
        jButtonContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonContinuarActionPerformed(evt);
            }
        });

        jButtonSalir.setText("Salir");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });

        jButtonVolver.setText("Volver");
        jButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Registrarse", "Acceder"}));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addComponent(jButtonVolver)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButtonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(134, 134, 134)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabelNombre)
                                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextFieldDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(19, 19, 19)
                                                                .addComponent(jLabelDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addComponent(jButtonContinuar)
                                .addContainerGap(106, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(jLabelDNI)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jButtonContinuar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonSalir)
                                        .addComponent(jButtonVolver))
                                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Boton que nos redirecciona a la ventana de elegir la dificultada, comprobando los parámetros de DNI y
     * nombre introducidos, con el método comprobarAcceso() y comprobarRegistro()
     */
    private void jButtonContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonContinuarActionPerformed
        // TODO add your handling code here:
        if (((String) jComboBox1.getSelectedItem()).equals("Acceder")) {
            comprobarAcceso();
        } else {
            comprobarRegistro();
        }
    }//GEN-LAST:event_jButtonContinuarActionPerformed

    /**
     * Botón que nos da la opción de salir del juego
     */
    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButtonSalirActionPerformed

    /**
     * Botón que nos da la posibilidad de retroceder a la ventana anterior
     */
    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
        // TODO add your handling code here:
        dispose();
        Menu1InicialController m = new Menu1InicialController();
        m.setVisible(true);
    }//GEN-LAST:event_jButtonVolverActionPerformed

    /**
     * Combo box que nos da la posibilidad de registrarnos o de acceder. Si elegimos la segunda, el campo de
     * NOMBRE desaparece, ya que no es necesario para acceder.
     */
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        String accion = (String) jComboBox1.getSelectedItem(); //Sacamos lo que el jugador ha elegido
        if (accion.equals("Acceder")) { //Si es acceder
            jLabelNombre.setVisible(false);//Inhabilitamos la etiqueta de nombre
            jTextFieldNombre.setVisible(false); //Y su campo de escritura
        } else {//Si es registrarse
            jLabelNombre.setVisible(true);//Habilitamos la etiqueta de nombre
            jTextFieldNombre.setVisible(true);//Y su campo de escritura
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * Método que nos comprueba si un usuario que intenta acceder, ya está registrado previamente.
     * Si no está registrado, no le deja acceder al juego
     */
    public void comprobarAcceso() {
        while (true) {
            if (!jugadores.containsKey(jTextFieldDNI.getText().toUpperCase())) { //Si no esá el DNI introducido en el HashMap de jugadores
                JOptionPane.showMessageDialog(this, "Este jugador no esta registrado", "ALERTA", JOptionPane.INFORMATION_MESSAGE); //Sacamos una alerta
                break;//Y no le dejamos acceder
            } else {//Si ya está registrado
                jugador = jugadores.get(jTextFieldDNI.getText().toUpperCase());//Obtenemos el jugador según su DNI
                Menu3PersonalizacionController m = new Menu3PersonalizacionController();
                m.setVisible(true);//Abrimos la siguiente ventana
                this.setVisible(false);//Cerramos la actual
                break;//Cortamos el bucle
            }
        }
    }

    /**
     * Método que comprueba si un jugador que se quiere registrar, ya estaba registrado previamente. Si lo está,
     * no se le permite registrarse otra vez
     */
    public void comprobarRegistro() {
        while (true) {
            if (jugadores.containsKey(jTextFieldDNI.getText().toUpperCase())) {//Si el HashMap ya tiene ese DNI
                JOptionPane.showMessageDialog(this, "Este jugador esta registrado", "ALERTA", JOptionPane.INFORMATION_MESSAGE);//Informamos de que ya está registrado
                break;//Y no le dejamos continuar
            } else {//Si no está registrado
                jugador = new Jugador(jTextFieldDNI.getText().toUpperCase());//Lo registramos metiendo su DNI
                jugador.setNombre(jTextFieldNombre.getText());//Su nombre
                jugadores.put(jTextFieldDNI.getText().toUpperCase(), jugador);//Y a la lista de jugadores
                Menu3PersonalizacionController m = new Menu3PersonalizacionController();
                m.setVisible(true);//Abrimos el menú 2
                this.setVisible(false);//Cerramos en el que estamos
                break;//Cortamos el bucle
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonContinuar;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JButton jButtonVolver;
    public static javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabelDNI;
    private javax.swing.JLabel jLabelNombre;
    public static javax.swing.JTextField jTextFieldDNI;
    public static javax.swing.JTextField jTextFieldNombre;

    public void setPreviousController(MenuInicialApplication menuInicialApplication) {
    }
// End of variables declaration//GEN-END:variables
}