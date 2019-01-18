package api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is intended to be your API
 * 
 * @author Ilir
 *
 */
public class Project {

	/** a map containing active session ids */
	public Map<Integer, User> active;

	/** the session id */
	protected static int session = 0;

	/** the user manager */
	protected UserManager um;
	
	/** the background manager */
	protected BackgroundManager bm;
	
	/** the users stored in the user manager */
	public Map<String, User> users;
	
	/** the background */
	public Background back;

	/**
	 * Return a new project.
	 * 
	 */

	public Project() {
		try {
			this.active = new HashMap<Integer, User>();
			this.um = new UserManager("user");
			this.bm = new BackgroundManager("background");
			this.users = um.users;
			this.back = this.bm.backgrounds.get(0);
		} catch (FileNotFoundException e) {
			return;

		} catch (ClassNotFoundException e) {
			return;
		} catch (IOException e) {
			return;
		}
	}

	/**
	 * This method must add a new shopper user or administrator user.
	 * 
	 * @param userID
	 * @param password
	 * @param admin
	 *            -> if true, add an administrator user, otherwise add a shopper
	 *            user
	 * @return -> true if operation successful, false otherwise
	 */
	public boolean addUser(String userID, String password, boolean admin) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		if (admin) {
			if (!users.containsKey(userID)) {
				Administrator administrator = new Administrator(userID, password, back);
				um.add(administrator);
				return true;
			}

		} else {
			if (!users.containsKey(userID)) {
				Shopper shopper = new Shopper(userID, password, back);
				um.add(shopper);
				return true;
			}
		}
		return false;
	}

	/**
	 * Authenticates a user an creates an active work session
	 * 
	 * @param userID
	 * @param password
	 * @return -> SessionID if authentication successful, -1 otherwise.
	 */
	public int login(String userID, String password) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		if (um.users.containsKey(userID)) {
			if (um.users.get(userID).password.equals(password)) {
				Project.session++;
				int sessionID = Project.session;
				active.put(sessionID, users.get(userID));
				users.get(userID).back = this.back;
				return sessionID;
			}
		}
		return -1;
	}

	/**
	 * Makes sessionID unavailable for connection
	 * 
	 * @param sessionID
	 */
	public void logout(int sessionID) {
		// your code goeshere
		try {
			if (active.containsKey(sessionID)) {
				active.get(sessionID).back = null;
				active.remove(sessionID);
				um.saveTo("user");
				bm.saveTo("background");

			}
		} catch (IOException e) {
			return;
		}
	}

	/**
	 * This method must add a new category in your application.
	 * 
	 * @param catName
	 *            -> The name of the category to be added.
	 * @param sessionID
	 *            -> A session ID that belongs to an authenticated administrator
	 * @return -> The ID of the category you created if successful, -1 if not
	 *         successful.
	 */
	public int addCategory(String catName, int sessinID) {
		// Your code goes here.
		// Replace the statement below with the correct code.

		if (active.containsKey(sessinID)) {
			if (active.get(sessinID) instanceof api.Administrator) {

				Administrator admin = (Administrator) active.get(sessinID);
				int id = admin.addCategory(catName);
				return id;
			}
		}

		return -1;

	}

	/**
	 * Adds a distribution center to your application. If the given distribution
	 * center exists, or sesionID invalid, do nothing.
	 * 
	 * @param city
	 *            -> The city where distribution center must be based.
	 * @param sessionID
	 *            -> A session ID that belongs to an authenticated administrator
	 */
	public void addDistributionCenter(String city, int sessionID) {
		// Your code goes here
		if (active.containsKey(sessionID)) {
			if (active.get(sessionID) instanceof api.Administrator) {
				Administrator admin = (Administrator) active.get(sessionID);
				admin.addDistributionCentre(city);
			}
		}

	}

	/**
	 * Adds a new Customer to your application; the customer record that belongs
	 * to a newly added shopper user that has no customer record on the system.
	 * 
	 * @param custName
	 *            -> The name of the customer
	 * @param city
	 *            -> The city of the customer address
	 * @param street
	 *            -> The street address of the customer
	 * @param sessionID
	 *            -> A valid sessionID that belongs to an authenticated shopper
	 *            user.
	 * @return -> The added customer ID
	 */
	public int addCustomer(String custName, String city, String street, int sessionID) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		if (active.containsKey(sessionID)) {
			if (active.get(sessionID) instanceof api.Shopper) {
				Shopper shop = (Shopper) active.get(sessionID);
				int id = shop.addCustomer(custName, city, street);
				return id;
			}
		}
		return -1;

	}

	/**
	 * Adds a new Product to your application
	 * 
	 * @param prodName
	 *            -> The product name
	 * @param category
	 *            -> The product category.
	 * @param price
	 *            -> The product sales price
	 * @param sessionID
	 *            -> A session ID that belongs to an authenticated administrator
	 * @return -> Product ID if successful, -1 otherwise.
	 */
	public int addProduct(String prodName, int category, double price, int sessionID) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		if (active.containsKey(sessionID)) {
			if (active.get(sessionID) instanceof api.Administrator) {

				Administrator admin = (Administrator) active.get(sessionID);
				int id = admin.addProduct(prodName, category, price);
				return id;
			}
		}
		return -1;

	}

	/**
	 * Computes the available quantity of prodID in a specific distribution
	 * center.
	 * 
	 * @param prodID
	 * @param center
	 * @return -> Available quantity or -1 if prodID or center does not exist in
	 *         the database
	 */
	public int prodInquiry(int prodID, String center) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		if (back.getProduct(prodID) != null && back.getDistributionCentre(center) != null) {
			DistributionCentre d = back.getDistributionCentre(center);
			int qty = d.qtyInquiry(prodID);
			return qty;

		}
		return -1;
	}

	/**
	 * Updates the stock quantity of the product identified by prodID
	 * 
	 * @param prodID
	 *            -> The product ID to be updated
	 * @param distCentre
	 *            -> Distribution Center (in effect a city name)
	 * @param quantity
	 *            -> Quantity to add to the existing quantity
	 * @param sessionID
	 *            -> A session ID that belongs to an authenticated administrator
	 *            If currently the product 112 has quantity 100 in Toronto,
	 *            after the statement updateQuantity(112, "Toronto", 51) same
	 *            product must have quantity 151 in the Toronto distribution
	 *            center.
	 * @return -> true if the operation could be performed, false otherwise.
	 */
	public boolean updateQuantity(int prodID, String distCentre, int quantity, int sessionID) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		if (active.containsKey(sessionID)) {
			if (active.get(sessionID) instanceof api.Administrator) {

				Administrator admin = (Administrator) active.get(sessionID);
				boolean flag = admin.updateQty(prodID, distCentre, quantity);
				return flag;
			}
		}

		return false;
	}

	/**
	 * Adds two nodes cityA, cityB to the shipping graph Adds a route (an edge
	 * to the shipping graph) from cityA to cityB with length distance If the
	 * nodes or the edge (or both) exist, does nothing
	 * 
	 * @param cityA
	 * @param cityB
	 * @param distance
	 *            -> distance (in km, between cityA and cityB)
	 * @param sessionID
	 *            -> A session ID that belongs to an authenticated administrator
	 */
	public void addRoute(String cityA, String cityB, int distance, int sessionID) {
		// Your code goes here
		if (active.containsKey(sessionID)) {
			if (active.get(sessionID) instanceof api.Administrator) {
				Administrator admin = (Administrator) active.get(sessionID);
				admin.addRoutebetween(cityA, cityB, distance);
			}
		}

	}

	/**
	 * Attempts an order in behalf of custID for quantity units of the prodID
	 * 
	 * @param custID
	 *            -> The customer ID
	 * @param prodID
	 *            -> The product ID
	 * @param quantity
	 *            -> The desired quantity
	 * @param sessionID
	 *            -> A valid sessionID that belongs to an authenticated shopper
	 *            user.
	 * @return -> The orderID if successful, -1 if not.
	 */
	public int placeOrder(int custID, int prodID, int quantity, int sessionID) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		if (active.containsKey(sessionID)) {
			if (active.get(sessionID) instanceof api.Shopper) {
				Shopper shop = (Shopper) active.get(sessionID);

				Customer cust = shop.cusrecord;
				int result = cust.placeOrder(prodID, quantity, back);
				return result;
			}
		}
		return -1;
	}

	/**
	 * Returns the best (shortest) delivery route for a given order
	 * 
	 * @param orderID
	 *            -> The order ID we want the delivery route
	 * @param sessionID
	 *            -> A valid sessionID that belongs to an authenticated shopper
	 *            user.
	 * @return -> The actual route as an array list of cities, null if not
	 *         successful
	 */
	public ArrayList<String> getDeliveryRoute(int orderID, int sessionID) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		if (active.containsKey(sessionID)) {
			if (active.get(sessionID) instanceof api.Shopper) {

				Shopper shop = (Shopper) active.get(sessionID);
				Customer cust = shop.cusrecord;
				ArrayList<String> result = cust.getDeliRoute(orderID, back);
				return result;
			}
		}

		return null;
	}

	/**
	 * Computes the invoice amount for a given order. Please use the fixed price
	 * 0.01$/km to compute the shipping cost
	 * 
	 * @param orderID
	 * @param sessionID
	 *            -> A valid sessionID that belongs to an authenticated shopper
	 *            user.
	 * @return
	 */
	public double invoiceAmount(int orderID, int sessionID) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		if (active.containsKey(sessionID)) {
			if (active.get(sessionID) instanceof api.Shopper) {
				Shopper shop = (Shopper) active.get(sessionID);

				Customer cust = shop.cusrecord;
				double result = cust.getDeliFee(orderID, back);
				return result;
			}
		}

		return -1;
	}

}