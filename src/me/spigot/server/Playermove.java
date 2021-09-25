package me.spigot.server;

import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Playermove implements Listener {
	public boolean canwalk;
	@EventHandler
	public void onPlayerMove (PlayerMoveEvent event) {

		
		Material[] wools = {Material.BLACK_WOOL, 
							Material.BLUE_WOOL,
							Material.BROWN_WOOL,
							Material.CYAN_WOOL,
							Material.GRAY_WOOL,
							Material.GREEN_WOOL,
							Material.LIGHT_BLUE_WOOL,
							Material.LIGHT_GRAY_WOOL,
							Material.LIME_WOOL,
							Material.MAGENTA_WOOL,
							Material.ORANGE_WOOL,
							Material.PINK_WOOL,
							Material.PURPLE_WOOL,
							Material.RED_WOOL,
							Material.YELLOW_WOOL,
							Material.WHITE_WOOL};
		int rand = new Random().nextInt(wools.length);
		Material rWool = wools[rand];
		
		Player player = event.getPlayer();
		Location loc = player.getLocation();
		loc.setY(loc.getY()-1);
		World targetWorld = loc.getWorld();
		String blockName = loc.getBlock().getType().toString();
		if (blockName.equals("AIR") && canwalk) {
			targetWorld.getBlockAt(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ()).setType(rWool);
				
		}
		
		if (blockName.equals("LAVA")) {
			player.setHealth(0);
		}
	}
	//LivingEntity.getFireTicks != 0
	
}
