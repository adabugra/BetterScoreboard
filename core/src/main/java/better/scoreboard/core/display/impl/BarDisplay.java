package better.scoreboard.core.display.impl;

import better.scoreboard.core.BetterScoreboard;
import better.scoreboard.core.animation.impl.ColorAnimation;
import better.scoreboard.core.animation.impl.DivisionAnimation;
import better.scoreboard.core.animation.impl.HealthAnimation;
import better.scoreboard.core.animation.impl.TextAnimation;
import better.scoreboard.core.bridge.ConfigSection;
import better.scoreboard.core.display.Display;
import better.scoreboard.core.display.DisplayManager;

public class BarDisplay extends Display {

    private final ColorAnimation color;
    private final DivisionAnimation division;
    private final HealthAnimation health;
    private final TextAnimation text;

    /**
     * Initialize the BossBar, reading required data from the configuration.
     */
    public BarDisplay(BetterScoreboard plugin, ConfigSection config) {
        super(plugin, config);
        color = new ColorAnimation(plugin, config.getConfigSection("color"));
        division = new DivisionAnimation(plugin, config.getConfigSection("division"));
        health = new HealthAnimation(plugin, config.getConfigSection("health"));
        text = new TextAnimation(plugin, config.getConfigSection("text"));
    }

    /*
     * Getters.
     */

    /**
     * Return the color that should be displayed on the boss bar.
     */
    public ColorAnimation getColor() {
        return color;
    }

    /**
     * Return the division that the boss bar should be at.
     */
    public DivisionAnimation getDivision() {
        return division;
    }

    /**
     * Return the health that should be displayed on the boss bar.
     */
    public HealthAnimation getHealth() {
        return health;
    }

    /**
     * Return the text that should be displayed on the boss bar.
     */
    public TextAnimation getText() {
        return text;
    }

    /*
     * Functions.
     */

    /**
     * Load all BarDisplay objects from the config.yml file.
     */
    public static void load(BetterScoreboard plugin, ConfigSection config) {
        config = config.getConfigSection("boss-bars");
        if (config != null) {
            for (String bossBar : config.getChildren()) {
                ConfigSection section = config.getConfigSection(bossBar);

                if (section == null) {
                    plugin.getLogger().logWarning("Could not resolve boss bar named \"" + bossBar + "\" in config.yml!");
                    continue;
                }

                DisplayManager.addDisplay(new BarDisplay(plugin, section));
            }
        }
    }

    /**
     * Progress this boss bar 1 tick forward.
     */
    @Override
    public void tick() {
        color.tick();
        division.tick();
        health.tick();
        text.tick();
    }
}
