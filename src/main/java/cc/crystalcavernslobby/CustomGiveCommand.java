package cc.crystalcavernslobby;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static cc.crystalcavernslobby.CrystalCavernsLobby.plugin;

public class CustomGiveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly a player can execute this command!");
        }
        Player p = (Player) sender;
        if (args.length == 1) {
            FileConfiguration config = plugin.getConfig();
            if (config.contains("custom_items." + args[0])) {
                //Material
                ItemStack custom_item = new ItemStack(Material.getMaterial(config.get("custom_items." + args[0] + ".material").toString()));
                ItemMeta item_meta = custom_item.getItemMeta();
                //Name
                Component formatted_name = MiniMessage.miniMessage().deserialize(config.get("custom_items." + args[0] + ".name").toString());
                item_meta.displayName(formatted_name);
                //Lore
                if (config.contains("custom_items." + args[0] + ".lore")) {
                    List<String> lore = config.getStringList("custom_items." + args[0] + ".lore");
                    List<Component> formatted_lore = new ArrayList<>();
                    for (String lore_line : lore) {
                        formatted_lore.add(MiniMessage.miniMessage().deserialize(lore_line));
                    }
                    item_meta.lore(formatted_lore);
                }
                //CustomModelData
                if (config.contains("custom_items." + args[0] + ".customdata")) {
                    item_meta.setCustomModelData((Integer) config.get("custom_items." + args[0] + ".customdata"));
                }
                
            } else {
                p.sendMessage("§f\uDBF7\uDC35 §cCustom item not found.");
            }
        } else {
            p.sendMessage("§f\uDBCB\uDDAB §bCommand usage: §7/customgive [item]");
        } return false;
    }
}