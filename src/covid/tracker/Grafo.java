package covid.tracker;

import covid.tracker.Arista;
import covid.tracker.Vertice;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
        PROBABILIDAD_ARISTA = 0.90f;

    }

    /**
     * Genera un grafo aleatoriamente
     */
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

    /**
     * Recorrido bfs del grafo
     *
     * @param origen Nodo por el cual se empezara a recorrer el grafo.
     * @param raiz Ptr de la lista de adyacencia del grafo.
     * @return lista del recorrido bfs
     */
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

    /**
     * Halla los caminos minimos desde un nodo origen, hacia todos los demás
     * @param origen nodo de origen
     * @param raiz lista de adyacencia
     * @return Devuelve los caminos minimos desde el nodo de origen, hacia todos los demás
     */
    public Vertice dijstrak(Vertice origen, Vertice raiz) {

        Vertice destinos = null;
        double pesos[] = new double[raiz.cantidadDeVertices(raiz)];
        int cont = 0;
        Vertice v = raiz;

        while (v != null) {
            if (destinos == null) {
                destinos = new Vertice(v);
            } else {
                destinos.addNode(new Vertice(v));
            }
            pesos[cont++] = 1;
            v = (Vertice) v.getLink();
        }

        v = destinos;

        while (v != null) {
            v.setAristas(null);
            v = (Vertice) v.getLink();
        }

        // Este vertice debe provenir de la raiz del grafo
        Vertice indice = new Vertice(origen);
        // Vertices cuyo camino minimo ya ha sido hayado
        Vertice noIterar = new Vertice(origen);

        for (int i = 0; i < raiz.cantidadDeVertices(raiz) - 1; i++) {
            Arista aristas = indice.getAristas();

            while (aristas != null) {
                Vertice destino = (Vertice) destinos.getNodo(aristas.getId());

                // si su camino minimo aún no ha sido encontrado
                if (noIterar.getNodo(destino.getId()) == null) {
                    double peso = calcularPeso(indice, destino, destinos, raiz);
                    System.out.println("id: " + destino.getId());
                    System.out.println("peso: " + peso);
                    BigDecimal a = new BigDecimal(peso);
                    BigDecimal b = new BigDecimal(pesos[destino.getId()]);

                    if (a.compareTo(b) == -1) {
                        pesos[destino.getId()] = peso;
                        destino.setAristas(new Arista(indice.getId()));

                    }
                }

                aristas = (Arista) aristas.getLink();
            }

            indice = (Vertice) raiz.getNodo(hallarMenor(pesos));
            Vertice copiaIndice = new Vertice(indice);
            copiaIndice.setLink(null);
            noIterar.addNode(copiaIndice);
        }

        return null;
    }

    /**
     * Calcula el peso de un camino generado por el algoritmo de disjtrak
     * @param origen nodo de origen
     * @param destino nodo de destino
     * @param tabla tabla algoritmo de dijstrak
     * @param raiz lista de adyacencia
     * @return coste de recorrer el camino
     */
    public double calcularPeso(Vertice origen, Vertice destino, Vertice tabla, Vertice raiz) {
        Arista arista = origen.getArista(destino.getId());

        if (arista != null) {
            double peso = getProbabilidadNoInfectarse(destino, origen, arista);

            destino = origen;
            Vertice aux = (Vertice) tabla.getNodo(origen.getId());
            arista = aux.getAristas();

            if (arista != null) {
                origen = (Vertice) raiz.getNodo(arista.getId());
                return peso * calcularPeso(origen, destino, tabla, raiz);
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }

    /**
     * Encuentra el indice del elemento menor
     *
     * @param pesos lista de elementos de tipo double
     * @return indice en la lista, del elemento menor
     */
    public int hallarMenor(double[] pesos) {
        int index = 0;
        BigDecimal menor = new BigDecimal(pesos[0]);

        for (int i = 1; i < pesos.length; i++) {
            BigDecimal aux = new BigDecimal(pesos[i]);
            if (menor.compareTo(aux) == 1) {
                menor = aux;
                index = i;
            }
        }

        return index;
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
            /*    if (aislado) {
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
             */
            actual = (Vertice) actual.getLink();
        }
    }

    /**
     * Decide aleatoriamente, cuales nodos poseeran mascarillas, y cuales no
     */
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
     * Le asigna a todos los nodos su atributo de mascarilla como verdadero
     */
    public void aplicarMascarilla() {
        Vertice v1 = vertices;

        while (v1 != null) {
            v1.setMascarilla(true);
            v1 = (Vertice) v1.getLink();
        }
    }

    /**
     * Genera una iteración de infección de nodos
     *
     * @param infectados Lista de vertices que ya estaban infectados antes de
     * esta iteracion (Los que pueden infectar a otros en esta iteracion)
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

    /**
     * Obtiene las probabilidades que tiene un nodo no infectado, de no
     * infectarse. al interactuar con un nodo infectado
     *
     * @param destino nodo a infectar
     * @param origen nodo infectado
     * @param arista arista, con la información de la distancia entre ambos
     * vertices
     * @return probabilidad de que un nodo no quede infectado
     */
    public float getProbabilidadNoInfectarse(Vertice destino, Vertice origen, Arista arista) {

        if (!destino.isInfectado()) {
            if (!origen.isMascarilla()) {

                if (!destino.isMascarilla()) {

                    if (arista.getDistancia() > 2) {
                        return 0.2f;
                    } else {
                        return 0.1f;
                    }
                } else {
                    if (arista.getDistancia() > 2) {
                        return 06.f;
                    } else {
                        return 0.4f;
                    }
                }
            } else {
                if (!destino.isMascarilla()) {

                    if (arista.getDistancia() > 2) {
                        return 0.7f;
                    } else {
                        return 0.6f;
                    }
                } else {

                    if (arista.getDistancia() > 2) {
                        return 0.8f;
                    } else {
                        return 0.7f;
                    }

                }
            }

        }
        return 1;
    }

    /**
     * Lista los vertices a través de la consola, y muestra información
     * detallada acerca de ellos
     */
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

    /**
     * @return Retorna falso, si todavia quedan nodos sin infectar, verdadero,
     * si todos los nodos ya fueron infectados
     */
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

    /**
     * Lista por consola, los nodos que han sido infectados
     */
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

    /**
     * Devuelve una lista de todos los nodos que hay en el grafo
     *
     * @return Lista de adyacencia, con todos los nodos que hay en el grafo.
     */
    public Vertice getPtr() {
        return vertices;
    }

    /**
     * Permite cambiar el número de vertices del grafo
     *
     * @param numVertices número de vertices
     */
    public void setVertices(int numVertices) {
        this.numVertices = numVertices;
    }

    /**
     * Establece si ya se creo un paciente0
     *
     * @param paciente0 falso, si no existe aún un paciente0, verdadero si ya
     * fue creado
     */
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
