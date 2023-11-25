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

public class MenuCommand implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly a player can execute this command!");
        }
        Player p = (Player) sender;
        File file = new File("/home/container/plugins/CommandPanels/panels/menu.yml");
        Panel panel = new Panel(file, "menu");
        PanelPlaceholders placeholders = panel.placeholders;
        String character = "\uDBDC\uDD87";
        String coins_text = PlaceholderAPI.setPlaceholders(p,"%coinsengine_balance_rounded_coins%");
        String gemstones_text = PlaceholderAPI.setPlaceholders(p,"%coinsengine_balance_rounded_gemstones%");
        int coins = Integer.parseInt(coins_text.replaceAll(",",""));
        int gemstones = Integer.parseInt(gemstones_text.replaceAll(",",""));
        int coins_length = String.valueOf(coins).length();
        int gemstones_length = String.valueOf(gemstones).length();
        String coins_chars = character.repeat(7 - coins_length);
        String gemstones_chars = character.repeat(7 - gemstones_length);
        placeholders.addPlaceholder("coins",coins + coins_chars);
        placeholders.addPlaceholder("gemstones",gemstones_chars + gemstones);
        panel.open(p, PanelPosition.Top);
        return false;
    }
}