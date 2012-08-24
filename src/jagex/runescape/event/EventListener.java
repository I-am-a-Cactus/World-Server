package jagex.runescape.event;

import jagex.runescape.Constants;

import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * EventListener.java (Based on work by: Graham Edgecombe)
 * 
 * @author Ryley M. Kimmel <ryley.kimmel@live.com>
 * @version 1.0
 * Aug 20, 2012
 */
public final class EventListener implements Runnable {

    /** a @{link List} of all the events */
    private static final List<Event> events = new ArrayList<Event>();

    /** A queue of events that still need to be added. */
    private final Queue<Event> eventQueue = new ArrayDeque<Event>();

    /** a logging utility used to print messages to the console */
    private static final Logger logger = Logger.getLogger(EventListener.class.getSimpleName());

    /** The {@link ScheduledExecutorService} which schedules calls to the {@link #run()} method. */
    private final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

    /**
     * Loads and adds the events to their list
     * 
     * @throws Throwable if some error occurs
     */
    public static void init() throws Throwable {
	logger.log(Level.INFO, "Loading event listeners..");
	for (File clazz : new File("./bin/jagex/runescape/event/impl/").listFiles()) {
	    events.add((Event) Class.forName("jagex.runescape.event.impl." + clazz.getName().replace(".class", "")).newInstance());
	}
	logger.log(Level.INFO, "Loaded " + events.size() + " event listeners.");
    }

    /**
     * Handles an {@link Event} that has been added to the {@value #events} list
     * 
     * @throws Throwable if some error occurs during the execution of an event
     */
    public void handlesEvent(final Event event) throws Throwable {
	service.execute(new Runnable() {
	    @Override
	    public void run() {
		event.execute();
	    }
	});
	synchronized (eventQueue) {
	    eventQueue.add(event);
	}
    }

    /**
     * Creates and starts the event listener
     */
    public EventListener() {
	service.scheduleAtFixedRate(this, 0, Constants.CYCLE_PERIOD, TimeUnit.MILLISECONDS);
    }

    /**
     * This method is called every server cycle to handle necessary events that
     * have been queued and added into the list
     */
    public void run() {
	synchronized (eventQueue) {
	    Event event;
	    while ((event = eventQueue.poll()) != null) {
		events.add(event);
	    }
	}
	for (final Iterator<Event> it$ = events.iterator(); it$.hasNext();) {
	    final Event event = it$.next();
	    try {
		if (!event.tick()) {
		    it$.remove();
		}
	    } catch (Throwable t) {
		logger.log(Level.SEVERE, "Exception during event execution.", t);
	    }
	}
    }

}