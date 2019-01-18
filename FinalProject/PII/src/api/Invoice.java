package api;
import java.io.Serializable;
/**
 * Invoice tells what the customer buys and costs and who is buying 
 * every order has a sequence number
 * An administrator's information includes:
 * <ul>
 *    <li> UserID
 *    <li> password
 *    <li> Background information
 * </ul>
 * 
 * @author Zoe
 * @version  1.0
 *
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Invoice is Java class used to instantiate objects that is the shopper's
 * shopping invoice.
 * Its attributes includes:
 * <ul>
 *    <li> The invoice ID
 *    <li> The client's name
 *    <li> The list of products on it/
 * </ul>
 * 
 * @author Zoe
 * @version  1.0
 *
 */
public class Invoice implements Serializable{
	
	/** A static variable to create a unique number for a invoice. */
	public static int orderSequence = 0;
	
	/** The unique id of the invoice. */
	protected int ID;
	
	/** The client's name on this invoice. */
	protected String ClientName;
	
	/** The list of products on this invoice. */
	protected List<Integer> ListOfProduct; 
	
	/** The invoice amount*/
	protected double invoiceAmount;
	
	/**
	 * Instantiates a new invoice.
	 *
	 * @param ClientName the client's name
	 */
	public Invoice(String ClientName){
		orderSequence ++;
		ID = orderSequence;
		this.ClientName = ClientName;
		this.ListOfProduct = new ArrayList<>();
	}
	/**
	 * Add a product to this invoice.
	 * @param prodID the product ID
	 */
	public void addProduct(int prodID){
		this.ListOfProduct.add(prodID);
	}
	/**
	 * Set the invoice amount.
	 *
	 * @param a the invoice amount
	 */
	public void setInvoiceamount(double a){
		this.invoiceAmount = a;
	}
	
	/**
	 * Return a string representation of this invoice.
	 * @return the string representation
	 */
	public String toString(){
		return ID + ClientName + ListOfProduct;
	}
}