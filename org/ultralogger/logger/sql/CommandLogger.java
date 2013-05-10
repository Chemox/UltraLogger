package org.ultralogger.logger.sql;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.ultralogger.Main;

public class CommandLogger implements Listener,Runnable{

	private SQL manager;
	
	public CommandLogger(SQL sql){
		manager=sql;
		Thread t = new Thread(this,"[UltraLogger] - SQL Command Logger");
	    t.start();
	}
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void onCommand(PlayerCommandPreprocessEvent e){
		String name = e.getPlayer().getName();
		if(!((Main)manager.getPlugin()).getCommandManager().canLogCommand(e.getMessage()))
			return;
        boolean op = Main.isAdmin(e.getPlayer());
		boolean display =false;
		String msg = e.getMessage()+" ";
		String command = msg;
		if(msg.contains("/") && msg.contains(" "))
			command = msg.substring(msg.indexOf("/")+1, msg.indexOf(" "));
		if(manager.getPlugin().getServer().getPluginCommand(command)!=null || isServerCommand(command) ||
				manager.getPlugin().getServer().getCommandAliases().containsKey(command) || manager.getPlugin().getServer().getCommandAliases().containsValue(command)){
			display =true;
		}
        String message = manager.StringCheck(e.getMessage());
		manager.query("INSERT INTO `"+manager.getprefix()+"_command`(`time`,`op`, `playername`, `cmd`, `worked`) VALUES (NOW(),"+
				op + ",'" + name + "', '" + message + "', " + display + ")");
	}

    private boolean isServerCommand(String message) {
		if(message.startsWith("/help")||message.startsWith("/time set")||message.startsWith("/time add")||message.startsWith("/gamemode")
				||message.startsWith("/ban")||message.startsWith("/op")||message.startsWith("/deop")||message.startsWith("/defaultgamemode")
				||message.startsWith("/give")||message.startsWith("/kick")||message.startsWith("/list")||message.startsWith("/me")
				||message.startsWith("/pardon")||message.startsWith("/plugins")||message.startsWith("/pl")||message.startsWith("/reload")
				||message.startsWith("/save")||message.startsWith("/say")||message.startsWith("/xp")||message.startsWith("/seed")
				||message.startsWith("/time")||message.startsWith("/stop")||message.startsWith("/tell")||message.startsWith("/timings")
				||message.startsWith("/toggledownfall")||message.startsWith("/tp")||message.startsWith("/version")||message.startsWith("/whitelist")
				||message.startsWith("/kill")){
			return true;
		}
		return false;
	}

	@Override
	public void run() {
		manager.register(this);
		//Create the def command table
		manager.query("CREATE TABLE IF NOT EXISTS `"+manager.getprefix()+"_command` (`prim_key` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,`time` DATETIME NOT NULL,`op` BOOLEAN NOT NULL ,`playername` VARCHAR(255) NOT NULL," +
                "`cmd` VARCHAR(255) NOT NULL,`worked` BOOLEAN NOT NULL,PRIMARY KEY (prim_key)) COLLATE='utf8_general_ci'");	
	}

}
