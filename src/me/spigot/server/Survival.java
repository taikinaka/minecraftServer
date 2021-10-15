package me.spigot.server;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class Survival implements Runnable {
	
	public boolean ready;
	Main instance;
	private Integer assignedTaskId;
	public Survival(Main inst) {
		this.instance=inst;
		this.ready=false;
	}
	@Override
	public void run() {
		if(ready) {
			ready=false;
			this.assignedTaskId=Bukkit.getScheduler().scheduleSyncRepeatingTask(instance,this,0L,20L);
			Timer(3);
			
		}
	}
	public void Timer(int time) {
		CountdownTimer timer = new CountdownTimer(instance,
		        time,
		        () -> Bukkit.getOnlinePlayers().forEach((p)->p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("randomitem started !"))),
		        () -> {
		        	Bukkit.getOnlinePlayers().forEach((p)->p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Time's Up!")));
		        	ready=true;
		        	GiveRandomitem();
		        },
		        (t) -> {Bukkit.getOnlinePlayers().forEach((p)->p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(String.valueOf(t.getSecondsLeft()))));
				        }

		);

		// Start scheduling, don't use the "run" method unless you want to skip a second
		timer.scheduleTimer();
	}
	void GiveRandomitem() {
		Material[] mat=Material.values();
		for (Player players:Bukkit.getServer().getOnlinePlayers()) {
			int rand = new Random().nextInt(mat.length);
			ItemStack item = new ItemStack(mat[rand],1);
			players.getInventory().addItem(item);
		}
	}
	
}
