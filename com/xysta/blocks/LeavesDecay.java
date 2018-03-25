package com.xysta.blocks;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;

public class LeavesDecay implements Listener {
	@EventHandler
	private void cancelLeavesDecay(LeavesDecayEvent e) {
		e.setCancelled(true);
	}
}
