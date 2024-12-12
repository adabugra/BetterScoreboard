package better.scoreboard.display;

import java.util.HashSet;
import java.util.Set;

/**
 * This is a simple tracker for all active Displays.
 *
 * @Author: am noah
 * @Since: 1.4.0
 * @Updated: 1.4.0
 */
public class DisplayManager {

    private final static Set<Display> DISPLAYS = new HashSet<>();

    /*
     * Getters.
     */

    /**
     * Return all active Displays.
     */
    public static Set<Display> getDisplays() {
        return DISPLAYS;
    }

    /*
     * Set modifications.
     */

    /**
     * Add a Display to the tracking set.
     */
    public static void addDisplay(Display display) {
        DISPLAYS.add(display);
    }

    /**
     * Remove all Display objects from the tracking set.
     * NOTE: This will NOT remove the boards from inside BoardUser objects.
     */
    public static void clear() {
        DISPLAYS.clear();
    }

    /**
     * Remove the inputted Display from the tracking set.
     * NOTE: This will NOT remove the board from inside BoardUser objects.
     */
    public static void removeDisplay(Display display) {
        DISPLAYS.remove(display);
    }
}