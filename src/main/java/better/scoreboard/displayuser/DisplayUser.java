package better.scoreboard.displayuser;

import better.scoreboard.processor.Processor;
import better.scoreboard.processor.impl.BarProcessor;
import better.scoreboard.processor.impl.BoardProcessor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * This represents a player. It holds their scoreboard information and allows us to check what scoreboards they can use.
 *
 * @Author: am noah
 * @Since: 1.0.0
 * @Updated: 1.4.0
 */
public class DisplayUser {

    private final List<Processor> processors = new ArrayList<>();

    /**
     * Initialize the DisplayUser object.
     */
    public DisplayUser(Player player) {
        processors.add(new BarProcessor(player));
        processors.add(new BoardProcessor(player));
        checkDisplays();
    }

    /*
     * Functions.
     */

    /**
     * Check what boards the user is allowed to run, automatically switching to whichever has the highest weight.
     */
    public void checkDisplays() {
        for (Processor processor : processors) processor.checkDisplays();
    }


    public void clearDisplays() {
        for (Processor processor : processors) processor.switchDisplay(null);
    }

    /**
     * Handle a server tick, updating lines as necessary.
     */
    public void tick() {
        for (Processor processor : processors) processor.tick();
    }
}
