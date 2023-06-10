package cc.crystalcavernslobby;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ClaimCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly a player can execute this command!");
            return true;
        }
        Player p = (Player) sender;
        String player = p.getName();
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        FileConfiguration config = CrystalCavernsLobby.getPlugin().getConfig();
        if (args.length == 1) {
            if (Objects.requireNonNull(config.getConfigurationSection("vouchers")).getKeys(false).contains(args[0])) {
                if (!config.getBoolean("vouchers." + args[0] + ".used")) {
                    config.set("vouchers." + args[0] + ".used", true);
                    CrystalCavernsLobby.getPlugin().saveConfig();
                    if (Objects.equals(config.getString("vouchers." + args[0] + ".type"), "cap")) {
                        Bukkit.dispatchCommand(console, "lp user " + player + " meta set " + (config.getString("vouchers." + args[0] + ".meta") + " 1"));
                        p.sendMessage("§f\uDBDD\uDD29 §aVoucher code redeemed successfully! Enjoy your reward.");
                    }
                } else {
                    p.sendMessage("§f\uDBF7\uDC35 §cThis voucher code was already used!");
                }
            } else {
                p.sendMessage("§f\uDBF7\uDC35 §cUnknown voucher code!");
            }
        } else {
            p.sendMessage("§f\uDBCB\uDDAB §bCommand usage: §7/claim [voucher code]");
        }
        return false;
    }
}
