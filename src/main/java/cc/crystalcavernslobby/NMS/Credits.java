package cc.crystalcavernslobby.NMS;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import cc.crystalcavernslobby.NMS.impl.Modern;
import org.bukkit.entity.Player;

public class Credits {
    private static Credits instance;
    private final ICredits nms;
    private Credits() {
        this.nms = new Modern();
    }
    public static void init() {
        getInstance();
    }
    private static Credits getInstance() {
        if (instance == null) {
            instance = new Credits();
        }
        return instance;
    }
    public static void showCredits(Player player) {
        getInstance().nms.showCredits(player);
    }
    public static void sendPacket(Player player, PacketContainer packet) {
        ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
    }
}
