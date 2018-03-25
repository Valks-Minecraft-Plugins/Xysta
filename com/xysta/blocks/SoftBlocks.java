package com.xysta.blocks;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class SoftBlocks implements Listener {
	public void softenBlocks(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Block block = p.getLocation().getBlock();
		switch (block.getType()) {
		case CHORUS_FLOWER:
		case YELLOW_FLOWER:
		case LONG_GRASS:
		case SNOW:
			block.setType(Material.AIR);
			break;
		default:
			break;
		}
		Block block_below = p.getLocation().subtract(0, 1, 0).getBlock();
		switch (block_below.getType()) {
		case LEAVES_2:
		case LEAVES:
		case ICE:
			block_below.setType(Material.AIR);
			break;
		default:
			break;
		}
	}
}
