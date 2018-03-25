package com.xysta.blocks;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FoodEffects implements Listener {
	@EventHandler
	private void consume(PlayerItemConsumeEvent e) {
		Player p = e.getPlayer();
		int[] effects = { 1, 2, 3, 4, 5 };
		int randomEffect = effects[new Random().nextInt(effects.length)];
		switch (randomEffect) {
		case 4:
			if (!p.hasPotionEffect(PotionEffectType.REGENERATION)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 0));
			}
			break;
		case 3:
			if (!p.hasPotionEffect(PotionEffectType.SLOW)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 400, 0));
			}
			break;
		case 2:
			if (!p.hasPotionEffect(PotionEffectType.BLINDNESS)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 400, 0));
			}
			break;
		case 1:
			if (!p.hasPotionEffect(PotionEffectType.CONFUSION)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 400, 0));
			}
			break;
		default:
			break;
		}
	}
}
