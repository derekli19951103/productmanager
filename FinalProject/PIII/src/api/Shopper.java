
package api;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Shopper is a type of user. Shopper can add product to his shopping cart,
 * check out and generate invoices. Shopper can also see the list of products
 * purchased. Shopper's information includes:
 * <ul>
 * <li>UserID
 * <li>password
 * <li>Background information
 * </ul>
 * 
 * @author Zoe
 * @version 1.0
 *
 */
public class Shopper extends User implements Serializable {

	/** The ShoppingCart to put product. */
	protected ShoppingCart cart;

	/** The customer of this shopper . */
	public Customer cusrecord;

	/**
	 * Instantiates a new shopper.
	 *
	 * @param userID
	 *            the user ID
	 * @param password
	 *            the password
	 * @param back
	 *            the background information
	 */
	public Shopper(String userID, String password, Background back) {
		super(userID, password, back);
		cart = new ShoppingCart();
	}

	/**
	 * Adds the customer to the background information.
	 *
	 * @param custName
	 *            the customer name
	 * @param city
	 *            the city where the customer lives
	 * @param street
	 *            the street address of the customer
	 * @return integer
	 */
	public int addCustomer(String custName, String city, String street) {
		Customer c = new Customer(custName, city, street);
		if (this.cusrecord==null) {
			this.cusrecord = c;
			return c.ID;
		}
		return -1;

	}

	/**
	 * Adds the product to cart.
	 *
	 * @param prodID
	 *            the product ID
	 * @param demand
	 *            the actual demand for that product
	 * @return true if successful, false otherwise
	 */
	public boolean addProductToCart(int prodID, int demand)  {
		boolean result = cart.addProduct(prodID, demand, this.back);
		return result;
	}

	/**
	 * Removes the product from cart.
	 *
	 * @param prodID
	 *            the product ID
	 * @param demand
	 *            the actual demand for that product
	 */
	public void removeProductFromCart(int prodID, int demand) {
		cart.removeProduct(prodID, demand, this.back);

	}

	/**
	 * Empty this shopping cart.
	 */
	public void emptyShoppingCart() {
		cart.emptyCart(this.back);

	}
	/**
	 * A customer check out
	 * @return a invoice of the customer
	 */
	public Invoice checkOut(){
		Set<Integer> set = this.cart.products.keySet();
		Map<Integer, Integer> products= this.cart.products;
		double sum=0;
		Invoice invoice = new Invoice(this.cusrecord.name);
		for(int prodID:set){
			sum+= (back.getProduct(prodID).price)*(products.get(prodID));
			invoice.addProduct(prodID);
			sum += this.cusrecord.placeOrder(prodID, products.get(prodID), this.back);
			
			
		}
		invoice.setInvoiceamount(sum);
		this.cusrecord.invoices.put((Integer)invoice.ID, invoice);
		return invoice;
	}

}
