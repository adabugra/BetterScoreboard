package better.scoreboard.spigot.bridge;

import better.scoreboard.core.bridge.PlaceholderProcessor;
import better.scoreboard.spigot.BetterScoreboardSpigot;
import com.github.retrooper.packetevents.protocol.player.User;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;

public class SpigotPlaceholderProcessor implements PlaceholderProcessor {

    private final BetterScoreboardSpigot plugin;

    public SpigotPlaceholderProcessor(BetterScoreboardSpigot plugin) {
        this.plugin = plugin;
    }
    @Override
    public String setPlaceholders(User user, String placeholder) {
        if (plugin.isPAPIInstalled()) {
            placeholder = PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(user.getUUID()), placeholder);
        }

        return placeholder;
    }
}
