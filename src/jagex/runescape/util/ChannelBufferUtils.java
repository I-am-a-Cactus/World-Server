package jagex.runescape.util;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * ChannelBufferUtils.java
 * 
 * @author Ryley M. Kimmel <ryley.kimmel@live.com>
 * @version 1.0
 * Aug 19, 2012
 */
public final class ChannelBufferUtils {

    /**
     * Reads a string from a buffer.
     * 
     * @param buffer The buffer to read from.
     * @param stringTerminator The terminator of a string.
     * @return The product string which was builded by reading the buffer.
     */
    public static String readString(final ChannelBuffer buffer, final int stringTerminator) {
	final StringBuilder bldr = new StringBuilder();
	byte b;
	while (buffer.readable() && ((b = buffer.readByte()) != stringTerminator)) {
	    bldr.append(b);
	}
	return bldr.toString();
    }

    /**
     * Writes a string object to a buffer.
     * 
     * @param buffer The buffer to write to.
     * @param stringTerminator The terminator of a string.
     * @param string The string to write.
     */
    public static void writeString(final ChannelBuffer buffer, final String string, final int stringTerminator) {
	for (final char c : string.toCharArray()) {
	    buffer.writeByte(c);
	}
	buffer.writeByte(stringTerminator);
    }

    /**
     * Default private constructor to prevent instantiation by other classes.
     */
    private ChannelBufferUtils() { }

}