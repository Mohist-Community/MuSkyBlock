package us.talabrek.ultimateskyblock.util.command.completion;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Convenience implementation of a tab-completer for all online players
 */
public class OnlinePlayerTabCompleter extends AbstractTabCompleter {
    @Override
    protected List<String> getTabList(CommandSender commandSender, String term) {
        List<String> tabList = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player != null && player.isOnline()) {
                tabList.add(player.getName());
            }
        }
        return tabList;
    }
}
