package better.scoreboard.display;

import better.scoreboard.trigger.Trigger;
import better.scoreboard.trigger.TriggerManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

/**
 * This class makes different types of Displays more generic, allowing them to be added with ease.
 * BetterScoreboard is expanding past just being a scoreboard plugin, now including a boss bar too.
 * If future types of Displays are to be added, that can now be done with just a few classes instead of many.
 *
 * @Author: am noah
 * @Since: 1.4.0
 * @Updated: 1.4.0
 */
public abstract class Display {

    protected final Trigger trigger;
    protected final int weight;

    /**
     * Initialize the Display object.
     */
    public Display(ConfigurationSection config) {
        weight = config.getInt("weight", 0);
        trigger = TriggerManager.retrieveTrigger(config.getString("trigger"));
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
    public boolean canRun(Player player) {
        return trigger.canRun(player);
    }

    /*
     * Methods to be implemented.
     */

    /**
     * Progress the Display by 1 tick.
     */
    public abstract void tick();
}
