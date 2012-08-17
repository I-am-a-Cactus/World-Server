package jagex.runescape.net;

import java.util.logging.Level;
import java.util.logging.Logger;

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

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
	logger.log(Level.INFO, "Channel connected from host: " + ctx.getChannel().getRemoteAddress());
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
	logger.log(Level.INFO, "Channel disconnected from host: " + ctx.getChannel().getRemoteAddress());
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) { }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) { }

}