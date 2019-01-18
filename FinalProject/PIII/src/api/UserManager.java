package api;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages saving and loading of Shoppers
 * 
 * @author Sunny
 *
 */
public class UserManager {
	/** A mapping of User ID to Shopper. */
	protected Map<String, User> users;

	/** Creates a new empty UserManager. */

	public UserManager() {
	       this.users = new HashMap<String, User>();
	}

	/**
	 * Creates a new ShopperManager for the Shopper whose information is stored
	 * in filePath
	 * 
	 * @param filePath
	 * @throws FileNotFoundException
	 * @throws IOException
	 *             if filePath is not valid
	 * @throws ClassNotFoundException
	 */
	public UserManager(String filePath) throws IOException, ClassNotFoundException, FileNotFoundException {
		this.users = new HashMap<>();

		File file = new File(filePath);
		if (file.exists()) {
			readFrom(file.getPath());
		} else
			file.createNewFile();
	}

	/**
	 * Adds record to this ShopperManager.
	 * 
	 * @param record
	 *            a record to be added.
	 */

	public void add(User record) {
		users.put(record.userID, record);
	}

	/**
	 * Writes the ShopperManager to file at filePath.
	 * 
	 * @param filePath
	 *            the file to write records to
	 * @throws IOException
	 */

	public void saveTo(String filePath) throws IOException {
		OutputStream file = new FileOutputStream(filePath);
		OutputStream buffer = new BufferedOutputStream(file);
		ObjectOutput output = new ObjectOutputStream(buffer);
		output.writeObject(users);
		output.close();
	}

	/**
	 * Reads the Shoppers from file at a path.
	 * 
	 * @param filePath
	 *            the file to read records from
	 * @throws ClassNotFoundException
	 */

	@SuppressWarnings("unchecked")
	private void readFrom(String path) throws ClassNotFoundException {
		try (InputStream file = new FileInputStream(path);
				InputStream buffer = new BufferedInputStream(file);
				ObjectInput input = new ObjectInputStream(buffer);) {
			users = (Map<String, User>) input.readObject();
		} catch (IOException ex) {
			return;
		}
	}

	/**
	 * Override the Shoppers in the file to String.
	 */

	@Override
	public String toString() {
		String result = "";
		for (User s : users.values()) {
			result += s.toString() + "\n";
		}
		return result;
	}
}
