package com.xysta.chat;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.xysta.Xysta;
import com.xysta.configs.PlayerFiles;
import com.xysta.utils.Prefix;

public class Chat implements Listener {
	Xysta plugin;
	
	public Chat(Xysta instance) {
		plugin = instance;
	}
	
	@EventHandler
	private boolean playerChat(AsyncPlayerChatEvent event) {
		Player p = event.getPlayer();
		PlayerFiles cm = PlayerFiles.getConfig(p);
		FileConfiguration config = cm.getConfig();
		
		if (config.getBoolean("muted") == true) {
			p.sendMessage(Prefix.prefix() + "You're muted, you may not speak.");
			event.setCancelled(true);
			return true;
		}

		String name = event.getPlayer().getDisplayName();
		String message = event.getMessage();
		String[] badwords = { "fuck", "nigger", "cunt", "cunts", "bitch", "whore", "slut", "motherfucker", "fucker",
				"blowjob", "dick", "kunt", "faggot", "niglet", "prick" };
		String chatColor = config.getString("color");

		String msg = plugin.getConfig().getString("chat");

		String change1 = msg.replace("%player%", name);
		String change2 = change1.replace("%message%", message);
		String change3 = change2.replace("#color#", chatColor);
		String change4 = change3.replaceAll("%", "%%");

		String msgColor = ChatColor.translateAlternateColorCodes('&', change4);

		String prefix = plugin.getConfig().getString("ranks." + config.getString("rank").toLowerCase());

		String prefixColor = ChatColor.translateAlternateColorCodes('&', prefix);

		for (String s : badwords) {
			String check = s.toLowerCase();
			if (message.toLowerCase().contains(check)) {
				String clean = msgColor.toLowerCase().replaceAll(check, "meoowww");
				event.setFormat(prefixColor + clean);
			} else {
				event.setFormat(prefixColor + msgColor);
			}
		}
		
		return true;
	}
}
