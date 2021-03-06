package jagex.runescape.fs;

import jagex.runescape.dispatch.RequestWorkerPool;
import jagex.runescape.net.FileServerHandler;
import jagex.runescape.net.JagGrabPipelineFactory;
import jagex.runescape.net.NetworkConstants;
import jagex.runescape.net.OnDemandPipelineFactory;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timer;

/**
 * The core class of the file server.
 * @author Graham
 */
public final class FileServer {

    /**
     * The logger for this class.
     */
    private static final Logger logger = Logger.getLogger(FileServer.class.getName());

    /**
     * The executor service.
     */
    private static final ExecutorService service = Executors.newCachedThreadPool();

    /**
     * The request worker pool.
     */
    private static final RequestWorkerPool pool = new RequestWorkerPool();

    /**
     * The file server event handler.
     */
    private static final FileServerHandler handler = new FileServerHandler();

    /**
     * The timer used for idle checking.
     */
    private static final Timer timer = new HashedWheelTimer();

    /**
     * Starts the file server.
     * @throws Exception if an error occurs.
     */
    public static void init() throws Exception {
	logger.info("Starting workers...");
	pool.start();

	logger.info("Starting services...");
	start("JAGGRAB", new JagGrabPipelineFactory(handler, timer), NetworkConstants.JAGGRAB_PORT);
	start("ondemand", new OnDemandPipelineFactory(handler, timer), NetworkConstants.SERVICE_PORT);

	logger.info("Ready for connections.");
    }

    /**
     * Starts the specified service.
     * @param name The name of the service.
     * @param pipelineFactory The pipeline factory.
     * @param port The port.
     */
    private static void start(String name, ChannelPipelineFactory pipelineFactory, int port) {
	SocketAddress address = new InetSocketAddress(port);

	logger.info("Binding " + name + " service to " + address + "...");

	ServerBootstrap bootstrap = new ServerBootstrap();
	bootstrap.setFactory(new NioServerSocketChannelFactory(service, service));
	bootstrap.setPipelineFactory(pipelineFactory);
	bootstrap.bind(address);
    }

}
