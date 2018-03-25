package com.xysta.populators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

public class TreePopulator extends BlockPopulator {
	@Override
	public void populate(World world, Random random, Chunk chunk) {
		for (int n = 0; n < 2; n++) {
			int cX = chunk.getX() * 16;
			int cZ = chunk.getZ() * 16;
			int cXOff = cX + random.nextInt(10);
			int cZOff = cZ + random.nextInt(10);

			boolean sand = false;
			boolean snow = false;

			Location check = new Location(world, cXOff, world.getHighestBlockYAt(cXOff, cZOff) - 1, cZOff);
			Location loc = new Location(world, cXOff, world.getHighestBlockYAt(cXOff, cZOff), cZOff);
			switch (check.getBlock().getType()) {
			case SAND:
				check.getBlock().setType(Material.GRASS);
				sand = true;
				break;
			case SNOW_BLOCK:
				check.getBlock().setType(Material.GRASS);
				snow = true;
				break;
			default:
				break;
			}
			Biome biome = world.getHighestBlockAt(cXOff, cZOff).getBiome();

			switch (biome) {
			case OCEAN:
			case DEEP_OCEAN:
			case FROZEN_OCEAN:
				world.generateTree(loc, TreeType.REDWOOD);
				break;
			case DESERT:
			case MUTATED_DESERT:
			case DESERT_HILLS:
			case JUNGLE:
			case JUNGLE_EDGE:
			case JUNGLE_HILLS:
			case MUTATED_JUNGLE:
			case MUTATED_JUNGLE_EDGE:
				world.generateTree(loc, TreeType.ACACIA);
				break;
			case RIVER:
			case FROZEN_RIVER:
				break;
			default:
				world.generateTree(loc, TreeType.TREE);
				//customTreeV2(world, cXOff, cZOff);
				break;
			}

			switch (check.getBlock().getType()) {
			case GRASS:
				if (sand) {
					check.getBlock().setType(Material.SAND);
				}
				if (snow) {
					check.getBlock().setType(Material.SNOW_BLOCK);
				}
				break;
			default:
				break;
			}
		}
	}

	public void customTreeV2(World world, int xOff, int zOff) {
		Block setPoint = world.getHighestBlockAt(xOff, zOff);
		if (setPoint.getLocation().subtract(0, 1, 0).getBlock().getType().equals(Material.LEAVES)) {
			return;
		}
		for (int y = 0; y < 10 + new Random().nextInt(30); y++) {
			Location loc = setPoint.getLocation();
			loc.add(0, y, 0).getBlock().setType(Material.LOG);
			if (y % 2 == 0 && y != 0) {
				double chance = Math.random();
				if (chance < 0.5) {
					int size = 3;
					for (int x = 0; x < size; x++) {
						for (int z = 0; z < size; z++) {
							if (loc.add(x - size / 2, 0, z - size / 2).getBlock().getType().equals(Material.AIR)) {
								loc.getBlock().setType(Material.LEAVES);
								loc.subtract(x - size / 2, 0, z - size / 2);
							}
						}
					}
				} else {
					int size = 5;
					for (int x = 0; x < size; x++) {
						for (int z = 0; z < size; z++) {
							if (loc.add(x - size / 2, 0, z - size / 2).getBlock().getType().equals(Material.AIR)) {
								loc.getBlock().setType(Material.LEAVES);
								loc.subtract(x - size / 2, 0, z - size / 2);
							}
						}
					}
				}
			}
		}
	}
}
