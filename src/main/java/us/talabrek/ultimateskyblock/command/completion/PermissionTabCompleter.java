package us.talabrek.ultimateskyblock.command.completion;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.CommandSender;
import us.talabrek.ultimateskyblock.menu.PartyPermissionMenuItem;
import us.talabrek.ultimateskyblock.uSkyBlock;
import us.talabrek.ultimateskyblock.util.command.completion.AbstractTabCompleter;

public class PermissionTabCompleter extends AbstractTabCompleter {
    private uSkyBlock plugin;

    public PermissionTabCompleter(uSkyBlock plugin) {
        this.plugin = plugin;
    }

    @Override
    protected List<String> getTabList(CommandSender commandSender, String term) {
        List<String> list = new ArrayList<>();
        for (PartyPermissionMenuItem item : plugin.getMenu().getPermissionMenuItems()) {
            list.add(item.getPerm());
        }
        return list;
    }

}
