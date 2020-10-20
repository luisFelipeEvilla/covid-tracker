package covid.tracker;

import covid.tracker.Arista;
import covid.tracker.Vertice;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author JPPM
 */
public class Grafo {

    private Vertice vertices;
    //  private Vertice verticeP;
    private boolean paciente0;
    private int numVertices = 5;
    private int configuracion;
    private int iteracion;
    private float PROBABILIDAD_PACIENTE_CERO;
    private final float PROBABILIDAD_MASCARILLA;
    private final float PROBABILIDAD_ARISTA;
    private final float PROBABILIDAD_INFECTADO;
    private final float PROTECCCION_MASCARILLA;

    public Grafo() {
        vertices = null;
        paciente0 = false;
        this.numVertices = numVertices;
        configuracion = 0;
        iteracion = 0;
        PROBABILIDAD_MASCARILLA = 1 / 2;
        PROBABILIDAD_ARISTA = 0.69f;
        PROBABILIDAD_INFECTADO = 1 / 2;
        PROTECCCION_MASCARILLA = (float) 0.3;
    }

    public void generarGrafo() {
        Random rand = new Random();
        PROBABILIDAD_PACIENTE_CERO = 0.4f;

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

    public void aplicarMascarilla() {
        Vertice v1 = vertices;

        while (v1 != null) {
            v1.setMascarilla(true);
            v1 = (Vertice) v1.getLink();
        }
    }

    /**
     *
     * @param infectados Lista de vertices que ya estaban infectados antes de
     * esta iteracion (Los que pueden infectar a otros en esta iteracion)
     */
    /**
     * Crea los nodos del grafo con sus aristas aleatorias, y el paciente0 de
     * manera aleatoria
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
                    dado = dado / 10;

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

    public boolean todosInfectados() {
        Vertice v = vertices;
        while (v != null) {
            if (!v.isInfectado()) {
                return false;

            }
            v = (Vertice) v.link;
        }
        return true;
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

    public Vertice getPtr() {
        return vertices;
    }

    public void setVertices(int numVertices) {
        this.numVertices = numVertices;
    }
    //Generar el grafo que el ususario inserta via txt

    public void grafoUsuario(File f) throws FileNotFoundException {
        boolean primero = true;
        Scanner lectura = new Scanner(f);
        PrintWriter pw = new PrintWriter(f);

        int i = 0;
        Vertice.setIdGen(0);
        while (lectura.hasNext()) {
            String linea = lectura.nextLine();
            linea = linea.trim();
            char array[] = linea.toCharArray();
            if (primero) {
                vertices = new Vertice();
                //vertices.setId(array[i]);
                //i++;
                primero = false;
            } else {
                vertices.createVertice();
                //vertices.setId(i);  
            }
            for (int j = 2; j < array.length; j++) {

                if (array[j] != ',') {
                    vertices.addArista(array[j]);
                }
            }

        }
    }
}
