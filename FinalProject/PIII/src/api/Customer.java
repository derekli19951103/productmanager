/*
 * 
 */
package api;

import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * this customer is a representation of customer information it includes:
 * <ul>
 * <li>all the information about customer
 * 
 * </ul>
 * 
 * @author xiao
 *
 */
public class Customer implements Serializable{
	
	/** The unique code. */
	public static int code = 0;
	
	/** The id that represent customer. */
	public int ID;
	
	/** The name of customer. */
	protected String name;
	
	/** The city of this customer. */
	protected String city;
	
	/** The street address of customer. */
	protected String street;
	
	/** The orders that this customer made. */
	public Map<Integer, Order> orders;
	/** The invoices of this customer**/
	
	protected Map<Integer, Invoice> invoices;
	
	/**
	 * Instantiates a new customer.
	 *
	 * @param custName the customer's name
	 * @param city the city name
	 * @param street the address
	 */
	public Customer(String custName, String city, String street){
		this.street = street;
		this.name = custName;
		this.city = city;
		this.orders = new HashMap<Integer, Order>();
		code++;
		ID = code;
	}
	
	/**
	 * Place order by customer.
	 *
	 * @param prodID the product  ID
	 * @param quantity the number of amount
	 * @param back the background of info
	 * @return the order ID if successful, -1 if it fails
	 */
	public int placeOrder(int prodID, int quantity, Background back){
		for(DistributionCentre d: back.listofDistri){
			if(d.qtyInquiry(prodID) >= quantity){
				Order order = new Order(this, prodID, quantity);
				d.subQty(prodID, quantity);
				order.setCity(d.city);
				orders.put(order.ID, order);
				return order.ID;
			}
		}
		return -1;
	}
	
	/**
	 * Gets the delivery route.
	 *
	 * @param orderID the order ID
	 * @param back the background
	 * @return the deliver route
	 */
	public ArrayList<String> getDeliRoute(int orderID, Background back){
		Order order = orders.get(orderID);
		ArrayList<String> result = back.cityroute.getroute(order.city, this.city);
		return result;
	}
	
	/**
	 * Gets the delivery fee.
	 *
	 * @param orderID the order ID
	 * @param back the background
	 * @return the deliver fee
	 */
	public double getDeliFee(int orderID, Background back){
		Order order = orders.get(orderID);
		double result = back.cityroute.getShortestDis(order.city, this.city);
		return result *0.01;
	}
	
	/**
	 * Return a string representation of the vertex
	 * @return string  id + name + city
	 */
	public String toString(){
		return ID + " "+ name +" " +city;
	}


}
