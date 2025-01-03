package better.scoreboard.core.bridge;

import com.github.retrooper.packetevents.protocol.player.User;

public interface PlaceholderProcessor {

    String setPlaceholders(User user, String placeholder);
}
