package better.scoreboard.core.display.impl;

import better.scoreboard.core.BetterScoreboard;
import better.scoreboard.core.animation.impl.TextAnimation;
import better.scoreboard.core.bridge.ConfigSection;
import better.scoreboard.core.display.Display;
import better.scoreboard.core.display.DisplayManager;

import java.util.ArrayList;
import java.util.List;

public class BoardDisplay extends Display {

    private final List<TextAnimation> leftAligned, rightAligned;
    private final TextAnimation title;

    public BoardDisplay(BetterScoreboard plugin, ConfigSection config) {
        super(plugin, config);

        leftAligned = new ArrayList<>();
        rightAligned = new ArrayList<>();

        title = new TextAnimation(plugin, config.getConfigSection("title"));

        for (int i = 1; i <= 15; i++) {
            ConfigSection section = config.getConfigSection("line" + i);
            if (section == null) break;
            leftAligned.add(new TextAnimation(plugin, section.getConfigSection("left-aligned")));
            rightAligned.add(new TextAnimation(plugin, section.getConfigSection("right-aligned")));
        }
    }

    public TextAnimation getLeftText(int index) {
        return leftAligned.get(index);
    }

    public int getLineCount() {
        return leftAligned.size();
    }

    public TextAnimation getRightText(int index) {
        return rightAligned.get(index);
    }

    public TextAnimation getTitle() {
        return title;
    }

    public static void load(BetterScoreboard plugin, ConfigSection config) {
        config = config.getConfigSection("scoreboards");
        if (config != null) {
            for (String scoreboard : config.getChildren()) {
                ConfigSection section = config.getConfigSection(scoreboard);

                if (section == null) {
                    plugin.getLogger().logWarning("Could not resolve scoreboard named \"" + scoreboard + "\" in config.yml!");
                    continue;
                }

                DisplayManager.addDisplay(new BoardDisplay(plugin, section));
            }
        }
    }

    @Override
    public void tick() {
        title.tick();
        for (TextAnimation line : leftAligned) line.tick();
        for (TextAnimation line : rightAligned) line.tick();
    }
}
