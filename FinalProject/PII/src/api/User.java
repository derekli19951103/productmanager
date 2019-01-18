package api;

import java.io.Serializable;

/**
 * User is a general class which has administrator and shopper. Any user can
 * browse the merchandise that is available for sale. User's information
 * includes:
 * <ul>
 * <li>userID
 * <li>password
 * <li>Background information
 * </ul>
 * 
 * @author Zoe
 * @version 1.0
 *
 */
public class User implements Serializable {

	/** The user ID of the user. */
	protected String userID;

	/** The user's password. */
	protected String password;

	/** The background information of the user. */
	protected Background back;

	/**
	 * Instantiates a new user.
	 *
	 * @param userID
	 *            the user ID
	 * @param password
	 *            the user's password
	 * @param back
	 *            the background information
	 */
	public User(String userID, String password, Background back) {
		this.userID = userID;
		this.password = password;
		this.back = back;
	}

	/**
	 * a string representation of the user
	 * 
	 * @return this user
	 */
	public String toString() {
		return userID;
	}

}
