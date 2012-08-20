package jagex.runescape.net;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

/**
 * ConnectionHandler.java
 * 
 * @author Ryley M. Kimmel <ryley.kimmel@live.com>
 * @version 1.0
 * Aug 16, 2012
 */
public final class ConnectionHandler {

    /** A logging utility used to print messages to the console */
    private static final Logger logger = Logger.getLogger(ConnectionHandler.class.getSimpleName());

    /**
     * instantiates the {@code #bootstrap} and readies the server for incoming connections
     * 
     * @throws Throwable if some I/O error occurs
     */
    public static void init() throws Throwable {
	final SocketAddress address = new InetSocketAddress("localhost", 43594);
	final Executor pool = Executors.newCachedThreadPool();
	final ServerBootstrap bootstrap = new ServerBootstrap();

	bootstrap.setFactory(new NioServerSocketChannelFactory(pool, pool));
	bootstrap.setPipelineFactory(new PipelineFactory());
	bootstrap.setOption("localAddress", address);
	bootstrap.setOption("reuseAddress", true);
	bootstrap.setOption("child.tcpNoDelay", true);
	bootstrap.setOption("child.receiveBufferSize", 5000);
	bootstrap.bind();
	logger.log(Level.INFO, "RS2 Emulation is listening on address: " + address);
    }

    /**
     * Default private constructor to prevent instantiation by other classes.
     */
    private ConnectionHandler() { }

}