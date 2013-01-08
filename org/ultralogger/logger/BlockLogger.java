package org.ultralogger.logger;

import java.io.File;
import java.util.Date;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.inventory.ItemStack;
import org.ultralogger.LoggerFile;
import org.ultralogger.MainLogger;

public class BlockLogger implements Listener{
	
	private MainLogger plugin =null;
	private File log = new File("./Log/block.log");
	private LoggerFile out =null;
	
	public BlockLogger(MainLogger main,boolean is,int max,boolean s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		out =new LoggerFile(log,is,max,s);
	}
	public BlockLogger(MainLogger main,boolean is,int max,Date s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		out =new LoggerFile(log,is,max,s);
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Block i = e.getBlock();
		out.log(name+" "+plugin.translate("block.break")+" "+new ItemStack(i.getTypeId()).toString()+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+
		(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onBurn(BlockBurnEvent e){
		
		Block i = e.getBlock();
		out.log(new ItemStack(i.getTypeId()).toString()+" "+plugin.translate("block.burn")+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+
		(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onDispense(BlockDispenseEvent e){
		
		Block i = e.getBlock();
		out.log(new ItemStack(i.getTypeId()).toString()+" "+plugin.translate("block.dispense")+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+
		(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onForm(BlockFormEvent e){
		
		Block i = e.getBlock();
		out.log(new ItemStack(i.getTypeId()).toString()+" "+plugin.translate("block.form")+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+
				","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onGrow(BlockGrowEvent e){
		
		Block i = e.getBlock();
		out.log(i.getData()+" "+plugin.translate("block.grow")+" "+e.getNewState().getRawData()+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+
		(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onPlace(BlockPlaceEvent e){
		
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Block i = e.getBlock();
		out.log(name+" "+plugin.translate("block.place")+" "+new ItemStack(i.getTypeId()).toString()+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+
		(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	public void disable(){
		out.close();
	}

}
