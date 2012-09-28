package org.ultralogger.more;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.ultralogger.MainLogger;

public class RollbackCommandExecutor {
	
	private static ArrayList<Player> players = new ArrayList<Player>();
	private static ArrayList<ArrayList<Location>> undoList = new ArrayList<ArrayList<Location>>();
	private static ArrayList<ArrayList<BlockState>> states = new ArrayList<ArrayList<BlockState>>();
	
	public static void undo(Player p,Location from,Location to){
		int good =0;
		int max =0;
		World w = from.getWorld();
		int minX = 0;
		int minY = 0;
		int minZ = 0;
		int maxX = 0;
		int maxY = 0;
		int maxZ = 0;
		if(from.getX()>to.getX()){
			maxX=(int) from.getX();
			minX=(int) to.getX();
		}
		else{
			maxX=(int) to.getX();
			minX=(int) from.getX();
		}
		if(from.getY()>to.getY()){
			maxY=(int) from.getY();
			minY=(int) to.getY();
		}
		else{
			maxY=(int) to.getY();
			minY=(int) from.getY();
		}
		if(from.getZ()>to.getZ()){
			maxZ=(int) from.getZ();
			minZ=(int) to.getZ();
		}
		else{
			maxZ=(int) to.getZ();
			minZ=(int) from.getZ();
		}
		maxX++;
		maxY++;
		maxZ++;
		int newY = minY;
		int newZ = minZ;
		System.out.println("minX["+minX+"]minY["+minY+"]minZ["+minZ+"] ----- maxX["+maxX+"]maxY["+maxY+"]maxZ["+maxZ+"]");
		for(;minX!=maxX;minX++){
			newY=minY;
			newZ=minZ;
			for(;newY!=maxY;newY++){
				newZ=minZ;
				for(;newZ!=maxZ;newZ++){
					Location loc = new Location(w,minX,newY,newZ);
					max++;
					if(undoThis(p,loc))
						good++;
				}
			}
		}
		p.sendMessage(ChatColor.GOLD+" Succesfully undo change(s) "+good+"/"+max+" times !");
	}
	
	/**Just exec the command
	 * 
	 * @param p
	 * @param loc
	 */
	public static void undo(Player p,Location loc){
		if(undoThis(p,loc))
			p.sendMessage(ChatColor.GOLD+" Succesfully undo change !");
		else
			p.sendMessage(ChatColor.GOLD+" Failed to undo change !");
	}
	/**Undo the last block change at the specified loc
	 * 
	 * @param p
	 * @param loc
	 * @return if succesfully undo
	 */
	private static boolean undoThis(Player p,Location loc){
		HistoryManager h =HistoryManager.getInstance();
		int x = h.isHistoric(loc);
		if(x>=0){
			if(!players.contains(p)){	
				ArrayList<Location> locs =new ArrayList<Location>();
				ArrayList<BlockState> state = new ArrayList<BlockState>();
				locs.add(loc);				
				History i = h.getHistory(loc);
				String happ = i.lastEvent();
				if(happ==null){
					return false;
				}
				if(happ.startsWith("0")){//destroyed a block
					int id =Material.matchMaterial(happ.substring(happ.indexOf("+")+1)).getId();
					state.add(loc.getWorld().getBlockAt(loc).getState());//save block state that were here before
					loc.getWorld().getBlockAt(loc).setTypeId(id);//so place the block
				}
				else{//placed a block
					state.add(loc.getWorld().getBlockAt(loc).getState());//save block state that were here before
					loc.getWorld().getBlockAt(loc).setTypeId(0);// so destroy a block
				}
				players.add(0,p);
				undoList.add(0,locs);
				states.add(0,state);
				i.removeLast();
			}
			else{
				int y =players.indexOf(p);
				ArrayList<Location> locs = undoList.get(y);
				locs.add(loc);
				ArrayList<BlockState> state = states.get(y);
				History i = h.getHistory(loc);
				String happ = i.lastEvent();
				if(happ==null){
					return false;
				}
				if(happ.startsWith("0")){//destroyed a block
					int id =Material.matchMaterial(happ.substring(happ.indexOf("+")+1).trim()).getId();
					state.add(loc.getWorld().getBlockAt(loc).getState());//save block state that were here before
					loc.getWorld().getBlockAt(loc).setTypeId(id);//so place the block
				}
				else{//placed a block
					state.add(loc.getWorld().getBlockAt(loc).getState());//save block state that were here before
					loc.getWorld().getBlockAt(loc).setTypeId(0);// so destroy a block
				}
				i.removeLast();
			}
			return true;
		}
		else{		
			return false;
		}
	}
	
	
	public static void redo(Player p,Location from,Location to){
		int good =0;
		int max =0;
		World w = from.getWorld();
		int minX = 0;
		int minY = 0;
		int minZ = 0;
		int maxX = 0;
		int maxY = 0;
		int maxZ = 0;
		if(from.getX()>to.getX()){
			maxX=(int) from.getX();
			minX=(int) to.getX();
		}
		else{
			maxX=(int) to.getX();
			minX=(int) from.getX();
		}
		if(from.getY()>to.getY()){
			maxY=(int) from.getY();
			minY=(int) to.getY();
		}
		else{
			maxY=(int) to.getY();
			minY=(int) from.getY();
		}
		if(from.getZ()>to.getZ()){
			maxZ=(int) from.getZ();
			minZ=(int) to.getZ();
		}
		else{
			maxZ=(int) to.getZ();
			minZ=(int) from.getZ();
		}
		maxX++;
		maxY++;
		maxZ++;
		int newY = minY;
		int newZ = minZ;
		for(;minX!=maxX;minX++){
			newY=minY;
			newZ=minZ;
			for(;newY!=maxY;newY++){
				newZ=minZ;
				for(;newZ!=maxZ;newZ++){
					Location loc = new Location(w,minX,newY,newZ);
					max++;
					if(redoThis(p,loc))
						good++;
				}
			}
		}
		p.sendMessage(ChatColor.GOLD+" Succesfully redo change(s) "+good+"/"+max+" times !");
	}
	
	/**Just exec the command
	 * 
	 * @param p
	 * @param loc
	 */
	public static void redo(Player p,Location loc){
		if(redoThis(p,loc))
			p.sendMessage(ChatColor.GOLD+" Succesfully redo change !");
		else
			p.sendMessage(ChatColor.GOLD+" Failed to redo change !");
	}
	/**Redo the last block change at the specified loc
	 * 
	 * @param p
	 * @param loc
	 * @return if succesfully redo
	 */
	private static boolean redoThis(Player p,Location loc){
		HistoryManager h =HistoryManager.getInstance();
		if(players.contains(p)){
			int index = players.indexOf(p);
			boolean del = false;
			if(undoList.get(index).contains(loc)){
				ArrayList<Location> locs = undoList.get(index);
				if(locs.size()==1)
					del = true;
				History i = h.getHistory(loc);
				String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
				int x = locs.indexOf(loc);
				ArrayList<BlockState> state = states.get(index);
				BlockState st = state.get(x);
				st.update(true);
				if(loc.getBlock().getTypeId()<=0)
					i.add("-"+time+p.getName()+" "+MainLogger.plugin.translate("block.place")+" "+new ItemStack(st.getTypeId(),1));
				else if(st.getTypeId()<=0)
					i.add("-"+time+p.getName()+" "+MainLogger.plugin.translate("block.break")+" "+new ItemStack(loc.getBlock().getTypeId(),1));
				else
					i.add("-"+time+p.getName()+" "+MainLogger.plugin.translate("block.place")+" "+new ItemStack(st.getTypeId(),1));
				locs.remove(loc);
				state.remove(st);
				if(del){
					states.remove(state);
					undoList.remove(locs);
					players.remove(p);
				}
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}

}
