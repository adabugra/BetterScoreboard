package better.scoreboard.spigot.bridge;

import better.scoreboard.core.bridge.PluginLogger;
import better.scoreboard.spigot.BetterScoreboardSpigot;

public class SpigotPluginLogger implements PluginLogger {

    private BetterScoreboardSpigot plugin;

    public SpigotPluginLogger(BetterScoreboardSpigot plugin) {
        this.plugin = plugin;
    }

    @Override
    public void logInfo(String message) {
        plugin.getLogger().info(message);
    }

    @Override
    public void logWarning(String message) {
        plugin.getLogger().warning(message);
    }
}
