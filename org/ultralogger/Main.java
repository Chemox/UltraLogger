package org.ultralogger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.swing.Timer;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.ultralogger.logger.EventManager;
import org.ultralogger.logger.FileLogger;
import org.ultralogger.logger.sql.BlockLogger;
import org.ultralogger.logger.sql.ChatLogger;
import org.ultralogger.logger.sql.CommandLogger;
import org.ultralogger.logger.sql.CraftLogger;
import org.ultralogger.logger.sql.EnchantmentLogger;
import org.ultralogger.logger.sql.EntityLogger;
import org.ultralogger.logger.sql.InventoryLogger;
import org.ultralogger.logger.sql.PlayerLogger;
import org.ultralogger.logger.sql.PluginLogger;
import org.ultralogger.logger.sql.SQL;
import org.ultralogger.logger.sql.VehicleLogger;
import org.ultralogger.logger.sql.WeatherLogger;
import org.ultralogger.logger.sql.WorldLogger;
import org.ultralogger.util.CommandManager;
import org.ultralogger.util.HistoryManager;
import org.ultralogger.util.RollbackCommandExecutor;

public class Main extends JavaPlugin{
	
	public static final File LANG = new File("./Log/lang.yml");
	public static final String VERSION = "2.0.2";
	
	//We take an instance of this plugin for uses in other classes or sub-instances
	public Main plugin;
	/**
	 * The Minecraft Logger ( in the console )
	 */
	private Logger logger;
	/**
	 * Our Configuration file
	 */
	private LoggerConfig logConfig;
	/**
	 * A list that contains all enabled loggers
	 */
	private ArrayList<FileLogger> loggers;
	
	private SQL sql = null;
	
	/**
	 * A timer to AutoSave and also check if it's a new day
	 */
	private Timer autoSaveTimer;
	private CommandManager cmdManager;
	private HistoryManager histManager = null;
	
	public void onEnable(){
		logger = getLoggerSafely();
		//We attribute our variable plugin
		plugin = this;
		//We load configuration
		logConfig = new LoggerConfig();
		logConfig.load();
		//We can check updates now
		if(logConfig.canCheckUpdates())
			checkUpdates();
		//We load our cmdManager
		cmdManager = new CommandManager();
		//We load our file loggers
		loggers=logConfig.createFileLoggers(this);
		//We load our SQL loggers just before we check if we need to load or not SQL parts
		String host = logConfig.getValue("host");
		if(host!=null && !host.equalsIgnoreCase("blank") && !host.equalsIgnoreCase("null")){
			//We need to load SQL parts
			loadSQL();
		}
		//Now we load our historian ^^
		if(Boolean.parseBoolean(logConfig.getValue("history_logger")))
			histManager =new HistoryManager(this, Integer.parseInt(logConfig.getValue("item_revealer")));
		//We create start our timer
		autoSaveTimer = createAutoSaveTimer();
		autoSaveTimer.setRepeats(true);
		autoSaveTimer.start();
		logger.info("has been enabled");
	}
	
	private void loadSQL() {
		sql = new SQL(this, logConfig.getValue("host"), logConfig.getValue("port"), logConfig.getValue("name"), logConfig.getValue("pass"), logConfig.getValue("database"),
				logConfig.getValue("table_prefix"));
		if(logConfig.isSQLloggerEnabled("block"))
			new BlockLogger(sql);
		if(logConfig.isSQLloggerEnabled("chat"))
			new ChatLogger(sql);
		if(logConfig.isSQLloggerEnabled("command"))
			new CommandLogger(sql);
		if(logConfig.isSQLloggerEnabled("craft"))
			new CraftLogger(sql);
		if(logConfig.isSQLloggerEnabled("enchantment"))
			new EnchantmentLogger(sql);
		if(logConfig.isSQLloggerEnabled("entity"))
			new EntityLogger(sql);
		if(logConfig.isSQLloggerEnabled("inventory"))
			new InventoryLogger(sql);
		if(logConfig.isSQLloggerEnabled("player"))
			new PlayerLogger(sql);
		if(logConfig.isSQLloggerEnabled("plugin"))
			new PluginLogger(sql);
		if(logConfig.isSQLloggerEnabled("vehicle"))
			new VehicleLogger(sql);
		if(logConfig.isSQLloggerEnabled("weather"))
			new WeatherLogger(sql);
		if(logConfig.isSQLloggerEnabled("world"))
			new WorldLogger(sql);
	}

	public void onDisable(){
		//We stop our timer first to prevent saving with null loggers
		if(autoSaveTimer!=null)
			autoSaveTimer.stop();
		//We save our configuration
		logConfig.save();
		//We close the connection if SQL was enabled
		if(sql!=null)
			try {
				sql.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		//We disable our historian if it was on
		if(histManager!=null)
			try {
				histManager.save();
			} catch (Exception e) {
				e.printStackTrace();
			}
		//We disable our loggers
		for(Iterator<FileLogger> loggers = this.loggers.iterator(); loggers.hasNext(); loggers.next().disable());
		logger.info("has been disabled");
	}
	
	public Timer createAutoSaveTimer() {
		ActionListener action = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Date Check
				Date date = logConfig.lastCreation();
				Date now = new Date(System.currentTimeMillis());
				int diff = date.compareTo(now);
				if(diff>logConfig.getFolderDuration()){
					//We should reload the plugin now
					plugin.getPluginLoader().disablePlugin(plugin);
					plugin.getPluginLoader().enablePlugin(plugin);
				}
				else{
					// Here we save the logs
					for(Iterator<FileLogger> loggers = plugin.loggers.iterator(); loggers.hasNext(); loggers.next().save());
				}	
			}	
		};
		return new Timer(logConfig.getAutoSave()*1000,action);//The time is in ms so we convert into ms
	}
	
	/**
	 *  Get the Bukkit logger first, before we try to create our own
	 */
	private Logger getLoggerSafely() {
		Logger log = null;
		try {
			log = getLogger();
		} catch (Throwable e) {
			// We'll handle it
		}
		if (log == null)
			log = Logger.getLogger("Minecraft");
		return log;
	}
	
	/**
	 * Show in the console log the latest build available
	 */
	public void checkUpdates(){
		String url = "http://dev.bukkit.org/server-mods/ultralogger/";
		logger.info("Checking for Updates ... ");
		//Check to prevent exceptions
		if(urlExists(url)){
			BufferedReader in = null;
			try {
				URL site = new URL(url);
				in = new BufferedReader(new InputStreamReader(site.openStream()));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					//Our line looks like:
					// Latest build : X.Y.Z CraftBukkit : 1.X.Y
					if(inputLine.contains("Latest build :")){
						String last = inputLine.substring(inputLine.indexOf("Latest build :")+14,
								inputLine.indexOf("Latest build :")+21).trim();
						//We remove char that are not numbers
						char C =last.charAt(last.length()-1);
						while(C!='0'&&C!='1'&&C!='2'&&C!='3'&&C!='4'&&C!='5'&&C!='6'&&C!='7'&&C!='8'&&C!='9'){
							last=last.substring(0, last.length()-1);
							C =last.charAt(last.length()-1);
						}
						if(!last.equalsIgnoreCase(VERSION))
							logger.info("A new update is available : v"+last);
						else
							logger.info("You're running latest build available.");
						break;
					}
				}
				in.close();
			}
			catch (IOException ex)  {
				ex.printStackTrace();
			}
			finally{
				try {
					in.close();
				}
				catch (IOException ex){
					ex.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * STATIC METHOD PART
	 * 	- Permissions check
	 * 	- Web Methods 
	 */
	
	/**Check if the specified url exists and can be reached
	 * 
	 * @param url the url to check
	 * @return if the url exists
	 */
	public static boolean urlExists(String url) {
		try {
			URL site = new URL(url);
			try {
				site.openStream();
				return true;
			} catch (IOException ex) {
				return false;
			}
		} catch (MalformedURLException ex) {
			return false;
		}
	}
	
	/**
	 * Check if the specified player has the permission to not to be logged
	 */
	@SuppressWarnings("rawtypes")
	public boolean isEventForbidden(Player p, Class clas){
		int id = EventManager.getID(clas);
		ArrayList<String> names = logConfig.getGroupsWithID(id);
		for(Iterator<String> name_it = names.iterator(); name_it.hasNext();){
			String next = name_it.next();
			if(p.hasPermission("ul.avoid."+next))
				return true;
		}
		return p.hasPermission("ul.avoid."+id) || p.hasPermission("ul.avoid.*");
	}
	
	/**
	 * Check if the specified player has the permission to see a block history
	 */
	public static boolean canSeeHistory(Player p){
		return p.hasPermission("ul.history") || p.hasPermission("ul.*") || p.isOp();
	}
	
	/**
	 * Check if the specified player has the permission to undo or redo a block destroying or placement
	 */
	public static boolean canRollBack(Player p){
		return p.hasPermission("ul.rollback") || p.hasPermission("ul.*") || p.isOp();
	}
	
	/**
	 * Check if the specified player has the [Admin] prefix
	 */
	public static boolean isAdmin(HumanEntity p){
		return p.hasPermission("ul.admin") || p.hasPermission("ul.*") || p.isOp();
	}

	public CommandManager getCommandManager() {
		return cmdManager;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(!(sender instanceof Player))
			return true;
		Player player = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("/ulundo") && args.length<3){ // If the player typed //undo then do the following...
			Location loc = player.getLocation();
			loc.add(0, -2, 0);
			RollbackCommandExecutor.undo(player, loc);
			return true;
		}
		else if(cmd.getName().equalsIgnoreCase("/ulundo") && args.length==3){
			int x = Integer.parseInt(args[0]);
			int y = Integer.parseInt(args[1]);
			int z = Integer.parseInt(args[2]);
			Location loc = new Location(player.getLocation().getWorld(),x,y,z);
			RollbackCommandExecutor.undo(player, loc);
			return true;
		}
		else if(cmd.getName().equalsIgnoreCase("/ulundo") && args.length==6){
			int x = Integer.parseInt(args[0]);
			int y = Integer.parseInt(args[1]);
			int z = Integer.parseInt(args[2]);
			Location loc = new Location(player.getLocation().getWorld(),x,y,z);
			int x1 = Integer.parseInt(args[3]);
			int y1 = Integer.parseInt(args[4]);
			int z1 = Integer.parseInt(args[5]);
			Location loc1 = new Location(player.getLocation().getWorld(),x1,y1,z1);
			RollbackCommandExecutor.undo(player, loc,loc1);
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("/ulredo") && args.length<3){ // If the player typed //undo then do the following...
			Location loc = player.getLocation();
			loc.add(0, -2, 0);
			RollbackCommandExecutor.redo(player, loc);
			return true;
		}
		else if(cmd.getName().equalsIgnoreCase("/ulredo") && args.length==3){
			int x = Integer.parseInt(args[0]);
			int y = Integer.parseInt(args[1]);
			int z = Integer.parseInt(args[2]);
			Location loc = new Location(player.getLocation().getWorld(),x,y,z);
			RollbackCommandExecutor.redo(player, loc);
			return true;
		}
		else if(cmd.getName().equalsIgnoreCase("/ulredo") && args.length==6){
			int x = Integer.parseInt(args[0]);
			int y = Integer.parseInt(args[1]);
			int z = Integer.parseInt(args[2]);
			Location loc = new Location(player.getLocation().getWorld(),x,y,z);
			int x1 = Integer.parseInt(args[3]);
			int y1 = Integer.parseInt(args[4]);
			int z1 = Integer.parseInt(args[5]);
			Location loc1 = new Location(player.getLocation().getWorld(),x1,y1,z1);
			RollbackCommandExecutor.redo(player, loc,loc1);
			return true;
		}
		//If this has happened the function will break and return true. if this hasn't happened the a value of false will be returned.
		return false; 
	}

}
