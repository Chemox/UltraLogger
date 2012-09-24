package org.ultralogger.more;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.ultralogger.MainLogger;

public class HistoryManager implements Listener{
	
	public static File history = new File("./Log/dont_modify_me");
	
	private MainLogger plugin;
	
	private ArrayList<History> historic = new ArrayList<History>();
	private int itemID = 280;
	private int count =0;
	
	public HistoryManager(MainLogger mainLogger,int id) {
		plugin=mainLogger;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.itemID=id;
		if(!history.exists()){
			try {
				history.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;
		}
		name="("+e.getPlayer().getGameMode().name()+")"+name;
		Block i = e.getBlock();
		String msg ="-"+time+name+" "+plugin.translate("block.break")+" "+new ItemStack(i.getTypeId()).toString();
		int index =isHistoric(i.getLocation());
		if(index==-1){
			History h =new History(i.getLocation());
			h.add(msg);
			historic.add(h);
		}
		else{
			historic.get(index).add(msg);
		}
		count++;
		if(count%100==0){
			try {
				save();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	@EventHandler
	public void onPlace(BlockPlaceEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;
		}
		name="("+e.getPlayer().getGameMode().name()+")"+name;
		Block i = e.getBlock();
		String msg ="-"+time+name+" "+plugin.translate("block.place")+" "+new ItemStack(i.getTypeId()).toString();
		int index =isHistoric(i.getLocation());
		if(index==-1){
			History h =new History(i.getLocation());
			h.add(msg);
			historic.add(h);
		}
		else{
			historic.get(index).add(msg);
		}
		count++;
		if(count%100==0){
			try {
				save();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	private int times =0;
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		Player p =e.getPlayer();
		if(e.getAction()!=Action.RIGHT_CLICK_BLOCK||!e.hasItem()||!plugin.canSeeHistory(p)){ return;}
		int id = e.getPlayer().getItemInHand().getTypeId();
		if(id!=itemID){ return ;}
		times++;
		if(times%20==0){
			try {
				save();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		Location loc = e.getClickedBlock().getLocation();
		int index = isHistoric(loc);
		if(index==-1){
			p.sendMessage(ChatColor.RED+"No data found for this location !");
		}
		else{
			p.sendMessage(historic.get(index).whatHappened());
		}
		
	}
	
	public int isHistoric(Location loc){
		int x =0;
		for(Iterator<History> i = historic.iterator();i.hasNext();){
			History g = i.next();
			if(equal(g.getLocation(),loc)){
				return x;
			}
			x++;
		}
		return -1;
	}

	/**
	 * 
	 * @param location
	 * @param loc
	 * @return if these two loc are equals but with their values
	 */
	private boolean equal(Location location, Location loc) {
		int x = location.getBlockX(),y = location.getBlockY(),z = location.getBlockZ();
		World w = location.getWorld();
		if(w==loc.getWorld()&&x==loc.getBlockX()&&y==loc.getBlockY()&&z==loc.getBlockZ()){
			return true;
		}
		return false;
	}

	public void save() throws Exception{
		PrintWriter out = new PrintWriter(history);
		for(Iterator<History> i = historic.iterator();i.hasNext();){
			out.println(i.next().toString());
		}
		out.close();
	}
	public void load() throws Exception{
		BufferedReader r = new BufferedReader(new FileReader(history));
		String s=r.readLine();
		while(s!=null){
			History h =History.fromString(s, plugin.getServer());
			if(h==null){ continue;}
			historic.add(h);
			s=r.readLine();
		}
		r.close();
	}
}
