package better.scoreboard.processor;

import better.scoreboard.display.Display;
import better.scoreboard.display.DisplayManager;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

/**
 * A Processor handles a Display object for a DisplayUser. It makes the process of adding new Display types more
 * automated as there's just a Manager to implement.
 *
 * @Author: am noah
 * @Since: 1.4.0
 * @Updated: 1.4.0
 */
public abstract class Processor<E extends Display> {

    private final Class<E> classParam;
    protected final Player player;

    protected E display;

    /**
     * Initialize the Processor class.
     */
    public Processor(Class<E> classParam, Player player) {
        this.classParam = classParam;
        this.player = player;
    }

    /**
     * Check whether there are any Displays of the type that this Manager handles that should be actively displayed.
     */
    @SuppressWarnings("unchecked")
    public void checkDisplays() {
        if (display != null && !display.canRun(player)) switchDisplay(null);
        boolean switched = false;

        for (Display d : DisplayManager.getDisplays()) {
            // Verify it's of the type we handle here and the player can run it.
            if (!classParam.isInstance(d)) continue;
            if (!d.canRun(player)) continue;

            // Set the current display to it if there is no current display.
            if (display == null) {
                switchDisplay((E) d);
                switched = true;
                continue;
            }

            // Only change the
            if (d.getWeight() <= display.getWeight()) continue;

            switchDisplay((E) d);
            switched = true;
        }

        if (switched) tick();
    }

    /*
     * Methods to implement.
     */

    /**
     * Handle how the Processor should switch the active Display.
     * If null, then there should be no active Display.
     */
    public abstract void switchDisplay(@Nullable E display);

    /**
     * Handle what should happen when a server tick occurs.
     */
    public abstract void tick();
}
