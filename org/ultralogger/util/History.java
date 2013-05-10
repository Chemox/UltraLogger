package org.ultralogger.util;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;

public class History {
	
	private Location loc;
	private ArrayList<String> happened = new ArrayList<String>();
	
	public History(Location l){
		this.setLocation(l);
	}
	
	public void add(String event){
		happened.add(0,event);
	}
	
	public void remove(String event){
		happened.remove(event);
	}
	
	public String lastEvent(){
		if(happened.isEmpty())
			return null;
		String s ="";
		String last = happened.get(0);
		if(last.contains("placed"))
			s = "1+";
		else
			s = "0+";
		int x = last.lastIndexOf("{")+1;
		String block_id = last.substring(x, last.lastIndexOf("}")).trim();
		s+=block_id;
		return s;
	}
	
	public void clear(){
		happened.clear();
	}
	/**
	 * 
	 * @return all blocks that were destroyed or placed at this loc
	 */
	public String whatHappened(){
		if(happened.isEmpty())
			return ChatColor.RED+" No data found for this location !";
		String s= ChatColor.GREEN+"In ["+loc.getBlockX()+","+loc.getBlockY()+","+loc.getBlockZ()+"] :";
		for(Iterator<String> i = happened.iterator();i.hasNext();){
			String g =i.next();
			int index = g.lastIndexOf("{");
			int block_id = Integer.parseInt(g.substring(index+1, g.lastIndexOf("}")).trim());
			g=g.substring(0, index);
			String material = Material.getMaterial(block_id).name();
			g=g+material;
			s+="\n"+ChatColor.ITALIC+ChatColor.AQUA+g;
		}
		return s;
	}

	public Location getLocation() {
		return loc;
	}

	public void setLocation(Location loc) {
		this.loc = loc;
	}
	/**Create an History object from a String and a Server for the location because we need a world
	 * 
	 * @param s
	 * @param v
	 * @return
	 */
	public static History fromString(String s,Server v){
		History h = null;
		Location loc =null;
		String[] sep = s.split("-");
		for(int j=0;j<sep.length;j++){
			String i = sep[j];
			if(j==0 && i.contains("[") && i.contains("]") && i.contains(",")){
				String world = i.substring(0, i.indexOf("["));//To get the world : "...["
				if(v.getWorld(world)==null)
					return h;// If the world doesn't exist we return null
				int g = i.indexOf(",");
				int x = Integer.parseInt( i.substring(i.indexOf("[")+1, g) );//To get the loc X "[...,"
				int y = Integer.parseInt( i.substring(g+1, i.lastIndexOf(",") ) );//To get the loc Y ",...,"
				int z = Integer.parseInt( i.substring(i.lastIndexOf(",")+1, i.indexOf("]") ) );//To get the loc Z ",...]"
				loc = new Location( v.getWorld(world), x, y, z);
				h = new History(loc);
			}
			else if (h!=null)
				h.add(i);
		}
		return h;
	}
	//world[x,y,z]-time(GAMEMODE)Player placed/broke ItemStack{MATERIAL x Amount}
	@Override
	public String toString(){
		String s =loc.getWorld().getName()+"["+loc.getBlockX()+","+loc.getBlockY()+","+loc.getBlockZ()+"]";
		for(Iterator<String> i = happened.iterator();i.hasNext();){
			String r = i.next();
			if(!r.startsWith("-"))
				r="-"+r;
			s+=r;
		}					
		return s;
	}

	public void removeLast() {
		if(!happened.isEmpty())
			happened.remove(0);
	}

}
