package com.xysta.particles;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Particles implements Listener {
	/*
	 * Particles emit on damaging blocks.
	 */
	@EventHandler
	private void blockDamageParticles(BlockDamageEvent e) {
		Location loc = e.getBlock().getLocation();
		loc.getWorld().spawnParticle(Particle.PORTAL, loc, 1);
	}

	/*
	 * Particles emit when a entity is damaged.
	 */
	@EventHandler
	private void entityDamageParticles(EntityDamageByEntityEvent e) {
		Location loc = e.getEntity().getLocation();
		loc.getWorld().spawnParticle(Particle.LAVA, loc, 1);
		loc.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, loc, 1);
	}
	
	/*
	 * Particles emit when a entity dies.
	 */
	@EventHandler
	private void entityDeathParticles(EntityDeathEvent e) {
		Location loc = e.getEntity().getLocation();
		loc.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, loc, 1);
		loc.getWorld().spawnParticle(Particle.PORTAL, loc, 10);
		loc.getWorld().spawnParticle(Particle.DRAGON_BREATH, loc, 50);
	}

	/*
	 * Particles emit when a furnace is finished smelting.
	 */
	@EventHandler
	private void smeltParticles(FurnaceSmeltEvent e) {
		Location loc = e.getBlock().getLocation();
		loc.getWorld().spawnParticle(Particle.LAVA, loc, 1);
	}

	/*
	 * Particles emit when a player finishs crafting a recipe.
	 */
	@EventHandler
	private void chatParticles(AsyncPlayerChatEvent e) {
		Location loc = e.getPlayer().getLocation();
		loc.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, loc, 5);
	}
}
