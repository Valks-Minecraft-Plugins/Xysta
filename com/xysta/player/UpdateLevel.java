package com.xysta.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

import com.xysta.utils.Title;

public class UpdateLevel implements Listener {
	/*
	 * Player keeps xp on death.
	 */
	@EventHandler(priority = EventPriority.LOW)
	private void onPlayerDeath(PlayerDeathEvent e) {
		e.setKeepLevel(true);
	}
	
	/*
	 * Dropped block xp cleared.
	 */
	@EventHandler
	private void blockEvent(BlockBreakEvent e) {
		e.setExpToDrop(0);
	}
	
	/*
	 * Dropped entity death xp cleared.
	 */
	@EventHandler
	private void deathEvent(EntityDeathEvent e) {
		e.setDroppedExp(0);
	}
	
	/*
	 * Update player level at a cap of 100.
	 */
	@EventHandler
	private void levelChange(PlayerLevelChangeEvent e) {
		if (e.getNewLevel() > 100) {
			e.getPlayer().setLevel(100);
		} else {
			Title.sendActionMessage(e.getPlayer(), "You're now level " + e.getNewLevel(), "gold");
		}
	}
}
