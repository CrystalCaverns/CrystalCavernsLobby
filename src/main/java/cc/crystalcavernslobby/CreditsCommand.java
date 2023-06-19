package cc.crystalcavernslobby;

import cc.crystalcavernslobby.NMS.Credits;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CreditsCommand implements CommandExecutor {
    public CreditsCommand() {
    }
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player toShow;
        if (args.length > 0) {
            toShow = Bukkit.getPlayerExact(args[0]);
        } else {
            if (!(commandSender instanceof Player)) {
                commandSender.sendMessage(ChatColor.YELLOW + "You're not a player! Please specify a player when executing this command as console.");
                return true;
            }
            toShow = (Player)commandSender;
        }
        if (toShow == null) {
            commandSender.sendMessage(ChatColor.YELLOW + "Player not found!");
        } else {
            Credits.showCredits(toShow);
        }
        return true;
    }
}
