package us.talabrek.ultimateskyblock.menu;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import us.talabrek.ultimateskyblock.util.file.FileUtil;
import us.talabrek.ultimateskyblock.util.yml.YmlConfiguration;

/**
 * Simple menu-less editor for boolean values.
 */
public class BooleanEditMenu extends AbstractConfigMenu implements EditMenu {
    public BooleanEditMenu(YmlConfiguration menuConfig) {
        super(menuConfig);
    }

    @Override
    public boolean onClick(InventoryClickEvent e) {
        // The boolean menu is never active
        return false;
    }

    @Override
    public Inventory createEditMenu(String configName, String path, int page) {
        YmlConfiguration config = FileUtil.getYmlConfiguration(configName);
        if (config.isBoolean(path)) {
            boolean value = config.getBoolean(path);
            config.set(path, !value);
            config.set("dirty", true);
        }
        // never returns an editor...
        return null;
    }
}
