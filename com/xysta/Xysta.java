package com.xysta;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.xysta.blocks.BlockDrops;
import com.xysta.blocks.CropDrops;
import com.xysta.blocks.FoodEffects;
import com.xysta.blocks.Gravity;
import com.xysta.blocks.LavaFlow;
import com.xysta.blocks.LeavesDecay;
import com.xysta.blocks.ObtainableBlocks;
import com.xysta.blocks.SignColor;
import com.xysta.blocks.SoftBlocks;
import com.xysta.chat.Broadcasts;
import com.xysta.chat.Chat;
import com.xysta.commands.Color;
import com.xysta.commands.Heal;
import com.xysta.commands.Help;
import com.xysta.commands.Home;
import com.xysta.commands.Mute;
import com.xysta.commands.Nick;
import com.xysta.commands.Rank;
import com.xysta.commands.Reload;
import com.xysta.commands.Tpa;
import com.xysta.commands.Trail;
import com.xysta.commands.Version;
import com.xysta.commands.Whisper;
import com.xysta.configs.LoadPlayerFiles;
import com.xysta.generator.WorldGenerator;
import com.xysta.inventories.GUI;
import com.xysta.inventories.InitGUI;
import com.xysta.inventories.JoinInventory;
import com.xysta.items.Items;
import com.xysta.player.*;
import com.xysta.tools.Durabillity;
import com.xysta.tools.SuperPickaxe;
import com.xysta.mobs.CreatureSpawn;
import com.xysta.mobs.DamagedEffects;
import com.xysta.mobs.MobDrops;
import com.xysta.particles.Particles;
import com.xysta.trails.Trails;
import com.xysta.utils.Prefix;

public class Xysta extends JavaPlugin {
	private final InitGUI init = new InitGUI();

	@Override
	public void onEnable() {
		initRecipes();

		ConsoleCommandSender console = this.getServer().getConsoleSender();
		PluginDescriptionFile p = this.getDescription();
		console.sendMessage(ChatColor.AQUA + p.getName() + " v" + p.getVersion() + " has been enabled.");

		registerConfig(this.getConfig());
		registerCommands(this);
		registerEvents(getServer().getPluginManager());
		
		new BukkitRunnable() {

			@Override
			public void run() {
				getServer().broadcastMessage(Prefix.prefix() + "Finished reloading!");
				
			}
			
		}.runTaskLater(this, 20 * 2);
		
		new BukkitRunnable() {
			@Override
			public void run() {
				getServer().dispatchCommand(Bukkit.getConsoleSender(), "gamerule doTileDrops false");
				getServer().dispatchCommand(Bukkit.getConsoleSender(), "gamerule announceAdvancements false");
				getServer().dispatchCommand(Bukkit.getConsoleSender(), "gamerule doMobLoot false");
			}

		}.runTaskLater(this, 20 * 12);
	}

	@Override
	public void onDisable() {
		ConsoleCommandSender console = this.getServer().getConsoleSender();
		PluginDescriptionFile p = this.getDescription();
		console.sendMessage(ChatColor.DARK_AQUA + p.getName() + " v" + p.getVersion() + " has been disabled.");
		getServer().broadcastMessage(Prefix.prefix() + "Reloading.. please wait..");
	}

	private void initRecipes() {
		init.addRecipes();
		init.handRecipeHomeInv();
		init.craftingRecipeHomeInv();
		init.furnaceRecipeHomeInv();
		
		new BukkitRunnable() {
			@Override
			public void run() {
				getServer().getLogger().info("Clearing Recipes.");
				getServer().clearRecipes();
				getServer().getLogger().info("Adding Custom Recipes.");
				init.addRecipes();
				init.handRecipeHomeInv();
				init.craftingRecipeHomeInv();
				init.furnaceRecipeHomeInv();
				getServer().getLogger().info("Finished adding recipes.");
			}

		}.runTaskLater(this, 20 * 10);
	}

	private void registerConfig(final FileConfiguration config) {
		config.addDefault("chat", "&8%player%&8: &7#color#%message%");
		config.addDefault("ranks.owner", "&8{&bQueen Ni&8} ");
		config.addDefault("ranks.coowner", "&8{&3Co&8-&3Owner&8} ");
		config.addDefault("ranks.developer", "&8{&3Dev&8} ");
		config.addDefault("ranks.admin", "&8{&3Admin&8} ");
		config.addDefault("ranks.moderator", "&8{&3Mod&8} ");
		config.addDefault("ranks.builder", "&8{&3Builder&8} ");
		config.addDefault("ranks.helper", "&8{&3Helper&8} ");
		config.addDefault("ranks.donator", "&8{&3Donator&8} ");
		config.addDefault("ranks.default", "&8{&7Member&8} ");
		config.options().copyDefaults(true);
		saveConfig();
	}

	private void registerCommands(Xysta xy) {
		xy.getCommand("version").setExecutor(new Version(this));
		xy.getCommand("reload").setExecutor(new Reload(this));
		xy.getCommand("help").setExecutor(new Help());
		xy.getCommand("home").setExecutor(new Home());
		xy.getCommand("sethome").setExecutor(new Home());
		xy.getCommand("delhome").setExecutor(new Home());
		xy.getCommand("heal").setExecutor(new Heal());
		xy.getCommand("trail").setExecutor(new Trail());
		xy.getCommand("nick").setExecutor(new Nick());
		xy.getCommand("rank").setExecutor(new Rank());
		xy.getCommand("color").setExecutor(new Color());
		xy.getCommand("tpa").setExecutor(new Tpa());
		xy.getCommand("tpaccept").setExecutor(new Tpa());
		xy.getCommand("tpdeny").setExecutor(new Tpa());
		xy.getCommand("mute").setExecutor(new Mute());
		xy.getCommand("whisper").setExecutor(new Whisper());
	}

	private void registerEvents(PluginManager pm) {
		// com.xysta.blocks
		pm.registerEvents(new BlockDrops(), this);
		pm.registerEvents(new CropDrops(), this);
		pm.registerEvents(new Gravity(), this);
		pm.registerEvents(new ObtainableBlocks(), this);
		pm.registerEvents(new LavaFlow(), this);
		pm.registerEvents(new LeavesDecay(), this);
		pm.registerEvents(new FoodEffects(), this);
		pm.registerEvents(new SoftBlocks(), this);
		pm.registerEvents(new SignColor(), this);

		// com.xysta.chat
		pm.registerEvents(new Chat(this), this);
		pm.registerEvents(new Broadcasts(), this);

		// com.xysta.configs
		pm.registerEvents(new LoadPlayerFiles(), this);

		// com.xysta.inventories
		pm.registerEvents(new GUI(init), this);
		pm.registerEvents(new JoinInventory(), this);

		// com.xysta.items
		pm.registerEvents(new Items(), this);

		// com.xysta.mobs
		pm.registerEvents(new CreatureSpawn(this), this);
		//pm.registerEvents(new PlayerMount(), this);
		pm.registerEvents(new DamagedEffects(), this);
		pm.registerEvents(new MobDrops(), this);

		// com.xysta.particles
		pm.registerEvents(new Trails(), this);
		pm.registerEvents(new Particles(), this);

		// com.xysta.playerstats
		pm.registerEvents(new UpdateHealth(), this);
		pm.registerEvents(new UpdateLevel(), this);

		// com.xysta.tools
		pm.registerEvents(new Durabillity(), this);
		pm.registerEvents(new SuperPickaxe(), this);
	}

	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
		return new WorldGenerator(this);
	}
}