package cc.crystalcavernslobby;

import de.themoep.minedown.MineDown;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static cc.crystalcavernslobby.CrystalCavernsLobby.*;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        Location spawn = new Location(Bukkit.getWorld("world"),0.5,222,0.5,0,0);
        player.teleport(spawn);
        toSend.add(uuid);
        CrystalCavernsLobby.getPermissions();
        if (!perms.playerHas(player, "meta.color.#ffffff")) {
            perms.playerAdd("global", player, "meta.color.#ffffff");
        }
        if (!perms.playerHas(player, "meta.profile_color.#ffffff")) {
            perms.playerAdd("global", player, "meta.profile_color.#ffffff");
        }
        if (!perms.playerHas(player, "meta.nameplate.\uDBE2\uDCB1\uDBC2\uDD72")) {
            perms.playerAdd("global", player, "meta.nameplate.\uDBE2\uDCB1\uDBC2\uDD72");
        }
        if (!perms.playerHas(player, "suffix.1.&f")) {
            perms.playerAdd("global", player, "suffix.1.&f");
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
        }
        PlayerInventory inventory = player.getInventory();
        if (!inventory.contains(getItemStack()) && inventory.getItem(8) == null) {
            inventory.setItem(8, getItemStack());
        }}, 100L);
    }
    @NotNull
    private static ItemStack getItemStack() {
        ItemStack menu_shortcut = new ItemStack(Material.GHAST_TEAR);
        ItemMeta meta = menu_shortcut.getItemMeta();
        meta.setCustomModelData(5);
        Component name = Component.text("Crystal Caverns Menu")
            .color(TextColor.color(10044671))
            .decoration(TextDecoration.BOLD,true)
            .decoration(TextDecoration.ITALIC,false)
            .append(Component.text(" \uDBE7\uDCE8")
                .color(NamedTextColor.WHITE)
                .decoration(TextDecoration.BOLD,false));
        meta.displayName(name);
        menu_shortcut.setItemMeta(meta);
        return menu_shortcut;
    }
}