package org.ultralogger.logger;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.ultralogger.LoggerFile;
import org.ultralogger.MainLogger;

public class EnchantmentLogger implements Listener{
	
	private MainLogger plugin =null;
	private File log = new File("./Log/enchantment.log");
	private LoggerFile out =null;

	public EnchantmentLogger(MainLogger main,boolean is,int max,boolean s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		out =new LoggerFile(log,is,max,s);;
	}
	public EnchantmentLogger(MainLogger main,boolean is,int max,Date s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		out =new LoggerFile(log,is,max,s);
	}
	
	@EventHandler
	public void onEnchant(EnchantItemEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Player p =e.getEnchanter();
		String enchants ="";
		for(Iterator<Enchantment> i =e.getEnchantsToAdd().keySet().iterator();i.hasNext();){
			enchants+=""+i.next().getName()+",";
		}
		enchants=enchants.substring(0, enchants.lastIndexOf(","));
		int cost = e.getExpLevelCost();
		out.log(time+p.getName()+" "+plugin.translate("enchant.item")+" "+e.getItem().toString()+" "+plugin.translate("for")+" "+cost+" xp(s) "+
				plugin.translate("with")+" "+enchants);
	}
	public void disable(){
		out.close();
	}

}
