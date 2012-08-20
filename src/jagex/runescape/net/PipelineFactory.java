package jagex.runescape.net;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;

/**
 * PipelineFactory.java
 * 
 * @author Ryley M. Kimmel <ryley.kimmel@live.com>
 * @version 1.0
 * Aug 17, 2012
 */
public final class PipelineFactory implements ChannelPipelineFactory {

    /**
     * Returns the {@link ChannelHandler} of the specified type in this pipeline.
     *
     * @return the handler of the specified handler type.
     */
    public ChannelPipeline getPipeline() {
	final ChannelPipeline pipeline = Channels.pipeline();
	pipeline.addLast("logic", new ChannelHandler());
	return pipeline;
    }

}