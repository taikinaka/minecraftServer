package me.spigot.server;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class SimonSays implements Runnable {
	public Location loc;
	World targetWorld;
	Main instance;
	public boolean ready;
	private Integer assignedTaskId;
	public SimonSays(Location location,Main inst) {
		this.loc = location;
		this.targetWorld = location.getWorld();
		this.instance=inst;
		this.ready=false;
		
	}
	public void startgame() {
		resetArena();
		ready=true;
		this.assignedTaskId=Bukkit.getScheduler().scheduleSyncRepeatingTask(instance,this,0L,20L);
		
	}
	public void resetArena() {
		for(int x = 0; x < 40; x++) {
			for(int z = 0; z<40; z++)
			{
				if(x < 20) {
					if(z < 20) {
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY()-1,loc.getBlockZ()+z - 20).setType(Material.GREEN_CONCRETE);
					}
					else if(z > 20) {
						
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY()-1,loc.getBlockZ()+z - 20).setType(Material.RED_CONCRETE);
					}
					else {
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY()-1,loc.getBlockZ()+z - 20).setType(Material.BLACK_CONCRETE);
					}
				}
				else if(x > 20) {
					if(z < 20) {
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY()-1,loc.getBlockZ()+z - 20).setType(Material.BLUE_CONCRETE);
					}
					else if(z > 20) {
						
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY()-1,loc.getBlockZ()+z - 20).setType(Material.YELLOW_CONCRETE);
					}
					else {
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY()-1,loc.getBlockZ()+z - 20).setType(Material.BLACK_CONCRETE);
					}
				}
				else {
					targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY()-1,loc.getBlockZ()+z - 20).setType(Material.BLACK_CONCRETE);
				}
			}
		}
	}
	public void setLavaBlocks() {
		
		
		Random rand = new Random();
		int randomX = rand.nextInt(2);
		int randomY = rand.nextInt(2);
		
		
		if(randomX == 0) {
			if(randomY == 0) {
				for(int x = 20; x < 40; x++) {
					for(int z = 20; z<40; z++)
					{
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY()-1,loc.getBlockZ()+z - 20).setType(Material.LAVA);
					}
				}
			}
			else  {
				
				for(int x = 20; x < 40; x++) {
					for(int z = 0; z<20; z++)
					{
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY()-1,loc.getBlockZ()+z - 20).setType(Material.LAVA);
					}
				}
			}
		}
		else  {
			if(randomY == 0) {
				for(int x = 0; x < 20; x++) {
					for(int z = 20; z<40; z++)
					{
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY()-1,loc.getBlockZ()+z - 20).setType(Material.LAVA);
					}
				}
			}
			else {
				
				for(int x = 0; x < 20; x++) {
					for(int z = 0; z<20; z++)
					{
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY()-1,loc.getBlockZ()+z - 20).setType(Material.LAVA);
					}
				}
			}
		}
		
		
	}
	public void CountdownLava(int time) {
		CountdownTimer timer = new CountdownTimer(instance,
		        time,
		        () -> Bukkit.getOnlinePlayers().forEach((p)->p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Lava Incomming!"))),
		        () -> {
		        	Bukkit.getOnlinePlayers().forEach((p)->p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Time's Up!")));
		            
					setLavaBlocks();
					Countdownnormal(3);
		        },
		        (t) -> {Bukkit.getOnlinePlayers().forEach((p)->p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(String.valueOf(t.getSecondsLeft()))));
				        }

		);

		// Start scheduling, don't use the "run" method unless you want to skip a second
		timer.scheduleTimer();
	}
	public void Countdownnormal(int time) {
		CountdownTimer timer = new CountdownTimer(instance,
		        time,
		        () -> Bukkit.getOnlinePlayers().forEach((p)->p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Survive"))),
		        () -> {
		        	Bukkit.getOnlinePlayers().forEach((p)->p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Safe")));
		            
					resetArena();
					ready=true;
		        },
		        (t) -> {Bukkit.getOnlinePlayers().forEach((p)->p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(String.valueOf(t.getSecondsLeft()))));
				        }

		);

		// Start scheduling, don't use the "run" method unless you want to skip a second
		timer.scheduleTimer();
	}
	@Override
	public void run() {
		if(ready) {
			ready=false;
			CountdownLava(10);
			
		}
	}
	public void stopgame() {
		ready=false;
		if(assignedTaskId !=null) {
			Bukkit.getScheduler().cancelTask(assignedTaskId);
		}
		return;
	}
}
