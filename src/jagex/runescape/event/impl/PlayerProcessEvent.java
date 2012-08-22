package jagex.runescape.event.impl;

import jagex.runescape.event.Event;
import jagex.runescape.model.World;
import jagex.runescape.model.player.Player;

/**
 * PlayerProcessEvent.java
 * 
 * @author Ryley M. Kimmel <ryley.kimmel@live.com>
 * @version 1.0
 * Aug 22, 2012
 */
public final class PlayerProcessEvent extends Event {

    /**
     * The action that this event will perform
     */
    protected void execute() {
	try {
	    for (final Player player : World.getWorld().getPlayers()) {
		player.process();
	    }
	} catch (Throwable t)  {
	    for (Player player : World.getWorld().getPlayers()) {
		World.getWorld().unregister(player);
	    }
	    t.printStackTrace();
	}
    }

}