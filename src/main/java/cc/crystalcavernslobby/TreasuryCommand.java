package cc.crystalcavernslobby;

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

public class TreasuryCommand implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly a player can execute this command!");
        }
        CommandPanelsAPI api = CommandPanels.getAPI();
        Player p = (Player) sender;
        if (args.length == 0) {
            if (!api.isPanelOpen(p)) {
                File file = new File("/home/container/plugins/CommandPanels/panels/treasury.yml");
                Panel panel = new Panel(file, "treasury");
                LuckPerms lp_api = LuckPermsProvider.get();
                User lp_user = lp_api.getPlayerAdapter(Player.class).getUser(p);
                panel.placeholders.addPlaceholder("amethyst_rank_rewards", lp_user.getCachedData().getMetaData().getMetaValue("amethyst_rank_rewards"));
                panel.open(p, PanelPosition.Top);
            }
        } else {
            p.sendMessage("§f\uDBCB\uDDAB §bCommand usage: §7/treasury");
        } return false;
    }
}