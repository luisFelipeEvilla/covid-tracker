/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid.tracker;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author luisf
 */
public class CovidTracker {
    Vertice vertices = null;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CovidTracker app = new CovidTracker();
        app.generarGrafo();
    }

    public void generarGrafo() {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        int numVertices = 0;

        System.out.println("¿Cuantos vertices desea?");
        numVertices = scan.nextInt();
        

        if (numVertices > 0) {
            vertices = new Vertice();
        }

        for (int i = 1; i < numVertices; i++) {
            vertices.createVertice();
        }

        Vertice v1 = vertices;
        Vertice v2 = null;
        Vertice v3 = null;
        float probabilidad = 0;

        while (v1 != null) {
            v2 = vertices;

            // genera las aristas aleatoriamente
            while (v2 != null) {
                if (v2.equals(v1)) {
                    v2 = (Vertice) v2.getLink();
                } else {
                    probabilidad = rand.nextFloat();
                    if (probabilidad >= 0.5) {
                        v1.addArista(v2.getId());
                    }
                }

                if (v2 != null) {
                    v2 = (Vertice) v2.getLink();
                }
            }

            // si no posee ninguna arista, se le añade el nodo siguiente
            if (v1.getAristas() == null) {
                if (v1.getLink() != null) {
                    v1.addArista(v1.getLink().getId());
                } else {
                    // si no tiene siguiente, el anterior
                    v1.addArista(v3.getId());
                }
            }

            v3 = v1;
            v1 = (Vertice) v1.getLink();
        }

        v1 = vertices;

        v1 = vertices;
        while (v1 != null) {
            System.out.println("El vertice con id: " + v1.getId() + " tiene conexion con los vertices");
            v1.listAristas();
            v1 = (Vertice) v1.getLink();
        }

    }

}
