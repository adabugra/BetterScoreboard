package better.scoreboard.core.processor.impl;

import better.scoreboard.core.display.impl.BarDisplay;
import better.scoreboard.core.processor.Processor;
import com.github.retrooper.packetevents.protocol.player.User;
import org.jetbrains.annotations.Nullable;
import sharkbyte.bossbar.core.SBBossBar;

public class BarProcessor extends Processor<BarDisplay> {

    private final SBBossBar sbBossBar;

    public BarProcessor(User user) {
        super(BarDisplay.class, user);
        sbBossBar = SBBossBar.createBossBar(user);
    }

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
        sbBossBar.setHealth(super.display.getHealth().getAnimation().floatValue());
        sbBossBar.setText(super.display.getText().getAnimation().getText(user));
    }

    @Override
    public void tick() {
        if (display == null) return;

        if (display.getColor().isUpdateTick())
            sbBossBar.setColor(display.getColor().getAnimation());
        if (display.getDivision().isUpdateTick())
            sbBossBar.setDivision(display.getDivision().getAnimation());
        if (display.getHealth().isUpdateTick())
            sbBossBar.setHealth(display.getHealth().getAnimation().floatValue());
        if (display.getText().isUpdateTick())
            sbBossBar.setText(display.getText().getAnimation().getText(user));

        sbBossBar.update();
    }
}
