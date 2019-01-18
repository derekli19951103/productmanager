package api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

/**
 * This graph is a representation of a map of cities.Its information includes:
 * <ul>
 * <li>all the cities(vertices) on the map
 * 
 * </ul>
 * 
 * @author derek
 *
 */

public class Graph implements Serializable{
	/**
	 * all the vertices on the graph
	 */
	protected ArrayList<Vertex> vertices;

	/**
	 * initiates an empty graph
	 */
	public Graph() {
		vertices = new ArrayList<Vertex>();
	}

	/**
	 * Returns true if this graph is empty; otherwise, returns false.
	 * @return true or false
	 */
	public boolean isEmpty() {
		return (vertices.isEmpty());
	}

	/**
	 * Add a vertex to the graph.Vertex is not null.
	 * If the vertex is already in the graph, return false. Otherwise
	 * adds vertex to this graph.
	 * 
	 * @param vertex the added vertex
	 * @return true or false
	 */
	public boolean addVertex(Vertex vertex) {
		if (haveVertex(vertex)) {
			return false;
		} else {
			vertices.add(vertex);
			return true;
		}
	}

	/**
	 * add an edge connecting two vertices
	 * 
	 * @param fromVertex  the start vertex
	 * @param toVertex  the end vertex
	 * @param weight the distance
	 */
	public void addEdge(Vertex fromVertex, Vertex toVertex, double weight) {
		fromVertex.adjacent.add(new Edge(toVertex, weight));
		toVertex.adjacent.add(new Edge(fromVertex, weight));
	}

	/**
	 * return all the vertices in the graph
	 * 
	 * @return list of vertices
	 */
	public HashSet<Vertex> getallVertices() {
		HashSet<Vertex> set = new HashSet<Vertex>();
		for (Vertex v : vertices) {
			set.add(v);
		}
		return set;
	}

	/**
     * get the shortest route from one vertex to another
     * @param start the start vertex
     * @param end the end vertex
     * @return list of vertex on the optimal route
     */
	public ArrayList<String> getroute(String start, String end){
		Vertex fromVertex = getVertex(start);
		Vertex toVertex = getVertex(end);
		HashSet<Vertex> set = this.getallVertices();
		Alogrithm alo=new Alogrithm(fromVertex,toVertex,set);
		Alogrithm alo2=new Alogrithm(fromVertex,toVertex,set);
		return alo2.getResult();

	}

	/**
	 * get the shortest distance from one vertex to another
	 * 
	 * @param start the start vertex
	 * @param end the end vertex
	 * @return the optimal distance
	 */
	public double getShortestDis(String start, String end) {
		Vertex fromVertex = getVertex(start);
		Vertex toVertex = getVertex(end);
		HashSet<Vertex> set = this.getallVertices();
		Alogrithm alo = new Alogrithm(fromVertex, toVertex, set);
		Alogrithm alo2 = new Alogrithm(fromVertex, toVertex, set);
		
		return alo2.getOptimaldist();
	}

	/**
	 * get the vertex name if the city name is the same
	 * 
	 * @param vertexName the name of the vertex
	 * @return the vertex Name 
	 */
	public Vertex getVertex(String vertexName){
		for (Vertex v:vertices){
			if (v.city.equals(vertexName)){
				return v;
			}
		}
		return null;
	}
	
	/**
	 * whether other vertex is equal to this vertex.
	 * 
	 * @param other vertex
	 * @return true or false
	 */
	public boolean haveVertex(Vertex other) {
		for (Vertex v : vertices) {
			if (v.isEqual(other)) {
				return true;
			}
		}
		return false;
	}

}
