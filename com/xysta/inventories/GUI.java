package com.xysta.inventories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import com.xysta.utils.Prefix;

public class GUI implements Listener {
	InitGUI init;
	
	public GUI(InitGUI instance) {
		init = instance;
	}
	
	Map<String, Integer> handNavTracker = new HashMap<String, Integer>();
	Map<String, Integer> craftingNavTracker = new HashMap<String, Integer>();
	Map<String, Integer> furnaceNavTracker = new HashMap<String, Integer>();
	
	Map<String, Integer> handRecipeTracker = new HashMap<String, Integer>();
	Map<String, Integer> craftingRecipeTracker = new HashMap<String, Integer>();
	Map<String, Integer> furnaceRecipeTracker = new HashMap<String, Integer>();
	
	/*
	 * Recipe home navigation pages.
	 */
	public void itemNavigation(InventoryClickEvent event, List<Inventory> navigation, Map<String, Integer> navTracker, List<Inventory> recipes, Map<String, Integer> recipeTracker) {
		Player p = (Player) event.getWhoClicked();
		String invName = event.getInventory().getName();
		boolean isAir = event.getCurrentItem().getType().equals(Material.AIR);
		int slot = event.getSlot();
		// handle item home navigation
		if (invName.equals(navigation.get(0).getName())) {
			event.setCancelled(true);
			if (!isAir) {
				switch (slot) {
				case 45:
					if (navTracker.get(p.getName()) >= 1) {
						p.openInventory(navigation.get(navTracker.get(p.getName()) - 1));
						navTracker.put(p.getName(), navTracker.get(p.getName()) - 1);
					}
					break;
				case 49:
					p.openInventory(init.recipeGenres());
					break;
				case 53:
					if (navTracker.get(p.getName()) < navigation.size() - 1) {
						p.openInventory(navigation.get(navTracker.get(p.getName()) + 1));
						navTracker.put(p.getName(), navTracker.get(p.getName()) + 1);
					}
					break;
				default:
					int offset = navTracker.get(p.getName());
					p.openInventory(recipes.get(slot + (offset * 45)));
					recipeTracker.put(p.getName(), slot + (offset * 45));
				}

			}
		}
	}
	
	/*
	 * Specific recipe pages.
	 */
	public void recipeNavigation(InventoryClickEvent event, List<Inventory> navigation, Map<String, Integer> navTracker, List<Inventory> recipes, Map<String, Integer> recipeTracker) {
		Player p = (Player) event.getWhoClicked();
		String invName = event.getInventory().getName();
		int slot = event.getSlot();
		//handle recipe navigation
		if (invName.equals(recipes.get(0).getName())) {
			event.setCancelled(true);
			switch (slot) {
			case 18 + 9:
				if (recipeTracker.get(p.getName()) >= 1) {
					p.openInventory(recipes.get(recipeTracker.get(p.getName()) - 1));
					recipeTracker.put(p.getName(), recipeTracker.get(p.getName()) - 1);

				}
				break;
			case 26 + 9:
				if (recipeTracker.get(p.getName()) < recipes.size() - 1) {
					p.openInventory(recipes.get(recipeTracker.get(p.getName()) + 1));
					recipeTracker.put(p.getName(), recipeTracker.get(p.getName()) + 1);
				}
				break;
			case 18 + 9 + 4:
				navTracker.put(p.getName(), 0);
				p.openInventory(navigation.get(0));
				break;
			default:
				break;
			}
		}
	}
	
	@EventHandler
	public boolean onInventoryClick(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		Inventory inventory = event.getInventory();
		String invName = inventory.getName();
		int slot = event.getSlot();
		
		// handle item navigation
		itemNavigation(event, init.furnaceNavigation, furnaceNavTracker, init.translatedFurnaceRecipes, furnaceRecipeTracker);

		// all the hand recipes
		itemNavigation(event, init.handNavigation, handNavTracker, init.translatedHandRecipes, handRecipeTracker);

		// all the crafting recipes
		itemNavigation(event, init.craftingNavigation, craftingNavTracker, init.translatedCraftingRecipes, craftingRecipeTracker);

		// specific furnace recipe
		recipeNavigation(event, init.furnaceNavigation, furnaceNavTracker, init.translatedFurnaceRecipes, furnaceRecipeTracker);

		// specific hand recipe
		recipeNavigation(event, init.handNavigation, handNavTracker, init.translatedHandRecipes, handRecipeTracker);

		// specific crafting recipe
		recipeNavigation(event, init.craftingNavigation, craftingNavTracker, init.translatedCraftingRecipes, craftingRecipeTracker);

		// info
		if (invName.equals(init.info().getName())) {
			switch (slot) {
			case 0:
				p.closeInventory();
				p.sendMessage(Prefix.prefix() + "https://discord.gg/XxHHDAv");
				break;
			case 1:
				p.closeInventory();
				p.sendMessage(Prefix.prefix()
						+ "http://www.minecraftforum.net/forums/servers/pc-servers/2841405-viesta-custom-coded");
				break;
			case 2:
				p.closeInventory();
				p.sendMessage(Prefix.prefix() + "Usage: /tutorial <page>");
				break;
			case 3:
				p.closeInventory();
				p.sendMessage(Prefix.prefix() + "---------------------------");
				p.sendMessage(Prefix.prefix() + "Q: I'm stuck? A: Read the lores on the items for hints.");
				p.sendMessage(Prefix.prefix() + "---------------------------");
				break;
			case 4:
				p.closeInventory();
				p.sendMessage(Prefix.prefix() + "---------------------------");
				p.sendMessage(Prefix.prefix() + "No griefing.");
				p.sendMessage(Prefix.prefix() + "No bypassing the swear filters.");
				p.sendMessage(Prefix.prefix() + "Respect everyone including yourself.");
				p.sendMessage(Prefix.prefix() + "---------------------------");
				break;
			case 26:
				p.openInventory(init.help());
				break;
			default:
				break;
			}
		}

		// recipe genres
		if (invName.equals(init.recipeGenres().getName())) {
			switch (slot) {
			case 0:
				handNavTracker.put(p.getName(), 0);
				p.openInventory(init.handNavigation.get(0));
				break;
			case 1:
				craftingNavTracker.put(p.getName(), 0);
				p.openInventory(init.craftingNavigation.get(0));
				break;
			case 2:
				furnaceNavTracker.put(p.getName(), 0);
				p.openInventory(init.furnaceNavigation.get(0));
				break;
			case 26:
				p.openInventory(init.help());
				break;
			default:
				break;
			}
		}

		// help
		if (invName.equals(init.help().getName())) {
			switch (slot) {
			case 0:
				p.openInventory(init.info());
				break;
			case 1:
				p.openInventory(init.recipeGenres());
				break;
			default:
				break;
			}
		}

		return true;
	}
}
