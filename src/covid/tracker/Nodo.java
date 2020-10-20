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
public abstract class Nodo {

    // enlace a los otros nodo del grafo
    protected Nodo link;
    protected int id;

    public Nodo(int id) {
        this.id = id;
    }

    public void addNode(Nodo node) {
        Nodo p = this;
        
        while (p.getLink() != null) {
            p = p.getLink();
        }

        if (p != null) {
            p.setLink(node);
        }
    }
    
    public int cantidadDeVertices(Nodo ptr){
        int cont=0;
        Nodo temp = ptr;
        while(temp!=null){
            temp=temp.link;
            cont++;
        }
        return cont;
    }

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
    public void recorrerVertices(Nodo ptr){
        Nodo temp = ptr;
        while(temp!=null){
            temp=temp.link;
        }
    }
    
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
    
    public int getSize() {
        int contador = 0;
        
        Nodo v = this;
        
        while (v != null) {
            v = v.getLink();
            contador++;
        }
        
        return contador;
    }

}
