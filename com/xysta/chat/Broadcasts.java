package com.xysta.chat;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.xysta.utils.Title;

public class Broadcasts implements Listener {
	@EventHandler(priority = EventPriority.HIGH)
	private void onPlayerJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		String name = p.getName();
		Bukkit.createBossBar(name + " joined", BarColor.PURPLE, BarStyle.SOLID, BarFlag.DARKEN_SKY);
		if (p.hasPlayedBefore()) {
			Title.sendTitle(p, "Viesta", null, "red");
			String[] msgs = { "A wild &3%player% &7appeared!", "&3%player% &7has been summoned!",
					"&3%player% &7came out of no where!", "&7A wild &3%player% &7appeared! Say hi!" };
			String finalMsg = ChatColor.translateAlternateColorCodes('&',
					"&8{&b+&8} &7" + msgs[new Random().nextInt(msgs.length)]);
			String msgFinal = finalMsg.replace("%player%", name);
			event.setJoinMessage(msgFinal);
		} else {
			Title.sendTitle(p, "Viesta", "Welcome!", "blue");
			Bukkit.createBossBar("Its " + p.getName() + " first time here! Welcome!", BarColor.WHITE, BarStyle.SOLID, BarFlag.PLAY_BOSS_MUSIC);
			String[] msgs = { "Its &3%player% &dfirst time here! Welcome!!",
					"&3%player% &dfell from the sky! Its their first time here! Welcome!",
					"&3%player% &ddiscovered catlandia! +69XP Its their first time here! Welcome!" };
			String finalMsg = ChatColor.translateAlternateColorCodes('&',
					"&8{&b+&8} &d" + msgs[new Random().nextInt(msgs.length)]);
			String msgFinal = finalMsg.replace("%player%", name);
			event.setJoinMessage(msgFinal);
		}
	}

	@EventHandler
	private void onPlayerLeave(PlayerQuitEvent event) {
		String name = event.getPlayer().getName();
		String[] msgs = { "vanished into thin air", "dissapeared", "thought cats were too much for them",
				"left their cat behind", "learned that kittens are really tough", "divorced their cats",
				"ran away from the cats" };
		String finalMsg = ChatColor.translateAlternateColorCodes('&',
				"&8{&b-&8} &3%player% &7" + msgs[new Random().nextInt(msgs.length)]);
		String msgFinal = finalMsg.replace("%player%", name);
		event.setQuitMessage(msgFinal);

	}

	@EventHandler
	private void onPlayerKick(PlayerKickEvent event) {
		String name = event.getPlayer().getName();
		String msgColor = ChatColor.translateAlternateColorCodes('&', "&8{&b-&8} &3%player% &7was kicked");
		String msgFinal = msgColor.replace("%player%", name);
		event.setLeaveMessage(msgFinal);
	}

	@EventHandler
	private void onPlayerDeath(PlayerDeathEvent event) {
		String name = event.getEntity().getName();
		String[] msgs = { "&3%player% &7got devoured by a kitten", "&3%player% &7had a date with a cat",
				"&3%player% &7thought they were a cat", "&3%player% &7tried to tame a kitten",
				"&3%player%&7's limbs were smashed",
				"&3%player% &7learned to keep their distance from kittens and cats alike", "&3%player% &7fed a cat",
				"&3%player% &7got a cat as a pet", "&3%player% &7turned into cat food",
				"&3%player% &7thought cats could fly", "&3%player% &7turned into a cat",
				"&3%player% &7got married to a cat", "&3%player% &7got candy from a cat",
				"&3%player% &7became a kittens slave", "&3%player% &7made a kitten really mad.",
				"&3%player% &7was scratched to death by cute kittens." };
		String msgColor = ChatColor.translateAlternateColorCodes('&', msgs[new Random().nextInt(msgs.length)]);
		String msgFinal = msgColor.replace("%player%", name);
		event.setDeathMessage(msgFinal);
	}
}
