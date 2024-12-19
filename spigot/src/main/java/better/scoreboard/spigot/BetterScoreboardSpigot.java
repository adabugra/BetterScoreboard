package better.scoreboard.spigot;

import better.scoreboard.core.BetterScoreboard;
import better.scoreboard.core.placeholder.PlaceholderManager;
import better.scoreboard.core.trigger.TriggerManager;
import better.scoreboard.spigot.bridge.SpigotConfigSection;
import better.scoreboard.spigot.bridge.SpigotPlaceholderProcessor;
import better.scoreboard.spigot.bridge.SpigotPluginLogger;
import better.scoreboard.spigot.bridge.SpigotUserData;
import better.scoreboard.spigot.listener.PlayerUpdateListener;
import better.scoreboard.spigot.listener.ReloadListener;
import better.scoreboard.spigot.triggers.*;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class BetterScoreboardSpigot extends JavaPlugin {

    private static final int B_STATS_ID = 22862;

    // Core objects.
    private BetterScoreboard core;

    // Bukkit objects.
    private BukkitTask task;
    private Metrics metrics;

    private boolean papiInstalled;

    @Override
    public void onLoad() {
        core = new BetterScoreboard(
                new SpigotConfigSection(getConfig()),
                new SpigotPlaceholderProcessor(this),
                new SpigotPluginLogger(this),
                new SpigotUserData()
        );

        core.init();

        PlaceholderManager.registerPlaceholder("displayname", user -> Bukkit.getPlayer(user.getUUID()).getDisplayName());
        PlaceholderManager.registerPlaceholder("gamemode", user -> Bukkit.getPlayer(user.getUUID()).getGameMode().name());
        PlaceholderManager.registerPlaceholder("health", user -> String.valueOf(Bukkit.getPlayer(user.getUUID()).getHealth()));
        PlaceholderManager.registerPlaceholder("maxplayers", user -> String.valueOf(Bukkit.getMaxPlayers()));
        PlaceholderManager.registerPlaceholder("ping", user -> String.valueOf(Bukkit.getPlayer(user.getUUID()).getPing()));
        PlaceholderManager.registerPlaceholder("players", user -> String.valueOf(Bukkit.getOnlinePlayers().size()));
        PlaceholderManager.registerPlaceholder("world", user -> Bukkit.getPlayer(user.getUUID()).getWorld().getName());
        PlaceholderManager.registerPlaceholder("wordplayers", user -> String.valueOf(Bukkit.getPlayer(user.getUUID()).getWorld().getPlayers().size()));

        TriggerManager.registerTrigger("permission", new PermissionTrigger());
        TriggerManager.registerTrigger("world_whitelist", new WorldWhitelistTrigger());
        TriggerManager.registerTrigger("world_blacklist", new WorldBlacklistTrigger());
        TriggerManager.registerTrigger("world_whitelist_and_permission", new PermWorldWhitelistTrigger());
        TriggerManager.registerTrigger("world_blacklist_and_permission", new PermWorldBlacklistTrigger());
    }

    @Override
    public void onEnable() {
        core.enable();

        // Begin bStats.
        metrics = new Metrics(this, B_STATS_ID);

        // Check if PAPI is installed.
        papiInstalled = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;

        // Register all listeners.
        getServer().getPluginManager().registerEvents(new PlayerUpdateListener(), this);
        if (getServer().getPluginManager().getPlugin("BetterReload") != null) {
            getServer().getPluginManager().registerEvents(new ReloadListener(this), this);
        }

        load();
        task = getServer().getScheduler().runTaskTimerAsynchronously(this, () -> core.tick(), 0, 1);
    }

    @Override
    public void onDisable() {
        core.disable();

        HandlerList.unregisterAll(this);
        metrics.shutdown();
        task.cancel();
        task = null;
    }

    public void load() {
        saveDefaultConfig();
        reloadConfig();
        core.load();
    }

    public boolean isPAPIInstalled() {
        return papiInstalled;
    }
}
