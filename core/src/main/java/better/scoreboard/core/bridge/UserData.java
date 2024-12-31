package better.scoreboard.core.bridge;

import com.github.retrooper.packetevents.protocol.player.User;

public interface UserData {

    boolean hasPermission(User user, String... permission);

    String getWorld(User user);
}
