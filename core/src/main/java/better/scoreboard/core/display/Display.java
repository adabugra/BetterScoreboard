package better.scoreboard.core.display;

import better.scoreboard.core.bridge.ConfigSection;
import better.scoreboard.core.trigger.Trigger;
import better.scoreboard.core.trigger.TriggerManager;
import com.github.retrooper.packetevents.protocol.player.User;

public abstract class Display {

    protected final Trigger trigger;
    protected final int weight;

    /**
     * Initialize the Display object.
     */
    public Display(ConfigSection config) {
        weight = config.getObject(Integer.class, "weight");
        trigger = TriggerManager.retrieveTrigger(config.getObject(String.class, "trigger"));
        trigger.load(config);
    }

    /*
     * Getters.
     */

    /**
     * Return the board's weight.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Return whether the player is allowed to run this scoreboard.
     */
    public boolean canRun(User user) {
        return trigger.canRun(user);
    }

    /*
     * Methods to be implemented.
     */

    /**
     * Progress the Display by 1 tick.
     */
    public abstract void tick();
}
