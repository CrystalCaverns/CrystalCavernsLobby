package cc.crystalcavernslobby;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import java.util.regex.Pattern;

import static cc.crystalcavernslobby.CrystalCavernsLobby.plugin;

public class BlockPlace implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if (player.getItemInHand().getType() == Material.SHULKER_BOX && player.getItemInHand().getItemMeta().hasCustomModelData()) {
            Block block = e.getBlock();
            String rotation = String.valueOf(player.getLocation().getYaw());
            double x = block.getX() + 0.5;
            double y = block.getY();
            double z = block.getZ() + 0.5;
            block.setType(Material.BARRIER);
            if (!Pattern.matches("E-",rotation)) {
                rotation.replaceAll("E-","");
            }
            if (player.getItemInHand().getItemMeta().getCustomModelData() == 1) {
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "execute positioned " + x + " " + y + " " + z + " rotated " + rotation + " 0 run function animated_java:boss_crate/summon");
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "execute as @e[type=#animated_java:root,tag=aj.boss_crate.root,x=" + x + ",y=" + y + ",z=" + z + ",distance=..0001] run function animated_java:boss_crate/animations/open/play");
                BukkitScheduler scheduler = Bukkit.getScheduler();
                scheduler.runTaskLater(plugin, () -> {
                    Item item = (Item) block.getWorld().spawnEntity(new Location(block.getWorld(), x, y + 1, z), EntityType.DROPPED_ITEM);
                    ItemStack itemStack = new ItemStack(Material.GOLD_NUGGET);
                    item.setItemStack(itemStack);
                    item.setVelocity(new Vector(0, 0.3, 0));
                    }, 155L);
                scheduler.runTaskLater(plugin, () -> {
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "execute as @e[type=#animated_java:root,tag=aj.boss_crate.root,x=" + x + ",y=" + y + ",z=" + z + ",distance=..0001] run function animated_java:boss_crate/remove/this");
                    block.setType(Material.AIR);
                    }, 200L);
            }
            player.setItemInHand(new ItemStack(Material.AIR));
        }
    }
}
