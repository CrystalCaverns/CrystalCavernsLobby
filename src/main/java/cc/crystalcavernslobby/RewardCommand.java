package cc.crystalcavernslobby;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.NodeType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static cc.crystalcavernslobby.CrystalCavernsLobby.perms;

public class RewardCommand implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof ConsoleCommandSender consoleCommandSender)) {
            sender.sendMessage("§f\uDBF7\uDC35 §cCannot execute command.");
            return false;
        }
        Player p = Bukkit.getPlayer(args[0]);
        int cost = Integer.parseInt(args[1]);
        String key = args[2];
        if (key.contains("pass_tier")) {
            User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(p);
            int tier = Integer.parseInt(key.replaceAll("[^0-9]", ""));
            user.data().clear(NodeType.META.predicate(mn -> mn.getMetaKey().equals("crystal_pass_points")));
            perms.playerAdd("global", p, "meta.crystal_pass_points." + tier * 1000);
            p.sendMessage("§f\uDBFC\uDCD0 §6Tier " + tier + " of the Crystal Pass reached!");
        } else {
            CrystalCavernsLobby.getPermissions();
            perms.playerAdd("global", p, key);
        }
        Bukkit.dispatchCommand(consoleCommandSender,"gemstones take " + p.getName() + " " + cost + " -s");
        return false;
    }
}
