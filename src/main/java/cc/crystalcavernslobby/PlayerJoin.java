package cc.crystalcavernslobby;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.util.UUID;

import static cc.crystalcavernslobby.CrystalCavernsLobby.perms;
import static cc.crystalcavernslobby.CrystalCavernsLobby.toSend;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        File player_file = new File("/home/container/world/playerdata/" + uuid + ".dat");
        Location spawn = new Location(Bukkit.getWorld("world"),0.5,222,0.5,0,0);
        player.teleport(spawn);
        toSend.add(uuid);
        if (!player_file.exists()) {
            CrystalCavernsLobby.getPermissions();
            perms.playerAdd("global", player, "prefix.1000.&f􃼛");
            perms.playerAdd("global", player, "meta.nameplate.\uDBE2\uDCB1\uDBC2\uDD72");
            perms.playerAdd("global", player, "meta.profile_color.#ffffff");
            perms.playerAdd("global", player, "meta.color.#ffffff");
            perms.playerAdd("global", player, "suffix.1000.&f");
        }
    }
}