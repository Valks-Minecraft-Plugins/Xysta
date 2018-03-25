package com.xysta.utils;

import net.md_5.bungee.api.ChatColor;

public class Color {
	public static String message(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
}
