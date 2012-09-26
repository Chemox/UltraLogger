package org.ultralogger.logger;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.inventory.ItemStack;
import org.ultralogger.LoggerFile;
import org.ultralogger.MainLogger;

public class PlayerLogger implements Listener{

	private MainLogger plugin =null;
	private File log = new File("./Log/player.log");
	private LoggerFile out =null;

	public PlayerLogger(MainLogger main,boolean is,int max,boolean x){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		out =new LoggerFile(log,is,max,x);
	}
	public PlayerLogger(MainLogger main,boolean is,int max,Date s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		out =new LoggerFile(log,is,max,s);
	}
	
	@EventHandler
	public void onEnterBed(PlayerBedEnterEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;
		}
		name="("+e.getPlayer().getGameMode().name()+")"+name;
		Block bed = e.getBed();
		out.log(time+name+" "+plugin.translate("bed.enter")+" "+plugin.translate("in")+"  ["+(int)bed.getX()+","+(int)bed.getY()+","+(int)bed.getZ()+"]");
	}
	@EventHandler
	public void onLeaveBed(PlayerBedLeaveEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		if(e.getBed()==null||e.getPlayer()==null){return;}
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Block bed = e.getBed();
		out.log(time+name+" "+plugin.translate("bed.leave")+" "+plugin.translate("in")+"  ["+(int)bed.getX()+","+(int)bed.getY()+","+(int)bed.getZ()+"]");
	}
	@EventHandler
	public void onEmptyBucket(PlayerBucketEmptyEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Block bed = e.getBlockClicked();
		out.log(time+name+" "+plugin.translate("bucket.empty")+" "+plugin.translate("in")+" ["+(int)bed.getX()+","+(int)bed.getY()+","+(int)bed.getZ()+"]");
	}
	@EventHandler
	public void onFillBucket(PlayerBucketFillEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Block bed = e.getBlockClicked();
		out.log(time+name+" "+plugin.translate("bucket.fill")+" "+plugin.translate("in")+" ["+(int)bed.getX()+","+(int)bed.getY()+","+(int)bed.getZ()+"]");
	}
	@EventHandler
	public void onChangeWorld(PlayerChangedWorldEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		out.log(time+name+" "+plugin.translate("player.change.world")+" "+plugin.translate("from")+" "+e.getFrom().getName()+" "+plugin.translate("to")+" "+e.getPlayer().getWorld().getName());
	}
	@EventHandler
	public void onDropItem(PlayerDropItemEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Item i =e.getItemDrop();
		out.log(time+name+" "+plugin.translate("player.drop")+" "+i.getItemStack().toString()+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+
		(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onThrowEgg(PlayerEggThrowEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Egg i =e.getEgg();
		out.log(time+name+" "+plugin.translate("player.throw.egg")+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onExpChange(PlayerExpChangeEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		int i =e.getAmount();
		out.log(time+name+" "+plugin.translate("player.earns.xp")+" "+i+" xp(s) !");
	}
	@EventHandler
	public void onFish(PlayerFishEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		if(e.getState()==State.CAUGHT_ENTITY){
			Entity i =e.getCaught();
			if(i!=null){
				out.log(time+name+" "+plugin.translate("player.fishing")+" "+i.toString()+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+
			(int)i.getLocation().getZ()+"]");
			}
		}
		else{
			Player i =e.getPlayer();
			out.log(time+name+" "+plugin.translate("player.fish")+"("+e.getState().name()+") "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+
			(int)i.getLocation().getZ()+"]");
		}
	}
	@EventHandler
	public void onChangeGameMode(PlayerGameModeChangeEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		out.log(time+name+" "+plugin.translate("player.change.gamemode")+" "+e.getNewGameMode().name());
	}
	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Entity i = e.getRightClicked();
		out.log(time+name+" "+plugin.translate("entity.interact")+" "+i.toString()+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","
		+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		if(e.getAction()==Action.LEFT_CLICK_AIR||e.getAction()==Action.RIGHT_CLICK_AIR){
			Player i = e.getPlayer();
			if(e.hasItem()){
				out.log(time+name+" "+plugin.translate("player.interact")+" ("+e.getAction().name()+") "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+
			(int)i.getLocation().getZ()+"] "+plugin.translate("with")+" "+e.getItem().toString());
			}
			else{
				out.log(time+name+" "+plugin.translate("player.interact")+" ("+e.getAction().name()+") "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+
			(int)i.getLocation().getZ()+"] "+plugin.translate("player.with.hand")+"");
			}
		}
		else{
			Block i = e.getClickedBlock();
			if(e.hasItem()){
				out.log(time+name+" "+plugin.translate("player.interact")+" ("+e.getAction().name()+") "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+
			(int)i.getLocation().getZ()+"] "+plugin.translate("with")+" "+e.getItem().toString());
			}
			else{
				out.log(time+name+" "+plugin.translate("player.interact")+" ("+e.getAction().name()+") "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+
			(int)i.getLocation().getZ()+"] "+plugin.translate("player.with.hand")+"");
			}
		}
	}
	@EventHandler
	public void onBreakItem(PlayerItemBreakEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		ItemStack i =e.getBrokenItem();
		out.log(time+name+" "+plugin.translate("player.break.item")+" "+i.toString());
	}
	@EventHandler
	public void onEvent14(PlayerJoinEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		out.log(time+name+" "+plugin.translate("player.connected")+"");
	}
	@EventHandler
	public void onEvent15(PlayerPickupItemEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Item i =e.getItem();
		out.log(time+name+" "+plugin.translate("player.pickup.item")+" "+i.getItemStack().toString()+
				" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onEvent16(PlayerPortalEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Location i = e.getFrom();
		Location f = e.getTo();
		out.log(time+name+" "+plugin.translate("player.teleport")+" "+plugin.translate("from")+" ["+(int)i.getX()+","+(int)i.getY()+","+(int)i.getZ()+"] "+plugin.translate("to")+" ["+(int)f.getX()+","+(int)f.getY()+","+
		(int)f.getZ()+"] "+plugin.translate("cause")+" "+e.getCause().name());
	}
	@EventHandler
	public void onEvent17(AsyncPlayerPreLoginEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getName()+" ("+e.getAddress().getHostAddress()+")";
		if(e.getKickMessage()!=null&&e.getKickMessage()!=""){
			out.log(time+name+" "+plugin.translate("was")+" "+e.getResult().name()+" "+plugin.translate("cause")+" : "+e.getKickMessage());
		}
		else{
			out.log(time+name+" "+plugin.translate("was")+" "+e.getResult().name());
		}
	}
	@EventHandler
	public void onEvent18(PlayerQuitEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		out.log(time+name+" "+plugin.translate("player.disconnect")+" ");
	}
	@EventHandler
	public void onEvent19(PlayerRespawnEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Location i = e.getRespawnLocation();
		out.log(time+name+" "+plugin.translate("player.respawn")+" "+plugin.translate("in")+" ["+(int)i.getX()+","+(int)i.getY()+","+(int)i.getZ()+"]");
	}
	@EventHandler
	public void onEvent20(PlayerShearEntityEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;
			}
		name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Entity i = e.getEntity();
		out.log(time+name+" "+plugin.translate("player.shear")+" "+i.toString()+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+
		(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onEvent21(PlayerTeleportEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Location i = e.getFrom();
		Location f = e.getTo();
		out.log(time+name+" "+plugin.translate("player.teleport")+" "+plugin.translate("from")+" ["+(int)i.getX()+","+(int)i.getY()+","+(int)i.getZ()+"] "+plugin.translate("to")+" ["+(int)f.getX()+","+(int)f.getY()+","+
		(int)f.getZ()+"] "+plugin.translate("cause")+" "+e.getCause().name());
	}
	@EventHandler
	public void onEvent22(PlayerToggleFlightEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		if(e.isFlying()){
			out.log(time+name+" "+plugin.translate("player.start.fly"));
		}
		else{
			out.log(time+name+" "+plugin.translate("player.stop.fly"));
		}
	}
	@EventHandler
	public void onEvent23(PlayerToggleSprintEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		if(e.isSprinting()){
			out.log(time+name+" "+plugin.translate("player.start.sprint"));
		}
		else{
			out.log(time+name+" "+plugin.translate("player.stop.sprint"));
		}
	}
	@EventHandler
	public void onEvent24(PlayerToggleSneakEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		if(e.isSneaking()){
			out.log(time+name+" "+plugin.translate("player.start.sneak"));
		}
		else{
			out.log(time+name+" "+plugin.translate("player.stop.sneak"));
		}
	}
	@EventHandler
	public void onEvent25(PlayerDeathEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getEntity().getName();
		if(e.getEntity().isOp()){
			name="[Admin] "+name;}name="("+e.getEntity().getGameMode().name()+")"+name;
		
		out.log(time+name+" "+plugin.translate("entity.death")+"  : "+e.getDeathMessage());
	}
	@EventHandler
	public void onEvent26(PlayerLevelChangeEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		out.log(time+name+" "+plugin.translate("player.to.lvl")+" "+e.getNewLevel()+" "+plugin.translate("player.from.lvl")+" "+e.getOldLevel());
	}
	@EventHandler
	public void onEvent26(FoodLevelChangeEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getEntity().getName();
		if(e.getEntity().isOp()){
			name="[Admin] "+name;}name="("+e.getEntity().getGameMode().name()+")"+name;
		
		out.log(time+name+" "+plugin.translate("player.food.change")+" "+e.getFoodLevel());
	}
	

	public void disable(){
		out.close();
	}

}
