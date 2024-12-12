package better.scoreboard.animation.impl;

import better.scoreboard.BetterScoreboard;
import better.scoreboard.animation.Animation;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.Nullable;

/**
 * This represents the division of a boss bar. It can cycle through many different settings and randomly select settings
 * too if set to.
 *
 * @Author: am noah
 * @Since: 1.4.0
 * @Updated: 1.4.0
 */
public class DivisionAnimation extends Animation<BossBar.Overlay> {

    /**
     * Initialize the DivisionAnimation, reading required data from the configuration.
     */
    public DivisionAnimation(BetterScoreboard plugin, @Nullable ConfigurationSection config) {
        super(plugin, config);

        if (config == null) {
            animation.add(BossBar.Overlay.PROGRESS);
            return;
        }

        for (int division : config.getIntegerList("division")) {
            switch (division) {
                case 20:
                    animation.add(BossBar.Overlay.NOTCHED_20);
                    break;
                case 12:
                    animation.add(BossBar.Overlay.NOTCHED_12);
                    break;
                case 10:
                    animation.add(BossBar.Overlay.NOTCHED_10);
                    break;
                case 6:
                    animation.add(BossBar.Overlay.NOTCHED_6);
                    break;
                default:
                    animation.add(BossBar.Overlay.PROGRESS);
            }
        }

        if (random) currentIndex = (int) (animation.size() * Math.random());
    }
}
