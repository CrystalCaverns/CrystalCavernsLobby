package cc.crystalcavernslobby;

import me.clip.placeholderapi.PlaceholderAPI;
import me.rockyhawk.commandpanels.api.Panel;
import me.rockyhawk.commandpanels.openpanelsmanager.PanelPosition;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
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
                    LuckPerms lp_api = LuckPermsProvider.get();
                    Player target_player = getPlayer(target_player_name);
                    panel.placeholders.addPlaceholder("target_player", target_player_name);
                    User lp_user = lp_api.getPlayerAdapter(Player.class).getUser(target_player);
                    panel.placeholders.addPlaceholder("profile_color", lp_user.getCachedData().getMetaData().getMetaValue("profile_color"));
                    panel.placeholders.addPlaceholder("color", lp_user.getCachedData().getMetaData().getMetaValue("color"));
                    panel.placeholders.addPlaceholder("rank", lp_user.getCachedData().getMetaData().getMetaValue("rank"));
                    panel.placeholders.addPlaceholder("nameplate", lp_user.getCachedData().getMetaData().getMetaValue("nameplate"));
                    String discord_link = "%discordsrv_user_islinked%";
                    String discord_name = "%discordsrv_user_name%";
                    discord_link = PlaceholderAPI.setPlaceholders(target_player,discord_link);
                    discord_name = PlaceholderAPI.setPlaceholders(target_player,discord_name);
                    panel.placeholders.addPlaceholder("discord_linked", discord_link);
                    panel.placeholders.addPlaceholder("discord_name", discord_name);
                    if (p.getInventory().getHelmet() != null) {
                        panel.placeholders.addPlaceholder("helmet", "true");
                        ItemStack helmet = p.getInventory().getHelmet();
                        panel.placeholders.addPlaceholder("helmet_material", helmet.getType().toString());
                        ArmorMeta helmet_meta = (ArmorMeta) helmet.getItemMeta();
                        panel.placeholders.addPlaceholder("helmet_name", helmet_meta.getDisplayName());
                        if (helmet_meta.hasCustomModelData()) {
                            panel.placeholders.addPlaceholder("helmet_custom_data", String.valueOf(helmet_meta.getCustomModelData()));
                        } else {
                            panel.placeholders.addPlaceholder("helmet_custom_data", "0");
                        }
                        if (helmet_meta.hasLore()) {
                            String helmet_lore = helmet_meta.getLore().toString();
                            String helmet_lore_1 = helmet_lore.replace("[", "");
                            String helmet_lore_2 = helmet_lore_1.replace("]", "");
                            panel.placeholders.addPlaceholder("helmet_lore", helmet_lore_2);
                        } else {
                            panel.placeholders.addPlaceholder("helmet_lore", "");
                        }
                        if (helmet_meta.hasEnchants()) {
                            panel.placeholders.addPlaceholder("helmet_enchanted", "true");
                        } else {
                            panel.placeholders.addPlaceholder("helmet_enchanted", "false");
                        }
                        if (helmet.getType() == Material.LEATHER_HELMET) {
                            LeatherArmorMeta helmet_leather = (LeatherArmorMeta) helmet.getItemMeta();
                            panel.placeholders.addPlaceholder("helmet_color", String.valueOf(helmet_leather.getColor().asRGB()));
                        } else {
                            panel.placeholders.addPlaceholder("helmet_color", "");
                        }
                        if (helmet_meta.hasTrim()) {
                            panel.placeholders.addPlaceholder("helmet_trim_material", helmet_meta.getTrim().getMaterial().toString());
                            panel.placeholders.addPlaceholder("helmet_trim_pattern", helmet_meta.getTrim().getPattern().toString());
                        } else {
                            panel.placeholders.addPlaceholder("helmet_trim", "");
                        }
                    } else {
                        panel.placeholders.addPlaceholder("helmet", "");
                    }
                    panel.open(p, PanelPosition.Top);
                }
            } else {
                p.sendMessage("§f\uDBCB\uDDAB §bCommand usage: §7/profile [name]");
            } return false;
    }
}