package cc.crystalcavernslobby;

import me.clip.placeholderapi.PlaceholderAPI;
import me.rockyhawk.commandpanels.api.Panel;
import me.rockyhawk.commandpanels.openpanelsmanager.PanelPosition;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class PurchaseCommand implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage("§f\uDBF7\uDC35 §cCannot execute command.");
            return false;
        }
        Player p = Bukkit.getPlayer(args[0]);
        String gemstones_text = PlaceholderAPI.setPlaceholders(p, "%coinsengine_balance_rounded_gemstones%");
        int gemstones = Integer.parseInt(gemstones_text.replaceAll(",", ""));
        if (gemstones >= Integer.parseInt(args[1])) {
            File file = new File("/home/container/plugins/CommandPanels/panels/purchase.yml");
            Panel panel = new Panel(file, "purchase");
            panel.placeholders.addPlaceholder("cost", args[1]);
            panel.placeholders.addPlaceholder("material", args[2]);
            panel.placeholders.addPlaceholder("customdata", args[3]);
            panel.placeholders.addPlaceholder("leatherarmor", args[4]);
            panel.placeholders.addPlaceholder("key", args[5]);
            List<String> item_name_list = Arrays.asList(args).subList(6, args.length);
            String item_name_raw = item_name_list.toString().replaceAll(",","");
            String item_name_replaced = item_name_raw.replaceAll("\\[","");
            String item_name = item_name_replaced.replaceAll("]","");
            panel.placeholders.addPlaceholder("item_name", item_name);
            panel.open(p, PanelPosition.Top);
        } else {
            p.sendMessage("§f\uDBF7\uDC35 §cYou don't have enough gemstones to purchase this item!");
            TextComponent message = Component.text("\uDBE6\uDE59 ")
                .color(NamedTextColor.WHITE)
                .decoration(TextDecoration.ITALIC,false)
                .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.OPEN_URL, "https://store.crystalcaverns.net"))
                .append(Component.text("You can get more gemstones on our store at store.crystalcaverns.net!", TextColor.color(31743)));
            p.sendMessage(message);
        } return false;
    }
}
