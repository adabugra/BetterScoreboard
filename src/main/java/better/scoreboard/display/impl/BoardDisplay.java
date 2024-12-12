package better.scoreboard.display.impl;

import better.scoreboard.BetterScoreboard;
import better.scoreboard.animation.impl.TextAnimation;
import better.scoreboard.display.Display;
import better.scoreboard.display.DisplayManager;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

/**
 * This represents an entire scoreboard. It holds information about the board and all of its text.
 *
 * @Author: am noah
 * @Since: 1.0.0
 * @Updated: 1.4.0
 */
public class BoardDisplay extends Display {

    private final List<TextAnimation> leftAligned, rightAligned;
    private final TextAnimation title;

    /**
     * Initialize the Board, reading required data from the configuration.
     */
    public BoardDisplay(BetterScoreboard plugin, ConfigurationSection config) {
        super(config);

        leftAligned = new ArrayList<>();
        rightAligned = new ArrayList<>();

        title = new TextAnimation(plugin, config.getConfigurationSection("title"));

        for (int i = 1; i <= 15; i++) {
            ConfigurationSection section = config.getConfigurationSection("line" + i);
            if (section == null) break;
            leftAligned.add(new TextAnimation(plugin, section.getConfigurationSection("left-aligned")));
            rightAligned.add(new TextAnimation(plugin, section.getConfigurationSection("right-aligned")));
        }
    }

    /*
     * Getters.
     */

    /**
     * Return the text that should be displayed at the given index.
     */
    public TextAnimation getLeftText(int index) {
        return leftAligned.get(index);
    }

    /**
     * Return the total amount of lines on this board.
     */
    public int getLineCount() {
        return leftAligned.size();
    }

    public TextAnimation getRightText(int index) {
        return rightAligned.get(index);
    }

    /**
     * Return the text that should be displayed as the title.
     */
    public TextAnimation getTitle() {
        return title;
    }

    /*
     * Functions.
     */

    /**
     * Load all BoardDisplay objects from the config.yml file.
     */
    public static void load(BetterScoreboard plugin, ConfigurationSection config) {
        config = config.getConfigurationSection("scoreboards");
        if (config != null) {
            for (String scoreboard : config.getKeys(false)) {
                ConfigurationSection section = config.getConfigurationSection(scoreboard);

                if (section == null) {
                    plugin.getLogger().warning("Could not resolve scoreboard named \"" + scoreboard + "\" in config.yml!");
                    continue;
                }

                DisplayManager.addDisplay(new BoardDisplay(plugin, section));
            }
        }
    }

    /**
     * Progress this scoreboard 1 tick forward.
     */
    @Override
    public void tick() {
        title.tick();
        for (TextAnimation line : leftAligned) line.tick();
        for (TextAnimation line : rightAligned) line.tick();
    }
}
