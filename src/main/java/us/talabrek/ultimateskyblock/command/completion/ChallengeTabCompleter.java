package us.talabrek.ultimateskyblock.command.completion;

import java.util.List;
import org.bukkit.command.CommandSender;
import us.talabrek.ultimateskyblock.util.command.completion.AbstractTabCompleter;
import us.talabrek.ultimateskyblock.uSkyBlock;

/**
 * Lists all registered challenges.
 */
public class ChallengeTabCompleter extends AbstractTabCompleter {
    @Override
    protected List<String> getTabList(CommandSender commandSender, String term) {
        return uSkyBlock.getInstance().getChallengeLogic().getAllChallengeNames();
    }
}
