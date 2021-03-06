package me.spigot.server;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.MessagePrompt;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Boss;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class Main extends JavaPlugin{
	private static Main instance;
	public Playermove pm;
	public SimonSays game;
	public Survival survivalgame;
	@Override
	public void onEnable() {
		instance = this;
		pm=new Playermove();
		survivalgame = new Survival(getInstance());
		getServer().getPluginManager().registerEvents(pm, this);
	}
	public static Main getInstance() {
		return instance;
	}
	//Test Test Test Test TestTest 
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (label.equalsIgnoreCase("bow")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (!player.isOp()) {
					return false;
				}
				ItemStack item = new ItemStack(Material.BOW,1);
				item.addUnsafeEnchantment(Enchantment.KNOCKBACK,255);
				item.addUnsafeEnchantment(Enchantment.DURABILITY, 255);
				item.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK,255);
				player.getInventory().addItem(item);
				return true;
			}
		}
		if (label.equalsIgnoreCase("bow_game")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				Location loc = new Location(player.getLocation().getWorld(),0,102,0);
				if (!player.isOp()) {
					return false;
				}
				player.teleport(loc);
		//		player.getInventory().clear();
				ItemStack item = new ItemStack(Material.BOW,1);
				item.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 255);
				item.addUnsafeEnchantment(Enchantment.DURABILITY, 255);
				item.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK,255);
				player.getInventory().addItem(item);
				World world = loc.getWorld();
				Location m1loc = new Location(world,9,104,5);
				Minecart minecart1 = world.spawn(m1loc, Minecart.class);
				Entity villager1 = world.spawnEntity(m1loc, EntityType.VILLAGER);
				minecart1.addPassenger(villager1);
				
				Location m2loc = new Location(world,16,105,-5);
				Minecart minecart2 = world.spawn(m2loc, Minecart.class);
				Entity villager2 = world.spawnEntity(m2loc, EntityType.VILLAGER);
				minecart2.addPassenger(villager2);
				Location m3loc = new Location(world,21,107,5);
				Minecart minecart3 = world.spawn(m3loc, Minecart.class);
				Entity villager3 = world.spawnEntity(m3loc, EntityType.VILLAGER);
				minecart3.addPassenger(villager3);
				
				BossBar bar = Bukkit.createBossBar("Villager", BarColor.RED, BarStyle.SOLID);
				bar.setProgress(0);
				world.getPlayers().forEach(bar::addPlayer);
				targetdeath game = new targetdeath(villager1, villager2, villager3, minecart1, minecart2, minecart3,getInstance(),bar);
				game.startgame();
				return true;
			}
		}
		else if (label.equalsIgnoreCase("randomitem")) {
			System.out.println("Giving Random Item command input True");
			if (sender instanceof Player) {
				String randomswitch = args[0];
				Player player = (Player) sender;
				
				if (!player.isOp()) {
					return false;
				}
				System.out.println("Giving Random Item Opped True");
				if(randomswitch.equals("True") || randomswitch.equals("true")) {
					survivalgame.startgame();
				}
				else {
					survivalgame.stopgame();
				}
				return true;
			}
		}
		else if (label.equalsIgnoreCase("op_sword")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (!player.isOp()) {
					return false;
				}
				ItemStack item = new ItemStack(Material.NETHERITE_SWORD,1);
				item.addUnsafeEnchantment(Enchantment.DURABILITY,255);
				item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL,255);
				player.getInventory().addItem(item);
				System.out.println("op Sword");
				return true;
			}
		}
		else if (label.equalsIgnoreCase("god_apple")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (!player.isOp()) {
					return false;
				}
				ItemStack item = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE,128);
				item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL,255);
				item.addUnsafeEnchantment(Enchantment.FIRE_ASPECT,255);
				item.addUnsafeEnchantment(Enchantment.DIG_SPEED,255);
				player.getInventory().addItem(item);
				return true;
			}
		}
		else if (label.equalsIgnoreCase("pickaxe")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (!player.isOp()) {
					return false;
				}
				ItemStack item = new ItemStack(Material.NETHERITE_PICKAXE,1);
				item.addUnsafeEnchantment(Enchantment.DIG_SPEED,255);
				item.addUnsafeEnchantment(Enchantment.DURABILITY,255);
				item.addUnsafeEnchantment(Enchantment.MENDING,255);
				player.getInventory().addItem(item);
				return true;
			}
		}
		else if (label.equalsIgnoreCase("canwalk")) {
			pm.canwalk=!pm.canwalk;
		}
		else if (label.equalsIgnoreCase("clear_world")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (!player.isOp()) {
					return false;
				}
				Location loc = player.getLocation();
				World targetWorld = loc.getWorld();
				
				int width = Integer.parseInt(args[0]);
				int height = Integer.parseInt(args[1]);
				int length = Integer.parseInt(args[2]);
				for (int x=0; x<width;x++) {
					for(int y=0; y<height;y++) {
						for(int z=0;z<length;z++) {
							targetWorld.getBlockAt(loc.getBlockX()+x-width/2,loc.getBlockY()+y,loc.getBlockZ()+z-length/2).setType(Material.AIR);
						}
					}
				}
				for (int x=0; x<width;x++) {
					for (int z=0; z<length;z++) {
							targetWorld.getBlockAt(loc.getBlockX()+x-width/2,loc.getBlockY()-1,loc.getBlockZ()+z-length/2).setType(Material.GRASS_BLOCK);
					}
				}
				return true;
			}
		}
		else if(label.equalsIgnoreCase("arenastop")) {
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				if (!player.isOp()) {
					return false;
				}
				game.stopgame();
				return true;
				
			}
		}
		else if(label.equalsIgnoreCase("arena")) {
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				if (!player.isOp()) {
					return false;
				}
				
				Location loc = new Location(player.getLocation().getWorld(),player.getLocation().getBlockX(),player.getLocation().getBlockY(),player.getLocation().getBlockZ());
				pm.arenaCenter=loc;
				for (Player players:Bukkit.getServer().getOnlinePlayers()) {
					players.teleport(loc);
					players.getInventory().clear();
				}
				
				player.sendTitle(ChatColor.AQUA +"Simon Says...","Get Ready!", 10, 70, 20); 
		        player.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent("Get Ready!"));
		        
		        game = new SimonSays(loc,getInstance());
				game.startgame();
				
				
				
					
				}
				return true;
				
			}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		return false;
	}
}
