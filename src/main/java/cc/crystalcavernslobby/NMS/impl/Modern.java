package cc.crystalcavernslobby.NMS.impl;

import com.comphenix.protocol.PacketType.Play.Server;
import com.comphenix.protocol.events.PacketContainer;
import cc.crystalcavernslobby.CrystalCavernsLobby;
import cc.crystalcavernslobby.NMS.Credits;
import cc.crystalcavernslobby.NMS.ICredits;
import java.lang.reflect.Field;
import org.bukkit.entity.Player;

public class Modern implements ICredits {
    static Field field_spectatorMode = null;
    public void showCredits(Player player) {
        if (field_spectatorMode == null) {
            CrystalCavernsLobby.plugin.getLogger().severe("Failed to show credits screen to player. This server version is not compatible.");
        } else {
            PacketContainer packet = new PacketContainer(Server.GAME_STATE_CHANGE);
            try {
                Object spectatorMode = field_spectatorMode.get(null);
                packet.getModifier().write(0, spectatorMode);
                packet.getFloat().write(0, 1.0F);
                Credits.sendPacket(player, packet);
            } catch (IllegalAccessException var4) {
                CrystalCavernsLobby.plugin.getLogger().severe("Failed to show credits screen to player:");
                var4.printStackTrace();
            }
        }
    }
    static {
        try {
            field_spectatorMode = Server.GAME_STATE_CHANGE.getPacketClass().getDeclaredField("e");
        } catch (NoSuchFieldException var1) {
            CrystalCavernsLobby.plugin.getLogger().severe("Failed to initialize the plugin. This server version is not compatible.");
            var1.printStackTrace();
        }
    }
}
