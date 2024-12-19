package better.scoreboard.core.displayuser;

import better.scoreboard.core.processor.Processor;
import better.scoreboard.core.processor.impl.BarProcessor;
import better.scoreboard.core.processor.impl.BoardProcessor;
import com.github.retrooper.packetevents.protocol.player.User;

import java.util.ArrayList;
import java.util.List;

public class DisplayUser {

    private final List<Processor> processors = new ArrayList<>();

    /**
     * Initialize the DisplayUser object.
     */
    public DisplayUser(User user) {
        processors.add(new BarProcessor(user));
        processors.add(new BoardProcessor(user));
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
