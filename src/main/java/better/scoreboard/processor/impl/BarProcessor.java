package better.scoreboard.processor.impl;

import better.scoreboard.display.impl.BarDisplay;
import better.scoreboard.processor.Processor;
import com.github.retrooper.packetevents.PacketEvents;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import sharkbyte.bossbar.core.SBBossBar;

/**
 * This class manages a boss bar for a DisplayUser.
 *
 * @Author: am noah
 * @Since: 1.4.0
 * @Updated: 1.4.0
 */
public class BarProcessor extends Processor<BarDisplay> {

    private final SBBossBar sbBossBar;

    /**
     * Initialize the BarProcessor object.
     */
    public BarProcessor(Player player) {
        super(BarDisplay.class, player);
        sbBossBar = SBBossBar.createBossBar(PacketEvents.getAPI().getPlayerManager().getUser(player));
    }

    /**
     * Switch the active Display to the given BarDisplay, also setting initial animation values.
     */
    @Override
    public void switchDisplay(@Nullable BarDisplay display) {
        if (display == null) {
            if (super.display == null) return;
            super.display = null;
            sbBossBar.destroy();
            return;
        }

        if (super.display == null) {
            sbBossBar.create();
        }

        super.display = display;
        sbBossBar.setColor(super.display.getColor().getAnimation());
        sbBossBar.setDivision(super.display.getDivision().getAnimation());
        sbBossBar.setHealth(super.display.getHealth().getAnimation());
        sbBossBar.setText(super.display.getText().getAnimation().getText(player));
    }

    /**
     * Update all the animations of the BarDisplay for the given player.
     */
    @Override
    public void tick() {
        if (display == null) return;

        if (display.getColor().isUpdateTick())
            sbBossBar.setColor(display.getColor().getAnimation());
        if (display.getDivision().isUpdateTick())
            sbBossBar.setDivision(display.getDivision().getAnimation());
        if (display.getHealth().isUpdateTick())
            sbBossBar.setHealth(display.getHealth().getAnimation());
        if (display.getText().isUpdateTick())
            sbBossBar.setText(display.getText().getAnimation().getText(player));

        sbBossBar.update();
    }
}