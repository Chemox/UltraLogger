package org.ultralogger.logger;

import java.io.File;
import java.util.Date;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.ItemStack;
import org.ultralogger.LoggerFile;
import org.ultralogger.MainLogger;

public class EntityLogger implements Listener{
	
	private MainLogger plugin =null;
	private File log = new File("./Log/entity.log");
	private LoggerFile out =null;
	
	public EntityLogger(MainLogger main,boolean is,int max,boolean s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		out =new LoggerFile(log,is,max,s);
	}
	public EntityLogger(MainLogger main,boolean is,int max,Date s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		out =new LoggerFile(log,is,max,s);
	}
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent e){
		Entity i =e.getEntity();
		out.log("["+e.getEntity().getWorld().getName()+"] "+i.toString()+" "+plugin.translate("creature.spawn")+" ("+e.getSpawnReason().name()+
				") "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");

	}
	@EventHandler
	public void onCreeperPower(CreeperPowerEvent e){
		Entity i =e.getEntity();
		out.log("["+e.getEntity().getWorld().getName()+"] "+i.toString()+" "+plugin.translate("creature.lightning")+" "+plugin.translate("in")+" ["+
				(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");

	}
	@EventHandler
	public void onEvent3(EntityBreakDoorEvent e){
		
		Entity i =e.getEntity();
		out.log("["+e.getEntity().getWorld().getName()+"] "+i.toString()+" "+plugin.translate("creature.broke.door")+" "+plugin.translate("in")+" ["+
				(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onDamaged(EntityDamageEvent e){		
		Entity i =e.getEntity();
		int dmg = e.getDamage();
		out.log("["+e.getEntity().getWorld().getName()+"] "+i.toString()+" "+plugin.translate("damaged")+" "+dmg+" "+plugin.translate("half.heart")+"  "+plugin.translate("in")+" ["+
				(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"] "+plugin.translate("cause")+" "+e.getCause().name());

	}
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e){
		Entity i =e.getEntity();
		out.log("["+e.getEntity().getWorld().getName()+"] "+i.toString()+" "+plugin.translate("entity.death")+" "+plugin.translate("in")+" ["+
				(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent e){
		
		Entity i =e.getEntity();
		if(i!=null){
			out.log("["+e.getEntity().getWorld().getName()+"] "+i.toString()+" "+plugin.translate("entity.explode")+MainLogger.transformToFlatLoc(i.getLocation()));
		}
		else{
			out.log(plugin.translate("plugin.explosion")+MainLogger.transformToFlatLoc(e.getLocation()));
		}
	}
	@EventHandler
	public void onEvent7(EntityInteractEvent e){
		
		Block i =e.getBlock();
		out.log("["+e.getEntity().getWorld().getName()+"] "+e.getEntity().toString()+" "+plugin.translate("entity.interact")+" "+new ItemStack(i.getTypeId())+" "+plugin.translate("in")+" ["+
				(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onEvent8(EntityRegainHealthEvent e){
		
		Entity i =e.getEntity();
		out.log("["+e.getEntity().getWorld().getName()+"] "+i.toString()+" "+plugin.translate("entity.regain")+" "+e.getAmount()+" "+plugin.translate("half.heart")+" "+plugin.translate("in")+" ["+
				(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"] "+plugin.translate("cause")+" "+e.getRegainReason().name());
	}
	@EventHandler
	public void onEvent9(EntityTameEvent e){
		
		Entity i =e.getEntity();
		out.log("["+e.getEntity().getWorld().getName()+"] "+i.toString()+" "+plugin.translate("entity.tame")+" "+e.getOwner().getName()+" "+plugin.translate("in")+" ["+
				(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onEvent10(ExpBottleEvent e){
		
		Entity i =e.getEntity();
		out.log("["+e.getEntity().getWorld().getName()+"] "+plugin.translate("expbottle.throw")+" "+plugin.translate("in")+" ["+
				(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onEvent11(PotionSplashEvent e){
		
		Entity i =e.getPotion();
		out.log("["+e.getEntity().getWorld().getName()+"] "+i.toString()+" "+plugin.translate("entity.throw")+" "+plugin.translate("in")+" ["+
				(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onEvent12(ProjectileHitEvent e){
		
		Entity i =e.getEntity();
		out.log("["+e.getEntity().getWorld().getName()+"] "+i.toString()+" "+plugin.translate("projectile.hit")+" "+plugin.translate("in")+" ["+
				(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	
	
	public void disable(){
		out.close();
	}

}
