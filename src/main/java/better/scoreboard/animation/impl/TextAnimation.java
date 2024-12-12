package better.scoreboard.animation.impl;

import better.scoreboard.BetterScoreboard;
import better.scoreboard.animation.Animation;
import better.scoreboard.display.Line;
import org.bukkit.configuration.ConfigurationSection;

import javax.annotation.Nullable;

/**
 * This represents a single line on the scoreboard. It can cycle through multiple lines of text and randomly select
 * a line if set to.
 *
 * @Author: am noah
 * @Since: 1.0.0
 * @Updated: 1.4.0
 */
public class TextAnimation extends Animation<Line> {

    /**
     * Initialize the Animation, reading required data from the configuration.
     */
    public TextAnimation(BetterScoreboard plugin, @Nullable ConfigurationSection config) {
        super(plugin, config);

        if (config == null) {
            animation.add(new Line(null));
            return;
        }

        for (String line : config.getStringList("text")) animation.add(new Line(line));
        if (random) currentIndex = (int) (animation.size() * Math.random());

        for (String line : config.getStringList("text")) System.out.println(line);
    }
}
