package com.xysta.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.xysta.utils.Prefix;

public class Heal implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("heal")) {
			if (!sender.isOp()) {
				sender.sendMessage(Prefix.prefix() + "You're not op.");
				return true;
			}

			if (args.length >= 1) {
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if (target == null) {
					sender.sendMessage(Prefix.prefix() + args[0] + " is not online.");
					return true;
				}
				target.setHealth(target.getMaxHealth());
				target.setFoodLevel(40);
				target.sendMessage(Prefix.prefix() + "You were healed by " + sender.getName() + ".");
				sender.sendMessage(Prefix.prefix() + "Healed " + args[0] + ".");
				return true;
			} else {
				if (sender instanceof ConsoleCommandSender) {
					sender.sendMessage(Prefix.prefix() + "You can't heal the console.");
					return true;
				}

				Player target = Bukkit.getServer().getPlayer(sender.getName());
				target.setHealth(target.getMaxHealth());
				target.setFoodLevel(40);
				sender.sendMessage(Prefix.prefix() + "Healed self.");
				return true;
			}
		}
		return false;
	}
}
