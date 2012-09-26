package org.ultralogger.logger;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.BrewerInventory;
import org.ultralogger.LoggerFile;
import org.ultralogger.MainLogger;

public class CraftLogger implements Listener{
	
	private MainLogger plugin =null;
	private File log = new File("./Log/craft.log");
	private LoggerFile out =null;

	public CraftLogger(MainLogger main,boolean is,int max,boolean s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		out =new LoggerFile(log,is,max,s);
	}
	public CraftLogger(MainLogger main,boolean is,int max,Date s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		out =new LoggerFile(log,is,max,s);
	}
	
	@EventHandler
	public void onBrew(BrewEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		BrewerInventory i =e.getContents();
		out.log(time+" "+plugin.translate("brew")+" "+i.getItem(0)+" "+plugin.translate("with")+" "+i.getItem(1));
	}
	@EventHandler
	public void onCraft(CraftItemEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name ="";
		Player p =(Player) e.getView().getPlayer();
		if(p!=null){
			name =" "+p.getName();
		}
		out.log(time+name+" "+plugin.translate("craft.item")+" "+e.getRecipe().getResult().toString());
	}
	@EventHandler
	public void onSmelt(FurnaceSmeltEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		out.log(time+" "+plugin.translate("furnace.smelt")+" "+e.getResult().toString());
	}
	public void disable(){
		out.close();
	}

}
