package jagex.runescape.net.codec;

import jagex.runescape.Constants;
import jagex.runescape.io.packet.Packet;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

/**
 * RS2Encoder.java
 * 
 * @author Ryley M. Kimmel <ryley.kimmel@live.com>
 * @version 1.0
 * Aug 15, 2012
 */
public final class RS2Decoder extends FrameDecoder {

    /** the instance for this class */
    private static RS2Decoder instance;

    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) {
	if (!buffer.readable()) {
	    return null;
	}
	int opCode = buffer.readUnsignedByte();
	int size = Constants.PACKET_SIZES[opCode];
	if (size < 0) {
	    size = buffer.readableBytes();
	}
	if (buffer.readableBytes() < size) {
	    return null;
	}
	Packet packet = new Packet(opCode, buffer);
	return packet;
    }

    /**
     * gets and creates the instance for this class
     * 
     * @return the instance
     */
    public RS2Decoder getInstance() {
	if (instance == null) {
	    instance = new RS2Decoder();
	}
	return instance;
    }

}