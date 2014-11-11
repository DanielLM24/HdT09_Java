/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Seccion 20
 * Peter Bennett 13243
 * Santiago Gonzalez 13263
 * Daniel Lara Moir 13424 
 * 
 * Implementacion de vertices para grafos.
 */// (c) 1998, 2001 duane a. bailey

public class Vertex<E>
{
	//Etiqueta o valor asociado.
    protected E label;
    protected boolean visitado;

    
    public Vertex(E label)
    {
        this.label = label;
        visitado = false;
    }

    public E label()
    {
        return label;
    }

    
    public boolean visit()
    {
        boolean was = visitado;
        visitado = true;
        return was;
    }


    public boolean isVisited()
    {
        return visitado;
    }

    //Util para realizar varios recorridos.
    public void reset()
    {
        visitado = false;
    }

    /**
     * Construct a string representing vertex.
     *
     * @post returns string representation of vertex
     * 
     * @return A string representing vertex.
     */
    public String toString()
    {
        return "<Vertice: "+label+">";
    }
}
  
