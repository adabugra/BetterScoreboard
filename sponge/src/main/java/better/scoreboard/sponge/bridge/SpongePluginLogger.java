package better.scoreboard.sponge.bridge;

import better.scoreboard.core.bridge.PluginLogger;
import org.apache.logging.log4j.Logger;

public class SpongePluginLogger implements PluginLogger {

    private final Logger logger;

    public SpongePluginLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void logInfo(String message) {
        logger.info(message);
    }

    @Override
    public void logWarning(String message) {
        logger.warn(message);
    }
}
