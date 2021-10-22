package me.spigot.server;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class Survival implements Runnable {
	
	public boolean ready;
	Main instance;
	private Integer assignedTaskId;
	public ItemStack[] randomitems;
	public Survival(Main inst) {
		this.instance=inst;
	//	this.ready=false;
		makeitems();
		System.out.println("game started");
	}
	@Override
	public void run() {
		if(ready) {
			ready=false;
			System.out.println("Giving Random Item #2");
			Timer(3);
		}
	}
	public void stopgame() {
		ready=false;
		if(assignedTaskId !=null) {
			Bukkit.getScheduler().cancelTask(assignedTaskId);
		}
		return;
	}
	public void startgame() {
		ready=true;
		this.assignedTaskId=Bukkit.getScheduler().scheduleSyncRepeatingTask(instance,this,0L,20L);
		return;
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
		//Material[] mat=Material.values();
		for (Player players:Bukkit.getServer().getOnlinePlayers()) {
			int rand = new Random().nextInt(randomitems.length);
			ItemStack item = randomitems[rand];
			players.getInventory().addItem(item);
		}
	}
	void makeitems() {
		randomitems= new ItemStack[Material.values().length+6];
		for(int i =0;i<Material.values().length;i++) {
			ItemStack item = new ItemStack(Material.values()[i],1);
			randomitems[i]=item;
		}
		ItemStack bow = new ItemStack(Material.BOW,1);
		bow.addUnsafeEnchantment(Enchantment.DURABILITY, 255);
		bow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK,5);
		bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE,255);
		bow.addUnsafeEnchantment(Enchantment.MENDING,255);
		randomitems[Material.values().length+0]=bow;
		
		ItemStack sword = new ItemStack(Material.NETHERITE_SWORD,1);
		sword.addUnsafeEnchantment(Enchantment.KNOCKBACK,255);
		sword.addUnsafeEnchantment(Enchantment.DURABILITY, 255);
		sword.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK,255);
		sword.addUnsafeEnchantment(Enchantment.MENDING,255);
		randomitems[Material.values().length+1]=sword;
		
		ItemStack helmet = new ItemStack(Material.NETHERITE_HELMET,1);
		helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,255);
		helmet.addUnsafeEnchantment(Enchantment.DURABILITY, 255);
		helmet.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS,255);
		helmet.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE,255);
		helmet.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE,255);
		helmet.addUnsafeEnchantment(Enchantment.MENDING,255);
		randomitems[Material.values().length+2]=helmet;
		
		ItemStack chestplate = new ItemStack(Material.NETHERITE_CHESTPLATE,1);
		chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,255);
		chestplate.addUnsafeEnchantment(Enchantment.DURABILITY, 255);
		chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS,255);
		chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE,255);
		chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE,255);
		chestplate.addUnsafeEnchantment(Enchantment.MENDING,255);
		randomitems[Material.values().length+3]=chestplate;
		
		ItemStack legging = new ItemStack(Material.NETHERITE_LEGGINGS,1);
		legging.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,255);
		legging.addUnsafeEnchantment(Enchantment.DURABILITY, 255);
		legging.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS,255);
		legging.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE,255);
		legging.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE,255);
		legging.addUnsafeEnchantment(Enchantment.MENDING,255);
		randomitems[Material.values().length+4]=legging;
		
		ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS,1);
		boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,255);
		boots.addUnsafeEnchantment(Enchantment.DURABILITY, 255);
		boots.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS,255);
		boots.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE,255);
		boots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE,255);
		boots.addUnsafeEnchantment(Enchantment.FROST_WALKER,255);
		boots.addUnsafeEnchantment(Enchantment.PROTECTION_FALL,255);
		boots.addUnsafeEnchantment(Enchantment.MENDING,255);
		randomitems[Material.values().length+5]=boots;
	}
	
}
