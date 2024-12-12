package better.scoreboard.processor.impl;

import better.scoreboard.display.impl.BoardDisplay;
import better.scoreboard.processor.Processor;
import com.github.retrooper.packetevents.PacketEvents;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import sharkbyte.scoreboard.core.Scoreboard;

/**
 * This class manages a scoreboard for a DisplayUser.
 *
 * @Author: am noah
 * @Since: 1.4.0
 * @Updated: 1.4.0
 */
public class BoardProcessor extends Processor<BoardDisplay> {

    private final Scoreboard scoreboard;

    /**
     * Initialize the BoardProcessor object.
     */
    public BoardProcessor(Player player) {
        super(BoardDisplay.class, player);
        scoreboard = new Scoreboard(PacketEvents.getAPI().getPlayerManager().getUser(player), "BetterScoreboard");
    }

    /**
     * Switch the active Display to the given BoardDisplay, also setting initial animation values.
     */
    @Override
    public void switchDisplay(@Nullable BoardDisplay display) {
        System.out.println("Switching board!");

        if (display == null) {
            if (super.display == null) return;
            super.display = null;
            scoreboard.destroy();
            return;
        }

        if (super.display == null) {
            scoreboard.create();
        }

        super.display = display;
        scoreboard.setTitle(super.display.getTitle().getAnimation().getText(player));
        // Set active lines.
        for (int i = 0; i < super.display.getLineCount(); i++) {
            scoreboard.setLeftAlignedText(i,
                    super.display.getLeftText(i).isConditionalTrue(player) ?
                            super.display.getLeftText(i).getAnimation().getText(player) :
                            null
            );
            scoreboard.setRightAlignedText(i,
                    super.display.getRightText(i).isConditionalTrue(player) ?
                            super.display.getRightText(i).getAnimation().getText(player) :
                            null
            );
        }
        // Remove unused lines.
        for (int i = super.display.getLineCount(); i < 15; i++) {
            scoreboard.setLeftAlignedText(i, null);
            scoreboard.setRightAlignedText(i, null);
        }

        scoreboard.display();
    }

    /**
     * Update all the animations of the BoardDisplay for the given player.
     */
    @Override
    public void tick() {
        if (display == null) return;

        if (display.getTitle().isUpdateTick())
            scoreboard.setTitle(display.getTitle().getAnimation().getText(player));
        for (int i = 0; i < display.getLineCount(); i++) {
            if (display.getLeftText(i).isUpdateTick()) {
                if (!display.getLeftText(i).isConditionalTrue(player))
                    scoreboard.setLeftAlignedText(i, null);
                else
                    scoreboard.setLeftAlignedText(i, display.getLeftText(i).getAnimation().getText(player));
            }

            if (display.getRightText(i).isUpdateTick()) {
                if (!display.getRightText(i).isConditionalTrue(player))
                    scoreboard.setRightAlignedText(i, null);
                else
                    scoreboard.setRightAlignedText(i, display.getRightText(i).getAnimation().getText(player));
            }
        }

        scoreboard.update();
    }
}
