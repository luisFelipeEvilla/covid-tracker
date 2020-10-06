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
public class Arista extends Nodo {
    
    public Arista(int id) {
        super(id);
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
    
    
}
