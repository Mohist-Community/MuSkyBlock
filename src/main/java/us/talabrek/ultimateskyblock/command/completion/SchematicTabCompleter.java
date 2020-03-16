package us.talabrek.ultimateskyblock.command.completion;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.talabrek.ultimateskyblock.uSkyBlock;
import us.talabrek.ultimateskyblock.util.command.completion.AbstractTabCompleter;

/**
 */
public class SchematicTabCompleter extends AbstractTabCompleter {
    private final uSkyBlock plugin;

    public SchematicTabCompleter(uSkyBlock plugin) {
        this.plugin = plugin;
    }

    @Override
    protected List<String> getTabList(CommandSender commandSender, String term) {
        if (commandSender instanceof Player) {
            return new ArrayList<>(plugin.getPerkLogic().getSchemes((Player) commandSender));
        }
        return plugin.getIslandGenerator().getSchemeNames();
    }
}
