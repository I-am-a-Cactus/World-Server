package jagex.runescape.model;

import jagex.runescape.Constants;
import jagex.runescape.event.Event;
import jagex.runescape.event.EventListener;
import jagex.runescape.event.impl.PlayerProcessEvent;
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
    private final EntityList<Player> players = new EntityList<Player>(Constants.MAXIMUM_WORLD_PLAYERS);

    /**
     * Instantiates the {@link World}
     * 
     * @throws Throwable if some error occurs
     */
    public static void init() throws Throwable {
	eventListener.handlesEvent(new PlayerProcessEvent());
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
    public Player getPlayer(Object other) {
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
    public EntityList<Player> getPlayers() {
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