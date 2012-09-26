package org.ultralogger.logger;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.*;
import org.ultralogger.LoggerFile;
import org.ultralogger.MainLogger;

public class WorldLogger implements Listener{
	
	private MainLogger plugin =null;
	private File log = new File("./Log/world.log");
	private LoggerFile out =null;
	
	public WorldLogger(MainLogger main,boolean is,int max,boolean s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		out =new LoggerFile(log,is,max,s);
	}
	public WorldLogger(MainLogger main,boolean is,int max,Date s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		out =new LoggerFile(log,is,max,s);
	}
	
	@EventHandler
	public void onPortal(PortalCreateEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Block i = e.getBlocks().get(0);
		out.log(time+"["+e.getWorld().getName()+"] "+plugin.translate("portal.create")+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+
		(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onGrow(StructureGrowEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name ="";
		if(e.getPlayer()!=null){
			name = e.getPlayer().getName();
			if(e.getPlayer().isOp()){
				name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
			
			name = ""+plugin.translate("by")+" "+name;
		}
		String bonemeal ="";
		if(e.isFromBonemeal()){
			bonemeal = " "+plugin.translate("cause.bonemeal")+"";
		}
		Location i = e.getLocation();
		out.log(time+"["+e.getWorld().getName()+"] a "+e.getSpecies().name()+" "+plugin.translate("structure.grow")+" "+plugin.translate("in")+" ["+(int)i.getX()+","+(int)i.getY()+","+(int)i.getZ()+"] "+bonemeal);
	}
	@EventHandler
	public void onSpawnChanges(SpawnChangeEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Location i =e.getPreviousLocation();
		Location it = e.getWorld().getSpawnLocation();
		out.log(time+"["+e.getWorld().getName()+"] "+plugin.translate("spawn.change")+" "+plugin.translate("from")+" ["+(int)i.getX()+","+(int)i.getY()+","+(int)i.getZ()+"] "+plugin.translate("to")+" ["+(int)it.getX()+","+(int)it.getY()+","+(int)it.getZ()+"] ");
	}
	public void disable(){
		out.close();
	}

}
