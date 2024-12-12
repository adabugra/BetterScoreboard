package better.scoreboard.display.impl;

import better.scoreboard.BetterScoreboard;
import better.scoreboard.animation.impl.ColorAnimation;
import better.scoreboard.animation.impl.DivisionAnimation;
import better.scoreboard.animation.impl.HealthAnimation;
import better.scoreboard.animation.impl.TextAnimation;
import better.scoreboard.display.Display;
import better.scoreboard.display.DisplayManager;
import org.bukkit.configuration.ConfigurationSection;

/**
 * This represents a boss bar. It holds information about the boss bar and its text.
 *
 * @Author: am noah
 * @Since: 1.4.0
 * @Updated: 1.4.0
 */
public class BarDisplay extends Display {

    private final ColorAnimation color;
    private final DivisionAnimation division;
    private final HealthAnimation health;
    private final TextAnimation text;

    /**
     * Initialize the BossBar, reading required data from the configuration.
     */
    public BarDisplay(BetterScoreboard plugin, ConfigurationSection config) {
        super(config);
        color = new ColorAnimation(plugin, config.getConfigurationSection("color"));
        division = new DivisionAnimation(plugin, config.getConfigurationSection("division"));
        health = new HealthAnimation(plugin, config.getConfigurationSection("health"));
        text = new TextAnimation(plugin, config.getConfigurationSection("text"));
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
    public static void load(BetterScoreboard plugin, ConfigurationSection config) {
        config = config.getConfigurationSection("boss-bars");
        if (config != null) {
            for (String bossBar : config.getKeys(false)) {
                ConfigurationSection section = config.getConfigurationSection(bossBar);

                if (section == null) {
                    plugin.getLogger().warning("Could not resolve boss bar named \"" + bossBar + "\" in config.yml!");
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
