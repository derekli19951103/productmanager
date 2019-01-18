package api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Administrator is a type of user. Administrator has access to modify products
 * and categories in the system. Administrator inherits User class. An
 * administrator's information includes:
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
public class Administrator extends User implements Serializable {

	/**
	 * Instantiates a new administrator object.
	 *
	 * @param userID the ID of the user 
	 * @param password the password of the user
	 * @param back the background
	 */
	public Administrator(String userID, String password, Background back) {
		super(userID, password, back);
	}


	/**
	 * Add a new category.
	 *
	 * @param catName the category name
	 * @return the id of the added category if successful, -1 if it fails
	 */
	public int addCategory(String catName) {

		if (back.containCategory(catName)) {
			return -1;
		} else {
			Category c = new Category(catName);
			back.listofCategory.add(c);
			return c.ID;
		}
	}

	/**
	 * Adds a new distribution center.
	 *
	 * @param city the city where the distribution center located
	 */
	public void addDistributionCentre(String city) {
		if (!back.containDestributionCen(city)) {
			DistributionCentre d = new DistributionCentre(city, back);
			back.listofDistri.add(d);
		}
	}

	/**
	 * Adds a new product.
	 *
	 * @param prodName the name of the product
	 * @param category the category of the product
	 * @param price the price of the product
	 * @return the ID of the product if successful, -1 if it fails
	 */
	public int addProduct(String prodName, int category, double price) {
		if (!back.containProduct(prodName)) {
			if (back.getCategory(category) != null) {
				Category c = back.getCategory(category);
				
				Product p = new Product(prodName, c, price);
				back.listofProduct.add(p);
				List<DistributionCentre> distri = back.listofDistri;
				for(DistributionCentre d:distri){
					d.addProduct(p.ID, 0);
				}
				return p.ID;
			}
		}
		return -1;
	}


	/**
	 * Add a route between city A and city B.
	 *
	 * @param cityA the city A
	 * @param cityB the city B
	 * @param distance the distance between city A and city B
	 */

	public void addRoutebetween(String cityA, String cityB, int distance) {
		Vertex A = new Vertex(cityA);
		Vertex B = new Vertex(cityB);
		boolean flag1 = back.cityroute.addVertex(A);
		boolean flag2 = back.cityroute.addVertex(B);
		if (flag1 && flag2) {
			back.cityroute.addEdge(A, B, distance);
		} else if (flag1 && !flag2) {
			Vertex vertexB = back.cityroute.getVertex(cityB);
			back.cityroute.addEdge(A, vertexB, distance);

		} else if (!flag1 && flag2) {
			Vertex vertexA = back.cityroute.getVertex(cityA);
			back.cityroute.addEdge(vertexA, B, distance);
		} else {
			List<Vertex> neighbor = back.cityroute.getVertex(cityA).getneighbours();
			for (Vertex v : neighbor) {
				if (v.isEqual(B)) {
					return;
				}
			}
			Vertex vertexC = back.cityroute.getVertex(cityA);
			Vertex vertexD = back.cityroute.getVertex(cityB);
			back.cityroute.addEdge(vertexC, vertexD, distance);

		}
	}

	/**
	 * Update the quantity of a product in a distribution center.
	 *
	 * @param prodID the product ID
	 * @param distCentre the name of the distribution center
	 * @param quantity the quantity of the product
	 * @return true, if successful
	 */
	public boolean updateQty(int prodID, String distCentre, int quantity) {
		if (back.getProduct(prodID) != null && back.containDestributionCen(distCentre)) {
			Product p = back.getProduct(prodID);
			DistributionCentre d = back.getDistributionCentre(distCentre);
			d.addQty(prodID, quantity);
			p.addQuantity(quantity);
			return true;
		}
		return false;
	}
	
	/**
	 * Change the available  quantity of a product.
	 *
	 * @param prodID the product ID
	 * @param qty the quantity
	 */
	public void changeAvailQty(int prodID, int qty){
		if (back.getProduct(prodID)!=null){
			back.getProduct(prodID).quantity = qty;
		}
	}
	
	/**
	 * Change the price of a product.
	 *
	 * @param prodID the product ID
	 * @param price the price of the product
	 */
	public void changePrice(int prodID, double price){
		if (back.getProduct(prodID)!=null){
			back.getProduct(prodID).price = price;
		}
		
	}
	
	/**
	 * Sets the image of a product.
	 *
	 * @param prodID the product ID
	 * @param imgPath the image path of the product
	 */
	public void setImage(int prodID,String imgPath){
		if (back.getProduct(prodID)!=null){
			back.getProduct(prodID).imagePath= imgPath;
		}
	}
	
	/**
	 * See  a list containing all the products.
	 *
	 * @return the list of all products
	 */
	public List<Product> seeProducts(){
		return back.listofProduct;
	}
	

}
