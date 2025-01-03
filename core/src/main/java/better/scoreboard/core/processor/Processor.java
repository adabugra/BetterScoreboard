package better.scoreboard.core.processor;

import better.scoreboard.core.display.Display;
import better.scoreboard.core.display.DisplayManager;
import com.github.retrooper.packetevents.protocol.player.User;
import org.jetbrains.annotations.Nullable;

public abstract class Processor<E extends Display> {

    private final Class<E> classParam;
    protected final User user;

    protected E display;

    public Processor(Class<E> classParam, User user) {
        this.classParam = classParam;
        this.user = user;
    }

    @SuppressWarnings("unchecked")
    public void checkDisplays() {
        if (display != null && !display.canRun(user)) switchDisplay(null);
        boolean switched = false;

        for (Display d : DisplayManager.getDisplays()) {
            // Verify it's of the type we handle here and the player can run it.
            if (!classParam.isInstance(d)) continue;
            if (!d.canRun(user)) continue;

            // Set the current display to it if there is no current display.
            if (display == null) {
                switchDisplay((E) d);
                switched = true;
                continue;
            }

            // Only change the display if the new board is more important
            if (d.getWeight() <= display.getWeight()) continue;

            switchDisplay((E) d);
            switched = true;
        }

        if (switched) tick();
    }

    public abstract void switchDisplay(@Nullable E display);

    public abstract void tick();
}
