package better.scoreboard.listener;

import better.scoreboard.displayuser.DisplayUserManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Simple listener used to either add players to our BoardUser map or check what board they can run.
 *
 * @Author: am noah
 * @Since: 1.0.0
 * @Updated: 1.4.0
 */
public class PlayerUpdateListener implements Listener {

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent event) {
        DisplayUserManager.getDisplayUser(event.getPlayer()).checkDisplays();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        DisplayUserManager.addDisplayUser(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        DisplayUserManager.removeDisplayUser(event.getPlayer());
    }
}
