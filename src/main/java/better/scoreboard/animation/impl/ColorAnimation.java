package better.scoreboard.animation.impl;

import better.scoreboard.BetterScoreboard;
import better.scoreboard.animation.Animation;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.Nullable;

/**
 * This represents the color of a boss bar. It can cycle through many different settings and randomly select settings
 * too if set to.
 *
 * @Author: am noah
 * @Since: 1.4.0
 * @Updated: 1.4.0
 */
public class ColorAnimation extends Animation<BossBar.Color> {

    /**
     * Initialize the ColorAnimation, reading required data from the configuration.
     */
    public ColorAnimation(BetterScoreboard plugin, @Nullable ConfigurationSection config) {
        super(plugin, config);

        if (config == null) {
            animation.add(BossBar.Color.PURPLE);
            return;
        }

        for (String color : config.getStringList("color")) animation.add(BossBar.Color.NAMES.value(color.toLowerCase()));
        if (random) currentIndex = (int) (animation.size() * Math.random());
    }
}
