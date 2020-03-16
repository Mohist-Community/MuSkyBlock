package us.talabrek.ultimateskyblock.island.level;

import java.util.List;
import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import us.talabrek.ultimateskyblock.Settings;
import us.talabrek.ultimateskyblock.api.model.BlockScore;
import us.talabrek.ultimateskyblock.island.level.yml.LegacyLevelConfigYmlReader;
import us.talabrek.ultimateskyblock.island.level.yml.LevelConfigYmlReader;
import us.talabrek.ultimateskyblock.uSkyBlock;

public abstract class CommonLevelLogic implements LevelLogic {
    static final String CN = CommonLevelLogic.class.getName();
    protected static final Logger log = Logger.getLogger(CN);
    protected final uSkyBlock plugin;
    protected final FileConfiguration config;

    BlockLevelConfigMap scoreMap;
    private final int pointsPerLevel;
    final int activateNetherAtLevel;

    CommonLevelLogic(uSkyBlock plugin, FileConfiguration config) {
        this.plugin = plugin;
        this.config = config;
        activateNetherAtLevel = config.getInt("nether.activate-at.level", 100);
        pointsPerLevel = config.getInt("general.pointsPerLevel");
        load();
    }

    private void load() {
        if (config.getInt("version", 0) < 100) {
            scoreMap = new LegacyLevelConfigYmlReader().readLevelConfig(config);
        } else {
            scoreMap = new LevelConfigYmlReader().readLevelConfig(config);
        }
    }

    Location getNetherLocation(Location l) {
        Location netherLoc = l.clone();
        netherLoc.setWorld(plugin.getSkyBlockNetherWorld());
        netherLoc.setY(Settings.nether_height);
        return netherLoc;
    }

    IslandScore createIslandScore(BlockCountCollection blockCollection) {
        List<BlockScore> blockScores = blockCollection.calculateScore(pointsPerLevel);
        return new IslandScore(blockScores.stream().mapToDouble(BlockScore::getScore).sum(), blockScores);
    }
}
