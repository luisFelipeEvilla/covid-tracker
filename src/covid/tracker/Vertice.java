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
public class Vertice extends Nodo{
    
    private static int idGen = 0;
    private boolean infectado;
    private Arista aristas;
    
    public Vertice() {
        super(idGen++);
        infectado = false;
    }
    
    public void addArista(int id) {
        if (aristas == null) {
            aristas = new Arista(id);
        } else {
            aristas.addNode(new Arista(id));
        }
    }
    
    public void listAristas() {
        Arista p = aristas;
        StringBuffer info = new StringBuffer();
        
        while (p != null) {
            info.append(p.getId() + ", ");
            p = p.getLink();
        }
        
        System.out.println(info);
    }

    public Arista getAristas() {
        return aristas;
    }
    
    public void createVertice() {
        this.addNode(new Vertice());
    }
    
    public Arista getArista(int id) {
        return (Arista) aristas.getNodo(id);
    }

    public static int getIdGen() {
        return idGen;
    }

    public static void setIdGen(int idGen) {
        Vertice.idGen = idGen;
    }

    public boolean isInfectado() {
        return infectado;
    }

    public void setInfectado(boolean infectado) {
        this.infectado = infectado;
    }

}
