package me.spigot.server;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class targetdeath implements Runnable {
	
	Entity villager1;
	Entity villager2;
	Entity villager3;
	Minecart minecart1;
	Minecart minecart2;
	Minecart minecart3;
	int assignedTaskId;
	Main instance;
	public targetdeath(Entity v1,Entity v2,Entity v3,Minecart m1,Minecart m2,Minecart m3, Main game) {
		this.villager1=v1;
		this.villager2=v2;
		this.villager3=v3;
		this.minecart1=m1;
		this.minecart2=m2;
		this.minecart3=m3;
		this.instance=game;
		
	}
	public void startgame() {
		this.assignedTaskId=Bukkit.getScheduler().scheduleSyncRepeatingTask(instance,this,0L,20L);
		return;
	}
	@Override
	public void run() {
		if (villager1.isDead()) {
			Entity damager = ((EntityDamageByEntityEvent) villager1.getLastDamageCause()).getDamager();
			Entity killer = (Entity) ((Projectile) damager).getShooter();
			System.out.print(killer.getName());
			if (killer.getType() == EntityType.PLAYER) {
				System.out.print("PLAYER KILLED VILLAGER!!!");
			}
		}
		
		
	}
	
}
