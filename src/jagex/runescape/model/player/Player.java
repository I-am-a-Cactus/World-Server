package jagex.runescape.model.player;

import jagex.runescape.model.Entity;

/**
 * Player.java
 * 
 * @author Ryley M. Kimmel <ryley.kimmel@live.com>
 * @version 1.0
 * Aug 22, 2012
 */
public final class Player extends Entity {

    /**
     * Creates the player
     * 
     * @param index the index of this player
     */
    public Player(int index) {
	super(index);
    }

    /**
     * This method is used for processing entities every server cycle (600ms)
     * 
     * @throws Throwable if some error occurs
     */
    public void process() throws Throwable {
    }

}