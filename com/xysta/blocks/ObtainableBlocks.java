package com.xysta.blocks;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import com.xysta.utils.Items;

public class ObtainableBlocks implements Listener {
	@EventHandler
	private void blockEvent(BlockBreakEvent event) {
		Block b = event.getBlock();
		switch (b.getType()) {
		case CHEST:
		case WOOD:
		case WOOD_STEP:
		case BED_BLOCK:
		case HOPPER:
		case WORKBENCH:
		case FURNACE:
		case ENDER_CHEST:
			Items.dropItem(b, 1, Items.item(b.getType(), 1, b.getType().name(), "???"));
			break;
		default:
			break;
		}
	}
}
