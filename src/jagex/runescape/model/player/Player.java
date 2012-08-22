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

    public Player(int index) {
	super(index);
    }

    @Override
    public void process() throws Throwable {
	System.out.println("Tick");
    }

}