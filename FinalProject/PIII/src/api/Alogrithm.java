package api;

import java.util.ArrayList;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
/**
 * The alogrithm which can calculate the shortest path between two cities.
 * which take in:
 * <ul>
 *    <li> a start city
 *    <li> an end city
 *    <li> the result of the algorithm
 *    <li> the optimal distance of the result
 * </ul>
 * 
 * 
 *
 */

public class Alogrithm {
	/**
	 * a start vertex
	 */
	protected Vertex start;
	/**
	 * an end vertex
	 */
	protected Vertex end;
	/**
	 * a result of list of cities
	 */
	protected ArrayList<String> result;
	/**
	 * the optimal distance
	 */
	protected double optimaldist;
    /**
     * instantiates a start vertex and end vertex and takes a hashset of all vertices
     * and calculate the path then store the result and the optimal distance
     * @param start  start vertex
     * @param end  end vertex
     * @param set  all the vertices on the graph
     */
	public Alogrithm(Vertex start, Vertex end, HashSet<Vertex> set) {
		getPath(start,set);

		result = getRoute(end);
		optimaldist = end.distance;

	}
    /**
     * get the result
     * @return list of cities in the optimal route
     */
	public ArrayList<String> getResult() {
		return result;
	}
    /**
     * get the optimal path from one city to every other cities.
     * @param source start vertex
     * @param setofVertex  all vertices on the graph
     */
	public static void getPath(Vertex source, HashSet<Vertex> setofVertex) {
		HashSet<Vertex> newset = (HashSet<Vertex>) setofVertex.clone();
		
		newset.remove(source);
		source.distance =0.0;
		newset.add(source);
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		for(Vertex v:newset){
			pq.add(v);
		}
		while (pq.isEmpty() == false) {
			Double min = getMinDistofSet(newset);
			if(min == Double.POSITIVE_INFINITY){
				return;
			}
			Vertex current = pq.remove();
				for (Vertex neighbour : current.getneighbours()) {
					double alt = current.distance + current.getWeight(neighbour);
					if (neighbour.distance > alt) {
						neighbour.distance = alt;
						neighbour.previous = current;
					}

				}
			}
		}
	
    /**
     * get the optimal distance
     * @return optimal distance
     */
	public double getOptimaldist() {
		return optimaldist;
	}
	/**
	 * get the smallest distance given by a set of vertex.
	 * @param set  set of vertex
	 * @return the minimal distance
	 */
	public static double getMinDistofSet(HashSet<Vertex> set){
		Double min =  Double.POSITIVE_INFINITY;
		for(Vertex v:set){
			if(v.distance < min){
				min = v.distance;
			}
		}
		return min;
	}
    /**
     * return the route of the optimal path.
     * @param target  target vertex
     * @return list of cities
     */
	public static ArrayList<String> getRoute(Vertex target) {
		ArrayList<String> result = new ArrayList<String>();
		Vertex c= target;
		while(c!=null){
			result.add(c.city);
			c=c.previous;
		}
		Collections.reverse(result);
		return result;
	}
	

			
		
	}

