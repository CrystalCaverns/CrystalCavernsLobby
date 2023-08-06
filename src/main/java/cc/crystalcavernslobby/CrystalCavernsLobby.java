package cc.crystalcavernslobby;

import cc.crystalcavernslobby.NMS.Credits;
import de.themoep.minedown.MineDown;
import dev.sergiferry.playernpc.api.NPCLib;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Duration;
import java.util.*;

public final class CrystalCavernsLobby extends JavaPlugin {
    public static List<UUID> toSend = new ArrayList<>();
    public static Permission perms = null;
    public static void getPermissions() {}
    private void setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = Objects.requireNonNull(rsp).getProvider();
    }
    @Override
    public void onEnable() {
        saveDefaultConfig();
        NPCLib.getInstance().registerPlugin(this);
        Objects.requireNonNull(getCommand("crystalcaverns")).setExecutor(new ReloadCommand());
        Objects.requireNonNull(getCommand("claim")).setExecutor(new ClaimCommand());
        Objects.requireNonNull(getCommand("profile")).setExecutor(new ProfileCommand());
        Objects.requireNonNull(getCommand("credits")).setExecutor(new CreditsCommand());
        Objects.requireNonNull(getCommand("homes")).setExecutor(new HomesCommand());
        Objects.requireNonNull(getCommand("treasury")).setExecutor(new TreasuryCommand());
        Objects.requireNonNull(getCommand("switchserver")).setExecutor(new SwitchServerCommand());
        Objects.requireNonNull(getCommand("battlepass")).setExecutor(new BattlePassCommand());
        Objects.requireNonNull(getCommand("spawn")).setExecutor(new SpawnCommand());
        Objects.requireNonNull(getCommand("menu")).setExecutor(new MenuCommand());
        getServer().getPluginManager().registerEvents(new PlayerJoin(),this);
        getServer().getPluginManager().registerEvents(new MobDeath(),this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(),this);
        getServer().getPluginManager().registerEvents(new BlockPlace(),this);
        getServer().getPluginManager().registerEvents(new NPCLook(),this);
        getLogger().info("Crystal Caverns Lobby plugin loaded successfully!");
        setupPermissions();
        Credits.init();
        plugin = this;
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (toSend.contains(player.getUniqueId())) {
                    String message = "&#bbbbbb&%coinsengine_balance_crystals% &white&\uDBEB\uDEEA      &#9944ff&%coinsengine_balance_gemstones% &white&\uDBCB\uDD10";
                    message = PlaceholderAPI.setPlaceholders(player,message);
                    TextComponent formatted_message = new TextComponent(MineDown.parse(message));
                    formatted_message.setFont("currency_display");
                    player.sendMessage(ChatMessageType.ACTION_BAR,formatted_message);
                }
                if (player.getEyeLocation().getBlock().getType() == Material.STRUCTURE_VOID) {
                    player.showTitle(Title.title(Component.text("\uDBEA\uDDE8"),Component.empty(),Title.Times.times(Duration.ZERO,Duration.ofMillis(2000),Duration.ofMillis(500))));
                }
                if (player.getScoreboardTags().contains("inside_portal") && player.getEyeLocation().getBlock().getType() != Material.STRUCTURE_VOID) {
                    player.removeScoreboardTag("inside_portal");
                }
            }
        }, 30L, 30L);
    }
    public static Plugin getPlugin() {
        return plugin;
    }
    public static JavaPlugin plugin;
}