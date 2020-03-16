package us.talabrek.ultimateskyblock.command.completion;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.talabrek.ultimateskyblock.util.command.completion.AbstractTabCompleter;

/**
 * Tab completion for players.
 */
public class OnlinePlayerTabCompleter extends AbstractTabCompleter {
    @Override
    public List<String> getTabList(CommandSender commandSender, String term) {
        List<String> list = new ArrayList<>();
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            list.add(player.getName());
        }
        return list;
    }
}
