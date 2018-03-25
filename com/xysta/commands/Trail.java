package com.xysta.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.xysta.configs.PlayerFiles;
import com.xysta.utils.Prefix;

public class Trail implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("trail")) {
			if (!sender.isOp()) {
				sender.sendMessage(Prefix.prefix() + "You're not op.");
				return true;
			}
			
			if (args.length == 0) {
				sender.sendMessage(Prefix.prefix() + "Specify at least one argument.");
				return true;
			}
			
			if (args.length >= 2) {
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if (target == null) {
					sender.sendMessage(Prefix.prefix() + args[0] + " is not online");
					return true;
				}
				
				PlayerFiles cm = PlayerFiles.getConfig(target);
				FileConfiguration config = cm.getConfig();
				
				config.set("trail", args[1].toUpperCase());
				cm.saveConfig();
				
				target.sendMessage(Prefix.prefix() + "Your trail was set to '" + args[1] + "' by " + sender.getName() + ".");
				sender.sendMessage(Prefix.prefix() + "Set trail '" + args[1] + "' for " + target.getName() + ".");
				return true;
			} else {
				if (sender instanceof ConsoleCommandSender) {
					sender.sendMessage(Prefix.prefix() + "You can't give the console a trail effect.");
					return true;
				}
				
				Player target = Bukkit.getServer().getPlayer(sender.getName());
				
				PlayerFiles cm = PlayerFiles.getConfig(target);
				FileConfiguration config = cm.getConfig();
				
				config.set("trail", args[0].toUpperCase());
				cm.saveConfig();
				
				sender.sendMessage(Prefix.prefix() + "Your trail was set to " + args[0] + ".");
				return true;
			}
		}
		return false;
	}

}
