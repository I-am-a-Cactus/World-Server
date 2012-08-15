package jagex.runescape.util;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * BufferUtils.java
 * 
 * @author Ryley M. Kimmel <ryley.kimmel@live.com>
 * @version 1.0
 * Aug 15, 2012
 */
public final class BufferUtils {

    /** the instance for this class */
    private static BufferUtils instance;

    /**
     * Reads a string from a buffer.
     * 
     * @param buf The buffer to read from.
     * @return The product string which was builded by reading the buffer.
     */
    public String readString(ChannelBuffer buf) {
	StringBuilder bldr = new StringBuilder();
	byte b;
	while (buf.readable() && (b = buf.readByte()) != 10) {
	    bldr.append((char) b);
	}
	return bldr.toString();
    }

    /**
     * Writes a string object to a buffer.
     * 
     * @param buf The buffer to write to.
     * @param string The string to write.
     */
    public void writeString(ChannelBuffer buf, String string) {
	for (char c : string.toCharArray()) {
	    buf.writeByte(c);
	}
	buf.writeByte(10);
    }

    /**
     * Gets and creates the instance for this class
     * 
     * @return the instance
     */
    public static BufferUtils getInstance() {
	if (instance == null) {
	    instance = new BufferUtils();
	}
	return instance;
    }

    /**
     * Blank constructor to prevent this class from being created
     */
    private BufferUtils() { }

}