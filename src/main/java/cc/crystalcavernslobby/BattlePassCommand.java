package cc.crystalcavernslobby;

import me.rockyhawk.commandpanels.api.Panel;
import me.rockyhawk.commandpanels.openpanelsmanager.PanelPosition;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class BattlePassCommand implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly a player can execute this command!");
        }
        Player p = (Player) sender;
        if (args.length == 0) {
                if (LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(p).getCachedData().getPermissionData().checkPermission("premium_battle_pass").asBoolean()) {
                    File file = new File("/home/container/plugins/CommandPanels/panels/battle_pass_premium_1.yml");
                    Panel panel = new Panel(file, "battle_pass_premium_1");
                    panel.open(p, PanelPosition.Top);
                } else {
                    File file = new File("/home/container/plugins/CommandPanels/panels/battle_pass_1.yml");
                    Panel panel = new Panel(file, "battle_pass_1");
                    panel.open(p, PanelPosition.Top);
                }
        } else {
            p.sendMessage("§f\uDBCB\uDDAB §bCommand usage: §7/battlepass");
        } return false;
    }
}