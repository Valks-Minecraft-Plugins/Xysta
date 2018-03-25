package com.xysta.populators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

public class GrassPopulator extends BlockPopulator {
	@SuppressWarnings("deprecation")
	@Override
	public void populate(World world, Random random, Chunk chunk) {
		int density = 100;
		for (int n = 0; n < density; n++) {
			int cX = chunk.getX() * 16;
			int cZ = chunk.getZ() * 16;
			int cXOff = cX + new Random().nextInt(10);
			int cZOff = cZ + new Random().nextInt(10);

			Block b = world.getBlockAt(cXOff, (world.getHighestBlockYAt(cXOff, cZOff)), cZOff);
			Block check = world.getBlockAt(cXOff, (world.getHighestBlockYAt(cXOff, cZOff) - 1), cZOff);
			switch (check.getType()) {
			case GRASS:
			case DIRT:
				b.setType(Material.LONG_GRASS);
				b.setData((byte) (new Random().nextInt(2) + 1));
				break;
			case SNOW_BLOCK:
				b.setType(Material.SNOW);
				b.setData((byte) (new Random().nextInt(2) + 1));
				break;
			case SAND:
				double chance = Math.random();
				if (chance < 0.05) {
					b.setType(Material.DEAD_BUSH);
				}
				break;
			case STONE:
				b.setType(Material.BROWN_MUSHROOM);
			default:
				break;
			}
		}
	}
}
