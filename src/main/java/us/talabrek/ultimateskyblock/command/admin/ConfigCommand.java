package us.talabrek.ultimateskyblock.command.admin;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import us.talabrek.ultimateskyblock.uSkyBlock;
import us.talabrek.ultimateskyblock.util.command.AbstractCommand;
import us.talabrek.ultimateskyblock.util.command.CompositeCommand;
import us.talabrek.ultimateskyblock.util.command.completion.AbstractTabCompleter;
import us.talabrek.ultimateskyblock.util.file.FileUtil;
import static us.talabrek.ultimateskyblock.util.po.I18nUtil.marktr;
import static us.talabrek.ultimateskyblock.util.po.I18nUtil.tr;
import us.talabrek.ultimateskyblock.util.yml.YmlConfiguration;

/**
 * Command for showing the config-gui.
 */
public class ConfigCommand extends CompositeCommand {
    private final uSkyBlock plugin;
    public static final List<String> CONFIGS = Arrays.asList("config", "levelConfig", "challenges", "signs");

    public ConfigCommand(uSkyBlock plugin) {
        super("config|c", "usb.admin.config", "?config", marktr("open GUI for config"));
        add(new AbstractCommand("search", marktr("searches config for a specific key")) {
            @Override
            public boolean execute(CommandSender commandSender, String alias, Map<String, Object> map, String... args) {
                if (args.length > 0) {
                    YmlConfiguration config = FileUtil.getYmlConfiguration(map.get("config") + ".yml");
                    String searchTerms = String.join(" ", args);
                    List<String> results = config.getKeys(true).stream()
                            .filter(f -> f.contains(searchTerms))
                            .map(m -> tr("\u00a79{0}\u00a78: \u00a7e{1}", m.replace(searchTerms, tr("\u00a7c{0}\u00a79", searchTerms)), ConfigCommand.toString(config, m)))
                            .collect(Collectors.toList());
                    results.add(0, tr("Found the following matching {0}:", searchTerms));
                    commandSender.sendMessage(results.toArray(new String[0]));
                    return true;
                }
                return false;
            }
        });
        this.plugin = plugin;
    }

    private static String toString(YmlConfiguration config, String key) {
        if (config.isConfigurationSection(key)) {
            return tr("\u00a7a<section>");
        } else if (config.isDouble(key)) {
            return tr("\u00a73{0,number,#.##}", config.getDouble(key));
        } else if (config.isBoolean(key)) {
            return tr("\u00a72{0}", config.getBoolean(key));
        } else if (config.isList(key)) {
            return "\n - " + String.join("\n - ", config.getStringList(key));
        }
        return config.get(key).toString();
    }

    @Override
    public TabCompleter getTabCompleter() {
        return new AbstractTabCompleter() {
            @Override
            protected List<String> getTabList(CommandSender commandSender, String term) {
                return CONFIGS;
            }
        };
    }

    @Override
    public boolean execute(CommandSender sender, String alias, Map<String, Object> data, String... args) {
        if (args.length > 0 && CONFIGS.contains(args[0])) {
            data.put("config", args[0]);
        }
        if (sender instanceof Player && args.length <= 1) {
            String configName = "config";
            if (args.length > 0) {
                if (CONFIGS.contains(args[0])) {
                    configName = args[0];
                } else {
                    sender.sendMessage(tr("\u00a7eInvalid configuration name"));
                    return false;
                }
            }
            plugin.getConfigMenu().showMenu((Player) sender, configName + ".yml", 1);
            return true;
        }
        return super.execute(sender, alias, data, args);
    }
}
