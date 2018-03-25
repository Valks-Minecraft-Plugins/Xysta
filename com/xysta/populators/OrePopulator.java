package com.xysta.populators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

public class OrePopulator extends BlockPopulator {
	@Override
	public void populate(World world, Random random, Chunk chunk) {
		generateOre(world, random, chunk, Material.COAL_ORE, 6, 8, 10);
		generateOre(world, random, chunk, Material.IRON_ORE, 20, 6, 8);
		generateOre(world, random, chunk, Material.GOLD_ORE, 36, 4, 6);
		generateOre(world, random, chunk, Material.DIAMOND_ORE, 50, 2, 4);
	}

	private void generateOre(World world, Random random, Chunk chunk, Material ore, int depth, int size, int density) {
		for (int n = 0; n < density; n++) {
			int cX = chunk.getX() * 16;
			int cZ = chunk.getZ() * 16;
			int cXOff = cX + random.nextInt(10);
			int cZOff = cZ + random.nextInt(10);

			for (int x = 0; x < new Random().nextInt(size) + 1; x++) {
				for (int z = 0; z < new Random().nextInt(size) + 1; z++) {
					for (int y = 0; y < new Random().nextInt(size) + 1; y++) {
						Block b = world.getBlockAt(cXOff + x, (world.getHighestBlockYAt(cXOff, cZOff) - depth) - y,
								cZOff + z);
						if (b.getType().equals(Material.STONE))
							b.setType(ore);
					}
				}
			}
		}
	}
}
