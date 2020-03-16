package us.talabrek.ultimateskyblock.util.command.completion;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

/**
 * Convenience implementation of a tab-completer for all offline players
 */
public class OfflinePlayerTabCompleter extends AbstractTabCompleter {
    @Override
    protected List<String> getTabList(CommandSender commandSender, String term) {
        List<String> tabList = new ArrayList<>();
        for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
            if (player != null) {
                tabList.add(player.getName());
            }
        }
        return tabList;
    }
}
