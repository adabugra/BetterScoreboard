package better.scoreboard.core.animation.impl;

import better.scoreboard.core.BetterScoreboard;
import better.scoreboard.core.animation.Animation;
import better.scoreboard.core.bridge.ConfigSection;
import org.jetbrains.annotations.Nullable;

public class HealthAnimation extends Animation<Double> {

    /**
     * Initialize the HealthAnimation, reading required data from the configuration.
     */
    public HealthAnimation(BetterScoreboard plugin, @Nullable ConfigSection config) {
        super(plugin, config);

        if (config == null) {
            animation.add(1.0);
            return;
        }

        animation.addAll(config.getList(Double.class, "health"));
        if (random) currentIndex = (int) (animation.size() * Math.random());
    }
}
