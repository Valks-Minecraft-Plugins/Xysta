package com.xysta.blocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import com.xysta.utils.Items;
import com.xysta.utils.Prefix;

public class BlockDrops implements Listener {
	@EventHandler
	private void blockEvent(BlockBreakEvent event) {
		Block b = event.getBlock();
		Material bt = b.getType();
		Location loc = b.getLocation();
		Player p = event.getPlayer();
		Material tool_type = p.getEquipment().getItemInMainHand().getType();

		switch (bt) {
		case DIRT:
		case GRASS:
			switch (tool_type) {
			case WOOD_SPADE:
				Items.dropItem(b, 1, Items.item(Material.DIRT, 1, "Mud", "Perhaps combine it?"));
				break;
			case STONE_SPADE:
				Items.dropItem(b, 1, Items.item(Material.DIRT, 2, "Mud", "Perhaps combine it?"));
				break;
			case IRON_SPADE:
				Items.dropItem(b, 1, Items.item(Material.DIRT, 3, "Mud", "Perhaps combine it?"));
				break;
			case GOLD_SPADE:
				Items.dropItem(b, 1, Items.item(Material.DIRT, 4, "Mud", "Perhaps combine it?"));
				break;
			case DIAMOND_SPADE:
				Items.dropItem(b, 1, Items.item(Material.DIRT, 5, "Mud", "Perhaps combine it?"));
				break;
			default:
				break;
			}
			break;
		case LEAVES:
		case LEAVES_2:
			Items.dropItem(b, 1, Items.item(Material.STICK, 2, "Twig", "Perhaps combine it?"));
			break;
		case LONG_GRASS:
			switch (tool_type) {
			case WOOD_HOE:
			case STONE_HOE:
			case IRON_HOE:
			case GOLD_HOE:
			case DIAMOND_HOE:
				Items.dropItem(b, 1, Items.item(Material.STICK, 4, "Twig", "Perhaps combine it?"));
				break;
			default:
				Items.dropItem(b, 0.3, Items.item(Material.STICK, 1, "Twig", "Perhaps combine it?"));
				break;
			}
			Items.moreBlocks(loc, 4, 4, Material.GRASS, Material.LONG_GRASS);
			break;
		case DIAMOND_ORE:
			Items.dropItem(b, 1, Items.item(Material.DIAMOND, 8, "Impure Crystal", "Try to make tools / weapons?"));
			break;
		case GOLD_ORE:
			b.setType(Material.LAVA);
			if (p.getInventory().firstEmpty() != -1) {
				p.getInventory().addItem(Items.item(Material.GOLD_INGOT, 12, "Shiny Stuff", "Try to make tools / weapons?"));
			} else {
				p.sendMessage(Prefix.prefix() + "Failed to harvest ore, your inventory is full.");
			}
			break;
		case IRON_ORE:
			b.setType(Material.WATER);
			switch(tool_type) {
			default:
				Items.dropItem(b, 1, Items.item(Material.GLASS, 3, "Crystal", "Perhaps burn it?"));
				break;
			}
			break;
		case COAL_ORE:
			switch (tool_type) {
			default:
				Items.dropItem(b, 1, Items.item(Material.FLINT, 1, "Dark Rock", "Its a dark rock"));
				break;
			}
			break;
		case STONE:
			switch (tool_type) {
			case STONE_PICKAXE:
				Items.dropItem(b, 1, Items.item(Material.INK_SACK, 2, (byte) 8, "Pebble", "It's smooth!"));
				break;
			case IRON_PICKAXE:
				Items.dropItem(b, 1, Items.item(Material.INK_SACK, 3, (byte) 8, "Pebble", "It's smooth!"));
				break;
			case GOLD_PICKAXE:
				Items.dropItem(b, 1, Items.item(Material.INK_SACK, 4, (byte) 8, "Pebble", "It's smooth!"));
				break;
			case DIAMOND_PICKAXE:
				Items.dropItem(b, 1, Items.item(Material.INK_SACK, 5, (byte) 8, "Pebble", "It's smooth!"));
				break;
			default:
				Items.dropItem(b, 1, Items.item(Material.INK_SACK, 1, (byte) 8, "Pebble", "It's smooth!"));
				break;
			}
			break;
		case LOG:
		case LOG_2:
			switch (tool_type) {
			case WOOD_AXE:
				Items.dropItem(b, 1, Items.item(Material.RABBIT_FOOT, 4, "Chunk of Wood", "Perhaps combine it?"));
				break;
			case STONE_AXE:
				Items.dropItem(b, 1, Items.item(Material.RABBIT_FOOT, 8, "Chunk of Wood", "Perhaps combine it?"));
				break;
			case IRON_AXE:
				Items.dropItem(b, 1, Items.item(Material.WOOD_STEP, 4, "Wood Slab", "Perhaps combine it?"));
				break;
			case GOLD_AXE:
				Items.dropItem(b, 1, Items.item(Material.WOOD_STEP, 8, "Wood Slab", "Perhaps combine it?"));
				break;
			case DIAMOND_AXE:
				Items.dropItem(b, 1, Items.item(Material.WOOD, 4, "Plank", "Try to make tools / weapons?"));
				break;
			default:
				Items.dropItem(b, 1, Items.item(Material.STICK, 6, "Twig", "Perhaps combine it?"));
				break;
			}
			break;
		default:
			break;
		}
	}
}
