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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import sun.java2d.pipe.BufferedOpCodes;
import utils.PosiblesContagios;

/**
 *
 * @author JPPM
 */
public class Ventana extends javax.swing.JFrame {

    // CovidTracker cvt;
    Emergencia3 e3;
    Emergencia1 e;
    Emergencia2 e2;
    int p;
    int posicionX;
    int posicionY;
    private final int DISTANCIA_NODOS_Y;
    private final int DISTANCIA_NODOS_X;
    private final int ANCHO_NODO = 60;
    private final int ALTO_NODO = 60;
    int reguladorIteracion = 0;
    private Grafo cvt;
    private Punto posiciones;
    Color xx = new Color(255, 89, 94);
    Color xx2 = new Color(138, 201, 38);
    MainMenu m;
    PosiblesContagios posiblesContagios;
    boolean posicionesCalculadas;

    public Ventana() {
        initComponents();
        posiciones = null;
        p = 0;
        this.DISTANCIA_NODOS_X = 150;
        this.DISTANCIA_NODOS_Y = this.grafoPanel.getHeight() / 3;

        // posicion desde donde se comienza a dibujar los vertices;
        posicionX = 40;
        posicionY = this.grafoPanel.getHeight() / 6;

        cvt = new Grafo();

        this.getContentPane().setBackground(new Color(106, 76, 147));

        // configuracion del jpanel 
        // this.titlePanel.setBackground(Color.red);
        this.grafoPanel.setBackground(new Color(255, 255, 255));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        //cofiguracion de botones
        grupoBotones.add(opcion2);
        grupoBotones.add(opcion1);
        grupoBotones.add(opcion3);

        this.setLocationRelativeTo(null);
        posiblesContagios = null;
        posicionesCalculadas = false;
    }

    // aplica la configuracion de aplicacion de mascarilla
    public void seleccionado() {
        if (this.opcion1.isSelected()) {
            return;
        } else if (this.opcion2.isSelected()) {
            cvt.aplicarMascarilla();
        } else if (this.opcion3.isSelected()) {
            cvt.aplicarMascarillaAleatorio();
        }

    }

    // lee la modalidad de aplicacion de mascarilla seleccionada, y desactiva las otras
    public int configuracion() {
        if (this.opcion2.isSelected()) {
            this.opcion1.setEnabled(false);
            this.opcion3.setEnabled(false);
            return 1;
        } else if (this.opcion1.isSelected()) {
            this.opcion2.setEnabled(false);
            this.opcion3.setEnabled(false);
            return 2;
        } else if (this.opcion3.isSelected()) {
            this.opcion2.setEnabled(false);
            this.opcion1.setEnabled(false);
            return 3;
        }
        return -1;
    }

    //lee el num de vertices
    public int numeroValido() {
        int n = Integer.parseInt(this.verticeField.getText());
        while (n <= 0) {
            this.verticeField.setText("");
            n = Integer.parseInt(JOptionPane.showInputDialog("DIGITE NUMERO DE VERTICES MAYOR A 0"));
        }
        return n;
    }

    // Dibuja los vertices
    public void dibujarVertices() {
        Graphics g = this.grafoPanel.getGraphics();
        Vertice v = cvt.getPtr();
        posicionX = 40;
        int contador = 0;

        while (v != null) {

            switch (contador) {
                case 0:
                    posicionY = this.grafoPanel.getHeight() / 2 - 40;
                    break;
                case 1:
                    posicionY -= DISTANCIA_NODOS_Y;
                    break;
                case 2:
                    posicionY += DISTANCIA_NODOS_Y * 2;
                    break;

            }
            if (!posicionesCalculadas) {
                if (posiciones == null) {
                    posiciones = new Punto(v.getId(), posicionX, posicionY);
                } else {
                    Punto p = new Punto(v.getId(), posicionX, posicionY);
                    posiciones.addNode(p);
                }
            }

            if (v.isInfectado()) {
                g.setColor(Color.black);
                g.drawOval(posicionX, posicionY, ANCHO_NODO, ALTO_NODO);
                g.setColor(xx);
                g.fillOval(posicionX, posicionY, ANCHO_NODO, ALTO_NODO);
                g.setColor(Color.white);
                g.drawString("usuario " + v.getId(), posicionX, posicionY + 30);
            } else {
                g.setColor(Color.black);
                g.drawOval(posicionX, posicionY, ANCHO_NODO, ALTO_NODO);
                g.setColor(xx2);
                g.fillOval(posicionX, posicionY, ANCHO_NODO, ALTO_NODO);
                g.setColor(Color.BLACK);
                g.drawString("usuario " + v.getId(), posicionX, posicionY + 30);
            }

            contador++;

            if (contador > 2) {
                posicionX += DISTANCIA_NODOS_X;
                contador = 0;
            }

            v = (Vertice) v.getLink();
        }
        
        posicionesCalculadas = true;
    }

    public void dibujarAristas() {
        Graphics g = this.grafoPanel.getGraphics();
        Vertice v = cvt.getPtr();
        Vertice v2 = null;
        int x1;
        int y1;
        int x2;
        int y2;

        while (v != null) {
            Arista a = v.getAristas();

            while (a != null) {
                // de donde sale la arista
                Punto coordenada1 = (Punto) posiciones.getNodo(v.getId());
                x1 = coordenada1.getX();
                y1 = coordenada1.getY();

                // a donde entra
                Punto coordenada2 = (Punto) posiciones.getNodo(a.getId());
                x2 = coordenada2.getX();
                y2 = coordenada2.getY();
                v2 = (Vertice) cvt.getPtr().getNodo(a.getId());

                g.setColor(Color.BLACK);

                // nodo saliente se encuentra encima del entrante
                if (y1 < y2) {
                    y1 += ALTO_NODO + 15;
                    // justo encima
                    if (x1 == x2) {
                        x1 += ANCHO_NODO / 2;
                        x2 += ANCHO_NODO / 2;
                        y2 -= 10;
                        // cuerpo de la flecha
                        g.drawLine(x1, y1, x2, y2);

                        // pico de la flecha
                        if (v2.isMascarilla()) {
                            g.setColor(Color.green);
                        } else {
                            g.setColor(xx);
                        }
                        g.drawLine(x2 - 8, y2 - 8, x2, y2);
                        g.drawLine(x2 + 8, y2 - 8, x2, y2);
                    } else {
                        // encima a la izquierda
                        if (x1 < x2) {
                            x1 += ANCHO_NODO;

                            // cuerpo de la flecha
                            g.drawLine(x1, y1, x2, y2);

                            // pico de la flecha
                            if (v2.isMascarilla()) {
                                g.setColor(Color.green);
                            } else {
                                g.setColor(xx);
                            }
                            g.drawLine(x2, y2, x2 - 12, y2 - 8);
                            g.drawLine(x2, y2, x2 + 4, y2 - 8);

                            // encima a la derecha   
                        } else {
                            x2 += ANCHO_NODO;
                            y2 -= 10;
                            y1 -= 10;
                            // cuerpo de la flecha
                            g.drawLine(x1, y1, x2, y2);

                            // pico de la flecha
                            if (v2.isMascarilla()) {
                                g.setColor(Color.green);
                            } else {
                                g.setColor(xx);
                            }
                            g.drawLine(x2 - 4, y2 - 8, x2, y2);
                            g.drawLine(x2 + 12, y2 - 8, x2, y2);
                        }
                    }
                } else {
                    // a la misma altura
                    if (y1 == y2) {
                        y1 += ALTO_NODO / 2;
                        y2 += ALTO_NODO / 2;

                        // a la izquierda
                        if (x1 < x2) {
                            x1 += ANCHO_NODO + 10;
                            x2 -= 10;
                            // cuerpo de la flecha
                            g.drawLine(x1, y1, x2, y2);

                            // pico de la flecha
                            if (v2.isMascarilla()) {
                                g.setColor(Color.green);
                            } else {
                                g.setColor(xx);
                            }
                            g.drawLine(x2 - 8, y2 - 8, x2, y2);
                            g.drawLine(x2 - 8, y2 + 8, x2, y2);

                            // a la derecha
                        } else {
                            x1 -= 10;
                            x2 += ANCHO_NODO + 10;

                            // cuerpo de la flecha
                            g.drawLine(x1, y1, x2, y2);

                            // pico de la flecha
                            if (v2.isMascarilla()) {
                                g.setColor(Color.green);
                            } else {
                                g.setColor(xx);
                            }
                            g.drawLine(x2 + 8, y2 - 8, x2, y2);
                            g.drawLine(x2 + 8, y2 + 8, x2, y2);
                        }

                        // por debajo
                    } else {
                        y1 -= 10;

                        //  justo debajo
                        if (x1 == x2) {
                            x1 += ANCHO_NODO / 2;
                            x2 += ANCHO_NODO / 2;
                            y2 += ALTO_NODO + 10;

                            // cuerpo de la flecha
                            g.drawLine(x1, y1, x2, y2);

                            // pico de la flecha
                            if (v2.isMascarilla()) {
                                g.setColor(Color.green);
                            } else {
                                g.setColor(xx);
                            }
                            g.drawLine(x2 - 8, y2 + 8, x2, y2);
                            g.drawLine(x2 + 8, y2 + 8, x2, y2);

                            // debajo a la izquierda
                        } else {
                            if (x1 < x2) {
                                x1 += ANCHO_NODO;
                                x2 -= 10;
                                y2 += ALTO_NODO;
                                // cuerpo de la flecha
                                g.drawLine(x1, y1, x2, y2);

                                // pico de la flecha
                                if (v2.isMascarilla()) {
                                    g.setColor(Color.green);
                                } else {
                                    g.setColor(xx);
                                }
                                g.drawLine(x2 - 8, y2 + 8, x2, y2);
                                g.drawLine(x2 + 8, y2 + 8, x2, y2);

                                // debajo a la derecha
                            } else {
                                x2 += ANCHO_NODO + 10;
                                x1 += 10;
                                y2 += ALTO_NODO + 10;

                                // cuerpo de la flecha
                                g.drawLine(x1, y1, x2, y2);

                                // pico de la flecha
                                if (v2.isMascarilla()) {
                                    g.setColor(Color.green);
                                } else {
                                    g.setColor(xx);
                                }
                                g.drawLine(x2 - 8, y2 + 8, x2, y2);
                                g.drawLine(x2 + 8, y2 + 8, x2, y2);
                            }
                        }
                    }
                }

                // punta de la flecha
                a = a.getLink();
            }

            v = (Vertice) v.getLink();
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

        grupoBotones = new javax.swing.ButtonGroup();
        grafoPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        verticeField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        opcion1 = new javax.swing.JRadioButton();
        opcion2 = new javax.swing.JRadioButton();
        opcion3 = new javax.swing.JRadioButton();
        Iteracion = new javax.swing.JButton();
        iteraciones = new javax.swing.JLabel();
        reiniciar = new javax.swing.JButton();
        crear = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        panelBack = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setUndecorated(true);
        setSize(new java.awt.Dimension(600, 600));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        grafoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        javax.swing.GroupLayout grafoPanelLayout = new javax.swing.GroupLayout(grafoPanel);
        grafoPanel.setLayout(grafoPanelLayout);
        grafoPanelLayout.setHorizontalGroup(
            grafoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 938, Short.MAX_VALUE)
        );
        grafoPanelLayout.setVerticalGroup(
            grafoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 578, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(106, 76, 147));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));

        verticeField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        verticeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verticeFieldActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel1.setText("Vertices");

        opcion1.setBackground(new java.awt.Color(135, 105, 177));
        opcion1.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        opcion1.setText("Sin Mascarilla");
        opcion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcion1ActionPerformed(evt);
            }
        });

        opcion2.setBackground(new java.awt.Color(135, 105, 177));
        opcion2.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        opcion2.setText("Mascarilla");
        opcion2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcion2ActionPerformed(evt);
            }
        });

        opcion3.setBackground(new java.awt.Color(135, 105, 177));
        opcion3.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        opcion3.setText("Aleatorio");
        opcion3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcion3ActionPerformed(evt);
            }
        });

        Iteracion.setBackground(new java.awt.Color(25, 130, 196));
        Iteracion.setText("Iterar");
        Iteracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IteracionActionPerformed(evt);
            }
        });

        iteraciones.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        iteraciones.setForeground(new java.awt.Color(255, 255, 255));

        reiniciar.setBackground(new java.awt.Color(255, 202, 58));
        reiniciar.setText("Reiniciar");
        reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reiniciarActionPerformed(evt);
            }
        });

        crear.setBackground(new java.awt.Color(255, 89, 94));
        crear.setText("Crear");
        crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/warning.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(verticeField, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(opcion3)
                    .addComponent(opcion2)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(iteraciones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(opcion1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(crear, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Iteracion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(reiniciar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(verticeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(36, 36, 36)
                .addComponent(opcion1)
                .addGap(18, 18, 18)
                .addComponent(opcion2)
                .addGap(18, 18, 18)
                .addComponent(opcion3)
                .addGap(27, 27, 27)
                .addComponent(crear)
                .addGap(18, 18, 18)
                .addComponent(Iteracion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(reiniciar)
                .addGap(18, 18, 18)
                .addComponent(iteraciones, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        panelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBackMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBackMouseEntered(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/back-arrow.png"))); // NOI18N

        javax.swing.GroupLayout panelBackLayout = new javax.swing.GroupLayout(panelBack);
        panelBack.setLayout(panelBackLayout);
        panelBackLayout.setHorizontalGroup(
            panelBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBackLayout.setVerticalGroup(
            panelBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBackLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(grafoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jLabel2))
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(413, 413, 413)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(grafoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void crearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearActionPerformed
        if (verticeField.getText().isEmpty() == false) {
            if (configuracion() != -1) {
                cvt.setVertices(numeroValido());
                cvt.generarGrafo();
                if (posiblesContagios != null) {
                    grafoPanel.removeMouseListener(posiblesContagios);
                }

                posiblesContagios = new PosiblesContagios(cvt.getPtr(), posiciones);
                seleccionado();
                this.dibujarVertices();
                this.dibujarAristas();
                crear.setEnabled(false);

                grafoPanel.addMouseListener(new PosiblesContagios(cvt.getPtr(), posiciones));
            } else {

                //JOptionPane.showMessageDialog(null, "SELECCIONE MODALIDAD");
                e2 = new Emergencia2();
                e2.setVisible(true);
            }
        } else {
            // JOptionPane.showMessageDialog(null, "DIGITE UNA CANTIDAD DE VERTICES");
            e = new Emergencia1();
            e.setVisible(true);
        }
    }//GEN-LAST:event_crearActionPerformed

    private void reiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reiniciarActionPerformed
        this.iteraciones.setText("");
        this.reguladorIteracion = 0;
        this.verticeField.setText("");
        this.opcion2.setEnabled(true);
        this.opcion1.setEnabled(true);
        this.opcion3.setEnabled(true);
        this.grafoPanel.repaint();
        this.grupoBotones.clearSelection();
        crear.setEnabled(true);
        cvt.setPaciente0(false);
        Vertice.setIdGen(0);
        posicionesCalculadas = false;
    }//GEN-LAST:event_reiniciarActionPerformed

    private void IteracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IteracionActionPerformed
        cvt.infectar(cvt.getPtr().getInfectados());
        dibujarVertices();
        String label = "Iteracion: " + ++reguladorIteracion;
        this.iteraciones.setText(label);
        if (cvt.todosInfectados() && verticeField.getText().isEmpty() == false) {
            //JOptionPane.showMessageDialog(null, "YA TODOS LOS USUARIOS TIENEN COVID-19");
            e3 = new Emergencia3();
            e3.setVisible(true);

        }
    }//GEN-LAST:event_IteracionActionPerformed

    private void opcion3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcion3ActionPerformed
        // cvt.aplicarMascarillaAleatorio();
    }//GEN-LAST:event_opcion3ActionPerformed

    private void opcion2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcion2ActionPerformed
        //cvt.aplicarMascarilla();
    }//GEN-LAST:event_opcion2ActionPerformed

    private void opcion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcion1ActionPerformed

    }//GEN-LAST:event_opcion1ActionPerformed

    private void verticeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verticeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_verticeFieldActionPerformed

    private void panelBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBackMouseEntered
        this.panelBack.setBackground(new Color(255, 202, 58));
    }//GEN-LAST:event_panelBackMouseEntered

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        this.panelBack.setBackground(new Color(106, 76, 147));
    }//GEN-LAST:event_formMouseEntered

    private void panelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBackMouseClicked
        m = new MainMenu();
        this.setVisible(false);
        m.setVisible(true);
    }//GEN-LAST:event_panelBackMouseClicked

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
    private javax.swing.JButton crear;
    private javax.swing.JPanel grafoPanel;
    private javax.swing.ButtonGroup grupoBotones;
    private javax.swing.JLabel iteraciones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton opcion1;
    private javax.swing.JRadioButton opcion2;
    private javax.swing.JRadioButton opcion3;
    private javax.swing.JPanel panelBack;
    private javax.swing.JButton reiniciar;
    private javax.swing.JTextField verticeField;
    // End of variables declaration//GEN-END:variables
}
