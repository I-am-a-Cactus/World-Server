package jagex.runescape.net;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;

/**
 * PipelineFactoru.java
 * 
 * @author Ryley M. Kimmel <ryley.kimmel@live.com>
 * @version 1.0
 * Aug 17, 2012
 */
public final class PipelineFactory implements ChannelPipelineFactory {

    @Override
    public ChannelPipeline getPipeline() {
	final ChannelPipeline pipeline = Channels.pipeline();
	return pipeline;
    }

}