package api;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * a distribution center locates in a city and has products and its quantity in this center.
 * Its information includes:
 * <ul>
 *    <li> city it locates
 *    <li> a map between product and its quantity in this center
 * </ul>
 */
public class DistributionCentre implements Serializable{
	/**
	 * a city in string
	 */
	protected String city;
	/**
	 * a map between product and its quantity in this center
	 */
	protected Map<Integer, Integer> products;
    /**
     * This creates a distribution center in a city and initiates every products' reserved
     * quantity in this center to 0.
     * @param city the name of the city
     * @param back the background
     */
	public DistributionCentre(String city,Background back) {
		this.city = city;
		this.products = new HashMap<Integer,Integer>();
		for(Product p:back.listofProduct){
			products.put(p.ID, 0);
		}
	}
	
	/**
	 * product inquiry
	 *
	 * @param prodID the product  ID
	 * @return whether products contains its ID
	 */
	public int qtyInquiry(int prodID){
		if (products.containsKey(prodID)){
			return products.get(prodID);
		}
		
		return -1;
	}
	
	/**
	 * Add product quantity
	 *
	 * @param prodID the product  ID
	 * @param num the number of quantity that added
	 */
	public void addQty(int prodID, int num){
		int oldQty = products.get(prodID);
		int newQty = oldQty+num;
		products.replace(prodID, oldQty,newQty);
	}
	/**
	 * Subtract product quantity
	 *
	 * @param prodID the product  ID
	 * @param num the number of quantity that subtracted
	 */
	public void subQty(int prodID, int num){
		int oldQty = products.get(prodID);
		int newQty = oldQty-num;
		products.replace(prodID, oldQty,newQty);
	}
	
	/**
	 * Return a string representation of the Distribution center
	 *
	 * @return this city
	 */
	public String toString(){
		return city;
	}

}
