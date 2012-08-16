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

    /**
     * Reads a string from a buffer.
     * 
     * @param buf The buffer to read from.
     * @return The product string which was builded by reading the buffer.
     */
    public static String readString(ChannelBuffer buf) {
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
    public static void writeString(ChannelBuffer buf, String string) {
	for (char c : string.toCharArray()) {
	    buf.writeByte(c);
	}
	buf.writeByte(10);
    }

    /**
     * Default private constructor to prevent instantiation by other classes.
     */
    private BufferUtils() { }

}