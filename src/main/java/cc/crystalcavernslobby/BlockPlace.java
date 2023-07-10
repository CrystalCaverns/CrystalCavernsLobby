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
import org.bukkit.util.Transformation;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

import static cc.crystalcavernslobby.CrystalCavernsLobby.plugin;

public class BlockPlace implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (e.getPlayer().getItemInHand().getType() == Material.SHULKER_BOX && e.getPlayer().getItemInHand().getItemMeta().hasCustomModelData()) {
            Block block = e.getBlock();
            block.setType(Material.BARRIER);
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
            e.getPlayer().setItemInHand(new ItemStack(Material.AIR));
            BukkitScheduler scheduler = Bukkit.getScheduler();
            scheduler.runTaskLater(plugin, () -> {
                itemdisplay.setInterpolationDuration(20);
                itemdisplay.setInterpolationDelay(-1);
                Transformation transformation = itemdisplay.getTransformation();
                transformation.getLeftRotation()
                    .set(new AxisAngle4f((float) Math.PI, new Vector3f(0,0,1)));
                itemdisplay.setTransformation(transformation);
            }, 10L);
            scheduler.runTaskLater(plugin, () -> {
                Item item = (Item) block.getWorld().spawnEntity(block.getLocation().add(0.5,1.1,0.5),EntityType.DROPPED_ITEM);
                item.setItemStack(new ItemStack(Material.GOLD_NUGGET));
                item.setVelocity(new org.bukkit.util.Vector());
                item.setGravity(false);
                item.setGlowing(true);
            }, 70L);
            scheduler.runTaskLater(plugin, () -> {
                block.setType(Material.AIR);
                itemdisplay.remove();
            }, 90L);
        }
    }
}
