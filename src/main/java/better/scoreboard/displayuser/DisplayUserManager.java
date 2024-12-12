package better.scoreboard.displayuser;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a simple tracker for all DisplayUsers.
 *
 * @Author: am noah
 * @Since: 1.0.0
 * @Updated: 1.4.0
 */
public class DisplayUserManager {

    private final static Map<Player, DisplayUser> DISPLAY_USER_MAP = new HashMap<>();

    /*
     * Getters.
     */

    /**
     * Return the DisplayUser associated with the given player.
     */
    public static DisplayUser getDisplayUser(Player player) {
        return DISPLAY_USER_MAP.get(player);
    }

    /**
     * Return all DisplayUsers.
     */
    public static Collection<DisplayUser> getDisplayUsers() {
        return DISPLAY_USER_MAP.values();
    }

    /*
     * Map modifications.
     */

    /**
     * Add a DisplayUser associated with the given player object.
     */
    public static void addDisplayUser(Player player) {
        DISPLAY_USER_MAP.put(player, new DisplayUser(player));
    }

    /**
     * Remove the DisplayUser associated with the given player object.
     */
    public static void removeDisplayUser(Player player) {
        DISPLAY_USER_MAP.remove(player);
    }
}
