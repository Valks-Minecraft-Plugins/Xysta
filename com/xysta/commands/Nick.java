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

import net.md_5.bungee.api.ChatColor;

public class Nick implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("nick")) {
			if (!sender.isOp()) {
				sender.sendMessage(Prefix.prefix() + "You're not op.");
				return true;
			}
			
			if (args.length == 0) {
				sender.sendMessage(Prefix.prefix() + "Usage: /nick <player> <nick>");
				return true;
			}
			
			if (args.length >= 2) {
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if (target == null) {
					sender.sendMessage(Prefix.prefix() + args[0] + " is not online.");
					return true;
				}
				String name = ChatColor.translateAlternateColorCodes('&', args[1]);
				PlayerFiles cm = PlayerFiles.getConfig(target);
				FileConfiguration config = cm.getConfig();
				config.set("nick", args[1]);
				cm.saveConfig();
				target.setDisplayName(name);
				target.sendMessage(Prefix.prefix() + sender.getName() + " changed your nickname to " + name + ".");
				sender.sendMessage(Prefix.prefix() + "Changed name for " + target.getName() + " to " + name + ".");
				return true;
			} else {
				if (sender instanceof ConsoleCommandSender) {
					sender.sendMessage(Prefix.prefix() + "You can't give the console a nickname.");
					return true;
				}
				
				Player target = Bukkit.getServer().getPlayer(sender.getName());
				String name = ChatColor.translateAlternateColorCodes('&', args[0]);
				PlayerFiles cm = PlayerFiles.getConfig(target);
				FileConfiguration config = cm.getConfig();
				config.set("nick", args[0]);
				cm.saveConfig();
				target.setDisplayName(name);
				sender.sendMessage(Prefix.prefix() + "Your nickname is now " + name + ".");
				return true;
			}
		}
		
		return false;
	}

}
