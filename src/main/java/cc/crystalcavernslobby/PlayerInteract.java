package cc.crystalcavernslobby;

import me.rockyhawk.commandpanels.CommandPanels;
import me.rockyhawk.commandpanels.api.CommandPanelsAPI;
import me.rockyhawk.commandpanels.api.Panel;
import me.rockyhawk.commandpanels.openpanelsmanager.PanelPosition;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.File;
import java.util.Objects;

import static cc.crystalcavernslobby.CrystalCavernsLobby.plugin;

public class PlayerInteract implements Listener {
    boolean disableInteractions = Boolean.parseBoolean(plugin.getConfig().getString("disableInteractions"));
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (disableInteractions) {
                Material clickedBlock = e.getClickedBlock().getType();
                if (clickedBlock != Material.SMITHING_TABLE && clickedBlock != Material.GRINDSTONE && clickedBlock != Material.STONECUTTER && clickedBlock != Material.ENCHANTING_TABLE) {
                    e.setCancelled(true);
                }
            }
        }
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
        }
        else {
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
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
        if (disableInteractions) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerDamageEntity(EntityDamageByEntityEvent e) {
        if (disableInteractions) {
            e.setCancelled(true);
        }
    }
}