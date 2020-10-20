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
    private boolean mascarilla;
    private Arista aristas;
    
    public Vertice() {
        super(idGen++);
        infectado = false;
        mascarilla = false;
        aristas = null;
    }

    public Vertice(Vertice original) {
        super(original.getId());
        this.infectado = original.isInfectado();
        this.mascarilla = original.isMascarilla();
        this.aristas = original.getAristas();
    }

    public void addArista(int id, int distancia) {
        if (aristas == null) {
            aristas = new Arista(id, distancia);
        } else {
            aristas.addNode(new Arista(id, distancia));
        }
    }
    
    public Vertice getInfectados() {
        Vertice v = this;
        Vertice infectados = null;
        
        while (v != null) {
            if (v.isInfectado()) {
                if ( infectados == null) {
                    infectados = new Vertice(v);
                } else {
                    infectados.addNode(new Vertice(v));
                }
            }
            v = (Vertice) v.getLink();
        }
        
        return infectados;
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

    public boolean isMascarilla() {
        return mascarilla;
    }

    public void setMascarilla(boolean mascarilla) {
        this.mascarilla = mascarilla;
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
    
    public int contarAristas() {
        int contador = 0;
        
        Arista a = aristas;
        
        while (a != null) {
            contador++;
            a = a.getLink();
        }
        
        return contador;
    }

}
