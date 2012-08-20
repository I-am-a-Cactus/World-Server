package jagex.runescape.net;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/**
 * ChannelHandler.java
 * 
 * @author Ryley M. Kimmel <ryley.kimmel@live.com>
 * @version 1.0
 * Aug 17, 2012
 */
public final class ChannelHandler extends SimpleChannelUpstreamHandler {

    /** A logging utility used to print messages to the console */
    private static final Logger logger = Logger.getLogger(ChannelHandler.class.getSimpleName());

    /**
     * Invoked when a {@link Channel} is open, bound to a local address, and connected to a remote address.
     * 
     * @param ctx the context of the {@code pipeline}
     * @param e the channels state
     */
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
	logger.log(Level.INFO, "Channel connected from host: " + ctx.getChannel().getRemoteAddress());
    }

    /**
     * Invoked when a {@link Channel} was disconnected from its remote peer.
     * 
     * @param ctx the context of the {@code pipeline}
     * @param e the channels state
     */
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
	logger.log(Level.INFO, "Channel disconnected from host: " + ctx.getChannel().getRemoteAddress());
    }

    /**
     * Invoked when a message object (e.g: {@link ChannelBuffer}) was received from a remote peer.
     * 
     * @param ctx the context of the {@code pipeline}
     * @param e represents the transmission or reception of a message
     */
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) { }

    /**
     * Invoked when an exception was raised by an I/O thread or a {@link ChannelHandler}.
     * 
     * @param ctx the context of the {@code pipeline}
     * @param e represents the notification of an exception raised by a {@link ChannelHandler}
     */
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) { }

}