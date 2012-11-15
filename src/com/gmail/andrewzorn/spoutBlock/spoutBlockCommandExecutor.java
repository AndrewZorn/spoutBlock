package com.gmail.andrewzorn.spoutBlock;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.CustomBlock;

import com.google.common.base.Joiner;

public class spoutBlockCommandExecutor implements CommandExecutor {
	private final spoutBlock plugin;
	
	public static CustomBlock testBlock;
	
	public spoutBlockCommandExecutor(spoutBlock plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "you must be logged on to use these commands");
			return false;
		}
		else if (command.getName().equalsIgnoreCase("inventoryBlock") && sender.hasPermission("spoutBlock.inventoryBlock")) {
			Player player = (Player) sender;
			testBlock = new testblock(plugin);
			player.getInventory().addItem(new SpoutItemStack(testBlock, 1));
			return true;
		}
		else if (command.getName().equalsIgnoreCase("worldBlock") && sender.hasPermission("spoutBlock.worldBlock")) {
			testBlock = new block(plugin);
			Player player = (Player) sender;
			Location loc = player.getLocation();
			Block b = player.getWorld().getBlockAt(loc);
			SpoutManager.getMaterialManager().overrideBlock(b, testBlock);
			SpoutBlock sb = (SpoutBlock) b;
			sb.setCustomBlock(testBlock);
			return true;
		}
		
		else if (command.getName().equalsIgnoreCase("message") && sender.hasPermission("spoutBlock.message") && args.length > 0) {
			this.plugin.getConfig().set("sample.message", Joiner.on(' ').join(args));
			return true;
		}
		else	return false;
	}
}