package cc.crystalcavernslobby;

import me.clip.placeholderapi.PlaceholderAPI;
import me.rockyhawk.commandpanels.api.Panel;
import me.rockyhawk.commandpanels.classresources.placeholders.PanelPlaceholders;
import me.rockyhawk.commandpanels.openpanelsmanager.PanelPosition;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
                //NAME
                placeholders.addPlaceholder("profile_color", metaData.getMetaValue("profile_color"));
                placeholders.addPlaceholder("profile_theme", metaData.getMetaValue("profile_theme"));
                placeholders.addPlaceholder("profile_theme_name", metaData.getMetaValue("profile_theme_name"));
                placeholders.addPlaceholder("color", metaData.getMetaValue("color"));
                placeholders.addPlaceholder("color_name", metaData.getMetaValue("color_name"));
                placeholders.addPlaceholder("rank", metaData.getMetaValue("rank"));
                placeholders.addPlaceholder("nameplate", metaData.getMetaValue("nameplate"));
                //DISCORD
                String discord_link = PlaceholderAPI.setPlaceholders(target_player,"%discordsrv_user_islinked%");
                String discord_name = PlaceholderAPI.setPlaceholders(target_player,"%discordsrv_user_name%");
                placeholders.addPlaceholder("discord_linked", discord_link);
                placeholders.addPlaceholder("discord_name", discord_name);
                //OPEN PANEL
                panel.open(p, PanelPosition.Top);
            }
        } else {
            p.sendMessage("§f\uDBCB\uDDAB §bCommand usage: §7/profile [name]");
        }
        return false;
    }
}