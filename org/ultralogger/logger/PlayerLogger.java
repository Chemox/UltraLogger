package org.ultralogger.logger;

import java.io.File;
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
		
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;
		}
		name="("+e.getPlayer().getGameMode().name()+")"+name;
		Block bed = e.getBed();
		out.log(name+" "+plugin.translate("bed.enter")+MainLogger.transformToFlatLoc(bed.getLocation()));
	}
	@EventHandler
	public void onLeaveBed(PlayerBedLeaveEvent e){
		
		if(e.getBed()==null||e.getPlayer()==null){return;}
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Block bed = e.getBed();
		out.log(name+" "+plugin.translate("bed.leave")+MainLogger.transformToFlatLoc(bed.getLocation()));
	}
	@EventHandler
	public void onEmptyBucket(PlayerBucketEmptyEvent e){
		
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Block bed = e.getBlockClicked();
		out.log(name+" "+plugin.translate("bucket.empty")+MainLogger.transformToFlatLoc(bed.getLocation()));
	}
	@EventHandler
	public void onFillBucket(PlayerBucketFillEvent e){
		
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Block bed = e.getBlockClicked();
		out.log(name+" "+plugin.translate("bucket.fill")+MainLogger.transformToFlatLoc(bed.getLocation()));
	}
	@EventHandler
	public void onChangeWorld(PlayerChangedWorldEvent e){
		
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		out.log(name+" "+plugin.translate("player.change.world")+" "+plugin.translate("from")+" "+e.getFrom().getName()+" "+plugin.translate("to")+" "+e.getPlayer().getWorld().getName());
	}
	@EventHandler
	public void onDropItem(PlayerDropItemEvent e){
		
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Item i =e.getItemDrop();
		out.log(name+" "+plugin.translate("player.drop")+" "+i.getItemStack().toString()+MainLogger.transformToFlatLoc(i.getLocation()));
	}
	@EventHandler
	public void onThrowEgg(PlayerEggThrowEvent e){
		
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Egg i =e.getEgg();
		out.log(name+" "+plugin.translate("player.throw.egg")+MainLogger.transformToFlatLoc(i.getLocation()));
	}
	@EventHandler
	public void onExpChange(PlayerExpChangeEvent e){
		
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		int i =e.getAmount();
		out.log(name+" "+plugin.translate("player.earns.xp")+" "+i+" xp(s) !");
	}
	@EventHandler
	public void onFish(PlayerFishEvent e){
		
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		if(e.getState()==State.CAUGHT_ENTITY){
			Entity i =e.getCaught();
			if(i!=null){
				out.log(name+" "+plugin.translate("player.fishing")+" "+i.toString()+MainLogger.transformToFlatLoc(i.getLocation()));
			}
		}
		else{
			Player i =e.getPlayer();
			out.log(name+" "+plugin.translate("player.fish")+"("+e.getState().name()+")"+MainLogger.transformToFlatLoc(i.getLocation()));
		}
	}
	@EventHandler
	public void onChangeGameMode(PlayerGameModeChangeEvent e){
		
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		out.log(name+" "+plugin.translate("player.change.gamemode")+" "+e.getNewGameMode().name());
	}
	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent e){
		
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Entity i = e.getRightClicked();
		out.log(name+" "+plugin.translate("entity.interact")+" "+i.toString()+MainLogger.transformToFlatLoc(i.getLocation()));
	}
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		if(e.getAction()==Action.LEFT_CLICK_AIR||e.getAction()==Action.RIGHT_CLICK_AIR){
			Player i = e.getPlayer();
			if(e.hasItem()){
				out.log(name+" "+plugin.translate("player.interact")+" ("+e.getAction().name()+")"+MainLogger.transformToFlatLoc(i.getLocation())+" "+plugin.translate("with")+" "+e.getItem().toString());
			}
			else{
				out.log(name+" "+plugin.translate("player.interact")+" ("+e.getAction().name()+")"+MainLogger.transformToFlatLoc(i.getLocation())+" "+plugin.translate("player.with.hand")+"");
			}
		}
		else{
			Block i = e.getClickedBlock();
			if(e.hasItem()){
				out.log(name+" "+plugin.translate("player.interact")+" ("+e.getAction().name()+")"+MainLogger.transformToFlatLoc(i.getLocation())+" "+plugin.translate("with")+" "+e.getItem().toString());
			}
			else{
				out.log(name+" "+plugin.translate("player.interact")+" ("+e.getAction().name()+")"+MainLogger.transformToFlatLoc(i.getLocation())+" "+plugin.translate("player.with.hand")+"");
			}
		}
	}
	@EventHandler
	public void onBreakItem(PlayerItemBreakEvent e){
		
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		ItemStack i =e.getBrokenItem();
		out.log(name+" "+plugin.translate("player.break.item")+" "+i.toString());
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		out.log(name+" "+plugin.translate("player.connected")+"");
	}
	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent e){
		
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Item i =e.getItem();
		out.log(name+" "+plugin.translate("player.pickup.item")+" "+i.getItemStack().toString()+MainLogger.transformToFlatLoc(i.getLocation()));
	}
	@EventHandler
	public void onTeleported(PlayerPortalEvent e){
		
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;
		}
		name="("+e.getPlayer().getGameMode().name()+")"+name;
		Location i = e.getFrom();
		Location f = e.getTo();
		out.log(name+" "+plugin.translate("player.teleport")+" "+plugin.translate("from")+" ["+(int)i.getX()+","+(int)i.getY()+","+(int)i.getZ()+"] "+plugin.translate("to")+" ["+(int)f.getX()+","+(int)f.getY()+","+
		(int)f.getZ()+"] "+plugin.translate("cause")+" "+e.getCause().name());
	}
	@EventHandler
	public void onBeforeLogin(AsyncPlayerPreLoginEvent e){
		
		String name = e.getName()+" ("+e.getAddress().getHostAddress()+")";
		if(e.getKickMessage()!=null&&e.getKickMessage()!=""){
			out.log(name+" "+plugin.translate("was")+" "+e.getResult().name()+" "+plugin.translate("cause")+" : "+e.getKickMessage());
		}
		else{
			out.log(name+" "+plugin.translate("was")+" "+e.getResult().name());
		}
	}
	@EventHandler
	public void onDisconnect(PlayerQuitEvent e){
		
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		out.log(name+" "+plugin.translate("player.disconnect")+" ");
	}
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e){
		
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Location i = e.getRespawnLocation();
		out.log(name+" "+plugin.translate("player.respawn")+MainLogger.transformToFlatLoc(i));
	}
	@EventHandler
	public void onPlayerUseShears(PlayerShearEntityEvent e){
		
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;
			}
		name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Entity i = e.getEntity();
		out.log(name+" "+plugin.translate("player.shear")+" "+i.toString()+MainLogger.transformToFlatLoc(i.getLocation()));
	}
	@EventHandler
	public void onTeleport(PlayerTeleportEvent e){
		
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Location i = e.getFrom();
		Location f = e.getTo();
		out.log(name+" "+plugin.translate("player.teleport")+" "+plugin.translate("from")+" ["+(int)i.getX()+","+(int)i.getY()+","+(int)i.getZ()+"] "+plugin.translate("to")+" ["+(int)f.getX()+","+(int)f.getY()+","+
		(int)f.getZ()+"] "+plugin.translate("cause")+" "+e.getCause().name());
	}
	@EventHandler
	public void onFly(PlayerToggleFlightEvent e){
		
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		if(e.isFlying()){
			out.log(name+" "+plugin.translate("player.start.fly"));
		}
		else{
			out.log(name+" "+plugin.translate("player.stop.fly"));
		}
	}
	@EventHandler
	public void onSprint(PlayerToggleSprintEvent e){
		
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		if(e.isSprinting()){
			out.log(name+" "+plugin.translate("player.start.sprint"));
		}
		else{
			out.log(name+" "+plugin.translate("player.stop.sprint"));
		}
	}
	@EventHandler
	public void onSneak(PlayerToggleSneakEvent e){
		
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		if(e.isSneaking()){
			out.log(name+" "+plugin.translate("player.start.sneak"));
		}
		else{
			out.log(name+" "+plugin.translate("player.stop.sneak"));
		}
	}
	@EventHandler
	public void onDeath(PlayerDeathEvent e){
		
		String name = e.getEntity().getName();
		if(e.getEntity().isOp()){
			name="[Admin] "+name;
		}
		name="("+e.getEntity().getGameMode().name()+")"+name;
		out.log(name+" "+plugin.translate("entity.death")+"  : "+e.getDeathMessage()+" -- "+MainLogger.transformToFlatLoc(e.getEntity().getLocation()));
	}
	@EventHandler
	public void onLvlChange(PlayerLevelChangeEvent e){
		
		String name = e.getPlayer().getName();
		if(MainLogger.isAdmin(e.getPlayer())){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		out.log(name+" "+plugin.translate("player.to.lvl")+" "+e.getNewLevel()+" "+plugin.translate("player.from.lvl")+" "+e.getOldLevel());
	}
	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent e){
		
		String name = e.getEntity().getName();
		if(e.getEntity().isOp()){
			name="[Admin] "+name;}name="("+e.getEntity().getGameMode().name()+")"+name;
		
		out.log(name+" "+plugin.translate("player.food.change")+" "+e.getFoodLevel());
	}
	

	public void disable(){
		out.close();
	}

}
