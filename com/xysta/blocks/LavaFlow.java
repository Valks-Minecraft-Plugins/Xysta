package com.xysta.blocks;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class LavaFlow implements Listener {
	@EventHandler
	private void cancelLiquidFlow(BlockFromToEvent e) {
		e.setCancelled(true);
	}
}