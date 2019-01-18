package api;
/**
 * A shopping cart is where acts as an online 
 * store's catalog and ordering process.
 * Its information includes:
 * <ul>
 *    <li> a hashmap which stores the products and their corresponding quantity demanded
 *  
 * </ul>
 * 
 * @author Derek
 * @version  1.0
 *
 */

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ShoppingCart implements Serializable {
	/**
	 *  A map which maps the product and its quantity demanded.
	 */
	protected Map<Integer, Integer> products;
	/**
	 * This method must return a new shopping cart with nothing in
	 */
	public ShoppingCart() {
		products = new HashMap<Integer, Integer>();
	}
	/**
	 * This method must add a product and its demand to shopping cart,if
	 * the product is already in the cart,add the demand to original demand,
	 * if quantity demanded excess available quantity NotAvailableException
	 * 
	 * @param prodID  the product ID
	 * @param demand  the quantity demanded
	 * @param back the background
	 * @throws NotAvailableException if the product is not available
	 */
	public void addProduct(int prodID, int demand,Background back) throws NotAvailableException{
		Product p = back.getProduct(prodID);
		if(p.quantity< demand){
			throw new NotAvailableException("Not Enough Product!");}
		else if (!products.containsKey(prodID)) {
			products.put(prodID, demand);
			p.subQuantity(demand);
		}else if (products.containsKey(prodID)){
			int oldval = products.get(prodID);
			int newval = oldval + demand;
			products.replace(prodID, oldval, newval);
		}
		}

	/**
	 * This method must remove a product and its demand from shopping cart,if
	 * needs 0 itmes of the product remove the product from the hashmap.
	 * else new quantity demanded is original demand minus requested removing quantity.
	 * 
	 * @param prodID the product ID
	 * @param demand the demanded quantity
	 * @param back the background
	 */
	public void removeProduct(int prodID, int demand,Background back) {
		Product p = back.getProduct(prodID);
		int oldval = products.get(prodID);
		int newval = oldval - demand;
		if (newval <= 0) {
			products.remove(prodID);
			p.addQuantity(oldval);
		} else {
			products.replace(prodID, oldval, newval);
			p.addQuantity(demand);
		}

	}
	/**
	 * This method must empty the shopping cart
	 * @param back the background
	 */
	public void emptyCart(Background back) {
		products.clear();
		Set<Integer> set = products.keySet();
		for(int i:set){
			Product p=back.getProduct(i);
			p.addQuantity(products.get(i));
		}
		

	}
}
