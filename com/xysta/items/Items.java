package com.xysta.items;

import java.util.Random;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import net.md_5.bungee.api.ChatColor;

public class Items implements Listener {
	@EventHandler
	private void customName(PlayerDropItemEvent e) {
		String[] names = new String[] { "Live", "Die", "Explore", "Hunt", "Eat", "Sleep", "Run", "You're next.", "Life",
				"Death", "Hunger", "Behind You", "Darkness", "Light", "Evil", "The One", "The Tower", "The Block",
				"Steve", "Notch", "Herobrine", "The Secrets", "The Unknown", "The Doctor", "What?", "Cats", "Dogs",
				"Bug", "Error", "Valk" };
		e.getItemDrop().setCustomName(ChatColor.YELLOW + names[new Random().nextInt(names.length)]);
		e.getItemDrop().setCustomNameVisible(true);
	}
}
