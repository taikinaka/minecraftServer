package me.spigot.server;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Playermove implements Listener {
	@EventHandler
	public void onPlayerMove (PlayerMoveEvent event) {
		Player player = event.getPlayer();
		Location loc = player.getLocation();
		loc.setY(loc.getY()-1);
		World targetWorld = loc.getWorld();
		String blockName = loc.getBlock().getType().toString();
		if (blockName.equals("AIR")) {
			targetWorld.getBlockAt(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ()).setType(Material.IRON_BLOCK);
		}
	}
}
