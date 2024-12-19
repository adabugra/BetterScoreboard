package better.scoreboard.core.animation.impl;

import better.scoreboard.core.BetterScoreboard;
import better.scoreboard.core.animation.Animation;
import better.scoreboard.core.bridge.ConfigSection;
import net.kyori.adventure.bossbar.BossBar;
import org.jetbrains.annotations.Nullable;

public class ColorAnimation extends Animation<BossBar.Color> {

    /**
     * Initialize the ColorAnimation, reading required data from the configuration.
     */
    public ColorAnimation(BetterScoreboard plugin, @Nullable ConfigSection config) {
        super(plugin, config);

        if (config == null) {
            animation.add(BossBar.Color.PURPLE);
            return;
        }

        for (String color : config.getList(String.class, "color")) animation.add(BossBar.Color.NAMES.value(color.toLowerCase()));
        if (random) currentIndex = (int) (animation.size() * Math.random());
    }
}
