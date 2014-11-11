/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Seccion 20
 * Peter Bennett 13243
 * Santiago Gonzalez 13263
 * Daniel Lara Moir 13424 
 * 
 * Implentacion de digrafo con matriz de adyacencia.
 */// (c) 1998, 2001 duane a. bailey

import java.util.ArrayList;
import java.util.Iterator;

public class GraphMatrixDirected<V,E> extends GraphMatrix<V,E>
{

    public GraphMatrixDirected(int size)
    {
        super(size,true);
    }

    public void addEdge(V vLabel1, V vLabel2, E label)
    {
        GraphMatrixVertex<V> vtx1,vtx2;
        // get vertices
        vtx1 = dict.get(vLabel1);
        vtx2 = dict.get(vLabel2);
        // update matrix with new edge
        Edge<V,E> e = new Edge<V,E>(vtx1.label(),vtx2.label(),label,true);
        data[vtx1.index()][vtx2.index()] = e;
    }

    @SuppressWarnings("unchecked")
    public E removeEdge(V vLabel1, V vLabel2)
    {
        // get indices
        int row = dict.get(vLabel1).index();
        int col = dict.get(vLabel2).index();
        // cache old value
        Edge<V,E> e = (Edge<V,E>)data[row][col];
        // update matrix
        data[row][col] = null;
        if (e == null) return null;
        else return e.label(); // return old value
    }

    public int edgeCount()
    {
        // count non-null entries in table
        int sum = 0;                
        for (int row=0; row<size; row++) 
            for (int col=0; col<size; col++)
                if (data[row][col] != null) sum++;
        return sum;
    }

    @SuppressWarnings("unchecked")
    public Iterator<Edge<V,E>> edges()
    {
        ArrayList<Edge<V,E>> list = new ArrayList<Edge<V,E>>();
        for (int row=size-1; row>=0; row--) 
            for (int col=size-1; col >= 0; col--) {
                Edge<V,E> e = (Edge<V,E>)data[row][col];
                if (e != null) list.add(e);
            }
        return list.iterator();
    }
}
