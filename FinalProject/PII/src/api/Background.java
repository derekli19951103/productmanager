package api;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * this background is stores all of the products, categories, distribution centers, customers, routes:
 * <ul>
 *    <li> a list of product
 *    <li> a list of category
 *    <li> a list of distribution center
 *    <li> a list of customer
 *    <li> the routes between cities
 * </ul>
 * 
 * @author xiao
 *
 */
public class Background implements Serializable{
	
	/** The list of product. */
	protected List<Product> listofProduct;
	
	/** The list of category. */
	protected List<Category> listofCategory;
	
	/** The list of distribution center. */
	protected List<DistributionCentre> listofDistri;
    
    /** The list of customer. */
    protected Map<Integer,Customer> listofCustomer;
    
    /** The routes between cities. */
    protected Graph cityroute;
	
    /**
     * Instantiates a new background.
     */
    public Background() {
		this.listofCategory = new ArrayList<Category>();
		this.listofProduct = new ArrayList<Product>();
		this.listofDistri = new ArrayList<DistributionCentre>();
		this.cityroute = new Graph(); 
		this.listofCustomer = new HashMap<Integer,Customer>();

	}

	/**
	 * Check whether the background contains the category.
	 *
	 * @param catName the category name
	 * @return true or false
	 */
	public boolean containCategory(String catName) {
		for (Category c : listofCategory) {
			if (c.catName.equals(catName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * check whether the background contains the product.
	 *
	 * @param proName the product name
	 * @return true or false
	 */
	public boolean containProduct(String proName) {
		for (Product p : listofProduct) {
			if (p.prodName.equals(proName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * check whether the background contains the distribution center.
	 *
	 * @param disName the name of the distribution center
	 * @return true or false
	 */
	public boolean containDestributionCen(String disName) {
		for (DistributionCentre d : listofDistri) {
			if (d.city.equals(disName)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets the category with a category id.
	 *
	 * @param catcode the category code
	 * @return the category or null
	 */
	public Category getCategory(int catcode){
		for (Category c:listofCategory){
			if (c.ID == catcode){
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Gets the product with a product id.
	 *
	 * @param prodID the product ID
	 * @return the product or null
	 */
	public Product getProduct(int prodID){
		for (Product p:listofProduct){
			if(p.ID == prodID){
				return p;
			}
		}
		return null;
	}
	
	/**
	 * Gets the distribution center with its name
	 *
	 * @param distCent the name of the distribution center
	 * @return the distribution center or null
	 */
	public DistributionCentre getDistributionCentre(String distCent){
		for (DistributionCentre d:listofDistri){
			if(d.city.equals(distCent)){
				return d;
			}
		}
		return null;
	}

}
