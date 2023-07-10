package cc.crystalcavernslobby;

import de.themoep.minedown.MineDown;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.util.UUID;

import static cc.crystalcavernslobby.CrystalCavernsLobby.*;

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
            perms.playerAdd("global", player, "prefix.1000.&f\uDBD6\uDC87");
            perms.playerAdd("global", player, "meta.rank.&f\uDBCF\uDF1B");
            perms.playerAdd("global", player, "meta.nameplate.\uDBE2\uDCB1\uDBC2\uDD72");
            perms.playerAdd("global", player, "meta.profile_color.#ffffff");
            perms.playerAdd("global", player, "meta.color.#ffffff");
            perms.playerAdd("global", player, "suffix.1000.&f");
        }
        Bukkit.getScheduler().runTaskLater(CrystalCavernsLobby.getPlugin(), () -> {
        String discord_link = "%discordsrv_user_islinked%";
        discord_link = PlaceholderAPI.setPlaceholders(player,discord_link);
        if (discord_link.equals("no")) {
            String message = "\uDBC9\uDF2A &#7289da&Hey there friend! Looks like you haven't yet linked your Minecraft and Discord accounts. It only takes a minute and gets you a super cool badge. Link your accounts with '/discord link'!";
            TextComponent formatted_message = new TextComponent(MineDown.parse(message));
            player.sendMessage(formatted_message);
        }
        if (!perms.playerInGroup(player,"booster") && discord_link.equals("yes")) {
            String message = "\uDBC9\uDF2A &#7289da&Did you know you can get super awesome rewards for boosting our Discord server? Well it's absolutely true!";
            TextComponent formatted_message = new TextComponent(MineDown.parse(message));
            player.sendMessage(formatted_message);
        }}, 100L);
    }
}