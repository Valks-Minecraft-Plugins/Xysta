package com.xysta.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

import com.xysta.Xysta;
import com.xysta.utils.Prefix;

public class Version implements CommandExecutor{
	Xysta plugin;
	
	public Version(Xysta instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("version")) {
			PluginDescriptionFile p = plugin.getDescription();
			sender.sendMessage(Prefix.prefix() + p.getName() + " v" + p.getVersion() + " by " + p.getAuthors());
			return true;
		}
		return false;
	}
}
