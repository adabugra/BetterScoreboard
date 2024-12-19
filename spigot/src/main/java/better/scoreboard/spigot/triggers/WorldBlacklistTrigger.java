package better.scoreboard.spigot.triggers;

import better.scoreboard.core.bridge.ConfigSection;
import better.scoreboard.core.trigger.Trigger;
import com.github.retrooper.packetevents.protocol.player.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class WorldBlacklistTrigger extends Trigger {

    private List<String> worlds = null;

    /**
     * The player can run this trigger if they're outside the configured worlds.
     */
    @Override
    public boolean canRun(User user) {
        Player player = Bukkit.getPlayer(user.getUUID());
        for (String world : worlds) if (player.getWorld().getName().equalsIgnoreCase(world)) return false;
        return true;
    }

    /**
     * Reload this trigger, grabbing the worlds from the configuration.
     */
    @Override
    public void load(ConfigSection config) {
        worlds = config.getList(String.class, "worlds");
    }
}
