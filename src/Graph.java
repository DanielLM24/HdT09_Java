/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Seccion 20
 * Peter Bennett 13243
 * Santiago Gonzalez 13263
 * Daniel Lara Moir 13424 
 * 
 * Interfaz para grafos.
 */

public interface Graph<V,E>
{

    public void add(V label);

    public void addEdge(V vtx1, V vtx2, E label);

    public V remove(V label);

    public E removeEdge(V vLabel1, V vLabel2);

    public V get(V label);

    public Edge<V,E> getEdge(V label1, V label2);

    public boolean contains(V label);

    public boolean containsEdge(V vLabel1, V vLabel2);

    public boolean visit(V label);

    public boolean visitEdge(Edge<V,E> e);

    public boolean isVisited(V label);

    public boolean isVisitedEdge(Edge<V,E> e);

    public void reset();
    
    public int size();

    public int edgeCount();

    public void clear();

    public boolean isEmpty();

    public boolean isDirected();
}

