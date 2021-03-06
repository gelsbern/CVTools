package org.cubeville.commons.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class PlayerUtils {

	public static void removeAllPotionEffects(Player player) {
		player.removePotionEffect(PotionEffectType.ABSORPTION);
	    player.removePotionEffect(PotionEffectType.BLINDNESS);
	    player.removePotionEffect(PotionEffectType.CONFUSION);
	    player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
	    player.removePotionEffect(PotionEffectType.FAST_DIGGING);
	    player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
	    player.removePotionEffect(PotionEffectType.GLOWING);
	    player.removePotionEffect(PotionEffectType.HARM);
	    player.removePotionEffect(PotionEffectType.HEAL);
	    player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
	    player.removePotionEffect(PotionEffectType.HUNGER);
	    player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
	    player.removePotionEffect(PotionEffectType.INVISIBILITY);
	    player.removePotionEffect(PotionEffectType.JUMP);
	    player.removePotionEffect(PotionEffectType.LEVITATION);
	    player.removePotionEffect(PotionEffectType.LUCK);
	    player.removePotionEffect(PotionEffectType.NIGHT_VISION);
	    player.removePotionEffect(PotionEffectType.POISON);
	    player.removePotionEffect(PotionEffectType.REGENERATION);
	    player.removePotionEffect(PotionEffectType.SATURATION);
	    player.removePotionEffect(PotionEffectType.SLOW);
	    player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
	    player.removePotionEffect(PotionEffectType.SPEED);
	    player.removePotionEffect(PotionEffectType.UNLUCK);
	    player.removePotionEffect(PotionEffectType.WATER_BREATHING);
	    player.removePotionEffect(PotionEffectType.WEAKNESS);
	    player.removePotionEffect(PotionEffectType.WITHER);
	}
	
	public static Set<ProtectedRegion> getRegionsAtPlayerLocation(Player player) {
	    Location location = player.getLocation();
	    Vector vLocation = new Vector(location.getX(), location.getY(), location.getZ());
	    WorldGuardPlugin worldGuard = (WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
	    RegionManager regionManager = worldGuard.getRegionManager(location.getWorld());
	    if(regionManager == null) { return null; }
	    return regionManager.getApplicableRegions(vLocation).getRegions();
	}
	
	@SuppressWarnings("unchecked")
    public static List<Player> getPlayersOutsideRegion(ProtectedRegion region, World world) {
	    Collection<Player> allPlayers = (Collection<Player>) Bukkit.getServer().getOnlinePlayers();
	    List<Player> playersInsideRegion = getPlayersInsideRegion(region, world);
	    List<Player> playersOutsideRegion = new ArrayList<Player>();
	    for(Player player: allPlayers) {
	        if(!playersInsideRegion.contains(player)) {
	            playersOutsideRegion.add(player);
	        }
	    }
	    return playersOutsideRegion;
	}
	
	@SuppressWarnings("unchecked")
    public static List<Player> getPlayersInsideRegion(ProtectedRegion region, World world) {
	    Collection<Player> allPlayers = (Collection<Player>) Bukkit.getServer().getOnlinePlayers();
	    List<Player> worldPlayers = new ArrayList<Player>();
	    for(Player player: allPlayers) {
	        if(player.getWorld().equals(world)) {
	            worldPlayers.add(player);
	        }
	    }
	    List<Block> regionBlocks = BlockUtils.getBlocksInWGRegion(world, region.getId(), Integer.MAX_VALUE);
	    List<Player> regionPlayers = new ArrayList<Player>();
	    for(Player player: worldPlayers) {
	        if(regionBlocks.contains(player.getLocation().getBlock())) {
	            regionPlayers.add(player);
	        }
	    }
	    return regionPlayers;
	}
}
