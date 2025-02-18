package cc.crystalcavernslobby;

import me.clip.placeholderapi.PlaceholderAPI;
import me.rockyhawk.commandpanels.api.Panel;
import me.rockyhawk.commandpanels.classresources.placeholders.PanelPlaceholders;
import me.rockyhawk.commandpanels.openpanelsmanager.PanelPosition;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class HomesCommand implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly a player can execute this command!");
        }
        Player p = (Player) sender;
            File file = new File("/home/container/plugins/CommandPanels/panels/homes.yml");
            Panel panel = new Panel(file, "homes");
            PanelPlaceholders placeholders = panel.placeholders;
            String homes = "%huskhomes_homes_list%";
            homes = PlaceholderAPI.setPlaceholders(p,homes);
            List<String> homeList = Arrays.asList(homes.split("\\s*,\\s*"));
            String Home1 = "Empty";
            String Home2 = "Empty";
            String Home3 = "Empty";
            String Home4 = "Empty";
            String Home5 = "Empty";
            if (!homeList.toString().equals("[]")) {
                if (homeList.toArray().length >= 1) Home1 = homeList.get(0);
                if (homeList.toArray().length >= 2) Home2 = homeList.get(1);
                if (homeList.toArray().length >= 3) Home3 = homeList.get(2);
                if (homeList.toArray().length >= 4) Home4 = homeList.get(3);
                if (homeList.toArray().length >= 5) Home5 = homeList.get(4);
            }
            placeholders.addPlaceholder("home1", Home1);
            placeholders.addPlaceholder("home2", Home2);
            placeholders.addPlaceholder("home3", Home3);
            placeholders.addPlaceholder("home4", Home4);
            placeholders.addPlaceholder("home5", Home5);
            panel.open(p, PanelPosition.Top);
            return false;
    }
}
