/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid.tracker;

import Grafico.Ventana;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author luisf
 */
public class CovidTracker {
    int x;
    private Vertice vertices;
    private boolean paciente0;
    private int numVertices;
    private int configuracion;
    private int iteracion;
    private float PROBABILIDAD_PACIENTE_CERO;
    private final float PROBABILIDAD_MASCARILLA;
    private final float PROBABILIDAD_ARISTA;
    private final float PROBABILIDAD_INFECTADO;
    private final float PROTECCCION_MASCARILLA;
    
    
    public Vertice getPtr(){
        return vertices;
    }

    public CovidTracker() {
        vertices = null;
        paciente0 = false;
        numVertices = 0;
        configuracion = 0;
        iteracion = 0;
        PROBABILIDAD_MASCARILLA = 1 / 2;
        PROBABILIDAD_ARISTA = 1 / 4;
        PROBABILIDAD_INFECTADO = 1/2;
        PROTECCCION_MASCARILLA= (float) 0.3;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CovidTracker app = new CovidTracker();
        Scanner scan = new Scanner(System.in);
        int opcion = 0;

        app.menuInicial(); 
        app.generarGrafo();
        
        switch (app.configuracion) {
            case 1:
                break;
            case 2:
                app.aplicarMascarilla();
                break;
            case 3:
                app.aplicarMascarillaAleatorio();
        }

        app.listarVertices();

        do {
            System.out.println("¿Desea generar una nueva iteracion?");
            System.out.println("1. Si");
            System.out.println("2. No");
            opcion = scan.nextInt();

            if (opcion == 1) {
                Vertice infectados = app.vertices.getInfectados();
                app.infectar(infectados);
            }

            app.mostrarInfectados();
        } while (opcion == 1 && opcion != 2);
    }

    /**
     * Crea los nodos del grafo con sus aristas aleatorias, y el paciente0 de
     * manera aleatoria
     */
    public void generarGrafo() {
        Random rand = new Random();
        PROBABILIDAD_PACIENTE_CERO = 1 - 1 / numVertices;

        // crear vertice inicial
        if (numVertices > 0) {
            vertices = new Vertice();
            
        }

        for (int i = 1; i < numVertices; i++) {
            vertices.createVertice();
        }

        Vertice v1 = vertices;
        Vertice v2 = null;
        Vertice v3 = null;
        float dado = 0;

        while (v1 != null) {
            v2 = vertices;

            // genera las aristas aleatoriamente
            while (v2 != null) {
                if (v2.equals(v1)) {
                    v2 = (Vertice) v2.getLink();
                } else {
                    dado = Math.round(rand.nextFloat() * 10);
                    dado = dado / 10;

                    if (dado >= PROBABILIDAD_ARISTA) {
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

            //generacion del paciente 0
            if (!paciente0) {
                dado = Math.round(rand.nextFloat() * 10);
                dado = dado / 10;
                if (dado > PROBABILIDAD_PACIENTE_CERO) {
                    v1.setInfectado(true);
                    paciente0 = true;
                }
            }

            v3 = v1;
            v1 = (Vertice) v1.getLink();
        }

        if (!paciente0) {
            vertices.setInfectado(true);
            paciente0 = true;
        }
       
    }

    public void aplicarMascarilla() {
        Vertice v1 = vertices;

        while (v1 != null) {
            v1.setMascarilla(true);
            v1 = (Vertice) v1.getLink();
        }
    }

    public void aplicarMascarillaAleatorio() {
        Vertice v1 = vertices;
        Random rand = new Random();
        float dado = 0;

        while (v1 != null) {
            dado = rand.nextFloat();
            dado = Math.round(dado * 10);

            if (dado > 5) {
                v1.setMascarilla(true);
            }
            v1 = (Vertice) v1.getLink();
        }

    }

    /**
     *
     * @param infectados Lista de vertices que ya estaban infectados antes de
     * esta iteracion (Los que pueden infectar a otros en esta iteracion)
     */
    public void infectar(Vertice infectados) {
        // Nodo que infecta     
        Vertice v = infectados;
         //nodo que se va a infectar
        Vertice v1 = v;
        Arista a = null;
        Random rand = new Random();
        float dado = 0;

        while (v != null) {
            a = v.getAristas();
            
            while (a != null) {
                v1 = (Vertice) vertices.getNodo(a.getId());
                
                if (!v1.isInfectado()) {
                    dado = Math.round(rand.nextFloat() * 10);
                    dado = dado/10;
                    
                    if (dado > PROBABILIDAD_INFECTADO + PROTECCCION_MASCARILLA) {
                        v1.setInfectado(true);
                    }
                }
                
                a = a.getLink();
            }

            v = (Vertice) v.getLink();
        }

       
        iteracion++;
    }

    public void menuInicial() {
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("¿Cuantos nodos desea para la simulación?");
            numVertices = scan.nextInt();
        } while (numVertices <= 0);

        do {
            System.out.println("¿Como desea realizar la simulación?");
            System.out.println("1. Todos los nodos sin mascarrilla");
            System.out.println("2. Todos los nodos con mascarilla");
            System.out.println("3. Asignación aleatoria de mascarilla");
            configuracion = scan.nextInt();

        } while (configuracion < 1 && configuracion > 3);
    }

    public void listarVertices() {
        Vertice v = vertices;

        while (v != null) {
            System.out.println("El vertice con id: " + v.getId() + " ¿esta infectado?");
            System.out.println(v.isInfectado());
            System.out.println("¿LLeva mascarilla?");
            System.out.println(v.isMascarilla());
            System.out.println("El vertice con id: " + v.getId() + " tiene conexion con los vertices");
            v.listAristas();
            v = (Vertice) v.getLink();
        }
    }

    public void mostrarInfectados() {
        Vertice v = vertices;

        System.out.println("Los nodos que no han sido infectando son: ");
        while (v != null) {
            if (!v.isInfectado()) {
                System.out.print(v.getId() + ", ");
            }
            v = (Vertice) v.getLink();
        }
        System.out.println("");
    }
}
