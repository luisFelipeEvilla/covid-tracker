/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import Grafico.Info;
import Grafico.Punto;
import covid.tracker.Arista;
import covid.tracker.Vertice;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author luisf
 */
public class PosiblesContagios implements MouseListener {

    Vertice vertices;
    Punto posiciones;
    Info i;
    JLabel labelEditar1 = new JLabel();
    JLabel labelEditar2 = new JLabel();
    JLabel labelEditar3 = new JLabel();
    JLabel labelEditar4 = new JLabel();

    public PosiblesContagios(Vertice vertices, Punto posiciones) {
        this.vertices = vertices;
        this.posiciones = posiciones;
    }

    @Override
    public void mouseClicked(MouseEvent me) {

    }

    @Override
    public void mousePressed(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        i = new Info();
        Punto p = posiciones;

        while (p != null) {
            if (x < (p.getX() + 60) && (x > p.getX())) {
                if (y >= p.getY() && y < p.getY() + 60) {
                    Vertice v = (Vertice) vertices.getNodo(p.getId());
                    StringBuffer info = new StringBuffer();
                    //info.append("ID: " + v.getId() + "\n");
                    //info.append("¿Usa mascarrilla?: " + v.isMascarilla() + "\n");
                    //info.append("¿Está infectado?: " + v.isInfectado() + "\n");
                    //info.append("Tiene conexiones con los nodos: \n");
                    labelEditar1.setText(""+v.getId());
                    labelEditar2.setText(""+v.isMascarilla());
                    labelEditar3.setText(""+v.isInfectado());
                    
                    Arista a = v.getAristas();

                    while (a != null) {
                        info.append(a.getId() + ", ");

                        a = a.getLink();
                    }

                   // info.append("\n");
                    labelEditar4.setText(""+info.toString());
                    i.setEditarId(labelEditar1);
                    i.setEditarMascarilla(labelEditar2);
                    i.setEditarInfectado(labelEditar3);
                    i.setEditarConexiones(labelEditar4);
                    i.setVisible(true);
                    //JOptionPane.showMessageDialog(null, info);
                }

            }
            p = (Punto) p.getLink();
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }


    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {

    }

}
