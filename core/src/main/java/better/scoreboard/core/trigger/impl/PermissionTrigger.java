package better.scoreboard.core.trigger.impl;

import better.scoreboard.core.BetterScoreboard;
import better.scoreboard.core.bridge.ConfigSection;
import better.scoreboard.core.trigger.Trigger;
import com.github.retrooper.packetevents.protocol.player.User;

public class PermissionTrigger extends Trigger {

    private final BetterScoreboard plugin;

    private String permission = null;

    public PermissionTrigger(BetterScoreboard plugin) {
        this.plugin = plugin;
    }

    /**
     * The player can run this trigger if they have the associated permission node.
     */
    @Override
    public boolean canRun(User user) {
        if (permission == null) return false;
        return plugin.getData().hasPermission(user, permission);
    }

    /**
     * Reload this trigger, grabbing the permission node from the configuration.
     */
    @Override
    public void load(ConfigSection config) {
        permission = config.getObject(String.class, "permission", "");
    }
}
