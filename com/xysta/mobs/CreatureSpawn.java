package com.xysta.mobs;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import com.xysta.Xysta;

import net.md_5.bungee.api.ChatColor;

@SuppressWarnings("unused")
public class CreatureSpawn implements Listener {
	private int id;
	
	Xysta plugin;

	public CreatureSpawn(Xysta instance) {
		plugin = instance;
	}

	/*
	 * Mob spawning controller.
	 */
	@SuppressWarnings("deprecation")
	@EventHandler
	public void customMobs(CreatureSpawnEvent e) {
		switch (e.getSpawnReason()) {
		case NATURAL:
		case CHUNK_GEN:
		case JOCKEY:
		case MOUNT:
			e.setCancelled(true);
			Location loc = e.getLocation();
			World w = loc.getWorld();
			if (w.getEntitiesByClass(WitherSkeleton.class).size() < 10) {
				final LivingEntity mob = (LivingEntity) w.spawnEntity(e.getLocation(), EntityType.WITHER_SKELETON);
				mob.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6000, 0));
				mob.setCustomName(ChatColor.DARK_GRAY + "The Forgotten");
				mob.setCustomNameVisible(true);
				mob.setSilent(true);
				mob.setRemoveWhenFarAway(true);
				EntityEquipment equip = mob.getEquipment();
				equip.setItemInMainHand(new ItemStack(Material.STONE_SWORD));
				equip.setItemInOffHand(new ItemStack(Material.STONE_SWORD));

				Horse mount = (Horse) w.spawnEntity(loc, EntityType.HORSE);
				mount.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6000, 1));
				mount.setBreed(true);
				mount.setPassenger(mob);
				mount.setColor(Color.BLACK);
				mount.setSilent(true);
				mount.setRemoveWhenFarAway(true);
				mount.setTamed(true);
				mount.setStyle(Style.NONE);

				w.strikeLightningEffect(loc);
				
				/*id = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
		            @Override
		            public void run() {
		            	if (mob.getHealth() > 1 || mob != null) {
							mob.getLocation().getWorld().spawnParticle(Particle.LAVA, mob.getLocation(), 2);
						} else {
							Bukkit.getScheduler().cancelTask(id);
						}
		            }
		        }, 0L, 10L);*/
			}
			break;
		default:
			break;
		}
	}
}
