package jagex.runescape.handler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * HandlerManager.java
 * @author Ryley M. Kimmel <ryley.kimmel@live.com>
 * @version 1.0
 * Aug 14, 2012
 */
public final class HandlerManager extends ClassLoader {

    /** The instance for this class */
    private static HandlerManager instance;

    /**	This singleton logger instance can be used for logging various component messages to the console */
    private static final Logger logger = Logger.getLogger(HandlerManager.class.getSimpleName());

    /** This linked list contains all the class files within the bin directory */
    private final LinkedList<String> classes = new LinkedList<String>();

    /**
     * Gets and creates the instance for this class
     * 
     * @return the instance
     */
    public static HandlerManager getInstance() {
	if (instance == null) {
	    instance = new HandlerManager();
	}
	return instance;
    }

    /**
     * Loads all the classes located in the bin directory
     * 
     * @throws Throwable if an i/o error occurs
     */
    public void init() throws Throwable {
	logger.log(Level.INFO, "Loading content...");
	final long started = System.currentTimeMillis();
	final LinkedList<String> classDirectorys = new LinkedList<String>();
	final LinkedList<String> directorys = new LinkedList<String>();
	for (final File file : new File("./bin/").listFiles()) {
	    if (file.isFile()) {
		classDirectorys.add(file.getPath().replace(".class", "").replace(".\\bin\\", "").replace("\\", "."));
	    } else if (file.isDirectory()) {
		directorys.add(file.getPath().replace("\\", "/"));
	    }
	}
	while (!directorys.isEmpty()) {
	    final String directory = directorys.poll();
	    for (final File file : new File(directory).listFiles()) {
		if (file.isFile()) {
		    classDirectorys.add(file.getPath().replace(".class", "").replace(".\\bin\\", "").replace("\\", "."));
		} else if (file.isDirectory()) {
		    directorys.add(file.getPath().replace("\\", "/"));
		}
	    }
	}
	for (final String directory : classDirectorys) {
	    try {
		loadClass(directory.replace("./bin/", "").replace("/", ".")).newInstance();
	    } catch (final Exception e) {
		continue;
	    }
	}
	final long taken = System.currentTimeMillis() - started;
	logger.log(Level.INFO, "Loaded content in " + taken + "ms.");
    }

    /**
     * Reloads a class from the specified directory
     * 
     * @param path the path of the class
     * @throws ClassNotFoundException if the class were trying to reload doesn't exist.
     */
    public Class<?> reloadClass(final String path) throws ClassNotFoundException {
	if (!new File("./bin/" + path.replace(".", "/") + ".class").exists()) {
	    return loadClass(path);
	} else if (!classes.contains(path)) {
	    classes.add(path);
	    return loadClass(path);
	}
	try {
	    final URLConnection connection = new URL("file:./bin/" + path.replace(".", "/") + ".class").openConnection();
	    final InputStream input = connection.getInputStream();
	    final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	    int data = input.read();
	    while (data != -1) {
		buffer.write(data);
		data = input.read();
	    }
	    input.close();
	    final byte[] classData = buffer.toByteArray();
	    return defineClass(path, classData, 0, classData.length);
	} catch (final Exception e) {
	    e.printStackTrace();
	}
	return null;
    }

}