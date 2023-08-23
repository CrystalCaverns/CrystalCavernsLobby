package cc.crystalcavernslobby;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StoreCommand implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly a player can execute this command!");
        }
        Player p = (Player) sender;
        if (args.length == 0) {
            TextComponent message = Component.text("Click here to visit our store: store.crystalcaverns.net")
                .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.OPEN_URL, "https://store.crystalcaverns.net"));
            p.sendMessage(message);
        }
        else {
            p.sendMessage("§f\uDBCB\uDDAB §bCommand usage: §7/battlepass [page]");
        }
        return false;
    }
}