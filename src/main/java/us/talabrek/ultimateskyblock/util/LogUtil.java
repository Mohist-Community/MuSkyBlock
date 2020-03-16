package us.talabrek.ultimateskyblock.util;

import java.util.logging.Level;
import us.talabrek.ultimateskyblock.uSkyBlock;

public enum LogUtil { ;
    public static void log(Level level, String message, Throwable t) {
        uSkyBlock.getInstance().getLogger().log(level, message, t);
    }

    public static void log(Level level, String message) {
        uSkyBlock.getInstance().getLogger().log(level, message);
    }
}
