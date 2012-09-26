package org.ultralogger.sql.logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.ultralogger.MainLogger;
import org.ultralogger.sql.SQL;

public class ChatLogger implements Listener,Runnable{

	private SQL manager;
	
	public ChatLogger(SQL sql){
		manager=sql;
		Thread t = new Thread(this,"[UltraLogger] v1.6 - SQL Chat Logger");
	    t.start();
	}
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void onSpeak(AsyncPlayerChatEvent e){
		String name = e.getPlayer().getName();
        boolean op = MainLogger.isAdmin(e.getPlayer());
        String message = manager.StringCheck(e.getMessage());

		manager.query("INSERT INTO `"+manager.getprefix()+"_chat`(`time`,`op`,`playername`,`text`) VALUES (NOW(),"+ op +",'"+name+"', '"+message+"')");
	}

	@Override
	public void run() {
		manager.register(this);
		//Create the def chat table
		manager.query("CREATE TABLE IF NOT EXISTS `"+manager.getprefix()+"_chat` (`prim_key` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,`time` DATETIME NOT NULL,`op` BOOLEAN NOT NULL,`playername` VARCHAR(255) NOT NULL," +
                "`text` VARCHAR(255) NOT NULL,PRIMARY KEY (`prim_key`)) COLLATE='utf8_general_ci'");
		
	}

}
