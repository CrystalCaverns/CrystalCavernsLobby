package cc.crystalcavernslobby;

import me.rockyhawk.commandpanels.api.Panel;
import me.rockyhawk.commandpanels.classresources.placeholders.PanelPlaceholders;
import me.rockyhawk.commandpanels.openpanelsmanager.PanelPosition;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.NodeType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static cc.crystalcavernslobby.CrystalCavernsLobby.perms;
import static cc.crystalcavernslobby.CrystalCavernsLobby.plugin;

public class MainCommand implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if ("interactions".equals(args[0])) {
            if (args[1].equals("disable")) {
                plugin.getConfig().set("disableInteractions", true);
                plugin.saveConfig();
                sender.sendMessage("§f\uDBDD\uDD29 §aInteractions are now disabled!");
                return false;
            }
            if (args[1].equals("enable")) {
                plugin.getConfig().set("disableInteractions", false);
                plugin.saveConfig();
                sender.sendMessage("§f\uDBDD\uDD29 §aInteractions are now enabled!");
                return false;
            }
            return false;
        }
        CrystalCavernsLobby.getPermissions();
        Player player = Bukkit.getPlayer(args[1]);
        User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(player);
        if ("nameplate".equals(args[0])) {
            user.data().clear(NodeType.META.predicate(mn -> mn.getMetaKey().equals("nameplate")));
            perms.playerAdd("global", player, "meta.nameplate." + args[2]);
            return false;
        }
        if ("color".equals(args[0])) {
            user.data().clear(NodeType.META.predicate(mn -> mn.getMetaKey().equals("color")));
            user.data().clear(NodeType.META.predicate(mn -> mn.getMetaKey().equals("color_name")));
            perms.playerAdd("global", player, "meta.color." + args[2]);
            perms.playerAdd("global", player, "meta.color_name." + args[3]);
            return false;
        }
        if ("profile_theme".equals(args[0])) {
            user.data().clear(NodeType.META.predicate(mn -> mn.getMetaKey().equals("profile_theme")));
            perms.playerAdd("global", player, "meta.profile_theme." + args[2] + ";" + args[3] + ";" + args[4]);
            return false;
        }
        if ("title".equals(args[0])) {
            user.data().clear(NodeType.META.predicate(mn -> mn.getMetaKey().equals("title")));
            perms.playerAdd("global", player, "meta.title." + args[2]);
            return false;
        }
        if ("medals".equals(args[0])) {
            File file = new File("/home/container/plugins/CommandPanels/panels/medals.yml");
            Panel panel = new Panel(file, "medals");
            PanelPlaceholders placeholders = panel.placeholders;
            CachedMetaData metaData = user.getCachedData().getMetaData();
            for (int i = 1; i <= 5; i++) {
                if (metaData.getMetaValue("medal" + i) != null) {
                    List<String> medal_data = new ArrayList<>(Arrays.asList(metaData.getMetaValue("medal" + i).split(";")));
                    placeholders.addPlaceholder("medal" + i, "true");
                    placeholders.addPlaceholder("medal" + i + "_customdata", medal_data.get(0));
                    placeholders.addPlaceholder("medal" + i + "_name", medal_data.get(1));
                    placeholders.addPlaceholder("medal" + i + "_lore", medal_data.get(2));
                }
            }
            panel.open(player, PanelPosition.Top);
            return false;
        }
        if ("medal".equals(args[0])) {
            user.data().clear(NodeType.META.predicate(mn -> mn.getMetaKey().equals("medal" + args[2])));
            perms.playerAdd("global", player, "meta.medal" + args[2] + "." + args[3] + ";" + args[4] + ";" + args[5]);
            return false;
        }
        if ("choose_medal".equals(args[0])) {
            File file = new File("/home/container/plugins/CommandPanels/panels/medal_" + args[3] + ".yml");
            Panel panel = new Panel(file, "medal_" + args[3]);
            panel.placeholders.addPlaceholder("index", args[2]);
            panel.open(player, PanelPosition.Top);
            return false;
        }
        return false;
    }
}