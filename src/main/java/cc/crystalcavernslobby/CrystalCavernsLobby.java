package cc.crystalcavernslobby;

import cc.crystalcavernslobby.NMS.Credits;
import fr.mrmicky.fastboard.FastBoard;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Duration;
import java.util.Objects;

public final class CrystalCavernsLobby extends JavaPlugin {
    public static Permission perms = null;
    public static void getPermissions() {}
    private void setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = Objects.requireNonNull(rsp).getProvider();
    }
    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        Objects.requireNonNull(getCommand("crystalcaverns")).setExecutor(new MainCommand());
        Objects.requireNonNull(getCommand("claim")).setExecutor(new ClaimCommand());
        Objects.requireNonNull(getCommand("profile")).setExecutor(new ProfileCommand());
        Objects.requireNonNull(getCommand("credits")).setExecutor(new CreditsCommand());
        Objects.requireNonNull(getCommand("homes")).setExecutor(new HomesCommand());
        Objects.requireNonNull(getCommand("crystalpass")).setExecutor(new CrystalPassCommand());
        Objects.requireNonNull(getCommand("spawn")).setExecutor(new SpawnCommand());
        Objects.requireNonNull(getCommand("purchase")).setExecutor(new PurchaseCommand());
        Objects.requireNonNull(getCommand("reward")).setExecutor(new RewardCommand());
        Objects.requireNonNull(getCommand("points")).setExecutor(new PointsCommand());
        Objects.requireNonNull(getCommand("menu")).setExecutor(new MenuCommand());
        Objects.requireNonNull(getCommand("store")).setExecutor(new StoreCommand());
        getServer().getPluginManager().registerEvents(new PlayerJoin(),this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(),this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(),this);
        getServer().getPluginManager().registerEvents(new BlockPlace(),this);
        getServer().getPluginManager().registerEvents(new PlayerDropItem(),this);
        getLogger().info("Crystal Caverns Lobby plugin loaded successfully!");
        setupPermissions();
        Credits.init();
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getScoreboardTags().contains("inside_portal")) {
                    if (player.getEyeLocation().getBlock().getType() == Material.STRUCTURE_VOID) {
                        player.showTitle(Title.title(Component.text("\uDBEA\uDDE8"), Component.empty(), Title.Times.times(Duration.ZERO, Duration.ofMillis(2000), Duration.ofMillis(500))));
                    }
                    if (player.getEyeLocation().getBlock().getType() != Material.STRUCTURE_VOID) {
                        player.removeScoreboardTag("inside_portal");
                    }
                }
                if (Boolean.parseBoolean(plugin.getConfig().getString("disableInteractions"))) {
                    if (player.getLocation().getBlockY() <= 215) {
                        player.performCommand("spawn");
                    }
                }
            }
        }, 30L, 30L);
    }
    public static Plugin getPlugin() {
        return plugin;
    }
    public static JavaPlugin plugin;
    
    public static void dialog(Player player, String row1, String row2, String row3, String row4, String row5, String row6) {
        FastBoard board = new FastBoard(player);
        board.updateLines("\uDBE5\uDC3A" + row1,"\uDBE5\uDC3B" + row2,"\uDBE5\uDC3C" + row3,"\uDBE5\uDC3D" + row4,"\uDBE5\uDC3E" + row5,"\uDBE5\uDC3F" + row6,"\uDBE5\uDC9A\uDBCD\uDE10");
        Bukkit.getScheduler().runTaskLater(plugin, board::delete, 200L);
    }
}