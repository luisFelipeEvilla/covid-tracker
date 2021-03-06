/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafico;

import java.awt.Color;

/**
 *
 * @author JPPM
 */
public class MainMenu extends javax.swing.JFrame {

    Ventana v ;
    public MainMenu() {
        initComponents();
       // this.setUndecorated(true);
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        panelPrincipal = new javax.swing.JPanel();
        panelExit = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelBoton1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        panelBoton2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        panelBoton3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelPrincipal.setBackground(new java.awt.Color(106, 76, 147));
        panelPrincipal.setPreferredSize(new java.awt.Dimension(800, 500));
        panelPrincipal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelPrincipalMouseEntered(evt);
            }
        });

        panelExit.setBackground(new java.awt.Color(106, 76, 147));
        panelExit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelExitMouseEntered(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/borrar.png"))); // NOI18N

        javax.swing.GroupLayout panelExitLayout = new javax.swing.GroupLayout(panelExit);
        panelExit.setLayout(panelExitLayout);
        panelExitLayout.setHorizontalGroup(
            panelExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelExitLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelExitLayout.setVerticalGroup(
            panelExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelExitLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Baskerville Old Face", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 202, 58));
        jLabel2.setText("COVID TRACKER");

        panelBoton1.setBackground(new java.awt.Color(125, 92, 171));
        panelBoton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 202, 58), 2));
        panelBoton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBoton1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBoton1MouseEntered(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        jLabel3.setText("INICIAR");

        javax.swing.GroupLayout panelBoton1Layout = new javax.swing.GroupLayout(panelBoton1);
        panelBoton1.setLayout(panelBoton1Layout);
        panelBoton1Layout.setHorizontalGroup(
            panelBoton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoton1Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel3)
                .addContainerGap(87, Short.MAX_VALUE))
        );
        panelBoton1Layout.setVerticalGroup(
            panelBoton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBoton1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        panelBoton2.setBackground(new java.awt.Color(125, 92, 171));
        panelBoton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 202, 58), 2));
        panelBoton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBoton2MouseEntered(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        jLabel4.setText("INSTRUCCIONES");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel4MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout panelBoton2Layout = new javax.swing.GroupLayout(panelBoton2);
        panelBoton2.setLayout(panelBoton2Layout);
        panelBoton2Layout.setHorizontalGroup(
            panelBoton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBoton2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(20, 20, 20))
        );
        panelBoton2Layout.setVerticalGroup(
            panelBoton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoton2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
        );

        panelBoton3.setBackground(new java.awt.Color(125, 92, 171));
        panelBoton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 202, 58), 2));
        panelBoton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBoton3MouseEntered(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        jLabel5.setText("ESPECIFICACIONES");

        javax.swing.GroupLayout panelBoton3Layout = new javax.swing.GroupLayout(panelBoton3);
        panelBoton3.setLayout(panelBoton3Layout);
        panelBoton3Layout.setHorizontalGroup(
            panelBoton3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBoton3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap())
        );
        panelBoton3Layout.setVerticalGroup(
            panelBoton3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoton3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addContainerGap(277, Short.MAX_VALUE)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(252, 252, 252))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelBoton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(panelBoton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelBoton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(332, 332, 332))))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(panelExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jLabel2)
                .addGap(85, 85, 85)
                .addComponent(panelBoton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(panelBoton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(panelBoton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 144, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 962, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void panelExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelExitMouseClicked
        
        this.dispose();
        //v.dispose();
    }//GEN-LAST:event_panelExitMouseClicked

    private void panelExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelExitMouseEntered
        this.panelExit.setBackground(new Color(255, 202, 58));
        
    }//GEN-LAST:event_panelExitMouseEntered

    private void panelPrincipalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPrincipalMouseEntered
        this.panelExit.setBackground(new Color(	106, 76, 147));
        this.panelBoton1.setBackground(new Color(125, 92, 171));
        this.panelBoton2.setBackground(new Color(125, 92, 171));
        this.panelBoton3.setBackground(new Color(125, 92, 171));
        
    }//GEN-LAST:event_panelPrincipalMouseEntered

    private void panelBoton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBoton1MouseEntered
       this.panelBoton1.setBackground(new Color(255, 202, 58));
    }//GEN-LAST:event_panelBoton1MouseEntered

    private void panelBoton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBoton3MouseEntered
        this.panelBoton3.setBackground(new Color(255, 202, 58));
    }//GEN-LAST:event_panelBoton3MouseEntered

    private void jLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseEntered
      
    }//GEN-LAST:event_jLabel4MouseEntered

    private void panelBoton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBoton2MouseEntered
        this.panelBoton2.setBackground(new Color(255, 202, 58));
    }//GEN-LAST:event_panelBoton2MouseEntered

    private void panelBoton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBoton1MouseClicked
        v = new Ventana();
        this.setVisible(false);
        v.setVisible(true);
        
    }//GEN-LAST:event_panelBoton1MouseClicked

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
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel panelBoton1;
    private javax.swing.JPanel panelBoton2;
    private javax.swing.JPanel panelBoton3;
    private javax.swing.JPanel panelExit;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
