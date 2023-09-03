package cc.crystalcavernslobby;

import me.rockyhawk.commandpanels.api.Panel;
import me.rockyhawk.commandpanels.classresources.placeholders.PanelPlaceholders;
import me.rockyhawk.commandpanels.openpanelsmanager.PanelPosition;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedDataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class BattlePassCommand implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly a player can execute this command!");
        }
        Player p = (Player) sender;
        if (args.length <= 1) {
            Integer page = 1;
            if (args.length == 1 && args[0].matches("[1-7]")) {
                page = Integer.parseInt(args[0]);
            }
            File configPath = new File("/home/container/plugins/CrystalCavernsLobby/battlepass.yml");
            FileConfiguration battlePassConfig = new YamlConfiguration();
            Panel panel;
            CachedDataManager cachedDataManager = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(p).getCachedData();
            try {
                battlePassConfig.load(configPath);
            } catch (IOException | InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }
            if (cachedDataManager.getPermissionData().checkPermission("premium_battle_pass").asBoolean()) {
                File file = new File("/home/container/plugins/CommandPanels/panels/battle_pass_premium_" + page + ".yml");
                panel = new Panel(file, "battle_pass_premium_" + page);
            } else {
                File file = new File("/home/container/plugins/CommandPanels/panels/battle_pass_" + page + ".yml");
                panel = new Panel(file, "battle_pass_" + page);
            }
            PanelPlaceholders placeholders = panel.placeholders;
            String progress_bar_template = "\uDBED\uDC5C\uDBFA\uDF35";
            int points = Integer.parseInt(cachedDataManager.getMetaData().getMetaValue("battle_pass_points"));
            double level_index = (double) points / 1000;
            int level = (int) Math.floor(level_index);
            int points_for_level = level * 1000;
            int leftover_points = points - points_for_level;
            int next_level = level + 1;
            double divider = 55.555;
            int repeat = 0;
            //FIRST PAGE EXCEPTION
            if (page.equals(1)) {
                //PROGRESS BAR
                if (level == 0) {
                    double progress_divided = points / divider;
                    int progress = Math.toIntExact(Math.round(progress_divided));
                    repeat = repeat + progress;
                }
                if (level >= 1) {
                    repeat = repeat + 18;
                }
                //FREE REWARD
                placeholders.addPlaceholder("reward0_material", battlePassConfig.getString("free.reward0.material"));
                placeholders.addPlaceholder("reward0_name", battlePassConfig.getString("free.reward0.name"));
                placeholders.addPlaceholder("reward0_lore1", battlePassConfig.getString("free.reward0.lore.line1"));
                placeholders.addPlaceholder("reward0_lore2", battlePassConfig.getString("free.reward0.lore.line2"));
                placeholders.addPlaceholder("reward0_lore3", battlePassConfig.getString("free.reward0.lore.line3"));
                placeholders.addPlaceholder("reward0_lore4", battlePassConfig.getString("free.reward0.lore.line4"));
                placeholders.addPlaceholder("reward0_lore5", battlePassConfig.getString("free.reward0.lore.line5"));
                placeholders.addPlaceholder("reward0_customdata", battlePassConfig.getString("free.reward0.customdata"));
                placeholders.addPlaceholder("reward0_command", battlePassConfig.getString("free.reward0.command"));
                //PREMIUM REWARD
                placeholders.addPlaceholder("premium_reward0_material", battlePassConfig.getString("premium.reward0.material"));
                placeholders.addPlaceholder("premium_reward0_name", battlePassConfig.getString("premium.reward0.name"));
                placeholders.addPlaceholder("premium_reward0_lore1", battlePassConfig.getString("premium.reward0.lore.line1"));
                placeholders.addPlaceholder("premium_reward0_lore2", battlePassConfig.getString("premium.reward0.lore.line2"));
                placeholders.addPlaceholder("premium_reward0_lore3", battlePassConfig.getString("premium.reward0.lore.line3"));
                placeholders.addPlaceholder("premium_reward0_lore4", battlePassConfig.getString("premium.reward0.lore.line4"));
                placeholders.addPlaceholder("premium_reward0_lore5", battlePassConfig.getString("premium.reward0.lore.line5"));
                placeholders.addPlaceholder("premium_reward0_customdata", battlePassConfig.getString("premium.reward0.customdata"));
                placeholders.addPlaceholder("premium_reward0_command", battlePassConfig.getString("premium.reward0.command"));
            }
            //LAST PAGE EXCEPTION
            if (page.equals(7)) {
                //FREE REWARD
                if (level >= 50) {
                    repeat = repeat + 18;
                } else if (next_level == 50) {
                    double progress_divided = leftover_points / divider;
                    int progress = Math.toIntExact(Math.round(progress_divided));
                    repeat = repeat + progress;
                }
                placeholders.addPlaceholder("reward50_material", battlePassConfig.getString("free.reward50.material"));
                placeholders.addPlaceholder("reward50_name", battlePassConfig.getString("free.reward50.name"));
                placeholders.addPlaceholder("reward50_lore1", battlePassConfig.getString("free.reward50.lore.line1"));
                placeholders.addPlaceholder("reward50_lore2", battlePassConfig.getString("free.reward50.lore.line2"));
                placeholders.addPlaceholder("reward50_lore3", battlePassConfig.getString("free.reward50.lore.line3"));
                placeholders.addPlaceholder("reward50_lore4", battlePassConfig.getString("free.reward50.lore.line4"));
                placeholders.addPlaceholder("reward50_lore5", battlePassConfig.getString("free.reward50.lore.line5"));
                placeholders.addPlaceholder("reward50_customdata", battlePassConfig.getString("free.reward50.customdata"));
                placeholders.addPlaceholder("reward50_command", battlePassConfig.getString("free.reward50.command"));
                //PREMIUM REWARD
                placeholders.addPlaceholder("premium_reward50_material", battlePassConfig.getString("premium.reward50.material"));
                placeholders.addPlaceholder("premium_reward50_name", battlePassConfig.getString("premium.reward50.name"));
                placeholders.addPlaceholder("premium_reward50_lore1", battlePassConfig.getString("premium.reward50.lore.line1"));
                placeholders.addPlaceholder("premium_reward50_lore2", battlePassConfig.getString("premium.reward50.lore.line2"));
                placeholders.addPlaceholder("premium_reward50_lore3", battlePassConfig.getString("premium.reward50.lore.line3"));
                placeholders.addPlaceholder("premium_reward50_lore4", battlePassConfig.getString("premium.reward50.lore.line4"));
                placeholders.addPlaceholder("premium_reward50_lore5", battlePassConfig.getString("premium.reward50.lore.line5"));
                placeholders.addPlaceholder("premium_reward50_customdata", battlePassConfig.getString("premium.reward50.customdata"));
                placeholders.addPlaceholder("premium_reward50_command", battlePassConfig.getString("premium.reward50.command"));
            }
            //HANDLE ALL OTHER PAGES
            for (int i = 1; i <= 7; i++) {
                int multiplier = page - 1;
                int addend = 7 * multiplier;
                int reward = i + addend;
                if (level > reward && i < 7) {
                    repeat = repeat + 18;
                } else if (reward == level && level != 0 && i < 7) {
                    double progress_divided = leftover_points / divider;
                    int progress = Math.toIntExact(Math.round(progress_divided));
                    repeat = repeat + progress;
                }
                if (i == 7 & page < 7) {
                    if (level > reward) {
                        repeat = repeat + 8;
                    } else if (level == reward) {
                        double progress_divided = leftover_points / divider;
                        int progress = Math.toIntExact(Math.round(progress_divided));
                        if (progress <= 8) {
                            repeat = repeat + progress;
                        } else {
                            repeat = repeat + 8;
                        }
                    }
                }
                if (i == 1 & page > 1) {
                    if (level >= reward) {
                        repeat = repeat + 8;
                    } else if (reward == next_level) {
                        double progress_divided = leftover_points / divider;
                        int progress = Math.toIntExact(Math.round(progress_divided));
                        if (progress > 8) {
                            int progress_second = progress - 8;
                            repeat = repeat + progress_second;
                        }
                    }
                }
                //FREE REWARD
                placeholders.addPlaceholder("reward" + reward + "_material", battlePassConfig.getString("free.reward" + reward + ".material"));
                placeholders.addPlaceholder("reward" + reward + "_name", battlePassConfig.getString("free.reward" + reward + ".name"));
                placeholders.addPlaceholder("reward" + reward + "_lore1", battlePassConfig.getString("free.reward" + reward + ".lore.line1"));
                placeholders.addPlaceholder("reward" + reward + "_lore2", battlePassConfig.getString("free.reward" + reward + ".lore.line2"));
                placeholders.addPlaceholder("reward" + reward + "_lore3", battlePassConfig.getString("free.reward" + reward + ".lore.line3"));
                placeholders.addPlaceholder("reward" + reward + "_lore4", battlePassConfig.getString("free.reward" + reward + ".lore.line4"));
                placeholders.addPlaceholder("reward" + reward + "_lore5", battlePassConfig.getString("free.reward" + reward + ".lore.line5"));
                placeholders.addPlaceholder("reward" + reward + "_customdata", battlePassConfig.getString("free.reward" + reward + ".customdata"));
                placeholders.addPlaceholder("reward" + reward + "_command", battlePassConfig.getString("free.reward" + reward + ".command"));
                //PREMIUM REWARD
                placeholders.addPlaceholder("premium_reward" + reward + "_material", battlePassConfig.getString("premium.reward" + reward + ".material"));
                placeholders.addPlaceholder("premium_reward" + reward + "_name", battlePassConfig.getString("premium.reward" + reward + ".name"));
                placeholders.addPlaceholder("premium_reward" + reward + "_lore1", battlePassConfig.getString("premium.reward" + reward + ".lore.line1"));
                placeholders.addPlaceholder("premium_reward" + reward + "_lore2", battlePassConfig.getString("premium.reward" + reward + ".lore.line2"));
                placeholders.addPlaceholder("premium_reward" + reward + "_lore3", battlePassConfig.getString("premium.reward" + reward + ".lore.line3"));
                placeholders.addPlaceholder("premium_reward" + reward + "_lore4", battlePassConfig.getString("premium.reward" + reward + ".lore.line4"));
                placeholders.addPlaceholder("premium_reward" + reward + "_lore5", battlePassConfig.getString("premium.reward" + reward + ".lore.line5"));
                placeholders.addPlaceholder("premium_reward" + reward + "_customdata", battlePassConfig.getString("premium.reward" + reward + ".customdata"));
                placeholders.addPlaceholder("premium_reward" + reward + "_command", battlePassConfig.getString("premium.reward" + reward + ".command"));
            }
            String progress_bar = progress_bar_template.repeat(repeat);
            placeholders.addPlaceholder("progress_bar", progress_bar);
            placeholders.addPlaceholder("level", String.valueOf(level));
            panel.open(p, PanelPosition.Top);
        } else {
            p.sendMessage("§f\uDBCB\uDDAB §bCommand usage: §7/battlepass [page]");
        } return false;
    }
}