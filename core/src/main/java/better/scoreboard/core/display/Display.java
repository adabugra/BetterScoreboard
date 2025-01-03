package better.scoreboard.core.display;

import better.scoreboard.core.BetterScoreboard;
import better.scoreboard.core.bridge.ConfigSection;
import better.scoreboard.core.condition.Condition;
import com.github.retrooper.packetevents.protocol.player.User;

public abstract class Display {

    protected final Condition condition;
    protected final int weight;

    /**
     * Initialize the Display object.
     */
    public Display(BetterScoreboard plugin, ConfigSection config) {
        weight = config.getObject(Integer.class, "weight", 1);
        if (config.hasNode("criteria")) condition = new Condition(plugin, config);
        else condition = null;
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
        if (condition == null) return true;
        return condition.isTrue(user);
    }

    /*
     * Methods to be implemented.
     */

    /**
     * Progress the Display by 1 tick.
     */
    public abstract void tick();
}
