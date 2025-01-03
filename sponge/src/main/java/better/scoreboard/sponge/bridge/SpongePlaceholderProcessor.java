package better.scoreboard.sponge.bridge;

import better.scoreboard.core.bridge.PlaceholderProcessor;
import better.scoreboard.sponge.BetterScoreboardSponge;
import com.github.retrooper.packetevents.protocol.player.User;

public class SpongePlaceholderProcessor implements PlaceholderProcessor {

    private BetterScoreboardSponge plugin;

    public SpongePlaceholderProcessor(BetterScoreboardSponge plugin) {
        this.plugin = plugin;
    }

    @Override
    public String setPlaceholders(User user, String placeholder) {
        return placeholder;
    }
}
