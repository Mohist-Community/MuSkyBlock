package us.talabrek.ultimateskyblock.handler;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.ItemStack;
import us.talabrek.ultimateskyblock.util.reflection.ReflectionUtil;
import us.talabrek.ultimateskyblock.util.util.VersionUtil;

/**
 * Abstraction for spawning entities (cross-version-support)
 */
public class EntitySpawner {
    public Skeleton spawnWitherSkeleton(Location location) {
        Skeleton mob = (Skeleton) location.getWorld().spawnEntity(location, EntityType.fromId(5));
        mob.getEquipment().setItemInMainHand(new ItemStack(Material.STONE_SWORD, 1));
        return mob;
    }

    public Blaze spawnBlaze(Location location) {
        return (Blaze) location.getWorld().spawnEntity(location, EntityType.BLAZE);
    }

    public Skeleton spawnSkeleton(Location location) {
        return (Skeleton) location.getWorld().spawnEntity(location, EntityType.SKELETON);
    }
}
