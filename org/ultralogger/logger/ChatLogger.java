package org.ultralogger.logger;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.ultralogger.LoggerFile;
import org.ultralogger.MainLogger;

public class ChatLogger implements Listener{
	
	private MainLogger plugin =null;
	private File log = new File("./Log/chat.log");
	private LoggerFile out =null;

	public ChatLogger(MainLogger main,boolean is,int max,boolean s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		out =new LoggerFile(log,is,max,s);
	}
	public ChatLogger(MainLogger main,boolean is,int max,Date s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		out =new LoggerFile(log,is,max,s);
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onSpeak(AsyncPlayerChatEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;
			}
		name="("+e.getPlayer().getGameMode().name()+")"+name;
		out.log(time+name+" --> "+e.getMessage());
	}
	public void log(String msg){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		out.log(time+msg);
	}
	public void disable(){
		out.close();
	}

}
