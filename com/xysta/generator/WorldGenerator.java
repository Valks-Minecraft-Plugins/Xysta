package com.xysta.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import com.xysta.Xysta;
import com.xysta.populators.ChestPopulator;
import com.xysta.populators.GrassPopulator;
import com.xysta.populators.OrePopulator;
import com.xysta.populators.SpawnerPopulator;
import com.xysta.populators.TreePopulator;

public class WorldGenerator extends org.bukkit.generator.ChunkGenerator {
	Xysta plugin;
	
	List<BlockPopulator> populators = new ArrayList<BlockPopulator>();

	public WorldGenerator(Xysta instance) {
		plugin = instance;
		
		populators.add(new TreePopulator());
		populators.add(new OrePopulator());
		populators.add(new ChestPopulator(plugin));
		populators.add(new GrassPopulator());
		populators.add(new SpawnerPopulator());
	}

	public List<BlockPopulator> getDefaultPopulators(World world) {
		return populators;
	}

	byte getBlock(int x, int y, int z, byte[][] chunk) {	
		// if the Block section the block is in hasn't been used yet, allocate it
		try {
			if (chunk[y >> 4] == null)
				return 0; // block is air as it hasnt been allocated
			if (!(y <= 256 && y >= 0 && x <= 16 && x >= 0 && z <= 16 && z >= 0))
				return 0;
			try {
				return chunk[y >> 4][((y & 0xF) << 8) | (z << 4) | x];
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}
	}

	@SuppressWarnings("deprecation")
	/*
	 * Sets a block in the chunk. If the Block section doesn't exist, it allocates
	 * it. [y>>4] the section id (y/16) the math for the second offset confuses me
	 */
	void setBlock(int x, int y, int z, byte[][] chunk, Material material) {
		if (chunk[y >> 4] == null)
			chunk[y >> 4] = new byte[16 * 16 * 16];
		if (!(y <= 256 && y >= 0 && x <= 16 && x >= 0 && z <= 16 && z >= 0))
			return; // Out of bounds
		try {
			chunk[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = (byte) material.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public byte[][] generateBlockSections(World world, Random rand, int ChunkX, int ChunkZ, BiomeGrid biomeGrid) {
		// where we will store our blocks
		byte[][] chunk = new byte[world.getMaxHeight() / 16][];

		SimplexOctaveGenerator gen1 = new SimplexOctaveGenerator(world, 8);
		SimplexOctaveGenerator gen2 = new SimplexOctaveGenerator(world, 8);

		gen1.setScale(1 / 128.0); // little note: the .0 is VERY important
		gen2.setScale(1 / 64.0);

		int bottomsMagnitude = 4;

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int realX = x + ChunkX * 16; // used so that the noise function gives us
				int realZ = z + ChunkZ * 16; // different values each chunk
				double frequency = 0.5; // the reciprocal of the distance between points
				double amplitude = 0.5; // The distance between largest min and max values
				int multitude = 80; // how much we multiply the value between -1 and 1. It will determine how
									// "steep" the hills will be. Set to 192 and sea level 16 and gen1.scale to 1/2048 for max.
				int sea_level = 100;

				double maxHeight = gen1.noise(realX, realZ, frequency, amplitude) * multitude + sea_level;
				for (int y = 0; y < maxHeight; y++) {
					setBlock(x, y, z, chunk, Material.STONE); // set the current block to stone
				}

				int bottomHeight = (int) (gen2.noise(realX, realZ, 0.5, 0.5) * bottomsMagnitude + 8);
				

				for (int y = bottomHeight + 1; y > bottomHeight && y < maxHeight; y++) { // the overhang
					int thisblock = getBlock(x, y, z, chunk);
					int blockabove = getBlock(x, y + 1, z, chunk);

					if (thisblock != Material.AIR.getId() && blockabove == Material.AIR.getId()) {
						switch (biomeGrid.getBiome(x, z)) {
						case RIVER:
						case FROZEN_RIVER:
							Material[] materials = new Material[] {Material.GRASS, Material.STONE, Material.COBBLESTONE, Material.LEAVES};
							setBlock(x, y, z, chunk, materials[new Random().nextInt(materials.length)]);
							break;
						case DESERT:
						case MUTATED_DESERT:
						case DESERT_HILLS:
						case JUNGLE:
						case JUNGLE_EDGE:
						case JUNGLE_HILLS:
						case MUTATED_JUNGLE:
						case MUTATED_JUNGLE_EDGE:
							biomeBlocks(chunk, x, y, z, Material.SAND, 4);
							break;
						case OCEAN:
						case DEEP_OCEAN:
						case FROZEN_OCEAN:
							biomeBlocks(chunk, x, y, z, Material.SNOW_BLOCK, 4);
							break;
						default:
							biomeGrid.setBiome(x, z, Biome.EXTREME_HILLS_WITH_TREES);
							biomeBlocks(chunk, x, y, z, Material.DIRT, 4);
							setBlock(x, y, z, chunk, Material.GRASS);
							break;
						}
					}
				}
				// the seas
				for (int y = 0; y < 12; y++) {
					if (getBlock(x, y, z, chunk) == Material.AIR.getId()) {
						setBlock(x, y, z, chunk, Material.STATIONARY_WATER);
					}
				}
				
				// bottom
				for (int y = 0; y < 5; y++) {
					if (getBlock(x, y, z, chunk) == Material.STATIONARY_WATER.getId()) {
						setBlock(x, y, z, chunk, Material.SAND);
					}
				}
				
				// beaches
				for (int y = 0; y < 15; y++) {
					if (getBlock(x, y, z, chunk) == Material.GRASS.getId()) {
						setBlock(x, y, z, chunk, Material.SAND);
					}
				}
				setBlock(x, 0, z, chunk, Material.BEDROCK);
			}
		}
		return chunk;
	}
	
	@SuppressWarnings("deprecation")
	public void biomeBlocks(byte[][] chunk, int x, int y, int z, Material material, int depth) {
		for (int d = 0; d < depth; d++) {
			if (getBlock(x, y - d, z, chunk) != Material.AIR.getId())
				setBlock(x, y - d, z, chunk, material);
		}
	}
}