package better.scoreboard.core.listener;

import better.scoreboard.core.displayuser.DisplayUserManager;
import com.github.retrooper.packetevents.event.SimplePacketListenerAbstract;
import com.github.retrooper.packetevents.event.UserDisconnectEvent;
import com.github.retrooper.packetevents.event.simple.PacketLoginSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;

public class JoinLeaveListener extends SimplePacketListenerAbstract {

    @Override
    public void onPacketLoginSend(PacketLoginSendEvent event) {
        if (event.getPacketType() != PacketType.Login.Server.LOGIN_SUCCESS) return;
        DisplayUserManager.addDisplayUser(event.getUser());
    }

    @Override
    public void onUserDisconnect(UserDisconnectEvent event) {
        DisplayUserManager.removeDisplayUser(event.getUser());
    }
}
