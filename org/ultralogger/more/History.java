package org.ultralogger.more;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.ultralogger.MainLogger;

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
	
	public void clear(){
		happened.clear();
	}
	/**
	 * 
	 * @return all blocks that were destroyed or placed at this loc
	 */
	public String whatHappened(){
		String s= ChatColor.GREEN+MainLogger.plugin.t.translate("in")+" ["+loc.getBlockX()+","+loc.getBlockY()+","+loc.getBlockZ()+"] :";
		for(Iterator<String> i = happened.iterator();i.hasNext();s+="\n"+ChatColor.ITALIC+""+ChatColor.AQUA+i.next());
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
			if(j==0){
				String world = i.substring(0, i.indexOf("["));
				if(v.getWorld(world)==null){ return h;}
				int g = i.indexOf(",");
				int x=Integer.parseInt(i.substring(s.indexOf("[")+1, g));
				g++;
				int y = Integer.parseInt(i.substring(g,i.indexOf(",", g)));
				g=i.indexOf(",", g)+1;
				int z = Integer.parseInt(i.substring(g, i.indexOf("]")));
				loc = new Location(v.getWorld(world), x,y,z);
				h = new History(loc);
			}
			else{
				h.add(i);
			}
		}
		return h;
	}
	
	@Override
	public String toString(){
		String s =loc.getWorld().getName()+"["+loc.getBlockX()+","+loc.getBlockY()+","+loc.getBlockZ()+"]";
		for(Iterator<String> i = happened.iterator();i.hasNext();){
			String r = i.next();
			if(!r.startsWith("-")){
				r="-"+r;
			}
			s+=r;
		}					
		return s;
	}

}
