/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid.tracker;

/**
 *
 * @author luisf
 */
public class CovidTracker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Vertice v = new Vertice();
        v.createVertice();
        v.createVertice();

        System.out.println("El nodo con id: " + v.getId() + " esta infectado?");
        System.out.println(v.isInfectado());

        Vertice v2 = (Vertice) v.getNodo(2);
        v2.setInfectado(true);
        System.out.println("El nodo con id: " + v2.getId() + " esta infectado?");
        System.out.println(v2.isInfectado());

        v2.addArista(1);
        v2.addArista(0);
        System.out.println("El nodo con id: "  + v2.getId() + " está conectado con los nodos con id: ");
        v2.listAristas();
    }

}
