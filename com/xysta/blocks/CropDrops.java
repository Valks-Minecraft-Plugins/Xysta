package com.xysta.blocks;

import org.bukkit.CropState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NetherWartsState;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.material.Crops;
import org.bukkit.material.NetherWarts;

import com.xysta.utils.Items;

public class CropDrops implements Listener{
	@SuppressWarnings("deprecation")
	@EventHandler
	private void blockEvent(BlockBreakEvent event) {
		Block b = event.getBlock();
		Material bt = b.getType();
		Location loc = b.getLocation();
		World w = loc.getWorld();

		/*
		 * Get drops from crops.
		 */
		switch (bt) {
		case CROPS:
			if (b.getData() == 7) {
				Items.dropItem(b, 1, Items.item(Material.MUSHROOM_SOUP, 1, "Mysterious Soup", "Restore hunger.."));
				Items.dropItem(b, 1, Items.item(Material.SEEDS, 2, "Powerful Essence", "Grow for Power"));
				w.spawnParticle(Particle.SLIME, loc, 30);
			}
			break;
		case PUMPKIN:
			Items.dropItem(b, 1, Items.item(Material.COOKIE, 3, "Mysterious Cookie", "Restore hunger.."));
			Items.dropItem(b, 1, Items.item(Material.PUMPKIN_SEEDS, 2, "Powerful Essence", "Grow for Power"));
			break;
		case MELON_BLOCK:
			Items.dropItem(b, 1, Items.item(Material.APPLE, 1, "Mysterious Apple", "Restore hunger.."));
			Items.dropItem(b, 1, Items.item(Material.POTATO_ITEM, 2, "Powerful Essence", "Grow for Power"));
			break;
		case POTATO:
			Crops potato = (Crops) b.getState().getData();
			if (potato.getState().equals(CropState.RIPE)) {
				Items.dropItem(b, 1, Items.item(Material.COOKED_FISH, 1, "Mysterious Fish", "Restore hunger.."));
				Items.dropItem(b, 1, Items.item(Material.CARROT_ITEM, 2, "Powerful Essence", "Grow for Power"));
			}
			break;
		case CARROT:
			Crops carrot = (Crops) b.getState().getData();
			if (carrot.getState().equals(CropState.RIPE)) {
				Items.dropItem(b, 1, Items.item(Material.COOKED_RABBIT, 2, "Mysterious Rabbit", "Restore hunger.."));
				Items.dropItem(b, 1, Items.item(Material.NETHER_STALK, 2, "Powerful Essence", "Grow for Power"));
			}
			break;
		case NETHER_WARTS:
			NetherWarts wart = (NetherWarts) b.getState().getData();
			if (wart.getState().equals(NetherWartsState.RIPE)) {
				Items.dropItem(b, 1, Items.item(Material.GOLDEN_APPLE, 1, "Mysterious Golden Apple", "Restore hunger.."));
				Items.dropItem(b, 1, Items.item(Material.NETHER_STALK, 2, "Powerful Essence", "Grow for Power"));
			}
			break;
		default:
			break;
		}
	}
}
