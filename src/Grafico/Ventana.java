/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafico;

import covid.tracker.Arista;
import covid.tracker.Grafo;
import covid.tracker.CovidTracker;
import covid.tracker.Nodo;
import covid.tracker.Vertice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author JPPM
 */
public class Ventana extends javax.swing.JFrame {

    // CovidTracker cvt;
    Pdf generar;
    int p;
    int x;
    int y;
    int reguladorIteracion = 0;
    Punto[] puntos;
    int contadorParaAgregarColumna;
    boolean grafoU = false;
    private Grafo cvt;

    public Ventana() {

        initComponents();
        puntos = new Punto[70];
        p = 0;
        x = 40;
        y = this.grafoPanel.getHeight() / 6;
        cvt = new Grafo();
        this.getContentPane().setBackground(Color.BLACK);
        contadorParaAgregarColumna = 0;
        this.titlePanel.setBackground(Color.red);
        this.grafoPanel.setBackground(new Color(255, 255, 255));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        grupoBotones.add(opcion1);
        grupoBotones.add(opcion2);
        grupoBotones.add(opcion3);
    }

    public void seleccionado() {
        if (this.opcion1.isSelected()) {
            return;
        } else if (this.opcion2.isSelected()) {
            cvt.aplicarMascarilla();
        } else if (this.opcion3.isSelected()) {
            cvt.aplicarMascarillaAleatorio();
        }

    }

    public int configuracion() {
        if (this.opcion1.isSelected()) {
            this.opcion2.setEnabled(false);
            this.opcion3.setEnabled(false);
            return 1;
        } else if (this.opcion2.isSelected()) {
            this.opcion1.setEnabled(false);
            this.opcion3.setEnabled(false);
            return 2;
        } else if (this.opcion3.isSelected()) {
            this.opcion1.setEnabled(false);
            this.opcion2.setEnabled(false);
            return 3;
        }
        return -1;
    }

    public int numeroValido() {
        int n = Integer.parseInt(this.verticeField.getText());
        while (n <= 0) {
            this.verticeField.setText("");
            n = Integer.parseInt(JOptionPane.showInputDialog("DIGITE NUMERO DE VERTICES MAYOR A 0"));
        }
        return n;
    }

    public void dibujarVertices(int opcion) {
        Graphics g = this.grafoPanel.getGraphics();
        Vertice v = cvt.getPtr();
        int op = 1;
        int y2;
        if (opcion == 1) {
            while (v != null) {
                Punto punto = new Punto();
                switch (op) {
                    case 1:
                        y2 = this.y * 3;
                        if (v.isInfectado()) {
                            g.fillOval(x, y2, 60, 60);
                            g.setColor(Color.white);
                            g.drawString("usuario " + v.getId(), x, y2 + 30);
                            g.setColor(Color.black);
                        } else {
                            g.drawOval(x, y2, 60, 60);
                            g.drawString("usuario " + v.getId(), x + 5, y2 + 30);
                        }
                        punto.setCordenada(x + 30, y2 + 30);
                        puntos[p] = punto;
                        p++;
                        op++;
                        break;
                    case 2:
                        y2 = this.y * 2;
                        if (v.isInfectado()) {
                            g.fillOval(x, y2, 60, 60);
                            g.setColor(Color.white);
                            g.drawString("usuario " + v.getId(), x, y2 + 30);
                            g.setColor(Color.black);

                        } else {
                            g.drawOval(x, y2, 60, 60);
                            g.drawString("usuario " + v.getId(), x + 5, y2 + 30);
                        }
                        punto.setCordenada(x + 30, y2 + 30);
                        puntos[p] = punto;
                        p++;
                        op++;
                        break;
                    case 3:
                        y2 = this.y * 4;
                        if (v.isInfectado()) {
                            g.fillOval(x, y2, 60, 60);
                            g.setColor(Color.white);
                            g.drawString("usuario " + v.getId(), x, y2 + 30);
                            g.setColor(Color.black);

                        } else {
                            g.drawOval(x, y2, 60, 60);
                            g.drawString("usuario " + v.getId(), x + 5, y2 + 30);
                        }
                        punto.setCordenada(x + 30, y2 + 30);
                        puntos[p] = punto;
                        p++;
                        this.x = this.x + 150;
                        op = 1;
                        break;
                }

                v = (Vertice) v.getLink();
            }
            this.x = 40;
        } else {
            while (v != null) {
                switch (op) {
                    case 1:
                        y2 = this.y * 3;
                        if (v.isInfectado()) {
                            g.fillOval(x, y2, 60, 60);
                            g.setColor(Color.white);
                            g.drawString("usuario " + v.getId(), x, y2 + 30);
                            g.setColor(Color.black);
                        }
                        // g.drawString("usuario " + v.getId(), x + 5, y2 + 30);
                        op++;
                        break;
                    case 2:
                        y2 = this.y * 2;
                        if (v.isInfectado()) {
                            g.fillOval(x, y2, 60, 60);
                            g.setColor(Color.white);
                            g.drawString("usuario " + v.getId(), x, y2 + 30);
                            g.setColor(Color.black);

                        }
                        op++;
                        break;
                    case 3:
                        y2 = this.y * 4;
                        if (v.isInfectado()) {
                            g.fillOval(x, y2, 60, 60);
                            g.setColor(Color.white);
                            g.drawString("usuario " + v.getId(), x, y2 + 30);
                            g.setColor(Color.black);

                        }
                        this.x = this.x + 150;
                        op = 1;
                        break;
                }

                v = (Vertice) v.getLink();
            }
            this.x = 40;
        }
    }

    public void dibujarAristas() {
        Graphics g = this.grafoPanel.getGraphics();
        Vertice v = cvt.getPtr();
        while (v != null) {
            Arista a = v.getAristas();
            while (a != null) {
                Punto coordenada1 = puntos[a.getId()];
                Punto coordenada2 = puntos[v.getId()];
//                 switch(cuadrante(coordenada1,coordenada2)){
//                     case 1:
//                         coordenada1.setX(coordenada1.x+30);
//                         coordenada2.setY(coordenada2.y+30);
//                     break;
//                     case 2:
//                         coordenada1.setX(coordenada1.x-30);
//                         coordenada2.setY(coordenada2.y+30);
//                     break;
//                     case 3:
//                         coordenada1.setX(coordenada1.x-30);
//                          coordenada2.setY(coordenada2.y-30);
//                     break;
//                     case 4:
//                         coordenada1.setX(coordenada1.x+30);
//                         coordenada2.setY(coordenada2.y-30);
//                     break;
//                 }        
                g.drawLine(coordenada1.x, coordenada1.y, coordenada2.x, coordenada2.y);
                g.drawLine(coordenada2.x, coordenada2.y, coordenada2.x + 8, coordenada2.y + 6);
                g.drawLine(coordenada2.x, coordenada2.y, coordenada2.x - 8, coordenada2.y + 6);
                a = a.getLink();
            }
            v = (Vertice) v.getLink();
        }

    }

    public int cuadrante(Punto coordenada1, Punto coordenada2) {

        int n = -1;

        if (coordenada2.x > coordenada1.x && coordenada2.y < coordenada1.y) {
            n = 1;
        } else if (coordenada2.x < coordenada1.x && coordenada2.y < coordenada1.y) {
            n = 2;
        } else if (coordenada2.x < coordenada1.x && coordenada2.y > coordenada1.y) {
            n = 3;
        } else {
            n = 4;
        }

        return n;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBotones = new javax.swing.ButtonGroup();
        grafoPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        titlePanel = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        verticeField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        opcion2 = new javax.swing.JRadioButton();
        opcion1 = new javax.swing.JRadioButton();
        opcion3 = new javax.swing.JRadioButton();
        Iteracion = new javax.swing.JButton();
        iteraciones = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setSize(new java.awt.Dimension(600, 600));

        grafoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        javax.swing.GroupLayout grafoPanelLayout = new javax.swing.GroupLayout(grafoPanel);
        grafoPanel.setLayout(grafoPanelLayout);
        grafoPanelLayout.setHorizontalGroup(
            grafoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1091, Short.MAX_VALUE)
        );
        grafoPanelLayout.setVerticalGroup(
            grafoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        title.setFont(new java.awt.Font("Verdana", 3, 48)); // NOI18N
        title.setForeground(new java.awt.Color(240, 240, 240));
        title.setText("COVID TRACKER");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/warning.png"))); // NOI18N

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addGap(392, 392, 392)
                .addComponent(title)
                .addGap(84, 84, 84)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addGroup(titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(titlePanelLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(title))
                    .addGroup(titlePanelLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel4)))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 4));

        verticeField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        verticeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verticeFieldActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel1.setText("Vertices");

        opcion2.setBackground(new java.awt.Color(255, 255, 255));
        opcion2.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        opcion2.setText("Sin Mascarilla");
        opcion2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcion2ActionPerformed(evt);
            }
        });

        opcion1.setBackground(new java.awt.Color(255, 255, 255));
        opcion1.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        opcion1.setText("Mascarilla");
        opcion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcion1ActionPerformed(evt);
            }
        });

        opcion3.setBackground(new java.awt.Color(255, 255, 255));
        opcion3.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        opcion3.setText("Aleatorio");
        opcion3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcion3ActionPerformed(evt);
            }
        });

        Iteracion.setBackground(new java.awt.Color(255, 255, 255));
        Iteracion.setText("Iterar");
        Iteracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IteracionActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Reiniciar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Generar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Crear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Iteracion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(iteraciones)
                        .addGap(86, 86, 86))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(verticeField, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(opcion2)
                            .addComponent(opcion3)
                            .addComponent(opcion1)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(42, 42, 42))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(verticeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(92, 92, 92)
                .addComponent(opcion2)
                .addGap(68, 68, 68)
                .addComponent(opcion1)
                .addGap(55, 55, 55)
                .addComponent(opcion3)
                .addGap(77, 77, 77)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Iteracion)
                    .addComponent(iteraciones, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addGap(64, 64, 64))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(grafoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(grafoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(216, 216, 216)
                                .addComponent(jLabel2)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 13, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IteracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IteracionActionPerformed

        if (verticeField.getText().isEmpty() == false) {
            if (configuracion() != -1) {

                if (reguladorIteracion == 0) {
                    if (!grafoU) {
                        cvt.setVertices(numeroValido());

                    }
                    grafoU = false;
                    cvt.generarGrafo();
                    seleccionado();
                    this.dibujarVertices(1);
                    this.dibujarAristas();
                    reguladorIteracion++;
                } else {
                    cvt.infectar(cvt.getPtr());
                    this.dibujarVertices(2);
                    reguladorIteracion++;
                }
            } else {
                JOptionPane.showMessageDialog(null, "SELECCIONE MODALIDAD");
            }

        } else {
            JOptionPane.showMessageDialog(null, "DIGITE UNA CANTIDAD DE VERTICES");
        }

        String label = "" + reguladorIteracion;
        this.iteraciones.setText(label);
        cvt.listarVertices();
        cvt.mostrarInfectados();
        if (cvt.todosInfectados() && verticeField.getText().isEmpty() == false && configuracion() != -1) {
            JOptionPane.showMessageDialog(null, "YA TODOS LOS USUARIOS TIENEN COVID-19");
        }
    }//GEN-LAST:event_IteracionActionPerformed

    private void opcion3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcion3ActionPerformed
        // cvt.aplicarMascarillaAleatorio();
    }//GEN-LAST:event_opcion3ActionPerformed

    private void opcion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcion1ActionPerformed
        //cvt.aplicarMascarilla();
    }//GEN-LAST:event_opcion1ActionPerformed

    private void opcion2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcion2ActionPerformed

    }//GEN-LAST:event_opcion2ActionPerformed

    private void verticeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verticeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_verticeFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.iteraciones.setText("");
        this.reguladorIteracion = 0;
        this.verticeField.setText("");
        this.opcion1.setEnabled(true);
        this.opcion2.setEnabled(true);
        this.opcion3.setEnabled(true);
        this.grafoPanel.repaint();
        this.grupoBotones.clearSelection();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (reguladorIteracion != 0) {
            generar = new Pdf(cvt.getPtr());
            generar.generarMatriz();
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JFileChooser abrir = new JFileChooser();
        abrir.showOpenDialog(this);
        File f = abrir.getSelectedFile();
        grafoU = true;
        try {
            cvt.grafoUsuario(f);
            this.verticeField.setText(String.valueOf(999));
            //this.verticeField.setText(String.valueOf(ptr.cantidadDeVertices(ptr)));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Iteracion;
    private javax.swing.JPanel grafoPanel;
    private javax.swing.ButtonGroup grupoBotones;
    private javax.swing.JLabel iteraciones;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton opcion1;
    private javax.swing.JRadioButton opcion2;
    private javax.swing.JRadioButton opcion3;
    private javax.swing.JLabel title;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JTextField verticeField;
    // End of variables declaration//GEN-END:variables
}
