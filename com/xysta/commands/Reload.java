package com.xysta.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.xysta.Xysta;
import com.xysta.configs.PlayerFiles;
import com.xysta.utils.Prefix;

public class Reload implements CommandExecutor {
	Xysta plugin;
	
	public Reload(Xysta instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("reload")) {
			if (!sender.isOp()) {
				sender.sendMessage(Prefix.prefix() + "You're not op.");
				return true;
			}
			
			if (args.length == 0) {
				sender.sendMessage(Prefix.prefix() + "Usage: /xysta reload <main | player>");
				return true;
			}
			
			if (args[0].toLowerCase().equals("main")) {
				plugin.reloadConfig();
				plugin.saveConfig();
				sender.sendMessage(Prefix.prefix() + "Reloaded main config.");
				return true;
			} else {
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if (target == null) {
					sender.sendMessage(Prefix.prefix() + args[0] + "is not online.");
					return true;
				}
				
				PlayerFiles cm = PlayerFiles.getConfig(target);
				cm.reload();
				cm.saveConfig();
				sender.sendMessage(Prefix.prefix() + "Reloaded config for " + args[0] + ".");
			}
			return true;
		}
		return false;
	}

}
