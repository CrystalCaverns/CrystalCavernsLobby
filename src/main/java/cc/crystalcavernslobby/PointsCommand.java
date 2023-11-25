package cc.crystalcavernslobby;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedDataManager;
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

public class PointsCommand implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage("§f\uDBF7\uDC35 §cCannot execute command.");
            return false;
        }
        Player p = Bukkit.getPlayer(args[0]);
        CachedDataManager cachedDataManager = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(p).getCachedData();
        int old_points = Integer.parseInt(cachedDataManager.getMetaData().getMetaValue("crystal_pass_points"));
        int new_points = Integer.parseInt(args[1]);
        User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(p);
        user.data().clear(NodeType.META.predicate(mn -> mn.getMetaKey().equals("crystal_pass_points")));
        perms.playerAdd("global", p, "meta.crystal_pass_points." + (old_points + new_points));
        double tier_index = (double) old_points / 1000;
        int tier = (int) Math.floor(tier_index);
        double next_tier_index = (double) (old_points + new_points) / 1000;
        int next_tier = (int) Math.floor(next_tier_index);
        if (next_tier > tier) {
            p.sendMessage("§f\uDBFC\uDCD0 §6Tier " + next_tier + " of the Crystal Pass reached!");
        }
        return false;
    }
}