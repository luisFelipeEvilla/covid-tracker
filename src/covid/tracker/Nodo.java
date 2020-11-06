/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid.tracker;

/**
 * Nodo de una lista enlazada simple
 * @author luisf
 */
public abstract class Nodo {

    // enlace a los otros nodo del grafo
    protected Nodo link;
    protected int id;

    public Nodo(int id) {
        this.id = id;
    }

    /**
     * Crea un nuevo nodo, apartir de uno ya existente
     * @param node Nodo original
     */
    public void addNode(Nodo node) {
        Nodo p = this;
        
        while (p.getLink() != null) {
            p = p.getLink();
        }

        if (p != null) {
            p.setLink(node);
        }
    }
    
    /**
     * Devuelve la cantidad de vertices que posee una lista.
     * @param ptr raiz de la lista
     * @return  número de nodos de la lista
     */
    public int cantidadDeVertices(Nodo ptr){
        int cont=0;
        Nodo temp = ptr;
        while(temp!=null){
            temp=temp.link;
            cont++;
        }
        return cont;
    }
    
    /**
     * Remueve un nodo de la lista
     * @param ptr raiz de la lista de adyacencia
     * @param id id del nodo a eliminar
     * @return retorna la nueva lista modificada
     */
    public Nodo removeNode(Nodo ptr, int id) {
        Nodo p = ptr;
        Nodo q = null;

        while (p != null && p.getId() != id) {
            q = p;
            p = p.getLink();
        }

        if (p != null) {
            if (p.equals(ptr)) {
                ptr = p.getLink();
            } else {
                q.setLink(p.getLink());
            }
        }

        return ptr;
    }
    
    /**
     * Recorre todos los nodos de la lista
     * @param ptr raiz de la lista
     */
    public void recorrerVertices(Nodo ptr){
        Nodo temp = ptr;
        while(temp!=null){
            temp=temp.link;
        }
    }
    
    /**
     * Busca un nodo especifico dentro de la lista
     * @param id id del nodo a buscar
     * @return instancia del nodo seleccionado o nulo, en caso de no encontrarlo
     */
    public Nodo getNodo(int id) {
        Nodo p = this;
        
        while (p != null && p.getId() != id) {
            p = p.getLink();
        }
        
        return p;
    }
    
    public Nodo getLink() {
        return link;
    }

    public void setLink(Nodo link) {
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
