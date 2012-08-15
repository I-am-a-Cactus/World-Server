package jagex.runescape.io;

import jagex.runescape.io.packet.Packet;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * OutputBuffer.java
 * @author Ryley M. Kimmel <ryley.kimmel@live.com>
 * @version 1.0
 * Aug 14, 2012
 */
public final class OutputBuffer extends Packet {

    /**
     * Constructs a new fixed raw output buffer.
     * 
     * @param buffer A specific payload buffer to use as the internal buffer.
     */
    public OutputBuffer(ChannelBuffer buffer) {
	super(buffer);
    }

    /**
     * Constructs a new fixed output buffer
     * 
     * @param opCode The operation code which is used to associate the data piece with it's handler.
     * @param buffer A specific payload buffer to use as the internal buffer.
     */
    public OutputBuffer(int opCode, ChannelBuffer buffer) {
	super(opCode, buffer);
    }

    /**
     * Constructs a new output buffer.
     * 
     * @param opCode The operation code which is used to associate the data piece with it's handler.
     * @param type The type of packet. This marks the additions needed and 
     * the type of recognition variables which need to be also 
     * attributed towards an outgoing buffer.
     * @param buffer A specific payload buffer to use as the internal buffer.
     */
    public OutputBuffer(int opCode, Type type, ChannelBuffer buffer) {
	super(opCode, type, buffer);
    }

    /**
     * Constructs a new output buffer.
     * 
     * @param opCode The operation code which is used to associate the data piece with it's handler.
     * @param type The type of packet. This marks the additions needed and 
     * the type of recognition variables which need to be also 
     * attributed towards an outgoing buffer.
     */
    public OutputBuffer(int opCode, Type type) {
	super(opCode, type);
    }

    /**
     * Constructs a new output buffer with a dynamic buffer.
     * 
     * @param opCode The operation code which is used to associate the data piece with it's handler.
     */
    public OutputBuffer(int opCode) {
	super(opCode);
    }

    /**
     * Constructs a new headless fixed output buffer.
     */
    public OutputBuffer() {
	super(-1);
    }

    public OutputBuffer writeByte(final int b) {
	buffer.writeByte(b);
	return this;
    }

    public OutputBuffer writeBytes(final byte... b) {
	buffer.writeBytes(b);
	return this;
    }

    public OutputBuffer writeBytes(final byte[] src, final int srcIndex, final int length) {
	buffer.writeBytes(src, srcIndex, length);
	return this;
    }

    public OutputBuffer writeByteA(final int b) {
	return writeByte(b - 128);
    }

    public OutputBuffer writeByteC(final int b) {
	return writeByte(- b);
    }

    public OutputBuffer writeByteS(final int b) {
	return writeByte(128 - b);
    }

    public OutputBuffer writeShort(final int v) {
	buffer.writeShort(v);
	return this;
    }

    public OutputBuffer writeShort(final int... v) {
	if (v.length < 1) {
	    throw new IllegalArgumentException("Must write at least one value.");
	}
	for (final int i : v) {
	    writeShort(i);
	}
	return this;
    }

    public OutputBuffer writeInt(final int v) {
	buffer.writeInt(v);
	return this;
    }

    public OutputBuffer writeInt(final int... v) {
	if (v.length < 1) {
	    throw new IllegalArgumentException("Must write at least one value.");
	}
	for (final int i : v) {
	    writeInt(i);
	}
	return this;
    }

    public OutputBuffer writeLong(final long v) {
	buffer.writeLong(v);
	return this;
    }

    public OutputBuffer writeLong(final long... v) {
	if (v.length < 1) {
	    throw new IllegalArgumentException("Must write at least one value.");
	}
	for (final long i : v) {
	    writeLong(i);
	}
	return this;
    }

}