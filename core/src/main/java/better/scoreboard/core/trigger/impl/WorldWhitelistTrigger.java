package better.scoreboard.core.trigger.impl;

import better.scoreboard.core.BetterScoreboard;
import better.scoreboard.core.bridge.ConfigSection;
import better.scoreboard.core.trigger.Trigger;
import com.github.retrooper.packetevents.protocol.player.User;

import java.util.List;

public class WorldWhitelistTrigger extends Trigger {

    private final BetterScoreboard plugin;

    private List<String> worlds = null;

    public WorldWhitelistTrigger(BetterScoreboard plugin) {
        this.plugin = plugin;
    }

    /**
     * The player can run this trigger if they're in one of the configured worlds.
     */
    @Override
    public boolean canRun(User user) {
        String userWorld = plugin.getData().getWorld(user);
        for (String world : worlds) if (userWorld.equalsIgnoreCase(world)) return true;
        return false;
    }

    /**
     * Reload this trigger, grabbing the worlds from the configuration.
     */
    @Override
    public void load(ConfigSection config) {
        worlds = config.getList(String.class, "worlds");
    }
}
