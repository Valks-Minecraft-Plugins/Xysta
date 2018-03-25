package com.xysta.mobs;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DamagedEffects implements Listener {
	@EventHandler
	private void entityDamaged(EntityDamageEvent e) {
		if (e.getEntity() instanceof LivingEntity) {
			LivingEntity target = (LivingEntity) e.getEntity();
			switch (e.getCause()) {
			case LIGHTNING:
			case BLOCK_EXPLOSION:
			case CONTACT:
			case DRAGON_BREATH:
			case DROWNING:
			case ENTITY_EXPLOSION:
			case FALL:
			case FIRE:
			case FALLING_BLOCK:
			case FLY_INTO_WALL:
			case HOT_FLOOR:
			case LAVA:
			case SUFFOCATION:
				if (!target.hasPotionEffect(PotionEffectType.BLINDNESS)) {
					target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 3));
					target.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 60, 3));
					target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 60, 3));
				}
				break;
			default:
				break;
			}
		}
	}
}
