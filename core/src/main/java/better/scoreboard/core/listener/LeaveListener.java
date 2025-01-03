package better.scoreboard.core.listener;

import better.scoreboard.core.displayuser.DisplayUserManager;
import com.github.retrooper.packetevents.event.SimplePacketListenerAbstract;
import com.github.retrooper.packetevents.event.UserDisconnectEvent;

public class LeaveListener extends SimplePacketListenerAbstract {

    @Override
    public void onUserDisconnect(UserDisconnectEvent event) {
        DisplayUserManager.removeDisplayUser(event.getUser());
    }
}
