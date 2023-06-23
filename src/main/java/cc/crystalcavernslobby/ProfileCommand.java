package cc.crystalcavernslobby;

import me.clip.placeholderapi.PlaceholderAPI;
import me.rockyhawk.commandpanels.CommandPanels;
import me.rockyhawk.commandpanels.api.CommandPanelsAPI;
import me.rockyhawk.commandpanels.api.Panel;
import me.rockyhawk.commandpanels.openpanelsmanager.PanelPosition;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.UUID;

import static org.bukkit.Bukkit.*;

public class ProfileCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly a player can execute this command!");
        }
        CommandPanelsAPI api = CommandPanels.getAPI();
        Player p = (Player) sender;
        if (!api.isPanelOpen(p)) {
            if (args.length <= 1) {
                String target_player = "";
                if (args.length == 0) {
                    target_player = p.getName();
                } else {
                    UUID selected_uuid = getPlayerUniqueId(args[0]);
                    File selected = new File("/home/container/world/playerdata/" + selected_uuid + ".dat");
                    if (selected.exists()) {
                        if (getPlayerExact(args[0]) != null) {
                            target_player = args[0];
                        } else {
                            p.sendMessage("§f\uDBF7\uDC35 §cPlayer is offline or in another realm.");
                        }
                    } else {
                        p.sendMessage("§f\uDBF7\uDC35 §cPlayer hasn't yet joined Crystal Caverns.");
                    }
                }
                if (!target_player.equals("")) {
                    File file = new File("/home/container/plugins/CommandPanels/panels/profile.yml");
                    Panel panel = new Panel(file, "profile");
                    LuckPerms lp_api = LuckPermsProvider.get();
                    User lp_user = lp_api.getPlayerAdapter(Player.class).getUser(getPlayer(target_player));
                    panel.placeholders.addPlaceholder("target_player", target_player);
                    panel.placeholders.addPlaceholder("profile_color", lp_user.getCachedData().getMetaData().getMetaValue("profile_color"));
                    panel.placeholders.addPlaceholder("color", lp_user.getCachedData().getMetaData().getMetaValue("color"));
                    panel.placeholders.addPlaceholder("rank", lp_user.getCachedData().getMetaData().getMetaValue("rank"));
                    panel.placeholders.addPlaceholder("nameplate", lp_user.getCachedData().getMetaData().getMetaValue("nameplate"));
                    String discord_link = "%discordsrv_user_islinked%";
                    String discord_name = "%discordsrv_user_name%";
                    discord_link = PlaceholderAPI.setPlaceholders(p,discord_link);
                    discord_name = PlaceholderAPI.setPlaceholders(p,discord_name);
                    panel.placeholders.addPlaceholder("discord_linked", discord_link);
                    panel.placeholders.addPlaceholder("discord_name", discord_name);
                    panel.open(p, PanelPosition.Top);
                }
            } else {
                p.sendMessage("§f\uDBCB\uDDAB §bCommand usage: §7/profile [name]");
            }
        } return false;
    }
}