package jagex.runescape.io;

import org.jboss.netty.buffer.ChannelBuffer;

import jagex.runescape.io.packet.Packet;

/**
 * InputBuffer.java
 * 
 * @author Ryley M. Kimmel <ryley.kimmel@live.com>
 * @version 1.0
 * Aug 14, 2012
 */
public final class InputBuffer extends Packet {

    /**
     * Constructs a new fixed raw input buffer.
     * 
     * @param buffer A specific payload buffer to use as the internal buffer.
     */
    public InputBuffer(ChannelBuffer buffer) {
	super(buffer);
    }

    /**
     * Constructs a new fixed input buffer
     * 
     * @param opCode The operation code which is used to associate the data piece with it's handler.
     * @param buffer A specific payload buffer to use as the internal buffer.
     */
    public InputBuffer(int opCode, ChannelBuffer buffer) {
	super(opCode, buffer);
    }

    /**
     * Constructs a new input buffer.
     * 
     * @param opCode The operation code which is used to associate the data piece with it's handler.
     * @param type The type of packet. This marks the additions needed and 
     * the type of recognition variables which need to be also 
     * attributed towards an outgoing buffer.
     * @param buffer A specific payload buffer to use as the internal buffer.
     */
    public InputBuffer(int opCode, Type type, ChannelBuffer buffer) {
	super(opCode, type, buffer);
    }

    /**
     * Constructs a new input buffer.
     * 
     * @param opCode The operation code which is used to associate the data piece with it's handler.
     * @param type The type of packet. This marks the additions needed and 
     * the type of recognition variables which need to be also 
     * attributed towards an outgoing buffer.
     */
    public InputBuffer(int opCode, Type type) {
	super(opCode, type);
    }

    /**
     * Constructs a new input buffer with a dynamic buffer.
     * 
     * @param opCode The operation code which is used to associate the data piece with it's handler.
     */
    public InputBuffer(int opCode) {
	super(opCode);
    }

    /**
     * Constructs a new headless fixed input buffer
     */
    public InputBuffer() {
	super(-1);
    }

    /**
     * Reads one <code>byte</code> from the buffer.
     * 
     * @return The next byte in the buffer.
     */
    public byte readByte() {
	return buffer.readByte();
    }

    /**
     * Reads and then unsigns one <code>byte</code> from the buffer.
     * 
     * @return The next unsigned byte in the buffer.
     */
    public short readUnsignedByte() {
	return buffer.readUnsignedByte();
    }

    /**
     * Reads one <code>short</code> from the buffer.
     * 
     * @return The next short in the buffer.
     */
    public short readShort() {
	return buffer.readShort();
    }

    /**
     * Reads and then unsigns one <code>short</code> from the buffer.
     * 
     * @return The next unsigned short in the buffer.
     */
    public int readUnsignedShort() {
	return buffer.readUnsignedShort();
    }

    /**
     * Reads one <code>short</code> special <tt>A</tt> from the buffer.
     * 
     * @return The next short value in the buffer <code>- 128</code>
     */
    public int readShortA() {
	return buffer.readShort() - 128;
    }

    /**
     * Reads one <code>short</code> special <tt>C</tt> from the buffer.
     * 
     * @return The next short value in the buffer inverted.
     */
    public int readShortC() {
	return - buffer.readShort();
    }

    /**
     * Reads one <code>short</code> special <tt>S</tt> from the buffer.
     * 
     * @return <code>128 -</code> the next short value in the buffer.
     */
    public int readShortS() {
	return 128 - buffer.readShort();
    }

    /**
     * Reads one <code>char</code> from the buffer.
     * 
     * @return The next character in the buffer.
     * @unsupported The functionality of this method is not supported
     * by the RuneScape protocol.
     */
    public char readChar() {
	return buffer.readChar();
    }

    /**
     * Reads one <code>int</code> from the buffer.
     * 
     * @return The next integer in the buffer.
     */
    public int readInt() {
	return buffer.readInt();
    }

    /**
     * Reads and then unsigns one <code>int</code> from the buffer.
     * 
     * @return The next unsigned integer in the buffer.
     */
    public long readUnsignedInt() {
	return buffer.readUnsignedInt();
    }

    /**
     * Reads one <code>long</code> from the buffer.
     * 
     * @return The next long in the buffer.
     */
    public long readLong() {
	return buffer.readLong();
    }

    /**
     * Reads one <code>double</code> from the buffer.
     * 
     * @return The next double in the buffer.
     * @unsupported The functionality of this method is not supported
     * by the RuneScape protocol.
     */
    public double readDouble() {
	return buffer.readDouble();
    }

    /**
     * Reads one <code>float</code> from the buffer.
     * 
     * @return The next float in the buffer.
     * @unsupported The functionality of this method is not supported
     * by the RuneScape protocol.
     */
    public float readFloat() {
	return buffer.readFloat();
    }

    /**
     * Checks whether or not the buffer is readable.
     * 
     * @return <code>true</code> if the buffer has greater
     * than <tt>0</tt> readable bytes.
     */
    public boolean isReadable() {
	return buffer.readable();
    }

    /**
     * Gets the number of readable bytes in the buffer.
     * 
     * @return The internal buffer's readable bytes.
     */
    public int readableBytes() {
	return buffer.readableBytes();
    }

}