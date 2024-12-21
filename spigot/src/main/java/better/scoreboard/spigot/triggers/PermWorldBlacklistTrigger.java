package better.scoreboard.spigot.triggers;

import better.scoreboard.core.bridge.ConfigSection;
import better.scoreboard.core.trigger.Trigger;
import com.github.retrooper.packetevents.protocol.player.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.util.List;

public class PermWorldBlacklistTrigger extends Trigger {

    private Permission permission = null;
    private List<String> worlds = null;

    /**
     * The player can run this trigger if they're outside the configured worlds and have the permission.
     */
    @Override
    public boolean canRun(User user) {
        Player player = Bukkit.getPlayer(user.getUUID());
        if (permission == null) return false;
        if (!player.hasPermission(permission)) return false;
        for (String world : worlds) if (player.getWorld().getName().equalsIgnoreCase(world)) return false;
        return true;
    }

    /**
     * Reload this trigger, grabbing the worlds and permission node from the configuration.
     */
    @Override
    public void load(ConfigSection config) {
        worlds = config.getList(String.class, "worlds");

        String node = config.getObject(String.class, "permission", "");

        if (node == null) {
            permission = null;
            return;
        }

        permission = new Permission(node, PermissionDefault.OP);
    }
}
