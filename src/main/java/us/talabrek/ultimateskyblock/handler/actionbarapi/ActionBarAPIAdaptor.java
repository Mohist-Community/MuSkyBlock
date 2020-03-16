package us.talabrek.ultimateskyblock.handler.actionbarapi;

import org.bukkit.entity.Player;
import red.mohist.api.TitleAPI;

/**
 * Runtime adaptor.
 */
public enum ActionBarAPIAdaptor {;
    public static void sendActionBar(Player player, String message) {
        TitleAPI.sendPlayerAbar(player, message);
    }
}
