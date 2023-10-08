package cc.crystalcavernslobby;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static cc.crystalcavernslobby.CrystalCavernsLobby.plugin;

public class ClaimCommand implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("§cOnly a player can execute this command!");
            return true;
        }
        // NEEDS COMPLETE REWRITE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // NEEDS COMPLETE REWRITE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // NEEDS COMPLETE REWRITE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // NEEDS COMPLETE REWRITE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // NEEDS COMPLETE REWRITE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // NEEDS COMPLETE REWRITE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        String player = p.getName();
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        if (args.length == 1) {
            File configPath = new File("/home/container/plugins/CrystalCavernsLobby/coupons");
            FileConfiguration couponsConfig = new YamlConfiguration();
            try {
                couponsConfig.load(configPath);
            } catch (IOException | InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }
            if (couponsConfig.getKeys(false).contains(args[0])) {
                if (!couponsConfig.getBoolean(args[0] + ".used")) {
                    couponsConfig.set(args[0] + ".used", true);
                    plugin.saveConfig();
                    if (Objects.equals(couponsConfig.getString("vouchers." + args[0] + ".type"), "cap")) {
                        Bukkit.dispatchCommand(console, "lp user " + player + " meta set " + (couponsConfig.getString("vouchers." + args[0] + ".meta") + " 1"));
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
