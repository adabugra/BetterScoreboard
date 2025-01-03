package better.scoreboard.spigot.bridge;

import better.scoreboard.core.bridge.UserData;
import com.github.retrooper.packetevents.protocol.player.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SpigotUserData implements UserData {

    @Override
    public boolean hasPermission(User user, String... permission) {
        Player player = Bukkit.getPlayer(user.getUUID());
        if (player == null) return false;
        if (player.isOp()) return true;

        for (String string : permission) {
            if (player.hasPermission(string)) return true;
        }

        return false;
    }
}
