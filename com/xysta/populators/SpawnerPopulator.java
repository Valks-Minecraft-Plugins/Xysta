package com.xysta.populators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

public class SpawnerPopulator extends BlockPopulator {
	@SuppressWarnings("deprecation")
	@Override
	public void populate(World world, Random random, Chunk chunk) {
		int density = 1;
		for (int n = 0; n < density; n++) {
			int cX = chunk.getX() * 16;
			int cZ = chunk.getZ() * 16;
			int cXOff = cX + random.nextInt(10);
			int cZOff = cZ + random.nextInt(10);

			int depth = 60;
			Block b = world.getBlockAt(cXOff, (world.getHighestBlockYAt(cXOff, cZOff) - depth ), cZOff);
			if (b.getType().equals(Material.STONE)) {
				b.setType(Material.MOB_SPAWNER);
				b.setData((byte) 59);
			}
		}
	}
}
