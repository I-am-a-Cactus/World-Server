package jagex.runescape.net.codec;

import jagex.runescape.io.OutputBuffer;

import java.io.UnsupportedEncodingException;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 * RS2Encoder.java
 * 
 * @author Ryley M. Kimmel <ryley.kimmel@live.com>
 * @version 1.0
 * Aug 15, 2012
 */
public final class RS2Encoder extends OneToOneEncoder {

    /** The instance for this class */
    private static RS2Encoder instance;

    @Override
    protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
	if (!(msg instanceof OutputBuffer)) {
	    throw new UnsupportedEncodingException();
	}
	OutputBuffer out = (OutputBuffer) msg;
	if (out.isHeadless()) {
	    return ChannelBuffers.wrappedBuffer(out.getBuffer());
	}
	int length = out.getLength() + 1;
	switch (out.getType()) {
	case VARIABLE_BYTE:
	    length += 1;
	    break;
	case VARIABLE_SHORT:
	    length += 2;
	    break;
	default:
	    break;
	}
	ChannelBuffer buffer = ChannelBuffers.buffer(length);
	buffer.writeByte(out.getOpCode());
	switch (out.getType()) {
	case VARIABLE_BYTE:
	    buffer.writeByte(out.getLength());
	    break;
	case VARIABLE_SHORT:
	    buffer.writeShort(out.getLength());
	    break;
	default:
	    break;
	}
	buffer.writeBytes(out.getBuffer());
	return buffer;
    }

    /**
     * Gets and creates the instance
     * 
     * @return the instance
     */
    public static RS2Encoder getInstance() {
	if (instance == null) {
	    instance = new RS2Encoder();
	}
	return instance;
    }

}