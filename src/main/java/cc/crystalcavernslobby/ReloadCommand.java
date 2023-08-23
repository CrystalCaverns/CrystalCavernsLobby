package cc.crystalcavernslobby;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (args.length == 1 && "reload".equals(args[0])) {
            CrystalCavernsLobby.getPlugin().reloadConfig();
            sender.sendMessage("§f\uDBDD\uDD29 §aCrystalCavernsLobby has been reloaded.");
        }
        return false;
    }
}