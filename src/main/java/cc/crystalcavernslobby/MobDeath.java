package cc.crystalcavernslobby;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;

import java.util.Collection;
import java.util.Random;

public class MobDeath implements Listener {
    @EventHandler
    public void onMobDeath(EntityDeathEvent e) {
        if (e.getEntity().getLastDamageCause() == null) {
            return;
        }
        if (e.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent) {
            Entity damageEntity = ((EntityDamageByEntityEvent) e.getEntity().getLastDamageCause()).getDamager();
            if (damageEntity instanceof Player player) {
                if (e.getEntity() instanceof Zombie) {
                    LootContext context =
                        new LootContext.Builder(e.getEntity().getLocation())
                            .killer(player)
                            .lootedEntity(e.getEntity())
                            .build();
                    final Collection<ItemStack> extraItems = new BossLoot().populateLoot(new Random(), context);
                    e.getDrops().clear();
                    e.getDrops().addAll(extraItems);
                }
            }
        }
    }
}
