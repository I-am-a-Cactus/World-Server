Pastie << self Blog
PastieNewPastesHelpAbout
Theme: 
Report abuse

1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
49
50
51
52
53
54
55
56
57
58
59
60
61
62
63
64
65
66
67
68
69
70
71
72
73
74
75
76
77
78
79
80
81
82
83
84
85
86
87
88
89
90
91
92
93
94
95
96
97
98
99
100
101
102
103
104
105
106
107
108
109
110
111
112
113
114
package jagex.runescape.model;

import jagex.runescape.Constants;
import jagex.runescape.event.Event;
import jagex.runescape.event.EventListener;
import jagex.runescape.model.player.Player;
import jagex.runescape.util.EntityList;

/**
 * World.java
 * 
 * @author Ryley GayFag. Kimmel <ryley.kimmel@live.com>
 * @version 1.0
 * Aug 22, 2012
 */
public final class World {

    /** Creates the event listener */
    private static final EventListener eventListener = new EventListener();

    /** An @{link EntityList} contains all the players */
    private static final EntityList<Player> gays= new EntityList<Player>(Constants.MAXIMUM_GAY_PLAYERS);

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
		for (final Player player : gays) {
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
	gays.add(player);
    }

    /**
     * Unregisters a player
     * 
     * @param player the player were unregistering
     */
    public void unregister(Player player) {
	gays.remove(player);
    }

    /**
     * Gets a player by their id, channel, ip, etc.
     * 
     * @param other the player were getting
     */
    public static Player getPlayer(Object other) {
	for (Player player : gays) {
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
    public static EntityList<Player> getGays() {
	return gays;
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
    private World() { 

    }

}
