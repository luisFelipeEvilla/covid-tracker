/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid.tracker;

/**
 * Arista de un grafo dirigido
 * @author luisf
 */
public class Arista extends Nodo {
    
    private final int distancia;
    
    /**
     * Crea una nueva instancia de una arista de un grafo dirigido
     * @param id id del nodo destino de la arista
     * @param distancia distancia entre el nodo origen, y el nodo destino.
     */
    public Arista(int id, int distancia) {
        super(id);
        this.distancia = distancia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Arista getLink() {
        return (Arista) link;
    }

    public void setLink(Arista link) {
        this.link = link;
    }

    public int getDistancia() {
        return distancia;
    }
    
}
