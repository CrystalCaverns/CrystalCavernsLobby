package cc.crystalcavernslobby;

import dev.sergiferry.playernpc.api.NPC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static cc.crystalcavernslobby.CrystalCavernsLobby.plugin;

public class NPCLook implements Listener {
    @EventHandler
    public void onNPCMove(NPC.Events.Move event) {
        String From = event.getFrom().toString();
        String To = event.getTo().toString();
        plugin.getLogger().info("NPC MOVED FROM " + From + " TO " + To);
    }
}
