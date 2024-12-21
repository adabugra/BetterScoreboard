package better.scoreboard.spigot.triggers;

import better.scoreboard.core.bridge.ConfigSection;
import better.scoreboard.core.trigger.Trigger;
import com.github.retrooper.packetevents.protocol.player.User;
import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

public class PermissionTrigger extends Trigger {

    private Permission permission = null;

    /**
     * The player can run this trigger if they have the associated permission node.
     */
    @Override
    public boolean canRun(User user) {
        if (permission == null) return false;
        return Bukkit.getPlayer(user.getUUID()).hasPermission(permission);
    }

    /**
     * Reload this trigger, grabbing the permission node from the configuration.
     */
    @Override
    public void load(ConfigSection config) {
        String node = config.getObject(String.class, "permission", "");

        if (node == null) {
            permission = null;
            return;
        }

        permission = new Permission(node, PermissionDefault.OP);
    }
}
