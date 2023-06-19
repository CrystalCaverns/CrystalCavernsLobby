package cc.crystalcavernslobby;

import cc.crystalcavernslobby.NMS.Credits;
import de.themoep.minedown.MineDown;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class CrystalCavernsLobby extends JavaPlugin {
    public static List<UUID> toSend = new ArrayList<>();
    public static Permission perms = null;
    public static void getPermissions() {
    }
    private void setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = Objects.requireNonNull(rsp).getProvider();
    }
    @Override
    public void onEnable() {
        saveDefaultConfig();
        Objects.requireNonNull(getCommand("crystalcaverns")).setExecutor(new ReloadCommand());
        Objects.requireNonNull(getCommand("claim")).setExecutor(new ClaimCommand());
        Objects.requireNonNull(getCommand("profile")).setExecutor(new ProfileCommand());
        Objects.requireNonNull(getCommand("credits")).setExecutor(new CreditsCommand());
        getServer().getPluginManager().registerEvents(new PlayerJoin(),this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(),this);
        getServer().getPluginManager().registerEvents(new BlockPlace(),this);
        getLogger().info("Crystal Caverns Lobby plugin loaded successfully!");
        setupPermissions();
        Credits.init();
        plugin = this;
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (toSend.contains(player.getUniqueId())) {
                    String message = "&#bbbbbb&%vault_eco_balance% &white&\uDBEB\uDEEA      &#9944ff&%gamepoints_balance% &white&\uDBCB\uDD10";
                    message = PlaceholderAPI.setPlaceholders(player,message);
                    TextComponent formatted_message = new TextComponent(MineDown.parse(message));
                    formatted_message.setFont("currency_display");
                    player.sendMessage(ChatMessageType.ACTION_BAR,formatted_message);
                }
            }
        }, 30L, 30L);
    }
    public static Plugin getPlugin() {
        return plugin;
    }
    public static JavaPlugin plugin;
}

