package cc.crystalcavernslobby;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTable;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static cc.crystalcavernslobby.CrystalCavernsLobby.plugin;

public class BossLoot implements LootTable {
    @NotNull
    @Override
    public Collection<ItemStack> populateLoot(@NotNull Random random, @NotNull LootContext lootContext) {
        final List<ItemStack> items = new ArrayList<>();
        if (random.nextDouble() <= (.05)) {
            int dropAmount = random.nextInt(3);
            items.add(new ItemStack(Material.LEATHER, dropAmount == 0 ? 1 : dropAmount));
        }
        ItemStack boss_crate = new ItemStack(Material.SHULKER_BOX);
        ItemMeta meta = boss_crate.getItemMeta();
        meta.setCustomModelData(1);
        Component name = Component.text("Sunstone Crate")
            .color(TextColor.color(14843976))
            .decoration(TextDecoration.BOLD,true)
            .decoration(TextDecoration.ITALIC,false);
        meta.displayName(name);
        boss_crate.setItemMeta(meta);
        items.add(boss_crate);
        return items;
    }
    @Override
    public void fillInventory(@NotNull Inventory inventory, @NotNull Random random, @NotNull LootContext lootContext) {
    }
    @NotNull
    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(plugin,"boss_loot");
    }
}