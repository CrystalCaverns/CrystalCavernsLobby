package cc.crystalcavernslobby;

import me.rockyhawk.commandpanels.CommandPanels;
import me.rockyhawk.commandpanels.api.CommandPanelsAPI;
import me.rockyhawk.commandpanels.api.Panel;
import me.rockyhawk.commandpanels.openpanelsmanager.PanelPosition;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
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
            return true;
        }
        CommandPanelsAPI api = CommandPanels.getAPI();
        Player p = (Player) sender;
        String player = p.getName();
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        if (!api.isPanelOpen(p)) {
            File file = new File("/home/container/plugins/CommandPanels/panels/profile.yml");
            Panel panel = new Panel(file,"profile");
            if (args.length == 0) {
                Bukkit.dispatchCommand(console, "cpdata -s set " + player + " profile_looking_up " + player);
                panel.open(p, PanelPosition.Top);
            }
            if (args.length == 1) {
                UUID selected_uuid = getPlayerUniqueId(args[0]);
                File selected = new File("/home/container/world/playerdata/" + selected_uuid + ".dat");
                if (selected.exists()) {
                    Bukkit.dispatchCommand(console, "cpdata -s set " + player + " profile_looking_up " + args[0]);
                    panel.open(p, PanelPosition.Top);
                } else {
                    p.sendMessage("§f\uDBF7\uDC35 §cUnknown player!");
                }
            }
            if (args.length >= 2) {
                p.sendMessage("§f\uDBCB\uDDAB §bCommand usage: §7/profile [player name]");
            }
            return false;
        }
        return false;
    }
}