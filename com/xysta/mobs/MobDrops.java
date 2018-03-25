package com.xysta.mobs;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.xysta.utils.Items;

public class MobDrops implements Listener {
	@EventHandler
	public void entityDeath(EntityDeathEvent e) {
		LivingEntity target = (LivingEntity) e.getEntity();
		switch (target.getType()) {
		case WITHER_SKELETON:
			Items.dropItem(e.getEntity().getLocation(), 1, Items.item(Material.STRING, 3, "Silk", "Smooth and Silky"));
			break;
			default:
				break;
		}
	}
}
