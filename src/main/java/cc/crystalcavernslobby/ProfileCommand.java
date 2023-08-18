package cc.crystalcavernslobby;

import me.clip.placeholderapi.PlaceholderAPI;
import me.rockyhawk.commandpanels.api.Panel;
import me.rockyhawk.commandpanels.classresources.placeholders.PanelPlaceholders;
import me.rockyhawk.commandpanels.openpanelsmanager.PanelPosition;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.jetbrains.annotations.NotNull;

import java.io.File;

import static org.bukkit.Bukkit.getOfflinePlayer;
import static org.bukkit.Bukkit.getPlayer;

public class ProfileCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly a player can execute this command!");
        }
        Player p = (Player) sender;
        if (args.length <= 1) {
            String target_player_name = "";
            if (args.length == 0) {
                target_player_name = p.getName();
            } else {
                OfflinePlayer offlinePlayer = getOfflinePlayer(args[0]);
                if (offlinePlayer.hasPlayedBefore()) {
                    if (offlinePlayer.isOnline()) {
                        target_player_name = args[0];
                    } else {
                        p.sendMessage("§f\uDBF7\uDC35 §cPlayer is offline or in another realm.");
                    }
                } else {
                    p.sendMessage("§f\uDBF7\uDC35 §cPlayer hasn't yet joined Crystal Caverns.");
                }
            }
            if (!target_player_name.isEmpty()) {
                File file = new File("/home/container/plugins/CommandPanels/panels/profile.yml");
                Panel panel = new Panel(file, "profile");
                PanelPlaceholders placeholders = panel.placeholders;
                LuckPerms lp_api = LuckPermsProvider.get();
                Player target_player = getPlayer(target_player_name);
                placeholders.addPlaceholder("target_player", target_player_name);
                User lpUser = lp_api.getPlayerAdapter(Player.class).getUser(target_player);
                CachedMetaData metaData = lpUser.getCachedData().getMetaData();
                String profile_color = metaData.getMetaValue("profile_color") + metaData.getMetaValue("profile_color_name");
                placeholders.addPlaceholder("profile_color", profile_color);
                String color = metaData.getMetaValue("color") + metaData.getMetaValue("color_name");
                placeholders.addPlaceholder("color", color);
                placeholders.addPlaceholder("rank", metaData.getMetaValue("rank"));
                placeholders.addPlaceholder("nameplate", metaData.getMetaValue("nameplate"));
                String discord_link = PlaceholderAPI.setPlaceholders(target_player,"%discordsrv_user_islinked%");
                String discord_name = PlaceholderAPI.setPlaceholders(target_player,"%discordsrv_user_name%");
                placeholders.addPlaceholder("discord_linked", discord_link);
                placeholders.addPlaceholder("discord_name", discord_name);
                if (p.getInventory().getHelmet() != null) {
                    placeholders.addPlaceholder("helmet", "true");
                    ItemStack helmet = p.getInventory().getHelmet();
                    placeholders.addPlaceholder("helmet_material", helmet.getType().toString());
                    ArmorMeta helmet_meta = (ArmorMeta) helmet.getItemMeta();
                    placeholders.addPlaceholder("helmet_name", helmet_meta.getDisplayName());
                    if (helmet_meta.hasCustomModelData()) {
                        placeholders.addPlaceholder("helmet_custom_data", String.valueOf(helmet_meta.getCustomModelData()));
                    } else {
                        placeholders.addPlaceholder("helmet_custom_data", "0");
                    }
                    if (helmet_meta.hasLore()) {
                        String helmet_lore_raw = helmet_meta.getLore().toString();
                        String helmet_lore_noStart = helmet_lore_raw.replace("[", "");
                        String helmet_lore = helmet_lore_noStart.replace("]", "");
                        placeholders.addPlaceholder("helmet_lore", helmet_lore);
                    } else {
                        placeholders.addPlaceholder("helmet_lore", "");
                    }
                    if (helmet.getType() == Material.LEATHER_HELMET) {
                        LeatherArmorMeta helmet_leather = (LeatherArmorMeta) helmet.getItemMeta();
                        placeholders.addPlaceholder("helmet_color", helmet_leather.getColor().getRed() + "," + helmet_leather.getColor().getGreen() + "," + helmet_leather.getColor().getBlue());
                    } else {
                        placeholders.addPlaceholder("helmet_color", "");
                    }
                    if (helmet_meta.hasTrim()) {
                        ArmorTrim armorTrim = helmet_meta.getTrim();
                        placeholders.addPlaceholder("helmet_trim", armorTrim.getMaterial() + " " + armorTrim.getPattern());
                    } else {
                        placeholders.addPlaceholder("helmet_trim", "");
                    }
                } else {
                    placeholders.addPlaceholder("helmet", "");
                }
                
                
                
                
                panel.open(p, PanelPosition.Top);
            }
        } else {
            p.sendMessage("§f\uDBCB\uDDAB §bCommand usage: §7/profile [name]");
        }
        return false;
    }
}