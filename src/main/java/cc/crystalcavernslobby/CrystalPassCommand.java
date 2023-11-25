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

public class CrystalPassCommand implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("§cOnly a player can execute this command!");
            return false;
        }
        if (args.length <= 1) {
            Integer page = 1;
            if (args.length == 1 && args[0].matches("[1-7]")) {
                page = Integer.parseInt(args[0]);
            }
            File configPath = new File("/home/container/plugins/CrystalCavernsLobby/crystalpass.yml");
            FileConfiguration CrystalPassConfig = new YamlConfiguration();
            Panel panel;
            CachedDataManager cachedDataManager = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(p).getCachedData();
            try {
                CrystalPassConfig.load(configPath);
            } catch (IOException | InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }
            if (cachedDataManager.getPermissionData().checkPermission("crystal_pass_plus").asBoolean()) {
                File file = new File("/home/container/plugins/CommandPanels/panels/crystal_pass_plus_" + page + ".yml");
                panel = new Panel(file, "crystal_pass_plus_" + page);
            } else {
                File file = new File("/home/container/plugins/CommandPanels/panels/crystal_pass_" + page + ".yml");
                panel = new Panel(file, "crystal_pass_" + page);
            }
            PanelPlaceholders placeholders = panel.placeholders;
            //COST OF LEVEL UPGRADE
            //COST OF LEVEL UPGRADE
            int level_cost = 100;
            //COST OF LEVEL UPGRADE
            //COST OF LEVEL UPGRADE
            String progress_bar_template = "\uDBED\uDC5C\uDBFA\uDF35";
            int points = Integer.parseInt(cachedDataManager.getMetaData().getMetaValue("crystal_pass_points"));
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
                placeholders.addPlaceholder("reward0_material", CrystalPassConfig.getString("free.reward0.material"));
                placeholders.addPlaceholder("reward0_name", CrystalPassConfig.getString("free.reward0.name"));
                placeholders.addPlaceholder("reward0_lore1", CrystalPassConfig.getString("free.reward0.lore.line1"));
                placeholders.addPlaceholder("reward0_lore2", CrystalPassConfig.getString("free.reward0.lore.line2"));
                placeholders.addPlaceholder("reward0_lore3", CrystalPassConfig.getString("free.reward0.lore.line3"));
                placeholders.addPlaceholder("reward0_lore4", CrystalPassConfig.getString("free.reward0.lore.line4"));
                placeholders.addPlaceholder("reward0_lore5", CrystalPassConfig.getString("free.reward0.lore.line5"));
                placeholders.addPlaceholder("reward0_customdata", CrystalPassConfig.getString("free.reward0.customdata"));
                placeholders.addPlaceholder("reward0_leatherarmor", CrystalPassConfig.getString("free.reward0.leatherarmor"));
                placeholders.addPlaceholder("reward0_command", CrystalPassConfig.getString("free.reward0.command"));
                //PLUS REWARD
                placeholders.addPlaceholder("plus_reward0_material", CrystalPassConfig.getString("plus.reward0.material"));
                placeholders.addPlaceholder("plus_reward0_name", CrystalPassConfig.getString("plus.reward0.name"));
                placeholders.addPlaceholder("plus_reward0_lore1", CrystalPassConfig.getString("plus.reward0.lore.line1"));
                placeholders.addPlaceholder("plus_reward0_lore2", CrystalPassConfig.getString("plus.reward0.lore.line2"));
                placeholders.addPlaceholder("plus_reward0_lore3", CrystalPassConfig.getString("plus.reward0.lore.line3"));
                placeholders.addPlaceholder("plus_reward0_lore4", CrystalPassConfig.getString("plus.reward0.lore.line4"));
                placeholders.addPlaceholder("plus_reward0_lore5", CrystalPassConfig.getString("plus.reward0.lore.line5"));
                placeholders.addPlaceholder("plus_reward0_customdata", CrystalPassConfig.getString("plus.reward0.customdata"));
                placeholders.addPlaceholder("plus_reward0_leatherarmor", CrystalPassConfig.getString("plus.reward0.leatherarmor"));
                placeholders.addPlaceholder("plus_reward0_command", CrystalPassConfig.getString("plus.reward0.command"));
            }
            //LAST PAGE EXCEPTION
            if (page.equals(7)) {
                if (level >= 50) {
                    repeat = repeat + 18;
                } else if (next_level == 50) {
                    double progress_divided = leftover_points / divider;
                    int progress = Math.toIntExact(Math.round(progress_divided));
                    repeat = repeat + progress;
                }
                placeholders.addPlaceholder("reward50_cost", String.valueOf((50 * level_cost) - (level * level_cost)));
                placeholders.addPlaceholder("reward50_tiers", String.valueOf(50 - level));
                placeholders.addPlaceholder("reward50_tier", String.valueOf(50));
                //FREE REWARD
                placeholders.addPlaceholder("reward50_material", CrystalPassConfig.getString("free.reward50.material"));
                placeholders.addPlaceholder("reward50_name", CrystalPassConfig.getString("free.reward50.name"));
                placeholders.addPlaceholder("reward50_lore1", CrystalPassConfig.getString("free.reward50.lore.line1"));
                placeholders.addPlaceholder("reward50_lore2", CrystalPassConfig.getString("free.reward50.lore.line2"));
                placeholders.addPlaceholder("reward50_lore3", CrystalPassConfig.getString("free.reward50.lore.line3"));
                placeholders.addPlaceholder("reward50_lore4", CrystalPassConfig.getString("free.reward50.lore.line4"));
                placeholders.addPlaceholder("reward50_lore5", CrystalPassConfig.getString("free.reward50.lore.line5"));
                placeholders.addPlaceholder("reward50_customdata", CrystalPassConfig.getString("free.reward50.customdata"));
                placeholders.addPlaceholder("reward50_leatherarmor", CrystalPassConfig.getString("free.reward50.leatherarmor"));
                placeholders.addPlaceholder("reward50_command", CrystalPassConfig.getString("free.reward50.command"));
                //PLUS REWARD
                placeholders.addPlaceholder("plus_reward50_material", CrystalPassConfig.getString("plus.reward50.material"));
                placeholders.addPlaceholder("plus_reward50_name", CrystalPassConfig.getString("plus.reward50.name"));
                placeholders.addPlaceholder("plus_reward50_lore1", CrystalPassConfig.getString("plus.reward50.lore.line1"));
                placeholders.addPlaceholder("plus_reward50_lore2", CrystalPassConfig.getString("plus.reward50.lore.line2"));
                placeholders.addPlaceholder("plus_reward50_lore3", CrystalPassConfig.getString("plus.reward50.lore.line3"));
                placeholders.addPlaceholder("plus_reward50_lore4", CrystalPassConfig.getString("plus.reward50.lore.line4"));
                placeholders.addPlaceholder("plus_reward50_lore5", CrystalPassConfig.getString("plus.reward50.lore.line5"));
                placeholders.addPlaceholder("plus_reward50_customdata", CrystalPassConfig.getString("plus.reward50.customdata"));
                placeholders.addPlaceholder("plus_reward50_leatherarmor", CrystalPassConfig.getString("plus.reward50.leatherarmor"));
                placeholders.addPlaceholder("plus_reward50_command", CrystalPassConfig.getString("plus.reward50.command"));
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
                placeholders.addPlaceholder("reward" + reward + "_cost", String.valueOf((reward * level_cost) - (level * level_cost)));
                placeholders.addPlaceholder("reward" + reward + "_tiers", String.valueOf(reward - level));
                placeholders.addPlaceholder("reward" + reward + "_tier", String.valueOf(reward));
                //FREE REWARD
                placeholders.addPlaceholder("reward" + reward + "_material", CrystalPassConfig.getString("free.reward" + reward + ".material"));
                placeholders.addPlaceholder("reward" + reward + "_name", CrystalPassConfig.getString("free.reward" + reward + ".name"));
                placeholders.addPlaceholder("reward" + reward + "_lore1", CrystalPassConfig.getString("free.reward" + reward + ".lore.line1"));
                placeholders.addPlaceholder("reward" + reward + "_lore2", CrystalPassConfig.getString("free.reward" + reward + ".lore.line2"));
                placeholders.addPlaceholder("reward" + reward + "_lore3", CrystalPassConfig.getString("free.reward" + reward + ".lore.line3"));
                placeholders.addPlaceholder("reward" + reward + "_lore4", CrystalPassConfig.getString("free.reward" + reward + ".lore.line4"));
                placeholders.addPlaceholder("reward" + reward + "_lore5", CrystalPassConfig.getString("free.reward" + reward + ".lore.line5"));
                placeholders.addPlaceholder("reward" + reward + "_customdata", CrystalPassConfig.getString("free.reward" + reward + ".customdata"));
                placeholders.addPlaceholder("reward" + reward + "_leatherarmor", CrystalPassConfig.getString("free.reward" + reward + ".leatherarmor"));
                placeholders.addPlaceholder("reward" + reward + "_command", CrystalPassConfig.getString("free.reward" + reward + ".command"));
                //PLUS REWARD
                placeholders.addPlaceholder("plus_reward" + reward + "_material", CrystalPassConfig.getString("plus.reward" + reward + ".material"));
                placeholders.addPlaceholder("plus_reward" + reward + "_name", CrystalPassConfig.getString("plus.reward" + reward + ".name"));
                placeholders.addPlaceholder("plus_reward" + reward + "_lore1", CrystalPassConfig.getString("plus.reward" + reward + ".lore.line1"));
                placeholders.addPlaceholder("plus_reward" + reward + "_lore2", CrystalPassConfig.getString("plus.reward" + reward + ".lore.line2"));
                placeholders.addPlaceholder("plus_reward" + reward + "_lore3", CrystalPassConfig.getString("plus.reward" + reward + ".lore.line3"));
                placeholders.addPlaceholder("plus_reward" + reward + "_lore4", CrystalPassConfig.getString("plus.reward" + reward + ".lore.line4"));
                placeholders.addPlaceholder("plus_reward" + reward + "_lore5", CrystalPassConfig.getString("plus.reward" + reward + ".lore.line5"));
                placeholders.addPlaceholder("plus_reward" + reward + "_customdata", CrystalPassConfig.getString("plus.reward" + reward + ".customdata"));
                placeholders.addPlaceholder("plus_reward" + reward + "_leatherarmor", CrystalPassConfig.getString("plus.reward" + reward + ".leatherarmor"));
                placeholders.addPlaceholder("plus_reward" + reward + "_command", CrystalPassConfig.getString("plus.reward" + reward + ".command"));
            }
            String progress_bar = progress_bar_template.repeat(repeat);
            placeholders.addPlaceholder("progress_bar", progress_bar);
            placeholders.addPlaceholder("level", String.valueOf(level));
            panel.open(p, PanelPosition.Top);
        } else {
            p.sendMessage("§f\uDBCB\uDDAB §bCommand usage: §7/crystalpass [page]");
        } return false;
    }
}