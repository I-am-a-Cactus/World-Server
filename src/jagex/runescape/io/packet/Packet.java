package jagex.runescape.io.packet;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/**
 * Packet.java
 * 
 * @author Ryley M. Kimmel <ryley.kimmel@live.com>
 * @version 1.0
 * Aug 14, 2012
 */
public class Packet {

    /**
     * @author Ryley M. Kimmel <ryley.kimmel@live.com>
     * @version 1.0
     * Aug 14, 2012
     */
    public static enum Type {

	/**
	 * This packet type is fixed.
	 * Nothing will change during encoding.
	 */
	FIXED,

	/**
	 * This packet type notifies that a variation of one 
	 * <code>byte</code> will be attributed during encoding.
	 */
	VARIABLE_BYTE,

	/**
	 * This packet type notifies that a variation of one 
	 * <code>short</code> will be attributed during encoding.
	 */
	VARIABLE_SHORT;

    }

    /** The operation code which is used to associate the data piece with its handler. */
    private final int opCode;

    /**
     * The type of packet. This marks the additions needed and 
     * the type of recognition variables which need to be also 
     * attributed towards an outgoing buffer.
     */
    private final Type type;

    /**
     * An instance of a dynamic buffer which is utilized for children
     * of a packet, so that it may only publicize methods of the buffer
     * which are specific to that child.
     */
    protected final ChannelBuffer buffer;

    /**
     * Constructs a new packet.
     * 
     * @param opCode The operation code which is used to associate the data piece with it's handler.
     * @param type The type of packet. This marks the additions needed and 
     * the type of recognition variables which need to be also 
     * attributed towards an outgoing buffer.
     * @param buffer A specific payload buffer to use as the packet's buffer.
     */
    public Packet(int opCode, Type type, ChannelBuffer buffer) {
	this.opCode = opCode;
	this.type = type;
	this.buffer = buffer;
    }

    /**
     * Constructs a new packet with a dynamic buffer.
     * 
     * @param opCode The operation code which is used to associate the data piece with it's handler.
     * @param type The type of packet. This marks the additions needed and 
     * the type of recognition variables which need to be also 
     * attributed towards an outgoing buffer.
     */
    public Packet(int opCode, Type type) {
	this(opCode, type, ChannelBuffers.dynamicBuffer());
    }

    /**
     * Constructs a new fixed packet with a dynamic buffer.
     * 
     * @param opCode The operation code which is used to associate the data piece with it's handler.
     */
    public Packet(int opCode) {
	this(opCode, Type.FIXED);
    }


    /**
     * Constructs a new fixed packet.
     * 
     * @param opCode The operation code which is used to associate the data piece with it's handler.
     * @param buffer A specific payload buffer to use as the packet's buffer.
     */
    public Packet(int opCode, ChannelBuffer buffer) {
	this(opCode, Type.FIXED, buffer);
    }

    /**
     * Constructs a new fixed headless packet.
     * 
     * @param buffer A specific payload buffer to use as the packet's buffer.
     */
    public Packet(ChannelBuffer buffer) {
	this(-1, buffer);
    }

    /**
     * Gets the packet's {@link #opCode}.
     * 
     * @return the opCode
     */
    public int getOpCode() {
	return opCode;
    }

    /**
     * Gets the packet's {@link #type}.
     * 
     * @return the type
     */
    public Type getType() {
	return type;
    }

    /**
     * Returns the backing <code>ChannelBuffer</code> of the packet.
     * 
     * @return the buffer
     */
    public ChannelBuffer getBuffer() {
	return buffer;
    }

    /**
     * Gets the number of readable bytes in the packet.
     * 
     * @return The length of the packet.
     */
    public int getLength() {
	return buffer.readableBytes();
    }

    /**
     * Checks if the packet is headless
     * 
     * @return <code>true</code> if the operation code is equal to <tt>-1</tt>.
     */
    public boolean isHeadless() {
	return opCode == -1;
    }

}