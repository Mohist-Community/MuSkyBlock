package us.talabrek.ultimateskyblock.command.completion;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.CommandSender;
import us.talabrek.ultimateskyblock.util.command.completion.AbstractTabCompleter;
import us.talabrek.ultimateskyblock.command.island.BiomeCommand;

/**
 * TabCompleter for Biomes.
 */
public class BiomeTabCompleter extends AbstractTabCompleter {
    private static final List<String> BIOMES = new ArrayList<>(BiomeCommand.BIOMES.keySet());

    @Override
    protected List<String> getTabList(CommandSender commandSender, String term) {
        return filter(BIOMES, term);
    }
}
