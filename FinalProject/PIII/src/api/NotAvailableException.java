package api;
/**
 * This is an exception for adding more than available quantity of some product 
 * in the shopping cart
 * @author derek
 *
 */
public class NotAvailableException extends Exception {
	/**
     * this inherits all from Exception
     */
	public NotAvailableException() {
		super();

	}
	/**
     * This method can contain one message in this exception.
     * @param msg the message
     */
	public NotAvailableException(String msg) {
		super(msg);

	}
}