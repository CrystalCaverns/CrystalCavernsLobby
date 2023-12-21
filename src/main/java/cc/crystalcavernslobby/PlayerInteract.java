package cc.crystalcavernslobby;

import me.rockyhawk.commandpanels.CommandPanels;
import me.rockyhawk.commandpanels.api.CommandPanelsAPI;
import me.rockyhawk.commandpanels.api.Panel;
import me.rockyhawk.commandpanels.openpanelsmanager.PanelPosition;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static cc.crystalcavernslobby.CrystalCavernsLobby.plugin;

public class PlayerInteract implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (Boolean.parseBoolean(plugin.getConfig().getString("disableInteractions"))) {
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
                        Component name = Component.text("Dungeon Run Entry Key")
                            .color(TextColor.color(15885614))
                            .decoration(TextDecoration.BOLD,true)
                            .decoration(TextDecoration.ITALIC,false);
                        Component lore1 = Component.text("Your one-way ticket to a Dungeon Run!")
                            .color(TextColor.color(13027014))
                            .decoration(TextDecoration.BOLD,false)
                            .decoration(TextDecoration.ITALIC,false);
                        Component lore2 = Component.text("Can be both-way if you're good enough.")
                            .color(TextColor.color(13027014))
                            .decoration(TextDecoration.BOLD,false)
                            .decoration(TextDecoration.ITALIC,false);
                        List<Component> lore = new ArrayList<>();
                        lore.add(lore1);
                        lore.add(lore2);
                        ItemStack item = new ItemStack(Material.KNOWLEDGE_BOOK);
                        ItemMeta meta = item.getItemMeta();
                        meta.displayName(name);
                        meta.lore(lore);
                        meta.setCustomModelData(302);
                        item.setItemMeta(meta);
                        panel.placeholders.addPlaceholder("open_dungeons", String.valueOf(p.getInventory().contains(item)));
                        panel.open(p, PanelPosition.Top);
                        p.addScoreboardTag("inside_portal");
                    }
                }
            }
        }
    }
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
        if (Boolean.parseBoolean(plugin.getConfig().getString("disableInteractions"))) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerInteractArmorStand(PlayerArmorStandManipulateEvent e) {
        if (Boolean.parseBoolean(plugin.getConfig().getString("disableInteractions"))) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerDamageEntity(EntityDamageByEntityEvent e) {
        if (Boolean.parseBoolean(plugin.getConfig().getString("disableInteractions"))) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent e) {
        if (Boolean.parseBoolean(plugin.getConfig().getString("disableInteractions"))) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent e) {
        if (Boolean.parseBoolean(plugin.getConfig().getString("disableInteractions"))) {
            e.setCancelled(true);
        }
    }
}