package com.gmail.andrewzorn.spoutBlock;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

public class spoutBlock extends JavaPlugin {
	spoutBlockCommandExecutor executor = new spoutBlockCommandExecutor(this);
	
	public void onEnable() {
		getLogger().log(Level.INFO, "[Spout Block Test Plugin] Enabled!");
		
		saveDefaultConfig();
		
		new spoutBlockListener(this);
		
		this.getCommand("message").setExecutor(executor);
		this.getCommand("inventoryBlock").setExecutor(executor);
		this.getCommand("worldBlock").setExecutor(executor);
	}
	
	public void onDisable() {
		getLogger().log(Level.INFO, "[Spout Test Plugin] Disabled!");
	}
}