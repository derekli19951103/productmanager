package api;
import java.io.Serializable;
/**
 * a vertex on the map which means a city on the map.
 * a vertex's information includes:
 * <ul>
 *    <li> the city it represents
 *    <li> adjacent cities which is connected
 *    <li> the distance
 *    <li> the previous vertex visited
 *    <li> a marker which means whether it is marked
 * </ul>
 * 
 * @author Zoe
 * @version  1.0
 *
 */
import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex> ,Serializable{
	/**
	 * the name of city
	 */
	protected String city;
	/**
	 * connected cities and the weight
	 */
	protected List<Edge> adjacent;
	/**
	 * the distance
	 */
	protected double distance = Double.POSITIVE_INFINITY;
	/**
	 * previous visited vertex
	 */
	protected Vertex previous;
	/**
	 * the marker which marks visited or not
	 */
	protected boolean marked = false;
    /**
     * creates a new vertex correspond to the city and initialized its adjacent cities
     * @param city the city name
     */
	public Vertex(String city) {
		this.city = city;
		this.adjacent = new ArrayList<Edge>();
	}
	/**
	 * mark the vertex
	 */
	public void markVertext(){
		this.marked = true;
	}
	/**
	 * set the previous vertex
	 * @param v vertex which needed to be settle
	 */
	public void setPrevious(Vertex v){
		this.previous = v;
	}
	/**
	 * get the neighbour vertices
	 * @return list of vertex
	 */
	public List<Vertex> getneighbours(){
		List<Vertex>result = new ArrayList<Vertex>();
		for (Edge e:adjacent){
			result.add(e.destination);
		}
		return result;
	}
	/**
	 * get the weight to the target vertex
	 * @param target  target vertex
	 * @return the weight
	 */
	public double getWeight(Vertex target){
		Double result=0.0;
		for (Edge e:this.adjacent){
			if (e.destination.isEqual(target)){
				result = e.weight;
			}
		}
		return result;
		
	}
    /**
     * compare to vertex depending on the distances
     * @param other  the other vertex
     */
	public int compareTo(Vertex other) {
		return Double.compare(this.distance, other.distance);
	}
	/**
	 * to tell whether two vertex are the same depending whether they are same city
	 * @param other  the other vertex
	 * @return true or false
	 */
	public boolean isEqual(Vertex other){
		if (this.city.equals(other.city)){
			return true;
		}
		return false;
	}
	/**
	 * a string representation of the vertex
	 * @return this city
	 */
	public String toString(){
		return this.city;
	}

}