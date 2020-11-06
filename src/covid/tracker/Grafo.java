package covid.tracker;

import covid.tracker.Arista;
import covid.tracker.Vertice;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

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

    public Grafo() {
        vertices = null;
        paciente0 = false;
        this.numVertices = numVertices;
        configuracion = 0;
        iteracion = 0;
        PROBABILIDAD_MASCARILLA = 1 / 2;
        PROBABILIDAD_ARISTA = 0.7f;

    }

    public void generarGrafo() {
        paciente0 = false;
        Vertice.setIdGen(0);
        Random rand = new Random();
        PROBABILIDAD_PACIENTE_CERO = 0.8f;
        int distancia = 0;

        // crear vertice inicial
        if (numVertices > 0) {
            vertices = new Vertice();

        }

        for (int i = 1; i < numVertices; i++) {
            vertices.createVertice();
        }

        Vertice actual = vertices;
        Vertice iterador = null;
        Vertice anterior = actual;
        float dado = 0;

        while (actual != null) {
            iterador = vertices;

            // genera las aristas aleatoriamente
            while (iterador != null) {
                if (iterador.equals(actual)) {
                    iterador = (Vertice) iterador.getLink();
                } else {
                    dado = Math.round(rand.nextFloat() * 10);
                    dado = dado / 10;

                    if (dado >= PROBABILIDAD_ARISTA) {
                        distancia = (int) (Math.random() * 5);
                        actual.addArista(iterador.getId(), distancia);
                    }
                }

                if (iterador != null) {
                    iterador = (Vertice) iterador.getLink();
                }
            }

            // si no posee ninguna arista saliente, se le añade el nodo siguiente
            if (actual.getAristas() == null) {
                distancia = (int) (Math.random() * 5);
                if (actual.getLink() != null) {
                    actual.addArista(actual.getLink().getId(), distancia);
                } else {
                    // si no tiene siguiente, el anterior
                    actual.addArista(anterior.getId(), distancia);
                }
            }

            //generacion del paciente 0
            if (!paciente0) {
                dado = Math.round(rand.nextFloat() * 10);
                dado = dado / 10;
                if (dado > PROBABILIDAD_PACIENTE_CERO) {
                    actual.setInfectado(true);
                    paciente0 = true;
                }
            }

            anterior = actual;
            actual = (Vertice) actual.getLink();
        }

        if (!paciente0) {
            vertices.setInfectado(true);
            paciente0 = true;
        }
        // conectarTodo();
    }
    
        public Vertice BFS(Vertice origen, Vertice raiz) {
        Vertice v;
        boolean visitados[] = new boolean[raiz.cantidadDeVertices(raiz)];
        Vertice path = new Vertice(origen);

        Queue<Vertice> cola = new LinkedList();
        visitados[origen.getId()] = true;
        cola.add(origen);

        while (!cola.isEmpty()) {
            v = cola.remove();
            path.addNode(new Vertice(v));
            v = (Vertice) raiz.getNodo(v.getId());

            for (int i = 0; i < raiz.cantidadDeVertices(raiz); i++) {
                if (v != null) {
                    if (v.getArista(i) != null) {
                        if (!visitados[i]) {
                            visitados[i] = true;
                            cola.add((Vertice) raiz.getNodo(i));
                        }
                    }
                }
            }
        }

        path = (Vertice) path.getLink();
        return path;
    }

    public Vertice dfs(Vertice inicio) {
        Stack<Vertice> s = new Stack();

        s.add(inicio);
        Vertice visitados = new Vertice(inicio);
        visitados.setLink(null);

        while (!s.isEmpty()) {
            Vertice v = s.pop();

            Arista a = v.getAristas();

            while (a != null) {
                if (visitados.getNodo(a.getId()) == null) {
                    Vertice w = (Vertice) vertices.getNodo(a.getId());
                    visitados.addNode(w);
                    s.add(w);
                }
                
                a = a.getLink();
                System.out.println("iteracion");
            }
            System.out.println("bucle principal");
        }
        
        System.out.println("finalizado");
        return visitados;
    }

    public void conectarTodo() {
        Vertice actual = vertices;
        Vertice anterior = actual;
        Vertice iterador;
        int distancia = 0;
        Random rand = new Random();

        // garantizar que no existe ningun nodo aislado
        while (actual != null) {
            iterador = vertices;
            boolean aislado = true;
            int id = rand.nextInt(numVertices + 1) + numVertices;
            // busca almenos un nodo que se pueda conectar con el nodo v1
            while (iterador != null && aislado) {
                if (iterador.getArista(actual.getId()) != null) {
                    aislado = false;
                }
                iterador = (Vertice) iterador.getLink();
            }

            // si se encuentra aislado, se conecta a algun nodo
            if (aislado) {
                distancia = (int) (Math.random() * 5);
                // se conecta con el nodo siguiente
                if (actual.getLink() != null) {
                    Vertice siguiente = (Vertice) actual.getLink();
                    siguiente.addArista(actual.getId(), distancia);
                } else {
                    // si no tiene siguiente, el anterior
                    anterior.addArista(actual.getId(), distancia);
                }
            }

            actual = (Vertice) actual.getLink();
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
        Vertice v1;
        Arista a = null;
        Random rand = new Random();
        float dado = 0;

        while (v != null) {
            a = v.getAristas();

            while (a != null) {
                v1 = (Vertice) vertices.getNodo(a.getId());

                if (!v1.isInfectado()) {
                    dado = rand.nextFloat();

                    if (!v.isMascarilla()) {

                        if (!v1.isMascarilla()) {

                            if (a.getDistancia() > 2) {
                                if (dado > 0.2) {
                                    v1.setInfectado(true);
                                }
                            } else {
                                if (dado > 0.1) {
                                    v1.setInfectado(true);
                                }
                            }

                        } else {
                            if (a.getDistancia() > 2) {
                                if (dado > 0.6) {
                                    v1.setInfectado(true);
                                }
                            } else {
                                if (dado > 0.4) {
                                    v1.setInfectado(true);
                                }
                            }
                        }
                    } else {
                        if (!v1.isMascarilla()) {

                            if (a.getDistancia() > 2) {
                                if (dado > 0.7) {
                                    v1.setInfectado(true);
                                }
                            } else {
                                if (dado > 0.6) {
                                    v1.setInfectado(true);
                                }
                            }
                        } else {

                            if (a.getDistancia() > 2) {
                                if (dado > 0.8) {
                                    v1.setInfectado(true);
                                }
                            } else {

                                if (dado > 0.7) {
                                    v1.setInfectado(true);
                                }
                            }

                        }
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

    public void setPaciente0(boolean paciente0) {
        this.paciente0 = paciente0;
    }

    /**
     * //Generar el grafo que el ususario inserta via txt
     *
     * public void grafoUsuario(File f) throws FileNotFoundException { boolean
     * primero = true; Scanner lectura = new Scanner(f); PrintWriter pw = new
     * PrintWriter(f);
     *
     * int i = 0; Vertice.setIdGen(0); while (lectura.hasNext()) { String linea
     * = lectura.nextLine(); linea = linea.trim(); char array[] =
     * linea.toCharArray(); if (primero) { vertices = new Vertice();
     * //vertices.setId(array[i]); //i++; primero = false; } else {
     * vertices.createVertice(); //vertices.setId(i); } for (int j = 2; j <
     * array.length; j++) {
     *
     * if (array[j] != ',') { vertices.addArista(array[j]); } }
     *
     * }
     * }
     * *
     */
}
