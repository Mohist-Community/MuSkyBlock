package us.talabrek.ultimateskyblock.imports;

import java.util.List;
import org.bukkit.command.CommandSender;
import us.talabrek.ultimateskyblock.uSkyBlock;
import us.talabrek.ultimateskyblock.util.command.completion.AbstractTabCompleter;

/**
 * Tab support for the registered importers.
 */
public class ImportTabCompleter extends AbstractTabCompleter {
    @Override
    protected List<String> getTabList(CommandSender commandSender, String term) {
        return uSkyBlock.getInstance().getImporter().getImporterNames();
    }
}
