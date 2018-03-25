package com.xysta.populators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import com.xysta.Xysta;
import com.xysta.inventories.InitGUI;

public class ChestPopulator extends BlockPopulator {
	Xysta plugin;

	private Random randomGenerator;

	public ChestPopulator(Xysta instance) {
		plugin = instance;
		randomGenerator = new Random();
	}

	@Override
	public void populate(World world, Random random, Chunk chunk) {
		for (int n = 0; n < 4; n++) {
			int cX = chunk.getX() * 16;
			int cZ = chunk.getZ() * 16;
			int cXOff = cX + random.nextInt(10);
			int cZOff = cZ + random.nextInt(10);

			Location loc = new Location(world, cXOff, world.getHighestBlockYAt(cXOff, cZOff) - (12 + new Random().nextInt(60)), cZOff);
			Block b = loc.getBlock();
			Material bt = b.getType();
			switch (bt) {
			case STATIONARY_LAVA:
			case STATIONARY_WATER:
			case WATER:
			case LAVA:
			case AIR:
			case LEAVES:
			case LEAVES_2:
				break;
			default:
				b.setType(Material.CHEST);
				Chest chest = (Chest) b.getState();
				Inventory inv = chest.getInventory();
				for (int i = 0; i < new Random().nextInt(27) + 4; i++) {
					if (!InitGUI.handRecipeItems.isEmpty()) {
						int index = randomGenerator.nextInt(InitGUI.handRecipeItems.size());
						ItemStack item = InitGUI.handRecipeItems.get(index);
						inv.addItem(item);
					}
				}
				break;
			}

		}
	}
}
