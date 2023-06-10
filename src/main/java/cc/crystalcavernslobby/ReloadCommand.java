package cc.crystalcavernslobby;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(final @NotNull CommandSender sender, final @NotNull Command command, final @NotNull String label, final String[] args) {
        if (args.length == 1 && "reload".equals(args[0])) {
            CrystalCavernsLobby.getPlugin().reloadConfig();
            sender.sendMessage("Crystal Caverns Lobby config has been reloaded.");
            return true;
        }
        return false;
    }
}