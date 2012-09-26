package org.ultralogger.logger;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.ultralogger.LoggerFile;
import org.ultralogger.MainLogger;

public class CommandLogger implements Listener{

	private MainLogger plugin =null;
	private File log = new File("./Log/command.log");
	private LoggerFile out =null;

	public CommandLogger(MainLogger main,boolean is,int max,boolean s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		out =new LoggerFile(log,is,max,s);
	}
	public CommandLogger(MainLogger main,boolean is,int max,Date s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		out =new LoggerFile(log,is,max,s);
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name= "[Admin] "+name;
		}
		String display =" "+plugin.t.translate("try.command")+" --> ";
		if(plugin.getServer().getPluginCommand(e.getMessage())!=null||isServerCommand(e.getMessage())){
			display =" "+plugin.t.translate("command")+" --> ";
		}
		out.log(time+name+display+e.getMessage());
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

	public void disable(){
		out.close();
	}

}
