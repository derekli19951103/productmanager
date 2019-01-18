package api;
/**
 * Attempts an order in behalf of custID for quantity units of the prodID

* <ul>
 *    <li> its customer
 *    <li> the product
 *    <li> its ordered quantity
 *    <li> An ID generated by system
 * </ul> 
 * 
 * @author Derek
 * @version  1.0
 *
 */

import java.io.Serializable;

public class Order implements Serializable {
	/**
	 * its customer
	 */
	protected Customer cust;
	/**
	 * its product
	 */
	protected int prodID;
	/**
	 * its ordered quantity is an integer
	 */
	protected int quantity;
	/**
	 * the city which the order delivered from
	 */
	protected String city;
	/**
	 * An ID which generated sequentially
	 */

	protected static int code;
	/**
	 * the code which store the ID
	 */
	protected int ID;

	/**
	 * This method must create a new order
	 * 
	 * @param cust the customer name
	 * @param prodID the product ID
	 * @param quantity the quantity of the product
	 */

	public Order(Customer cust, int prodID, int quantity) {
		this.cust = cust;
		this.prodID = prodID;
		this.quantity = quantity;
		code++;
		ID =code;

	}

	/**
	 * Sets the city for the customer who ordered.
	 *
	 * @param city
	 *            the city of the customer
	 */

	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * a string representation of the order
	 * 
	 * @return this order
	 */
	public String toString() {
		return cust + " " + prodID + " " + quantity + " " + city;
	}
}
