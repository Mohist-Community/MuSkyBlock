package us.talabrek.ultimateskyblock.util.command.completion;

import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

/**
 * TabCompleter that supports multiple tab-completers for different arguments.
 */
public class CompositeTabCompleter implements TabCompleter {
    private final TabCompleter[] completers;

    public CompositeTabCompleter(TabCompleter... completers) {
        this.completers = completers;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String alias, String[] args) {
        if (completers.length <= args.length) {
            return completers[args.length-1].onTabComplete(commandSender, command, alias, args);
        }
        return null;
    }
}
