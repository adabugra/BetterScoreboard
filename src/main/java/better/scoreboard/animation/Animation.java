package better.scoreboard.animation;

import better.scoreboard.BetterScoreboard;
import better.scoreboard.condition.Condition;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * After the addition of boss bars into the plugin, this class was made more generic as to allow different types of
 * animations to exist. Now, this class serves as a structure for different types of animations to be implemented on
 * top of.
 *
 * @Author: am noah
 * @Since: 1.0.0
 * @Updated: 1.4.0
 */
public abstract class Animation<E> {

    protected final List<E> animation = new ArrayList<>();
    protected final int animationSpeed;
    protected final boolean random;
    protected final Condition condition;

    protected int currentIndex, currentTick;
    protected boolean updateTick;

    /**
     * Initialize the Animation, reading required data from the configuration.
     */
    public Animation(BetterScoreboard plugin, @Nullable ConfigurationSection config) {
        currentIndex = currentTick = 0;

        if (config == null) {
            animationSpeed = -1;
            random = false;
            condition = null;
            return;
        }

        random = config.getBoolean("random", false);
        animationSpeed = config.getInt("speed", 1);

        if (animationSpeed < 0) updateTick = true;

        if (config.get("criteria") != null) condition = new Condition(plugin, config);
        else condition = null;
    }

    /*
     * Getters.
     */

    public E getAnimation() {
        return animation.get(currentIndex);
    }

    /**
     * Return whether this line should display according to
     */
    public boolean isConditionalTrue(Player player) {
        if (condition == null) return true;
        return condition.isTrue(player);
    }

    /**
     * Return whether the current tick is an animation update tick.
     */
    public boolean isUpdateTick() {
        return updateTick;
    }

    /*
     * Methods.
     */

    public void tick() {
        // Should not proceed if it's a static board.
        if (animationSpeed < 0) return;

        // Only progress further once the animation's refresh rate has elapsed.
        currentTick++;
        updateTick = false;
        if (currentTick < animationSpeed) return;
        currentTick = 0;
        updateTick = true;

        // Randomly select an index if that's chosen in the config.
        if (random) {
            currentIndex = (int) (Math.random() * animation.size());
            return;
        }

        // Otherwise progress the current index.
        currentIndex++;
        if (currentIndex == animation.size()) currentIndex = 0;
    }
}
