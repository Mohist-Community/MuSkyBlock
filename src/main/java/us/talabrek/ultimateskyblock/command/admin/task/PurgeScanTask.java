package us.talabrek.ultimateskyblock.command.admin.task;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import us.talabrek.ultimateskyblock.island.IslandInfo;
import us.talabrek.ultimateskyblock.uSkyBlock;
import us.talabrek.ultimateskyblock.util.IslandUtil;
import static us.talabrek.ultimateskyblock.util.LogUtil.log;
import us.talabrek.ultimateskyblock.util.ProgressTracker;
import us.talabrek.ultimateskyblock.util.file.FileUtil;
import static us.talabrek.ultimateskyblock.util.po.I18nUtil.marktr;
import static us.talabrek.ultimateskyblock.util.po.I18nUtil.tr;
import us.talabrek.ultimateskyblock.util.util.TimeUtil;
import us.talabrek.ultimateskyblock.uuid.PlayerDB;

/**
 * Scans for all players on a list of islands.
 */
public class PurgeScanTask extends BukkitRunnable {
    private final List<String> islandList;
    private final List<String> purgeList;
    private final long cutOff;
    private final uSkyBlock plugin;
    private final CommandSender sender;
    private final Runnable callback;
    private final double purgeLevel;
    private final ProgressTracker tracker;
    private final long tStart;
    private final PlayerDB playerDB;
    private volatile boolean active;
    private boolean done;

    public PurgeScanTask(uSkyBlock plugin, File islandDir, int time, double purgeLevel, CommandSender sender, Runnable callback) {
        this.plugin = plugin;
        this.sender = sender;
        this.callback = callback;
        this.cutOff = System.currentTimeMillis() - (time * 3600000L);
        String[] islandList = islandDir.list(IslandUtil.createIslandFilenameFilter());
        this.islandList = new ArrayList<>(Arrays.asList(islandList));
        purgeList = new ArrayList<>();
        this.purgeLevel = purgeLevel;
        int feedbackEvery = plugin.getConfig().getInt("async.long.feedbackEvery", 30000);
        tStart = System.currentTimeMillis();
        tracker = new ProgressTracker(sender, marktr("\u00a77- SCANNING: {0,number,##}% ({1}/{2} failed: {3}) ~ {4}"), 25, feedbackEvery);
        active = true;
        playerDB = plugin.getPlayerDB();
    }

    private void generatePurgeList() {
        int progress = 0;
        int failed = 0;
        int total = islandList.size();
        while (!islandList.isEmpty()) {
            if (!active) {
                break;
            }
            String islandFile = islandList.remove(0);
            String islandName = FileUtil.getBasename(islandFile);
            try {
                IslandInfo islandInfo = plugin.getIslandInfo(islandName);
                if (islandInfo != null) {
                    Set<UUID> members = islandInfo.getMemberUUIDs();
                    if (!islandInfo.ignore() && islandInfo.getLevel() < purgeLevel && abandonedSince(members)) {
                        purgeList.add(islandName);
                    }
                }
            } catch (Exception e) {
                failed++;
            }
            progress++;
            tracker.progressUpdate(progress, total, failed, TimeUtil.millisAsString(System.currentTimeMillis()-tStart));
        }
    }

    public boolean isActive() {
        return active;
    }

    public boolean isDone() {
        return done;
    }

    public void stop() {
        active = false;
    }

    public List<String> getPurgeList() {
        return purgeList;
    }

    private boolean abandonedSince(Set<UUID> members) {
        for (UUID member : members) {
            OfflinePlayer player = playerDB.getOfflinePlayer(member);
            if (player == null || player.getLastPlayed() > cutOff) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void run() {
        generatePurgeList();
        if (!active) {
            sender.sendMessage(tr("\u00a74PURGE:\u00a79 Scanning aborted."));
            return;
        }
        log(Level.INFO, "Done scanning - found " + purgeList.size() + " candidates for purging.");
        sender.sendMessage(tr("\u00a74PURGE:\u00a79 Scanning done, found {0} candidates, below level {1}, ready for purgatory.", purgeList.size(), purgeLevel));
        done = true;
        if (!purgeList.isEmpty()) {
            callback.run();
        } else {
            active = false;
        }
    }
}
