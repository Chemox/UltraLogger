package org.ultralogger.logger;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

import org.bukkit.block.Block;
import org.bukkit.entity.Painting;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.painting.PaintingBreakByEntityEvent;
import org.bukkit.event.painting.PaintingPlaceEvent;
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
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Block i = e.getBlock();
		out.log(time+name+" "+plugin.translate("block.break")+" "+new ItemStack(i.getTypeId()).toString()+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+
		(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onBurn(BlockBurnEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Block i = e.getBlock();
		out.log(time+new ItemStack(i.getTypeId()).toString()+" "+plugin.translate("block.burn")+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+
		(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onDispense(BlockDispenseEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Block i = e.getBlock();
		out.log(time+new ItemStack(i.getTypeId()).toString()+" "+plugin.translate("block.dispense")+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+
		(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onForm(BlockFormEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Block i = e.getBlock();
		out.log(time+new ItemStack(i.getTypeId()).toString()+" "+plugin.translate("block.form")+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+
				","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onGrow(BlockGrowEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Block i = e.getBlock();
		out.log(time+i.getData()+" "+plugin.translate("block.grow")+" "+e.getNewState().getRawData()+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+
		(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onPlace(BlockPlaceEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Block i = e.getBlock();
		out.log(time+name+" "+plugin.translate("block.place")+" "+new ItemStack(i.getTypeId()).toString()+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+
		(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onPaintPlace(PaintingPlaceEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Block i = e.getBlock();
		out.log(time+name+" "+plugin.translate("paint.place")+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+
				","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onPaintDestroy(PaintingBreakByEntityEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getRemover().toString();
		if(e.getRemover() instanceof Player){
			name = ((Player) e.getRemover()).getName();
			if(((Player) e.getRemover()).isOp()){
				name="[Admin] "+name;}name="("+((Player) e.getRemover()).getGameMode().name()+")"+name;
			
		}
		Painting i = e.getPainting();
		out.log(time+name+" "+plugin.translate("paint.break")+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+
				","+(int)i.getLocation().getZ()+"]");
	}
	public void disable(){
		out.close();
	}

}
