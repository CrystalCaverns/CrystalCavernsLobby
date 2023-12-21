package cc.crystalcavernslobby;

import de.themoep.minedown.MineDown;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static cc.crystalcavernslobby.CrystalCavernsLobby.*;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Player player = event.getPlayer();
        player.setGameMode(GameMode.ADVENTURE);
        Location spawn = new Location(Bukkit.getWorld("world"),0.5,222,0.5,0,0);
        player.teleport(spawn);
        CrystalCavernsLobby.getPermissions();
        Bukkit.getScheduler().runTaskLater(getPlugin(), () -> {
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
        Bukkit.getScheduler().runTaskLater(getPlugin(), () -> {
            String row1 = "\uDBE5\uDC51\uDBED\uDC5C\uDBE5\uDC4E\uDBED\uDC5C\uDBE5\uDC55\uDBED\uDC5C\uDBE5\uDC55\uDBED\uDC5C\uDBE5\uDC58\uDBED\uDC5C\uDBEB\uDF43\uDBED\uDC5C\uDBE5\uDC4F\uDBED\uDC5C\uDBE5\uDC5B\uDBED\uDC5C\uDBE5\uDC52\uDBED\uDC5C\uDBE5\uDC4E\uDBED\uDC5C\uDBE5\uDC57\uDBED\uDC5C\uDBE5\uDC4D\uDBED\uDC5C\uDBE5\uDC32\uDBED\uDC5C\uDBEB\uDF43\uDBED\uDC5C\uDBE5\uDC60\uDBED\uDC5C\uDBE5\uDC4E\uDBED\uDC5C\uDBE5\uDC55\uDBED\uDC5C\uDBE5\uDC4C\uDBED\uDC5C\uDBE5\uDC58\uDBED\uDC5C\uDBE5\uDC56\uDBED\uDC5C\uDBE5\uDC4E\uDBED\uDC5C";
            String row2 = "\uDBE5\uDC4B\uDBED\uDC5C\uDBE5\uDC4A\uDBED\uDC5C\uDBE5\uDC4C\uDBED\uDC5C\uDBE5\uDC54\uDBED\uDC5C\uDBEB\uDF43\uDBED\uDC5C\uDBE5\uDC5D\uDBED\uDC5C\uDBE5\uDC58\uDBED\uDC5C\uDBEB\uDF43\uDBED\uDC5C\uDBE5\uDC4C\uDBED\uDC5C\uDBE5\uDC5B\uDBED\uDC5C\uDBE5\uDC62\uDBED\uDC5C\uDBE5\uDC5C\uDBED\uDC5C\uDBE5\uDC5D\uDBED\uDC5C\uDBE5\uDC4A\uDBED\uDC5C\uDBE5\uDC55\uDBED\uDC5C";
            String row3 = "\uDBE5\uDC4C\uDBED\uDC5C\uDBE5\uDC4A\uDBED\uDC5C\uDBE5\uDC5F\uDBED\uDC5C\uDBE5\uDC4E\uDBED\uDC5C\uDBE5\uDC5B\uDBED\uDC5C\uDBE5\uDC57\uDBED\uDC5C\uDBE5\uDC5C\uDBED\uDC5C\uDBE5\uDC32\uDBED\uDC5C\uDBEB\uDF43\uDBED\uDC5C\uDBE5\uDC5D\uDBED\uDC5C\uDBE5\uDC58\uDBED\uDC5C\uDBE5\uDC4D\uDBED\uDC5C\uDBE5\uDC4A\uDBED\uDC5C\uDBE5\uDC62\uDBED\uDC5C\uDBEB\uDF43\uDBED\uDC5C\uDBE5\uDC52\uDBED\uDC5C\uDBE5\uDC5C\uDBED\uDC5C\uDBEB\uDF43\uDBED\uDC5C\uDBE5\uDC4A\uDBED\uDC5C";
            String row4 = "\uDBE5\uDC5F\uDBED\uDC5C\uDBE5\uDC4E\uDBED\uDC5C\uDBE5\uDC5B\uDBED\uDC5C\uDBE5\uDC62\uDBED\uDC5C\uDBEB\uDF43\uDBED\uDC5C\uDBE5\uDC4B\uDBED\uDC5C\uDBE5\uDC4E\uDBED\uDC5C\uDBE5\uDC4A\uDBED\uDC5C\uDBE5\uDC5E\uDBED\uDC5C\uDBE5\uDC5D\uDBED\uDC5C\uDBE5\uDC52\uDBED\uDC5C\uDBE5\uDC4F\uDBED\uDC5C\uDBE5\uDC5E\uDBED\uDC5C\uDBE5\uDC55\uDBED\uDC5C\uDBEB\uDF43\uDBED\uDC5C\uDBE5\uDC4D\uDBED\uDC5C\uDBE5\uDC4A\uDBED\uDC5C\uDBE5\uDC62\uDBED\uDC5C\uDBE5\uDC31\uDBED\uDC5C";
            String row5 = "\uDBE5\uDC4D\uDBED\uDC5C\uDBE5\uDC58\uDBED\uDC5C\uDBE5\uDC57\uDBED\uDC5C\uDBE5\uDC34\uDBED\uDC5C\uDBE5\uDC5D\uDBED\uDC5C\uDBEB\uDF43\uDBED\uDC5C\uDBE5\uDC62\uDBED\uDC5C\uDBE5\uDC58\uDBED\uDC5C\uDBE5\uDC5E\uDBED\uDC5C\uDBEB\uDF43\uDBED\uDC5C\uDBE5\uDC5D\uDBED\uDC5C\uDBE5\uDC51\uDBED\uDC5C\uDBE5\uDC52\uDBED\uDC5C\uDBE5\uDC57\uDBED\uDC5C\uDBE5\uDC54\uDBED\uDC5C\uDBE5\uDC33\uDBED\uDC5C";
            String row6 = "\uDBE5\uDC4A\uDBED\uDC5C\uDBE5\uDC57\uDBED\uDC5C\uDBE5\uDC62\uDBED\uDC5C\uDBE5\uDC60\uDBED\uDC5C\uDBE5\uDC4A\uDBED\uDC5C\uDBE5\uDC62\uDBED\uDC5C\uDBE5\uDC5C\uDBED\uDC5C\uDBE5\uDC31\uDBED\uDC5C\uDBEB\uDF43\uDBED\uDC5C\uDBE5\uDC51\uDBED\uDC5C\uDBE5\uDC4A\uDBED\uDC5C\uDBE5\uDC5F\uDBED\uDC5C\uDBE5\uDC4E\uDBED\uDC5C\uDBEB\uDF43\uDBED\uDC5C\uDBE5\uDC4F\uDBED\uDC5C\uDBE5\uDC5E\uDBED\uDC5C\uDBE5\uDC57\uDBED\uDC5C\uDBE5\uDC32\uDBED\uDC5C";
            dialog(player,row1,row2,row3,row4,row5,row6);
        }, 300L);
    }
}