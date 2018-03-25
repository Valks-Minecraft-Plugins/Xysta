package com.xysta.trails;

import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.xysta.configs.PlayerFiles;

import net.md_5.bungee.api.ChatColor;

public class Trails implements Listener{
	@EventHandler(priority = EventPriority.HIGHEST)
	public void trails(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		PlayerFiles cm = PlayerFiles.getConfig(p);
		FileConfiguration config = cm.getConfig();
		if (!config.getString("trail").equals("NONE")) {
			try {
				p.getWorld().spawnParticle(Particle.valueOf(config.getString("trail")), p.getLocation(), 1);
			} catch (Exception err) {
				config.set("trail", "NONE");
				cm.saveConfig();
				p.sendMessage(ChatColor.DARK_RED + "Error: " + ChatColor.RED + err);
			}
		}
	}
}
