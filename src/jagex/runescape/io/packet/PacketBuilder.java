package jagex.runescape.io.packet;

import jagex.runescape.io.packet.Packet.Type;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/**
 * PacketBuilder.java
 * @author Ryley M. Kimmel <ryley.kimmel@live.com>
 * @version 1.0
 * Aug 14, 2012
 */
public final class PacketBuilder {

    private static final int[] BIT_MASK = new int[32];

    static {
	for (int i = 0; i < BIT_MASK.length; ++i) {
	    BIT_MASK[i] = (1 << i) - 1;
	}
    }

    private int bitIndex;

    private final int opCode;

    private final ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();

    private final Type type;

    public PacketBuilder(final int opCode) {
	this(opCode, Type.FIXED);
    }

    public PacketBuilder(final int opCode, final Type type) {
	this.opCode = opCode;
	this.type = type;
    }

    public void writeBits(int numBits, final int value) {
	int bytePos = bitIndex >> 3;
	int bitOffset = 8 - (bitIndex & 7);
	bitIndex += numBits;

	int requiredSpace = (bytePos - buffer.writerIndex()) + 1;
	requiredSpace += (numBits + 7) / 8;
	buffer.ensureWritableBytes(requiredSpace);

	for (; numBits > bitOffset; bitOffset = 8) {
	    int tmp = buffer.getByte(bytePos);
	    tmp &= ~BIT_MASK[bitOffset];
	    tmp |= (value >> (numBits - bitOffset)) & BIT_MASK[bitOffset];
	    buffer.setByte(bytePos++, tmp);
	    numBits -= bitOffset;
	}
	if (numBits == bitOffset) {
	    int tmp = buffer.getByte(bytePos);
	    tmp &= ~BIT_MASK[bitOffset];
	    tmp |= value & BIT_MASK[bitOffset];
	    buffer.setByte(bytePos, tmp);
	} else {
	    int tmp = buffer.getByte(bytePos);
	    tmp &= ~(BIT_MASK[numBits] << (bitOffset - numBits));
	    tmp |= (value & BIT_MASK[numBits]) << (bitOffset - numBits);
	    buffer.setByte(bytePos, tmp);
	}
    }

    public void switchToBitAccess() {
	bitIndex = buffer.writerIndex() * 8;
    }

    public void switchToByteAccess() {
	buffer.writerIndex((bitIndex + 7) / 8);
    }

    public int getLength() {
	return buffer.writerIndex();
    }

    public Packet createPacket() {
	return new Packet(opCode, type, buffer);
    }

}