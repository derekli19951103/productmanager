package api;

import java.io.Serializable;

/**
 * Category is a collection of the same type of products which has its own
 * category name and description . Its information includes:
 * <ul>
 * <li>its name
 * <li>An ID generated by system
 * <li>its code
 * </ul>
 * 
 * @author Sunny
 * @version 1.0
 *
 */

public class Category implements Serializable{
	/**
	 * its name is a string
	 */
	protected String catName;
	/**
	 * An code which generated sequentially
	 */
	protected static int code = 0;
	/**
	 * a ID which stores the code
	 */
	protected int ID;

	/**
	 * This method must return a new category
	 * 
	 * @param catName the name of the category
	 */
	public Category(String catName) {
		this.catName = catName;
		code++;
		ID = code;
	}
	
	/**
	 * Return a string representation of the category
	 * 
	 * @return the string
	 */


	public String toString (){
		return ID +this.catName;
	}
}
