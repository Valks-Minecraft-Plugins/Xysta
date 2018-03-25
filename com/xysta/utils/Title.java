package com.xysta.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Title {
	public static void sendTitle(Player player, String title, String subtitle, String color) {
		String name = player.getName();
		if (subtitle != null) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
					"title " + name + " subtitle {\"text\":\"" + subtitle + "\", \"color\":\"" + color + "\"}");
		}
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
				"title " + name + " title {\"text\":\"" + title + "\", \"color\":\"" + color + "\"}");
	}

	public static void sendSubtitle(Player player, String text, String color) {
		String name = player.getName();
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
				"title " + name + " subtitle {\"text\":\"" + text + "\", \"color\":\"" + color + "\"}");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + name + " title \"" + "" + "\"");
	}

	public static void sendActionMessage(Player player, String text, String color) {
		String name = player.getName();
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + name + " actionbar {\"text\":\"" + text
				+ "\", \"color\":\"" + color + "\", \"bold\":false}");
	}
}
