package better.scoreboard.fabric;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BetterScoreboardFabric implements ModInitializer {
    public static final String MOD_ID = "better-scoreboard";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Hello, world!");
    }
}
