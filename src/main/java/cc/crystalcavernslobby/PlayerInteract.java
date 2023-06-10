package cc.crystalcavernslobby;

import me.rockyhawk.commandpanels.CommandPanels;
import me.rockyhawk.commandpanels.api.CommandPanelsAPI;
import me.rockyhawk.commandpanels.api.Panel;
import me.rockyhawk.commandpanels.openpanelsmanager.PanelPosition;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.File;
import java.util.Objects;

public class PlayerInteract implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && (Tag.TRAPDOORS.isTagged(Objects.requireNonNull(e.getClickedBlock()).getType()))) {
            e.setCancelled(true);
        }
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && (Tag.BUTTONS.isTagged(Objects.requireNonNull(e.getClickedBlock()).getType()))) {
            e.setCancelled(true);
        }
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && (Tag.DOORS.isTagged(Objects.requireNonNull(e.getClickedBlock()).getType()))) {
            e.setCancelled(true);
        }
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && (Tag.FENCE_GATES.isTagged(Objects.requireNonNull(e.getClickedBlock()).getType()))) {
            e.setCancelled(true);
        }
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && (Material.BARREL.equals(Objects.requireNonNull(e.getClickedBlock()).getType()))) {
            e.setCancelled(true);
        }
        if (e.getAction().equals(Action.PHYSICAL) && (Material.REDSTONE_ORE.equals(Objects.requireNonNull(e.getClickedBlock()).getType()))) {
            Player p = e.getPlayer();
            if (p.getEyeLocation().getBlock().getType().equals(Material.STRUCTURE_VOID)) {
                CommandPanelsAPI api = CommandPanels.getAPI();
                if (!api.isPanelOpen(p)) {
                    File file = new File("/home/container/plugins/CommandPanels/panels/portal.yml");
                    Panel panel = new Panel(file, "portal");
                    panel.open(p, PanelPosition.Top);
                }
            }
        }
    }
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType().equals(EntityType.ITEM_FRAME)) {
            e.setCancelled(true);
        }
    }
}