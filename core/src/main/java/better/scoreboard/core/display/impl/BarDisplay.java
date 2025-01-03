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

    public BarDisplay(BetterScoreboard plugin, ConfigSection config) {
        super(plugin, config);
        color = new ColorAnimation(plugin, config.getConfigSection("color"));
        division = new DivisionAnimation(plugin, config.getConfigSection("division"));
        health = new HealthAnimation(plugin, config.getConfigSection("health"));
        text = new TextAnimation(plugin, config.getConfigSection("text"));
    }

    public ColorAnimation getColor() {
        return color;
    }

    public DivisionAnimation getDivision() {
        return division;
    }

    public HealthAnimation getHealth() {
        return health;
    }

    public TextAnimation getText() {
        return text;
    }

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

    @Override
    public void tick() {
        color.tick();
        division.tick();
        health.tick();
        text.tick();
    }
}
