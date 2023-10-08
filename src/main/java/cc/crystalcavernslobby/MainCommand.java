package cc.crystalcavernslobby;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.NodeType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static cc.crystalcavernslobby.CrystalCavernsLobby.perms;
import static cc.crystalcavernslobby.CrystalCavernsLobby.plugin;

public class MainCommand implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (args.length == 2) {
            if ("interactions".equals(args[0])) {
                if (args[1].equals("disable")) {
                    plugin.getConfig().set("disableInteractions", true);
                    plugin.saveConfig();
                    sender.sendMessage("§f\uDBDD\uDD29 §aInteractions are now disabled!");
                }
                if (args[1].equals("enable")) {
                    plugin.getConfig().set("disableInteractions", false);
                    plugin.saveConfig();
                    sender.sendMessage("§f\uDBDD\uDD29 §aInteractions are now enabled!");
                }
            }
            CrystalCavernsLobby.getPermissions();
            Player player = (Player) sender;
            User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(player);
            if ("nameplate".equals(args[0])) {
                user.data().clear(NodeType.META.predicate(mn -> mn.getMetaKey().equals("nameplate")));
                perms.playerAdd("global", player, "meta.nameplate." + args[1]);
            }
        }
        if (args.length >= 3) {
            CrystalCavernsLobby.getPermissions();
            Player player = (Player) sender;
            User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(player);
            if (args.length <= 4) {
                if ("color".equals(args[0])) {
                    user.data().clear(NodeType.META.predicate(mn -> mn.getMetaKey().equals("color")));
                    user.data().clear(NodeType.META.predicate(mn -> mn.getMetaKey().equals("color_name")));
                    perms.playerAdd("global", player, "meta.color." + args[1]);
                    if (args.length == 3) {
                        perms.playerAdd("global", player, "meta.color_name." + args[2]);
                    }
                    if (args.length == 4) {
                        perms.playerAdd("global", player, "meta.color_name." + args[2] + " " + args[3]);
                    }
                }
            }
            if (args.length <= 5) {
                if ("profile_theme".equals(args[0])) {
                    user.data().clear(NodeType.META.predicate(mn -> mn.getMetaKey().equals("profile_color")));
                    user.data().clear(NodeType.META.predicate(mn -> mn.getMetaKey().equals("profile_theme")));
                    user.data().clear(NodeType.META.predicate(mn -> mn.getMetaKey().equals("profile_theme_name")));
                    perms.playerAdd("global", player, "meta.profile_color." + args[1]);
                    perms.playerAdd("global", player, "meta.profile_theme." + args[2]);
                    if (args.length == 4) {
                        perms.playerAdd("global", player, "meta.profile_theme_name." + args[3]);
                    }
                    if (args.length == 5) {
                        perms.playerAdd("global", player, "meta.profile_theme_name." + args[3] + " " + args[4]);
                    }
                }
            }
        }
        return false;
    }
}