package us.talabrek.ultimateskyblock.command.admin;

import java.util.Map;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.talabrek.ultimateskyblock.uSkyBlock;
import us.talabrek.ultimateskyblock.util.command.AbstractCommand;
import us.talabrek.ultimateskyblock.util.po.I18nUtil;
import static us.talabrek.ultimateskyblock.util.po.I18nUtil.marktr;

/**
 * Registers an island to a player.
 */
public class RegisterIslandToPlayerCommand extends AbstractCommand {
    public RegisterIslandToPlayerCommand() {
        super("register", "usb.admin.register", "player", marktr("set a player''s island to your location"));
    }
    @Override
    public boolean execute(final CommandSender sender, String alias, Map<String, Object> data, final String... args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        if (args.length < 1) {
            return false;
        }
        String playerName = args[0];
        Player player = (Player) sender;
        if (uSkyBlock.getInstance().devSetPlayerIsland(player, player.getLocation(), playerName)) {
            sender.sendMessage(I18nUtil.tr("\u00a7aSet {0}''s island to the current island.", playerName));
        } else {
            sender.sendMessage(I18nUtil.tr("\u00a74Island not found: unable to set the island!"));
        }
        return true;
    }
}
