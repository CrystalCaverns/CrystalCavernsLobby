package cc.crystalcavernslobby;

import me.rockyhawk.commandpanels.CommandPanels;
import me.rockyhawk.commandpanels.api.CommandPanelsAPI;
import me.rockyhawk.commandpanels.api.Panel;
import me.rockyhawk.commandpanels.openpanelsmanager.PanelPosition;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.File;
import java.util.Objects;

public class PlayerInteract implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
//        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
//            if (Tag.TRAPDOORS.isTagged(Objects.requireNonNull(e.getClickedBlock()).getType())) {
//                e.setCancelled(true);
//            }
//            if (Tag.BUTTONS.isTagged(Objects.requireNonNull(e.getClickedBlock()).getType())) {
//                e.setCancelled(true);
//            }
//            if (Tag.DOORS.isTagged(Objects.requireNonNull(e.getClickedBlock()).getType())) {
//                e.setCancelled(true);
//            }
//            if (Tag.FENCE_GATES.isTagged(Objects.requireNonNull(e.getClickedBlock()).getType())) {
//                e.setCancelled(true);
//            }
//            if (Material.BARREL.equals(Objects.requireNonNull(e.getClickedBlock()).getType())) {
//                e.setCancelled(true);
//            }
//        }
        if (e.getAction() == Action.PHYSICAL) {
            if (Material.REDSTONE_ORE.equals(Objects.requireNonNull(e.getClickedBlock()).getType())) {
                Player p = e.getPlayer();
                if (p.getEyeLocation().getBlock().getType().equals(Material.STRUCTURE_VOID)) {
                    CommandPanelsAPI api = CommandPanels.getAPI();
                    if (!api.isPanelOpen(p) && !p.getScoreboardTags().contains("inside_portal")) {
                        File file = new File("/home/container/plugins/CommandPanels/panels/portal.yml");
                        Panel panel = new Panel(file, "portal");
                        panel.open(p, PanelPosition.Top);
                        p.addScoreboardTag("inside_portal");
                    }
                }
            }
        } else {
            if (e.hasItem()) {
                if (e.getMaterial() == Material.GHAST_TEAR) {
                    if (e.getItem().hasItemMeta()) {
                        if (e.getItem().getItemMeta().hasCustomModelData()) {
                            if (e.getItem().getItemMeta().getCustomModelData() == 5) {
                                CommandPanelsAPI api = CommandPanels.getAPI();
                                Player p = e.getPlayer();
                                if (!api.isPanelOpen(p)) {
                                    File file = new File("/home/container/plugins/CommandPanels/panels/menu.yml");
                                    Panel panel = new Panel(file, "menu");
                                    panel.open(p, PanelPosition.Top);
                                    e.setCancelled(true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
//    @EventHandler
//    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
//        if (e.getRightClicked().getType().equals(EntityType.ITEM_FRAME)) {
//            ItemFrame itemframe = (ItemFrame) e.getRightClicked();
//            if (!(itemframe.getItem().getType().equals(Material.AIR))) {
//                e.setCancelled(true);
//            }
//        }
//        if (e.getRightClicked().getType().equals(EntityType.GLOW_ITEM_FRAME)) {
//            GlowItemFrame glowitemframe = (GlowItemFrame) e.getRightClicked();
//            if (!(glowitemframe.getItem().getType().equals(Material.AIR))) {
//                e.setCancelled(true);
//            }
//        }
//    }