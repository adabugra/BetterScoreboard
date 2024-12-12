package better.scoreboard;

import better.scoreboard.condition.Condition;
import better.scoreboard.condition.ConditionManager;
import better.scoreboard.display.Display;
import better.scoreboard.display.DisplayManager;
import better.scoreboard.display.impl.BarDisplay;
import better.scoreboard.display.impl.BoardDisplay;
import better.scoreboard.displayuser.DisplayUser;
import better.scoreboard.displayuser.DisplayUserManager;
import better.scoreboard.listener.PlayerUpdateListener;
import better.scoreboard.listener.ReloadListener;
import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.manager.server.ServerVersion;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

/**
 * Our main class.
 *
 * @Author: am noah
 * @Since: 1.0.0
 * @Updated: 1.4.0
 */
public class BetterScoreboard extends JavaPlugin {

    private static final int B_STATS_ID = 22862;

    private Metrics metrics;
    private BukkitTask task;

    @Override
    public void onEnable() {
        /*
         * We only support 1.20.3+.
         * We could support below with no code changes, but I don't feel like dealing with the limitations of
         * sharkbyte-scoreboard in older versions.
         */
        if (PacketEvents.getAPI().getServerManager().getVersion().isOlderThan(ServerVersion.V_1_20_3)) {
            getLogger().warning("You are running on an unsupported version of Minecraft!");
            getLogger().warning("Please update to 1.20.3 or above!");
            return;
        }

        // Begin b_stats
        metrics = new Metrics(this, B_STATS_ID);

        // Register all listeners.

        getServer().getPluginManager().registerEvents(new PlayerUpdateListener(), this);

        if (Bukkit.getPluginManager().getPlugin("BetterReload") != null) {
            getServer().getPluginManager().registerEvents(new ReloadListener(this), this);
        }

        // Load everything else.

        MessageUtil.setUsePAPI(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null);
        reload();
        task = getServer().getScheduler().runTaskTimerAsynchronously(this, () -> {
            for (Display display : DisplayManager.getDisplays()) display.tick();
            for (DisplayUser user : DisplayUserManager.getDisplayUsers()) user.tick();
        }, 0, 1);
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        metrics.shutdown();
        task.cancel();
        task = null;
    }

    /**
     * When this is called we will rebuild everything from the config!
     */
    public void reload() {
        saveDefaultConfig();
        reloadConfig();

        MessageUtil.setDateFormat(getConfig().getString("settings.date-format"));

        // Nuke and rebuild Conditions.
        ConditionManager.clear();
        Condition.load(this, getConfig());

        // Nuke and rebuild Displays.
        for (DisplayUser user : DisplayUserManager.getDisplayUsers()) user.clearDisplays();
        DisplayManager.clear();
        BarDisplay.load(this, getConfig());
        BoardDisplay.load(this, getConfig());

        // Register users back to displays.
        for (DisplayUser user : DisplayUserManager.getDisplayUsers()) user.checkDisplays();
    }
}