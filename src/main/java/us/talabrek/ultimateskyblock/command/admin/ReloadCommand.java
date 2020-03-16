package us.talabrek.ultimateskyblock.command.admin;

import java.util.Map;
import org.bukkit.command.CommandSender;
import us.talabrek.ultimateskyblock.util.command.AbstractCommand;
import us.talabrek.ultimateskyblock.uSkyBlock;
import us.talabrek.ultimateskyblock.util.po.I18nUtil;
import static us.talabrek.ultimateskyblock.util.po.I18nUtil.marktr;

/**
 * Reloads the config-files for USB.
 */
public class ReloadCommand extends AbstractCommand {
    public ReloadCommand() {
        super("reload", "usb.admin.reload", marktr("reload configuration from file."));
    }

    @Override
    public boolean execute(CommandSender sender, String alias, Map<String, Object> data, String... args) {
        uSkyBlock.getInstance().reloadConfig();
        sender.sendMessage(I18nUtil.tr("\u00a7eConfiguration reloaded from file."));
        return true;
    }
}
