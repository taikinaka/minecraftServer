package me.spigot.server;

import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class targetdeath implements Runnable {
	
	boolean kill1;
	boolean kill2;
	boolean kill3;
	
	Entity villager1;
	Entity villager2;
	Entity villager3;
	Minecart minecart1;
	Minecart minecart2;
	Minecart minecart3;
	int assignedTaskId;
	
	int killcount;
	Main instance;
	BossBar bar;
	public targetdeath(Entity v1,Entity v2,Entity v3,Minecart m1,Minecart m2,Minecart m3, Main game,BossBar bar) {
		this.villager1=v1;
		this.villager2=v2;
		this.villager3=v3;
		this.minecart1=m1;
		this.minecart2=m2;
		this.minecart3=m3;
		this.instance=game;
		this.bar=bar;
		
	}
	public void startgame() {
		this.assignedTaskId=Bukkit.getScheduler().scheduleSyncRepeatingTask(instance,this,0L,20L);
		return;
	}
	@Override
	public void run() {
		if (villager1.isDead() && !kill1) {
			Entity damager = ((EntityDamageByEntityEvent) villager1.getLastDamageCause()).getDamager();
			Entity killer = (Entity) ((Projectile) damager).getShooter();
			System.out.print(killer.getName());
			if (killer.getType() == EntityType.PLAYER) {
				System.out.print("PLAYER KILLED VILLAGER 1!!!");
				killcount+=1;
				bar.setProgress(killcount/(double)3);
			}
			kill1=true;
		}
		else if (villager2.isDead() && !kill2) {
			Entity damager = ((EntityDamageByEntityEvent) villager2.getLastDamageCause()).getDamager();
			Entity killer = (Entity) ((Projectile) damager).getShooter();
			System.out.print(killer.getName());
			if (killer.getType() == EntityType.PLAYER) {
				System.out.print("PLAYER KILLED VILLAGER 2!!!");
				killcount+=1;
				bar.setProgress(killcount/(double)3);
			}
			kill2=true;
		}
		else if (villager3.isDead() && !kill3) {
			Entity damager = ((EntityDamageByEntityEvent) villager3.getLastDamageCause()).getDamager();
			Entity killer = (Entity) ((Projectile) damager).getShooter();
			System.out.print(killer.getName());
			if (killer.getType() == EntityType.PLAYER) {
				System.out.print("PLAYER KILLED VILLAGER 3!!!");
				killcount+=1;
				bar.setProgress(killcount/(double)3);
			}
			kill3=true;
		}
		if (killcount==3) {
			bar.removeAll();
		}
		
		
	}
	
}
