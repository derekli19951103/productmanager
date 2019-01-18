package api;

import java.io.Serializable;

/**
 * An edge is a connection between two vertex.
 * An edge's information includes:
 * <ul>
 *    <li> the destination a vertex connects to
 *    <li> the distance(weight)
 * </ul>
 * 
 * @author Zoe
 * @version  1.0
 *
 */
public class Edge implements Serializable{
	/**
	 * the target vertex connected to
	 */
	protected Vertex destination;
	/**
	 * the distance to target vertex
	 */
	protected double weight;
    /**
     * creates an edge connected to target vertex and add weight to it.
     * @param Target the target vertex
     * @param Weight the distance
     */
	public Edge(Vertex Target, double Weight) {
		this.destination = Target;
		this.weight = Weight;

	}
	/**
	 * Return a string representation of the distribution center.
	 * 
	 * @return the string
	 */
	
	public String toString (){
		return destination.city+" "+weight;
	}
}