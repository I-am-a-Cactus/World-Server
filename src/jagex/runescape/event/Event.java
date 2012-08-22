package jagex.runescape.event;

/**
 * Event.java (Based on work by: Graham Edgecombe)
 * 
 * @author Ryley M. Kimmel <ryley.kimmel@live.com>
 * @version 1.0
 * Aug 20, 2012
 */
public abstract class Event {

    /** the amount of 'ticks' we want the event to run for */
    private final int ticks;

    /** is our event running? */
    private boolean running = true;

    /** the count down before the event executes */
    private int countdown;

    /**
     * This method should be called by the scheduling class every cycle. It
     * updates the {@link #countdown} and calls the {@link #execute()} method if necessary.
     * 
     * @return the events state, whether it's running or not.
     */
    public boolean tick() {
	if (running && (--countdown == 0)) {
	    execute();
	    countdown = ticks;
	}
	return running;
    }

    /**
     * Stops our running event
     */
    public void stop() {
	running = false;
    }

    /**
     * Checks if the event is running.
     * 
     * @return {@code true} if so, {@code false} if not.
     */
    public boolean isRunning() {
	return running;
    }

    /**
     * Creates a single event
     */
    public Event() {
	this(1);
    }

    /**
     * Creates an event with a specified amount of tick
     * 
     * @param ticks the amount of ticks the event will run for
     */
    public Event(final int ticks) {
	this.ticks = ticks;
	countdown = ticks;
    }

    /**
     * The action that the event will perform
     */
    protected abstract void execute();

}