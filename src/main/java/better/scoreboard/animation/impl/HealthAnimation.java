package better.scoreboard.animation.impl;

import better.scoreboard.BetterScoreboard;
import better.scoreboard.animation.Animation;
import org.bukkit.configuration.ConfigurationSection;

import javax.annotation.Nullable;

/**
 * This represents the health of a boss bar. It can cycle through many different settings and randomly select settings
 * too if set to.
 *
 * @Author: am noah
 * @Since: 1.4.0
 * @Updated: 1.4.0
 */
public class HealthAnimation extends Animation<Float> {

    /**
     * Initialize the HealthAnimation, reading required data from the configuration.
     */
    public HealthAnimation(BetterScoreboard plugin, @Nullable ConfigurationSection config) {
        super(plugin, config);

        if (config == null) {
            animation.add(1.0F);
            return;
        }

        animation.addAll(config.getFloatList("health"));
        if (random) currentIndex = (int) (animation.size() * Math.random());
    }
}
