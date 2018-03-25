	package com.xysta.inventories;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import com.xysta.utils.Items;

public class InitGUI {
	public List<Inventory> translatedHandRecipes = new ArrayList<Inventory>();
	public List<Inventory> translatedCraftingRecipes = new ArrayList<Inventory>();
	public List<Inventory> translatedFurnaceRecipes = new ArrayList<Inventory>();

	static public List<ItemStack> handRecipeItems = new ArrayList<ItemStack>();
	static public List<ItemStack> craftingRecipeItems = new ArrayList<ItemStack>();
	static public List<ItemStack> furnaceRecipeItems = new ArrayList<ItemStack>();

	public List<Inventory> handNavigation = new ArrayList<Inventory>();
	public List<Inventory> craftingNavigation = new ArrayList<Inventory>();
	public List<Inventory> furnaceNavigation = new ArrayList<Inventory>();
	
	public void handRecipeHomeInv() {
		Inventory inv = null;
		final int breakEach = 45;
		for (int i = 0; i < handRecipeItems.size(); i++) {
			if (i % breakEach == 0) {
				inv = Bukkit.createInventory(null, 9 * 6, "Hand Recipe Navigation");
				inv.setItem(45, Items.item(Material.STAINED_GLASS_PANE, 1, (byte) 1, "Previous", "Flip a page."));
				inv.setItem(49, Items.item(Material.STAINED_GLASS_PANE, 1, (byte) 3, "Back", "Go back a page."));
				inv.setItem(53, Items.item(Material.STAINED_GLASS_PANE, 1, (byte) 2, "Next", "Flip a page."));
				handNavigation.add(inv);
			}
			inv.setItem(i % breakEach, handRecipeItems.get(i));
		}
	}
	
	public void addRecipes() {
		handRecipe(Items.item(Material.RABBIT_FOOT, 1, "Chunk of Wood", "Perhaps combine it?"), "ssss",
				new ItemStack[] { Items.item(Material.STICK) }, new char[] { 's' });
		handRecipe(Items.item(Material.WOOD_STEP, 1, "Wood Slab", "Perhaps combine it?"), "ssss",
				new ItemStack[] { Items.item(Material.RABBIT_FOOT) }, new char[] { 's' });
		handRecipe(Items.item(Material.WOOD, 1, "Plank", "Try to make tools / weapons?"), "soso",
				new ItemStack[] { Items.item(Material.WOOD_STEP) }, new char[] { 's' });
		handRecipe(Items.item(Material.FISHING_ROD, 1, "Rod", "Strong Rod"), "ossw",
				new ItemStack[] { Items.item(Material.STRING), Items.item(Material.STICK) }, new char[] { 'w', 's' });
		handRecipe(Items.item(Material.ARROW, 1, "Arrow", "Fling!"), "sooo", new ItemStack[] { Items.item(Material.STICK) },
				new char[] { 's' });
		handRecipe(Items.item(Material.BOW, 1, "Bow", "Whoosh.."), "swsw",
				new ItemStack[] { Items.item(Material.WOOD), Items.item(Material.STICK) }, new char[] { 'w', 's' });
		addTools();
		handRecipe(Items.item(Material.FENCE, 1, "Fence", "Keep em' out.."), "ssww",
				new ItemStack[] { Items.item(Material.WOOD), Items.item(Material.STICK) }, new char[] { 'w', 's' });
		handRecipe(Items.item(Material.FENCE_GATE, 1, "Gate", "Keep em' out.."), "wwss",
				new ItemStack[] { Items.item(Material.WOOD), Items.item(Material.STICK) }, new char[] { 'w', 's' });
		handRecipe(Items.item(Material.CHEST, 1, "Storage", "Store your ItemS safely."), "ssss",
				new ItemStack[] { Items.item(Material.WOOD) }, new char[] { 's' });
		handRecipe(Items.item(Material.LADDER, 1, "Ladder", "Ms. Saftey"), "ssoo", new ItemStack[] { Items.item(Material.STICK) },
				new char[] { 's' });
		handRecipe(Items.item(Material.WOOD_DOOR, 1, "Door", "Saftey comes first.."), "soso",
				new ItemStack[] { Items.item(Material.WOOD) }, new char[] { 's' });
		handRecipe(Items.item(Material.TRAP_DOOR, 1, "Door", "Saftey comes first.."), "ssoo",
				new ItemStack[] { Items.item(Material.WOOD) }, new char[] { 's' });
		handRecipe(Items.item(Material.WOOD_STAIRS, 1, "Jagged Plank", "Has many uses.."), "soss",
				new ItemStack[] { Items.item(Material.WOOD) }, new char[] { 's' });
		handRecipe(Items.item(Material.WOOD_BUTTON, 1, "Button", "Handy switch.."), "sooo",
				new ItemStack[] { Items.item(Material.WOOD_STEP) }, new char[] { 's' });
		handRecipe(Items.item(Material.WOOD_PLATE, 1, "Plate", "Handy switch.."), "sooo",
				new ItemStack[] { Items.item(Material.WOOD) }, new char[] { 's' });
		handRecipe(Items.item(Material.LOG, 1, "Log", "Sturdy Plank.."), "wddw",
				new ItemStack[] { Items.item(Material.WOOD), Items.item(Material.DIRT) }, new char[] { 'w', 'd' });
		handRecipe(Items.item(Material.BOAT, 1, "Boat", "Set sail.."), "ssoo", new ItemStack[] { Items.item(Material.LOG) },
				new char[] { 's' });
		handRecipe(Items.item(Material.WOOL, 1, "Soft", "Silky.."), "ssss", new ItemStack[] { Items.item(Material.STRING) },
				new char[] { 's' });
		handRecipe(Items.item(Material.WOOL, 1, (byte) 1, "Soft", "Silky.."), "ssss", new ItemStack[] { Items.item(Material.WOOL) },
				new char[] { 's' });
		for (int i = 0; i < 14; i++) {
			handRecipe(Items.item(Material.WOOL, 1, (byte) (i + 2), "Soft", "Silky.."), "sooo",
					new ItemStack[] { Items.item(Material.WOOL, 1, (byte) (i + 1), "Soft", "Silky..") }, new char[] { 's' });
		}
		handRecipe(Items.item(Material.BED, 1, "Bed", "Soft.. silky.."), "ssll",
				new ItemStack[] { Items.item(Material.LOG), Items.item(Material.WOOL) }, new char[] { 'l', 's' });
		handRecipe(Items.item(Material.SUGAR, 1, "White Essence", "Powerful Essence"), "ssss",
				new ItemStack[] { Items.item(Material.WOOL) }, new char[] { 's' });
		handRecipe(Items.item(Material.SEEDS, 1, "Powerful Essence", "Grow for Power"), "ssss",
				new ItemStack[] { Items.item(Material.SUGAR) }, new char[] { 's' });
		handRecipe(Items.item(Material.PUMPKIN_SEEDS, 1, "Powerful Essence", "Grow for Power"), "ssss",
				new ItemStack[] { Items.item(Material.SEEDS) }, new char[] { 's' });
		handRecipe(Items.item(Material.MELON_SEEDS, 1, "Powerful Essence", "Grow for Power"), "ssss",
				new ItemStack[] { Items.item(Material.PUMPKIN_SEEDS) }, new char[] { 's' });
		handRecipe(Items.item(Material.POTATO_ITEM, 1, "Powerful Essence", "Grow for Power"), "ssss",
				new ItemStack[] { Items.item(Material.MELON_SEEDS) }, new char[] { 's' });
		handRecipe(Items.item(Material.CARROT_ITEM, 1, "Powerful Essence", "Grow for Power"), "ssss",
				new ItemStack[] { Items.item(Material.POTATO_ITEM) }, new char[] { 's' });
		handRecipe(Items.item(Material.NETHER_STALK, 1, "Powerful Essence", "Grow for Power"), "ssss",
				new ItemStack[] { Items.item(Material.CARROT_ITEM) }, new char[] { 's' });
		handRecipe(Items.item(Material.DIRT, 1, (byte) 1, "Super Dirt", "???"), "ssss",
				new ItemStack[] { Items.item(Material.DIRT) }, new char[] { 's' });
		handRecipe(Items.item(Material.DIRT, 1, (byte) 2, "Super Dirt", "???"), "ssss",
				new ItemStack[] { Items.item(Material.DIRT, 1, (byte) 1, "Super Dirt", "???") }, new char[] { 's' });
		handRecipe(Items.item(Material.SOUL_SAND, 1, "Super Dirt", "???"), "ssss",
				new ItemStack[] { Items.item(Material.DIRT, 1, (byte) 2, "Super Dirt", "???") }, new char[] { 's' });
		handRecipe(Items.item(Material.WORKBENCH, 1, "Workbench", "The ultimate workbench.."), "ssss",
				new ItemStack[] { Items.item(Material.LOG) }, new char[] { 's' });
		handRecipe(Items.item(Material.PURPUR_SLAB, 1, "Stone Slab", "Perhaps combine it?"), "ssss",
				new ItemStack[] { Items.item(Material.INK_SACK, 1, (byte) 8, "Pebble", "It's smooth!") }, new char[] { 's' });
		handRecipe(Items.item(Material.COBBLESTONE, 1, "Cobble", "Try to make tools / weapons?"), "soso",
				new ItemStack[] { Items.item(Material.PURPUR_SLAB) }, new char[] { 's' });
		handRecipe(Items.item(Material.STONE_BUTTON, 1, "Button", "Handy switch.."), "sooo",
				new ItemStack[] { Items.item(Material.PURPUR_SLAB) }, new char[] { 's' });
		handRecipe(Items.item(Material.STONE_PLATE, 1, "Plate", "Handy switch.."), "sooo",
				new ItemStack[] { Items.item(Material.COBBLESTONE) }, new char[] { 's' });
		handRecipe(Items.item(Material.LEVER, 1, "Lever", "Saftey comes first.."), "sowo",
				new ItemStack[] { Items.item(Material.COBBLESTONE), Items.item(Material.WOOD) }, new char[] { 's', 'w' });
		handRecipe(Items.item(Material.COBBLESTONE_STAIRS, 1, "Jagged Cobble", "Has many uses.."), "soss",
				new ItemStack[] { Items.item(Material.COBBLESTONE) }, new char[] { 's' });
		handRecipe(Items.item(Material.REDSTONE_TORCH_ON, 1, "Torch", "Provides light.."), "sowo",
				new ItemStack[] { Items.item(Material.STICK), Items.item(Material.FLINT) }, new char[] { 'w', 's' });
		craftingRecipe(Items.item(Material.HOPPER, 1, "Funnel", "Very useful.."), "ssssossss",
				new ItemStack[] { Items.item(Material.COBBLESTONE) }, new char[] { 's' });
		craftingRecipe(Items.item(Material.FURNACE, 1, "Stove", "Very useful.."), "sssssssss",
				new ItemStack[] { Items.item(Material.COBBLESTONE) }, new char[] { 's' });
		furnaceRecipe(Items.item(Material.STAINED_GLASS, 1, (byte) 1, "Darkened Crystal I", "Perhaps burn it?"),
				Items.item(Material.GLASS));
		for (int i = 0; i < 14; i++) {
			furnaceRecipe(
					Items.item(Material.STAINED_GLASS, 1, (byte) (i + 2), "Darkened Crystal " + (i + 2), "Perhaps burn it?"),
					Items.item(Material.STAINED_GLASS, 1, (byte) (i + 1), "Darkened Crystal " + (i + 1), "Perhaps burn it?"));
		}
		handRecipe(Items.item(Material.IRON_INGOT, 1, "Solid Ingot", "Iron Age.."), "ssss",
				new ItemStack[] {
						Items.item(Material.STAINED_GLASS, 1, (byte) 15, "Darkened Crystal 15", "Perhaps burn it?") },
				new char[] { 's' });
		handRecipe(Items.item(Material.IRON_DOOR, 1, "Door", "Saftey comes first.."), "soso",
				new ItemStack[] { Items.item(Material.IRON_INGOT) }, new char[] { 's' });
		handRecipe(Items.item(Material.IRON_TRAPDOOR, 1, "Door", "Saftey comes first.."), "soos",
				new ItemStack[] { Items.item(Material.IRON_INGOT) }, new char[] { 's' });
		handRecipe(Items.item(Material.BRICK, 4, "Brick", "Building Blocks"), "sooo",
				new ItemStack[] { Items.item(Material.IRON_INGOT) }, new char[] { 's' });
		handRecipe(Items.item(Material.SMOOTH_BRICK, 8, "Brick", "Building Blocks"), "ssoo",
				new ItemStack[] { Items.item(Material.IRON_INGOT) }, new char[] { 's' });
		handRecipe(Items.item(Material.STONE, 8, "Brick", "Building Blocks"), "soso",
				new ItemStack[] { Items.item(Material.IRON_INGOT) }, new char[] { 's' });
		handRecipe(Items.item(Material.RAILS, 8, "Rails", "Transportation!"), "wssw",
				new ItemStack[] { Items.item(Material.IRON_INGOT), Items.item(Material.STICK) }, new char[] { 'w', 's' });
		handRecipe(Items.item(Material.POWERED_RAIL, 8, "Powerful Rail", "Boooossstttt!"), "wsoo",
				new ItemStack[] { Items.item(Material.RAILS), Items.item(Material.REDSTONE_TORCH_ON) }, new char[] { 'w', 's' });
		handRecipe(Items.item(Material.MINECART, 1, "Cart", "Like mario no? :("), "ssss",
				new ItemStack[] { Items.item(Material.IRON_INGOT) }, new char[] { 's' });
		handRecipe(Items.item(Material.IRON_FENCE, 1, "Bars", "Very strong."), "ssoo",
				new ItemStack[] { Items.item(Material.IRON_INGOT) }, new char[] { 's' });
		handRecipe(Items.item(Material.IRON_BLOCK, 1, "Block of Iron", "Building Blocks"), "soso",
				new ItemStack[] { Items.item(Material.IRON_INGOT) }, new char[] { 's' });
		handRecipe(Items.item(Material.WOOD_STEP, 1, "Wood Slab", "Perhaps combine it?"), "sooo",
				new ItemStack[] { Items.item(Material.BOWL) }, new char[] { 's' });
		handRecipe(Items.item(Material.ANVIL, 1, "Anvil", "Repair Stuff"), "iibb",
				new ItemStack[] { Items.item(Material.IRON_INGOT), Items.item(Material.IRON_BLOCK) }, new char[] { 'i', 'b' });
		handRecipe(Items.item(Material.BLACK_SHULKER_BOX, 1, "Advanced Storage", "Store stuff."), "wbbw",
				new ItemStack[] { Items.item(Material.WOOD), Items.item(Material.IRON_BLOCK) }, new char[] { 'w', 'b' });
		handRecipe(Items.item(Material.TORCH, 1, "Torch", "Emits Bright Light"), "boao",
				new ItemStack[] { Items.item(Material.IRON_INGOT), Items.item(Material.FLINT) }, new char[] { 'a', 'b' });
		addArmor();
		craftingRecipe(Items.item(Material.STICK, 1, "Wizard Wand", "Shoot snowballs."), "sssssssss",
				new ItemStack[] { Items.item(Material.STICK) }, new char[] { 's' });
		craftingRecipe(Items.item(Material.ARROW, 1, "Wizard Wand", "Shoot arrows."), "sssssssss",
				new ItemStack[] { Items.item(Material.WOOD) }, new char[] { 's' });
		craftingRecipe(Items.item(Material.BONE, 1, "Wizard Wand", "Shoot fireballs."), "sssssssss",
				new ItemStack[] { Items.item(Material.INK_SACK, 1, (byte) 8, "Pebble", "It's smooth!") }, new char[] { 's' });
		craftingRecipe(Items.item(Material.BLAZE_ROD, 1, "Wizard Wand", "Shoot lightning."), "sossossos",
				new ItemStack[] { Items.item(Material.COBBLESTONE) }, new char[] { 's' });
		craftingRecipe(Items.item(Material.BONE, 1, "Summoner Wand", "Summons wolfs."), "sosososos",
				new ItemStack[] { Items.item(Material.RABBIT_FOOT, 1, "Chunk of Wood", "Perhaps combine it?") },
				new char[] { 's' });
		craftingRecipe(Items.item(Material.BLAZE_ROD, 1, "Summoner Wand", "Summons horses."), "sosososos",
				new ItemStack[] { Items.item(Material.REDSTONE_TORCH_ON) }, new char[] { 's' });
		craftingRecipe(Items.item(Material.ARROW, 1, "Summoner Wand", "Summons golems."), "sosososos",
				new ItemStack[] { Items.item(Material.ARROW) }, new char[] { 's' });
	}

	public void addTools() {
		String[] types = new String[] { "WOOD", "STONE", "IRON", "GOLD", "DIAMOND" };
		String[] materials = new String[] { "WOOD", "COBBLESTONE", "IRON_INGOT", "GOLD_INGOT", "DIAMOND" };
		for (int i = 0; i < 5; i++) {
			handRecipe(Items.item(Material.valueOf(types[i] + "_AXE"), 1, "Hachet", "Chop trees to get more wood."), "wsww",
					new ItemStack[] { Items.item(Material.valueOf(materials[i])), Items.item(Material.STICK) },
					new char[] { 'w', 's' });
			handRecipe(Items.item(Material.valueOf(types[i] + "_PICKAXE"), 1, "Pick", "Mine stone for pebbles."), "wwsw",
					new ItemStack[] { Items.item(Material.valueOf(materials[i])), Items.item(Material.STICK) },
					new char[] { 'w', 's' });
			handRecipe(Items.item(Material.valueOf(types[i] + "_HOE"), 1, "Hoe", "Used to plant seeds."), "owso",
					new ItemStack[] { Items.item(Material.valueOf(materials[i])), Items.item(Material.STICK) },
					new char[] { 'w', 's' });
			handRecipe(Items.item(Material.valueOf(types[i] + "_SPADE"), 1, "Shovel", "Mine dirt to get dirt."), "woso",
					new ItemStack[] { Items.item(Material.valueOf(materials[i])), Items.item(Material.STICK) },
					new char[] { 'w', 's' });
			handRecipe(Items.item(Material.valueOf(types[i] + "_SWORD"), 1, "Sword", "Get loot from mobs."), "owsw",
					new ItemStack[] { Items.item(Material.valueOf(materials[i])), Items.item(Material.STICK) },
					new char[] { 'w', 's' });
		}
	}
	
	public void addArmor() {
		String[] types = new String[] { "LEATHER", "CHAINMAIL", "IRON", "GOLD", "DIAMOND" };
		String[] materials = new String[] { "WOOD", "IRON_FENCE", "IRON_INGOT", "GOLD_INGOT", "DIAMOND" };
		for (int i = 0; i < 5; i++) {
			craftingRecipe(Items.item(Material.valueOf(types[i] + "_HELMET"), 1, "Sturdy Helm", "Protection above.."), "ssssosooo",
					new ItemStack[] { Items.item(Material.valueOf(materials[i])) }, new char[] { 's' });
			craftingRecipe(Items.item(Material.valueOf(types[i] + "_CHESTPLATE"), 1, "Sturdy Plate", "Protection afront.."), "sosssssss",
					new ItemStack[] { Items.item(Material.valueOf(materials[i])) }, new char[] { 's' });
			craftingRecipe(Items.item(Material.valueOf(types[i] + "_LEGGINGS"), 1, "Sturdy Leggings", "Protection under.."), "ssssossos",
					new ItemStack[] { Items.item(Material.valueOf(materials[i])) }, new char[] { 's' });
			craftingRecipe(Items.item(Material.valueOf(types[i] + "_BOOTS"), 1, "Sturdy Boots", "Protection below.."), "ooosossos",
					new ItemStack[] { Items.item(Material.valueOf(materials[i])) }, new char[] { 's' });
		}
	}
	
	@SuppressWarnings("deprecation")
	public void handRecipe(ItemStack item, String rows, ItemStack[] ingredients, char[] ids) {
		ShapedRecipe recipe = new ShapedRecipe(item);
		String row1 = rows.substring(0, 2);
		String row2 = rows.substring(2, 4);
		recipe.shape(row1, row2);
		for (int i = 0; i < ingredients.length; i++) {
			recipe.setIngredient(ids[i], ingredients[i].getData());
		}
		Bukkit.addRecipe(recipe);
		translateHandRecipe(item, ingredients, rows, ids);
		handRecipeItems.add(item);
	}

	@SuppressWarnings("deprecation")
	public void craftingRecipe(ItemStack item, String rows, ItemStack[] ingredients, char[] ids) {
		ShapedRecipe recipe = new ShapedRecipe(item);
		String row1 = rows.substring(0, 3);
		String row2 = rows.substring(3, 6);
		String row3 = rows.substring(6, 9);
		recipe.shape(row1, row2, row3);
		for (int i = 0; i < ingredients.length; i++) {
			recipe.setIngredient(ids[i], ingredients[i].getData());
		}
		Bukkit.addRecipe(recipe);
		translateCraftingRecipe(item, ingredients, rows, ids);
		craftingRecipeItems.add(item);
	}

	public void furnaceRecipe(ItemStack result, ItemStack required) {
		FurnaceRecipe recipe = new FurnaceRecipe(result, required.getData());
		Bukkit.addRecipe(recipe);
		translateFurnaceRecipe(result, required);
		furnaceRecipeItems.add(result);
	}

	public void furnaceRecipeHomeInv() {
		Inventory inv = null;
		final int breakEach = 45;
		for (int i = 0; i < furnaceRecipeItems.size(); i++) {
			if (i % breakEach == 0) {
				inv = Bukkit.createInventory(null, 9 * 6, "Furnace Recipe Navigation");
				inv.setItem(45, Items.item(Material.STAINED_GLASS_PANE, 1, (byte) 1, "Previous", "Flip a page."));
				inv.setItem(49, Items.item(Material.STAINED_GLASS_PANE, 1, (byte) 3, "Back", "Go back a page."));
				inv.setItem(53, Items.item(Material.STAINED_GLASS_PANE, 1, (byte) 2, "Next", "Flip a page."));
				furnaceNavigation.add(inv);
			}
			inv.setItem(i % breakEach, furnaceRecipeItems.get(i));
		}
	}

	public void craftingRecipeHomeInv() {
		Inventory inv = null;
		final int breakEach = 45;
		for (int i = 0; i < craftingRecipeItems.size(); i++) {
			if (i % breakEach == 0) {
				inv = Bukkit.createInventory(null, 9 * 6, "Crafting Recipe Navigation");
				inv.setItem(45, Items.item(Material.STAINED_GLASS_PANE, 1, (byte) 1, "Previous", "Flip a page."));
				inv.setItem(49, Items.item(Material.STAINED_GLASS_PANE, 1, (byte) 3, "Back", "Go back a page."));
				inv.setItem(53, Items.item(Material.STAINED_GLASS_PANE, 1, (byte) 2, "Next", "Flip a page."));
				craftingNavigation.add(inv);
			}
			inv.setItem(i % breakEach, craftingRecipeItems.get(i));
		}
	}

	

	public void translateFurnaceRecipe(ItemStack result, ItemStack required) {
		Inventory recipe = Bukkit.createInventory(null, 36, "Furnace Recipe Guide");
		recipe.setItem(0, required);
		recipe.setItem(1, Items.item(Material.STAINED_GLASS_PANE, 1, (byte) 15, "--->", "--->"));
		recipe.setItem(2, result);
		recipe.setItem(18 + 9, Items.item(Material.STAINED_GLASS_PANE, 1, (byte) 1, "Previous", "Flip a page."));
		recipe.setItem(18 + 9 + 4, Items.item(Material.STAINED_GLASS_PANE, 1, (byte) 3, "Back", "Go back a page."));
		recipe.setItem(26 + 9, Items.item(Material.STAINED_GLASS_PANE, 1, (byte) 2, "Next", "Flip a page."));
		translatedFurnaceRecipes.add(recipe);
	}

	public void translateCraftingRecipe(ItemStack result, ItemStack[] required, String shape, char[] ids) {
		Inventory recipe = Bukkit.createInventory(null, 36, "Crafting Recipe Guide");
		for (int i = 0; i < ids.length; i++) {
			for (int n = 0; n < shape.length(); n++) {
				char c = shape.charAt(n);
				if (c == ids[i]) {
					if (n > 5) {
						recipe.setItem(n + 12, required[i]);
					} else if (n > 2) {
						recipe.setItem(n + 6, required[i]);
					} else {
						recipe.setItem(n, required[i]);
					}
				}
			}
		}
		recipe.setItem(12, Items.item(Material.STAINED_GLASS_PANE, 1, (byte) 15, "--->", "--->"));
		recipe.setItem(13, result);
		recipe.setItem(18 + 9, Items.item(Material.STAINED_GLASS_PANE, 1, (byte) 1, "Previous", "Flip a page."));
		recipe.setItem(18 + 9 + 4, Items.item(Material.STAINED_GLASS_PANE, 1, (byte) 3, "Back", "Go back a page."));
		recipe.setItem(26 + 9, Items.item(Material.STAINED_GLASS_PANE, 1, (byte) 2, "Next", "Flip a page."));
		translatedCraftingRecipes.add(recipe);
	}

	public void translateHandRecipe(ItemStack result, ItemStack[] required, String shape, char[] ids) {
		Inventory recipe = Bukkit.createInventory(null, 36, "Hand Recipe Guide");
		for (int i = 0; i < ids.length; i++) {
			for (int n = 0; n < shape.length(); n++) {
				char c = shape.charAt(n);
				if (c == ids[i]) {
					if (n > 1) {
						recipe.setItem(n + 7, required[i]);
					} else {
						recipe.setItem(n, required[i]);
					}
				}
			}
		}
		recipe.setItem(2, Items.item(Material.STAINED_GLASS_PANE, 1, (byte) 15, "--->", "--->"));
		recipe.setItem(3, result);
		recipe.setItem(18 + 9, Items.item(Material.STAINED_GLASS_PANE, 1, (byte) 1, "Previous", "Flip a page."));
		recipe.setItem(18 + 9 + 4, Items.item(Material.STAINED_GLASS_PANE, 1, (byte) 3, "Back", "Go back a page."));
		recipe.setItem(26 + 9, Items.item(Material.STAINED_GLASS_PANE, 1, (byte) 2, "Next", "Flip a page."));
		translatedHandRecipes.add(recipe);
	}

	public Inventory help() {
		Inventory help = Bukkit.createInventory(null, 9, "Help Central");
		help.setItem(0, Items.invInfo(Material.BOOK, "Info", new String[] { "Useful info." }));
		help.setItem(1, Items.invInfo(Material.PAPER, "Recipes", new String[] { "View all the recipes." }));
		
		help.setItem(8, Items.invInfo(Material.FEATHER, "Research", new String[] { "Book of Research"}));
		return help;
	}

	public Inventory recipeGenres() {
		Inventory genres = Bukkit.createInventory(null, 27, "Recipe Genres");
		genres.setItem(0, Items.invInfo(Material.FEATHER, "Hand Recipes", new String[] { "2 x 2" }));
		genres.setItem(1, Items.invInfo(Material.WORKBENCH, "Crafting Recipes", new String[] { "3 x 3" }));
		genres.setItem(2, Items.invInfo(Material.FURNACE, "Furnace Recipes", new String[] { "1 x 1" }));
		genres.setItem(26, Items.invInfo(Material.ANVIL, "Back", new String[] { "Go Back." }));
		return genres;
	}

	public Inventory info() {
		Inventory info = Bukkit.createInventory(null, 27, "Info");
		info.setItem(0, Items.invInfo(Material.BOOK, "Discord", new String[] { "Link to discord." }));
		info.setItem(1, Items.invInfo(Material.BOOK, "Forum", new String[] { "Link to forum." }));
		info.setItem(2, Items.invInfo(Material.BOOK, "Tutorial", new String[] { "In-game tutorial." }));
		info.setItem(3, Items.invInfo(Material.BOOK, "FAQ", new String[] { "Frequently asked questions." }));
		info.setItem(4, Items.invInfo(Material.BOOK, "Rules", new String[] { "List rules." }));
		info.setItem(26, Items.invInfo(Material.ANVIL, "Back", new String[] { "Go Back." }));
		return info;
	}
}
