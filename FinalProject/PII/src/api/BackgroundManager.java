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
 * Manages saving and loading of Backgrounds
 * 
 * @author Xiao
 *
 */
public class BackgroundManager {
	/** A mapping of Background ID to backgrounds. */
	protected Map<Integer, Background> backgrounds;

	/** Creates a new empty BackgroundManager. */

	public BackgroundManager() {
		backgrounds = new HashMap<Integer, Background>();
		backgrounds.put(0, new Background());
	}

	/**
	 * Creates a new BackgroundManager for the Category whose information is
	 * stored in filePath
	 * 
	 * @param filePath the path of the file
	 * @throws FileNotFoundException if the file is not found
	 * @throws IOException
	 *             if filePath is not valid
	 * @throws ClassNotFoundException if the class if not found
	 */
	public BackgroundManager(String filePath) throws IOException, ClassNotFoundException, FileNotFoundException {
		backgrounds = new HashMap<Integer, Background>();
		backgrounds.put(0, new Background());

		File file = new File(filePath);
		if (file.exists()) {
			readFrom(file.getPath());
		} else
			file.createNewFile();
	}

	/**
	 * Adds record to this BackgroundManager.
	 * 
	 * @param record
	 *            a record to be added.
	 */

	public void add(Background record) {
		backgrounds.put(0, record);
	}

	/**
	 * Writes the backgrounds to file at filePath.
	 * 
	 * @param filePath
	 *            the file to write records to
	 * @throws IOException if file path is not valid
	 */

	public void saveTo(String filePath) throws IOException {
		OutputStream file = new FileOutputStream(filePath);
		OutputStream buffer = new BufferedOutputStream(file);
		ObjectOutput output = new ObjectOutputStream(buffer);
		output.writeObject(backgrounds);
		output.close();
	}

	/**
	 * Reads the backgrounds from file at a path.
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
			backgrounds = (Map<Integer, Background>) input.readObject();
		} catch (IOException ex) {
			return;
		}
	}

	/**
	 * Override the backgrounds in the file to String.
	 */

	@Override
	public String toString() {
		String result = "";
		for (Background b : backgrounds.values()) {
			result += b.toString() + "\n";
		}
		return result;
	}
}
