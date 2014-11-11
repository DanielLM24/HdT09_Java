/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Seccion 20
 * Peter Bennett 13243
 * Santiago Gonzalez 13263
 * Daniel Lara Moir 13424 
 * 
 * Implentacion de grafo con matriz de adyacencia.
 */
// (c) 1998, 2001 duane a. bailey

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public abstract class GraphMatrix<V,E> implements Graph<V,E>
{
	//Cantidad de vertices.
    protected int size;          

    //Almacenamiento de arcos.
    protected Edge data[][];     

    protected Map<V,GraphMatrixVertex<V>> dict;   
    /**
     * List of free vertex indices within graph.
     */
    protected ArrayList freeList;    // available indices in matrix
    /**
     * Whether or not graph is directed.
     */
    protected boolean directed;  // graph is directed

    protected GraphMatrix(int size, boolean dir)
    {
        this.size = size; // set maximum size
        directed = dir;   // fix direction of edges
        // the following constructs a size x size matrix
        data = new Edge[size][size];
        // label to index translation table
        dict = new Hashtable<V,GraphMatrixVertex<V>>(size);
        // put all indices in the free list
        freeList = new ArrayList<Integer>();
        for (int row = size-1; row >= 0; row--)
            freeList.add(new Integer(row));
    }

    //Agrega un vertice al grafo.
    public void add(V label)
    {
        // Salta duplicados.
        if (dict.containsKey(label)) return;
        int row = (int) freeList.remove(0);
        dict.put(label, new GraphMatrixVertex<V>(label, row));
    }

    //Agrega una conexión entre dos vertices.
    abstract public void addEdge(V v1, V v2, E label);

    //Elimina un vertice del grafo.
    public V remove(V label)
    {
        GraphMatrixVertex<V> vert;
        vert = dict.remove(label);
        if (vert == null) return null;
        int index = vert.index();
        for (int row=0; row<size; row++) {
            data[row][index] = null;
            data[index][row] = null;
        }
        freeList.add(new Integer(index));
        return vert.label();
    }

    //Elimina conexion enre dos grafos.
    abstract public E removeEdge(V vLabel1, V vLabel2);

    public V get(V label)
    {
        GraphMatrixVertex<V> vert;
        vert = dict.get(label);
        return vert.label();
    }

    //Obtiene la conexion dados dos vertices.
    @SuppressWarnings("unchecked")
    public Edge<V,E> getEdge(V label1, V label2)
    {
        int row,col;
        row = dict.get(label1).index();
        col = dict.get(label2).index();
        return (Edge<V,E>)data[row][col];
    }

    public boolean contains(V label)
    {
        return dict.containsKey(label);
    }

    public boolean containsEdge(V vLabel1, V vLabel2)
    {
        GraphMatrixVertex<V> vtx1, vtx2;
        vtx1 = dict.get(vLabel1);
        vtx2 = dict.get(vLabel2);
        return data[vtx1.index()][vtx2.index()] != null;
    }

    public boolean visit(V label)
    {
        Vertex<V> vert = dict.get(label);
        return vert.visit();
    }

    public boolean visitEdge(Edge<V,E> e)
    {
        return e.visit();
    }

    public boolean isVisited(V label)
    {
        GraphMatrixVertex<V> vert;
        vert = dict.get(label);
        return vert.isVisited();
    }

    public boolean isVisitedEdge(Edge<V,E> e)
    {
        return e.isVisited();
    }

    @SuppressWarnings("unchecked")
    public void reset()
    {
        Iterator<GraphMatrixVertex<V>> it = dict.values().iterator();
        while (it.hasNext())
        {
            it.next().reset();
        }
        for (int row=0; row<size; row++)
            for (int col=0; col<size; col++) {
                Edge<V,E> e = (Edge<V,E>)data[row][col];
                if (e != null) e.reset();
            }
    }

    public int size()
    {
        return dict.size();
    }

    public void clear()
    {
        dict.clear();
        for (int row=0; row<size; row++)
            for (int col=0; col<size; col++)
                data[row][col] = null;
        freeList = new ArrayList<Integer>();
        for (int row=size-1; row>=0; row--)
            freeList.add(new Integer(row));
    }

    public boolean isEmpty()
    {
      return dict.isEmpty();
    }

    public boolean isDirected()
    {
        return directed;
    }
    
}

