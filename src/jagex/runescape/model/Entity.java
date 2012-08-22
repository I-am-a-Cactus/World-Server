package jagex.runescape.model;

import jagex.runescape.util.EntityList;

/**
 * Entity.java
 * 
 * @author Ryley M. Kimmel <ryley.kimmel@live.com>
 * @version 1.0
 * Aug 21, 2012
 */
public abstract class Entity {

    /** The entities enlisted index inside it's appropriate {@link EntityList} of the {@link World} which it is active. */
    private int index;

    /**
     * Constructs a new entity.
     * 
     * @param index The entity's enlisted index inside it's appropriate {@link EntityList} of the world which it is active.
     */
    public Entity(final int index) {
	this.index = index;
    }

    /**
     * This method has been overrided in entities to
     * 
     * return the entity's {@link #index} value.
     */
    @Override
    public int hashCode() {
	return index;
    }

    /**
     * This method has been overridden in entities to be based upon the entity's {@link #index}.
     * 
     * @return <code>true</code> if both entities {@link #hashCode}'s match, <code>false</code> otherwise.
     */
    @Override
    public boolean equals(final Object o) {
	if (!(o instanceof Entity)) {
	    return false;
	}
	return ((Entity) o).hashCode() == hashCode();
    }

    /**
     * Gets the entities index
     * 
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Sets the entities index
     * 
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * This method is used for processing entities every server cycle (600ms)
     * 
     * @throws Throwable if some error occurs
     */
    public abstract void process() throws Throwable;

}