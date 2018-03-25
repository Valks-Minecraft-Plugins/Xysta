package com.xysta.tools;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import com.xysta.utils.Items;
import com.xysta.utils.Title;

public class Durabillity implements Listener {

	@EventHandler
	private void blockEvent(BlockBreakEvent event) {
		Player p = event.getPlayer();
		EntityEquipment equipment = p.getEquipment();
		Material tool_type = equipment.getItemInMainHand().getType();
		ItemStack hand_item = equipment.getItemInMainHand();
		switch (tool_type) {
		case WOOD_AXE:
		case WOOD_SPADE:
		case WOOD_PICKAXE:
		case WOOD_HOE:
		case WOOD_SWORD:
			hand_item.setDurability((short) (hand_item.getDurability() + 6));
			if (hand_item.getDurability() >= tool_type.getMaxDurability()) {
				equipment.setItemInMainHand(Items.item(Material.STICK, 2, "Twig", "Perhaps combine it?"));
				Title.sendActionMessage(p, "Your tool snapped in 2..", "red");
			}
			break;
		case STONE_AXE:
		case STONE_SPADE:
		case STONE_PICKAXE:
		case STONE_HOE:
		case STONE_SWORD:
			hand_item.setDurability((short) (hand_item.getDurability() + 5));
			if (hand_item.getDurability() >= tool_type.getMaxDurability()) {
				equipment.setItemInMainHand(Items.item(Material.STICK, 8, "Twig", "Perhaps combine it?"));
				Title.sendActionMessage(p, "Your tool shattered into several sticks..", "red");
			}
			break;
		case IRON_AXE:
		case IRON_SPADE:
		case IRON_PICKAXE:
		case IRON_HOE:
		case IRON_SWORD:
			hand_item.setDurability((short) (hand_item.getDurability() + 4));
			if (hand_item.getDurability() >= tool_type.getMaxDurability()) {
				equipment.setItemInMainHand(Items.item(Material.STICK, 16, "Twig", "Perhaps combine it?"));
				Title.sendActionMessage(p, "Your tool shattered into several sticks..", "red");
			}
			break;
		case GOLD_AXE:
		case GOLD_SPADE:
		case GOLD_PICKAXE:
		case GOLD_HOE:
		case GOLD_SWORD:
			hand_item.setDurability((short) (hand_item.getDurability() + 3));
			if (hand_item.getDurability() >= tool_type.getMaxDurability()) {
				equipment.setItemInMainHand(Items.item(Material.STICK, 32, "Twig", "Perhaps combine it?"));
				Title.sendActionMessage(p, "Your tool shattered into several sticks..", "red");
			}
			break;
		case DIAMOND_AXE:
		case DIAMOND_SPADE:
		case DIAMOND_PICKAXE:
		case DIAMOND_HOE:
		case DIAMOND_SWORD:
			hand_item.setDurability((short) (hand_item.getDurability() + 5));
			if (hand_item.getDurability() >= tool_type.getMaxDurability()) {
				equipment.setItemInMainHand(Items.item(Material.STICK, 64, "Twig", "Perhaps combine it?"));
				Title.sendActionMessage(p, "Your tool shattered into several sticks..", "red");
			}
			break;
		default:
			break;
		}
	}
}
