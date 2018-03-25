package com.xysta.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.xysta.inventories.InitGUI;

public class Help implements CommandExecutor {
	private final InitGUI init = new InitGUI();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("help")) {
			Player target = Bukkit.getPlayer(sender.getName());
			target.openInventory(init.help());
			return true;
		}
		return false;
	}
}
