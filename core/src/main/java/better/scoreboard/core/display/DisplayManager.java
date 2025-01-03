package better.scoreboard.core.display;

import java.util.HashSet;
import java.util.Set;

public class DisplayManager {

    private final static Set<Display> DISPLAYS = new HashSet<>();

    public static Set<Display> getDisplays() {
        return DISPLAYS;
    }

    public static void addDisplay(Display display) {
        DISPLAYS.add(display);
    }

    public static void clear() {
        DISPLAYS.clear();
    }

    public static void removeDisplay(Display display) {
        DISPLAYS.remove(display);
    }
}
