package jagex.runescape;

/**
 * Server.java
 * 
 * @author Ryley M. Kimmel <ryley.kimmel@live.com>
 * @version 1.0
 * Aug 14, 2012
 */
public final class Server {

    /**
     * Our entry point for this application
     * 
     * @param args the programs command line arguments
     */
    public static void main(String[] args) {
	try {
	    System.out.println(Constants.TAG);
	} catch (Throwable t) {
	    t.printStackTrace();
	}
    }

    /**
     * Default private constructor to prevent instantiation by other classes.
     */
    private Server() { }

}