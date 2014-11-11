/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Seccion 20
 * Peter Bennett 13243
 * Santiago Gonzalez 13263
 * Daniel Lara Moir 13424 
 * 
 * Implementacion de vertices para grafos con matriz de adyacencia.
 */// (c) 1998, 2001 duane a. bailey
public class GraphMatrixVertex<V> extends Vertex<V>
{
    protected int indice;

    public GraphMatrixVertex(V label, int idx)
    {
        super(label);
        indice = idx;
    }

    public int index()
    {
        return indice;
    }

    public String toString()
    {
        return "<GraphMatrixVertex: "+label()+">";
    }
}
