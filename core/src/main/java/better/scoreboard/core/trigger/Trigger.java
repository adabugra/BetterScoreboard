package better.scoreboard.core.trigger;

import better.scoreboard.core.bridge.ConfigSection;
import com.github.retrooper.packetevents.protocol.player.User;

public abstract class Trigger implements Cloneable {

    /*
     * These methods must be implemented.
     */

    /**
     * This method should be used to determine whether the player passes this trigger.
     */
    public abstract boolean canRun(User user);

    /**
     * When this method is called when a reload is initiated.
     * The Trigger will be provided the configuration section of a Scoreboard and must load its settings.
     */
    public abstract void load(ConfigSection config);

    /*
     * These methods can be implemented.
     */

    /**
     * To create duplicates of the Trigger object we use the clone method.
     * Mutable objects are NOT fully copied and must be manually copied.
     */
    @Override
    public Trigger clone() {
        try {
            return (Trigger) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
