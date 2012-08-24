package jagex.runescape.model;

import jagex.runescape.Constants;
import jagex.runescape.event.Event;
import jagex.runescape.event.EventListener;
import jagex.runescape.model.player.Player;
import jagex.runescape.util.EntityList;

/**
 * World.java
 * 
 * @author Ryley M. Kimmel <ryley.kimmel@live.com>
 * @version 1.0
 * Aug 22, 2012
 */
public final class World {

    /** Creates the event listener */
    private static final EventListener eventListener = new EventListener();

    /** An @{link EntityList} contains all the players */
    private static final EntityList<Player> players = new EntityList<Player>(Constants.MAXIMUM_WORLD_PLAYERS);

    /**
     * Instantiates the {@link World}
     * 
     * @throws Throwable if some error occurs
     */
    public static void init() throws Throwable {
	World.getWorld().submit(new Event() {
	    @Override
	    protected void execute() {
		final long started = System.currentTimeMillis();
		for (final Player player : players) {
		    if (player == null) {
			continue;
		    }
		    player.process();
		}
		final long taken = System.currentTimeMillis() - started;
		System.out.println("Taken: " + taken + "ms");
	    }
	});
    }

    /**
     * Handles an {@link Event}
     */
    public void submit(Event event) {
	try {
	    eventListener.handlesEvent(event);
	} catch (Throwable t) {
	    t.printStackTrace();
	}
    }

    /**
     * Registers a player
     * 
     * @param player the player were registering
     */
    public void register(Player player) {
	players.add(player);
    }

    /**
     * Unregisters a player
     * 
     * @param player the player were unregistering
     */
    public void unregister(Player player) {
	players.remove(player);
    }

    /**
     * Gets a player by their id, channel, ip, etc.
     * 
     * @param other the player were getting
     */
    public static Player getPlayer(Object other) {
	for (Player player : players) {
	    if (player.equals(other)) {
		return player;
	    }
	}
	return null;
    }

    /**
     * Gets the players from the {@code #players} entity list
     * 
     * @return the players
     */
    public static EntityList<Player> getPlayers() {
	return players;
    }

    /**
     * Creates access to this class
     * 
     * @return the world
     */
    public static World getWorld() {
	return new World();
    }

    /**
     * Default private constructor to prevent instantiation by other classes.
     */
    private World() { }

}