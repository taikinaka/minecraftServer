package me.spigot.server;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
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
