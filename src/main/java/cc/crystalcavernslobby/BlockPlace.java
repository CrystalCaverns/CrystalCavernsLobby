package cc.crystalcavernslobby;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

public class BlockPlace implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (e.getPlayer().getItemInHand().getType() == Material.SHULKER_BOX && e.getPlayer().getItemInHand().getItemMeta().hasCustomModelData()) {
            Block block = e.getBlock();
            ItemStack crate = new ItemStack(Material.GOLD_NUGGET);
            ItemMeta anim = crate.getItemMeta();
            if (e.getPlayer().getItemInHand().getItemMeta().getCustomModelData() == 1) {
                anim.setCustomModelData(10);
            }
            crate.setItemMeta(anim);
            ItemDisplay itemdisplay = (ItemDisplay) block.getWorld().spawnEntity(block.getLocation().add(0.5,0.5,0.5),EntityType.ITEM_DISPLAY);
            itemdisplay.setItemStack(crate);
            itemdisplay.setItemDisplayTransform(ItemDisplay.ItemDisplayTransform.HEAD);
            itemdisplay.setBrightness(new Display.Brightness(13, 13));
            block.setType(Material.BARRIER);
            e.getPlayer().setItemInHand(new ItemStack(Material.AIR));
            BukkitScheduler scheduler = Bukkit.getScheduler();
            scheduler.runTaskLater(CrystalCavernsLobby.getPlugin(), () -> {
                if (anim.getCustomModelData() == 10) {
                    anim.setCustomModelData(11);
                }
                crate.setItemMeta(anim);
                itemdisplay.setItemStack(crate);
            }, 20L);
            scheduler.runTaskLater(CrystalCavernsLobby.getPlugin(), () -> {
                if (anim.getCustomModelData() == 11) {
                    anim.setCustomModelData(12);
                }
                crate.setItemMeta(anim);
                itemdisplay.setItemStack(crate);
            }, 40L);
            scheduler.runTaskLater(CrystalCavernsLobby.getPlugin(), () -> {
                if (anim.getCustomModelData() == 12) {
                    anim.setCustomModelData(13);
                }
                crate.setItemMeta(anim);
                itemdisplay.setItemStack(crate);
            }, 60L);
            scheduler.runTaskLater(CrystalCavernsLobby.getPlugin(), () -> {
                if (anim.getCustomModelData() == 13) {
                    anim.setCustomModelData(14);
                }
                crate.setItemMeta(anim);
                itemdisplay.setItemStack(crate);
            }, 80L);
            scheduler.runTaskLater(CrystalCavernsLobby.getPlugin(), () -> {
                if (anim.getCustomModelData() == 14) {
                    Item item = (Item) block.getWorld().spawnEntity(block.getLocation().add(0.5,1.1,0.5),EntityType.DROPPED_ITEM);
                    item.setItemStack(new ItemStack(Material.GOLD_NUGGET));
                    item.setVelocity(new org.bukkit.util.Vector());
                    item.setGravity(false);
                    item.setGlowing(true);
                }
            }, 80L);
            scheduler.runTaskLater(CrystalCavernsLobby.getPlugin(), () -> {
                block.setType(Material.AIR);
                itemdisplay.remove();
            }, 100L);
        }
    }
}
