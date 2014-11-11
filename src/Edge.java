/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Seccion 20
 * Peter Bennett 13243
 * Santiago Gonzalez 13263
 * Daniel Lara Moir 13424 
 * 
 * Implentacion de arcos para grafos. Pueden ser dirigidos.
 */// (c) 1998, 2001 duane a. bailey

public class Edge<V,E>
{
	//Referencia a elementos adyacentes.
    protected V here, there;   

    protected E label;

    protected boolean visited; 

    protected boolean directed; 

    public Edge(V vtx1, V vtx2, E label, boolean directed)
    {
        here = vtx1;
        there = vtx2;
        this.label = label;
        visited = false;
        this.directed = directed;
    }

    public V here()
    {
        return here;
    }

    public V there()
    {
        return there;
    }

    /**
     * Sets the label associated with the edge.  May be null.
     *
     * @post sets label of this edge to label 
     * 
     * @param label Any object to label edge, or null.
     */
    public void setLabel(E label)
    {
        this.label = label;
    }

    public E label()
    {
        return label;
    }

    public boolean visit()
    {
        boolean was = visited;
        visited = true;
        return was;
    }

    public boolean isVisited()
    {
        return visited;
    }

    public boolean isDirected()
    {
        return directed;
    }

    public void reset()
    {
        visited = false;
    }
    
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        s.append("<Edge:");
        if (visited) s.append(" visited");
        s.append(" "+here());
        if (directed) s.append(" ->");
        else s.append(" <->");
        s.append(" "+there()+">");
        return s.toString();
    }
}
