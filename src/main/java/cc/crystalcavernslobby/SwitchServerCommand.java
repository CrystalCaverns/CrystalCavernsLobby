package cc.crystalcavernslobby;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static cc.crystalcavernslobby.CrystalCavernsLobby.plugin;

public class SwitchServerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        Player p = (Player) sender;
        p.sendTitle("\uDBEA\uDDE8", "", 10, 40, 10);
        Bukkit.getScheduler().runTaskLater(CrystalCavernsLobby.getPlugin(), () -> {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF(args[0]);
            Player player = Bukkit.getPlayerExact(p.getName());
            assert player != null;
            player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
        }, 20L);
        return false;
    }
}