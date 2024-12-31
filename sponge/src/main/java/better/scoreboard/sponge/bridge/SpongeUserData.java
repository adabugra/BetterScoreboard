package better.scoreboard.sponge.bridge;

import better.scoreboard.core.bridge.UserData;
import com.github.retrooper.packetevents.protocol.player.User;
import org.spongepowered.api.Game;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;

import java.util.Optional;

public class SpongeUserData implements UserData {

    private final Game game;

    public SpongeUserData(Game game) {
        this.game = game;
    }

    @Override
    public boolean hasPermission(User user, String... permission) {
        Optional<ServerPlayer> player = game.server().player(user.getUUID());
        if (player.isEmpty()) return false;
        for (String p : permission) if (!player.get().hasPermission(p)) return false;
        return true;
    }

    @Override
    public String getWorld(User user) {
        Optional<ServerPlayer> player = game.server().player(user.getUUID());
        return player.map(serverPlayer -> serverPlayer.world().key().asString()).orElse("");
    }
}
