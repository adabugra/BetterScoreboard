package better.scoreboard.core.animation.impl;

import better.scoreboard.core.BetterScoreboard;
import better.scoreboard.core.animation.Animation;
import better.scoreboard.core.bridge.ConfigSection;
import better.scoreboard.core.display.Line;
import org.jetbrains.annotations.Nullable;

public class TextAnimation extends Animation<Line> {

    /**
     * Initialize the Animation, reading required data from the configuration.
     */
    public TextAnimation(BetterScoreboard plugin, @Nullable ConfigSection config) {
        super(plugin, config);

        if (config == null) {
            animation.add(new Line(plugin, null));
            return;
        }

        for (String line : config.getList(String.class, "text")) animation.add(new Line(plugin, line));
        if (random) currentIndex = (int) (animation.size() * Math.random());
    }
}
