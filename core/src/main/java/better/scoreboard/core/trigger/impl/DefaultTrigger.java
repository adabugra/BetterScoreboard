package better.scoreboard.core.trigger.impl;

import better.scoreboard.core.bridge.ConfigSection;
import better.scoreboard.core.trigger.Trigger;
import com.github.retrooper.packetevents.protocol.player.User;

public class DefaultTrigger extends Trigger {

    /**
     * Any player can run this trigger.
     */
    @Override
    public boolean canRun(User user) {
        return true;
    }

    /**
     * Nothing is loaded by this trigger.
     */
    @Override
    public void load(ConfigSection config) {}
}
