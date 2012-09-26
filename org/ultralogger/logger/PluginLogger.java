package org.ultralogger.logger;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.enchantment.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.painting.*;
import org.bukkit.event.player.*;
import org.bukkit.event.server.*;
import org.bukkit.event.vehicle.*;
import org.bukkit.event.weather.*;
import org.bukkit.event.world.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredListener;
import org.ultralogger.LoggerFile;
import org.ultralogger.MainLogger;

public class PluginLogger implements Listener{
	
	private MainLogger plugin =null;
	private File log = new File("./Log/plugin.log");
	private LoggerFile out =null;
	private boolean printed =false;

	public PluginLogger(MainLogger main,boolean is,int max,boolean s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		out =new LoggerFile(log,is,max,s);
	}
	public PluginLogger(MainLogger main,boolean is,int max,Date s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		out =new LoggerFile(log,is,max,s);
	}
	
	public void printListener() {
		boolean timer =false;
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		
		//BLOCK EVENT
				RegisteredListener[] listeners =BlockBreakEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"block breack",i.getPriority());
				}
				listeners =BlockBurnEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"block burn",i.getPriority());
				}
				listeners =BlockCanBuildEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"block can build",i.getPriority());
				}
				listeners =BlockDamageEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"block damage",i.getPriority());
				}
				listeners =BlockDispenseEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"block dispense",i.getPriority());
				}
				listeners =BlockFadeEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"block fade",i.getPriority());
				}
				listeners =BlockFormEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"block form",i.getPriority());
				}
				listeners =BlockFromToEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"block from to",i.getPriority());
				}
				listeners =BlockGrowEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"block grow",i.getPriority());
				}
				listeners =BlockIgniteEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"block ignite",i.getPriority());
				}
				listeners =BlockPhysicsEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"block physics",i.getPriority());
				}
				listeners =BlockPistonExtendEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"piston extend",i.getPriority());
				}
				listeners =BlockPistonRetractEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"piston retract",i.getPriority());
				}
				listeners =BlockPlaceEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"block place",i.getPriority());
				}
				listeners =BlockRedstoneEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"block redstone",i.getPriority());
				}
				listeners =BlockSpreadEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"block spread",i.getPriority());
				}
				listeners =EntityBlockFormEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"entity block form",i.getPriority());
				}
				listeners =LeavesDecayEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"leaves decay",i.getPriority());
				}
				listeners =NotePlayEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"note play",i.getPriority());
				}
				listeners =SignChangeEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"sign change",i.getPriority());
				}
				//ENCHANTMENT EVENTS
				listeners =EnchantItemEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"enchant item",i.getPriority());
				}
				listeners =PrepareItemEnchantEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"prepare item enchant",i.getPriority());
				}
				//ENTITY EVENTS
				listeners =CreatureSpawnEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"creature spawn",i.getPriority());
				}
				listeners =CreeperPowerEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"creeper power",i.getPriority());
				}
				listeners =EntityBreakDoorEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"entity break door",i.getPriority());
				}
				listeners =EntityChangeBlockEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"entity change block",i.getPriority());
				}
				listeners =EntityCombustEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"entity combust",i.getPriority());
				}
				listeners =EntityCreatePortalEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"entity create portal",i.getPriority());
				}
				listeners =EntityDamageEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"entity damage",i.getPriority());
				}
				listeners =EntityDeathEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"entity death",i.getPriority());
				}
				listeners =EntityExplodeEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"entity explode",i.getPriority());
				}
				listeners =EntityInteractEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"entity interact",i.getPriority());
				}
				listeners =EntityPortalEnterEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"entity portal enter",i.getPriority());
				}
				listeners =EntityRegainHealthEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"entity regain health",i.getPriority());
				}
				listeners =EntityShootBowEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"entity shoot bow",i.getPriority());
				}
				listeners =EntityTameEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"entity tame",i.getPriority());
				}
				listeners =EntityTargetEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"entity target",i.getPriority());
				}
				listeners =EntityTargetLivingEntityEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"entity target living entity",i.getPriority());
				}
				listeners =EntityTeleportEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"entity teleport",i.getPriority());
				}
				listeners =ExpBottleEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"exp bottle",i.getPriority());
				}
				listeners =ExplosionPrimeEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"explosion prime",i.getPriority());
				}
				listeners =FoodLevelChangeEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"food level change",i.getPriority());
				}
				listeners =ItemDespawnEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"item despawn",i.getPriority());
				}
				listeners =ItemSpawnEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"item spawn",i.getPriority());
				}
				listeners =PigZapEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"pig zap",i.getPriority());
				}
				listeners =PlayerDeathEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player death",i.getPriority());
				}
				listeners =PotionSplashEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"potion slash",i.getPriority());
				}
				listeners =ProjectileHitEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"projectile hit",i.getPriority());
				}
				listeners =ProjectileLaunchEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"projectile launch",i.getPriority());
				}
				listeners =SheepDyeWoolEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"sheep dye wool",i.getPriority());
				}
				listeners =SheepRegrowWoolEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"sheep regrow wool",i.getPriority());
				}
				listeners =SlimeSplitEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"slime split",i.getPriority());
				}
				//INVENTORY EVENTS
				listeners =BrewEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"brew",i.getPriority());
				}
				listeners =CraftItemEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"craft item",i.getPriority());
				}
				listeners =FurnaceBurnEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"furnace burn",i.getPriority());
				}
				listeners =FurnaceSmeltEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"furnace smelt",i.getPriority());
				}
				listeners =InventoryClickEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"inventory click",i.getPriority());
				}
				listeners =InventoryCloseEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"inventory close",i.getPriority());
				}
				listeners =InventoryOpenEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"inventory open",i.getPriority());
				}
				listeners =PrepareItemCraftEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"prepare item craft",i.getPriority());
				}
				//PAINTING EVENTS
				listeners =PaintingBreakByEntityEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"painting break by entity",i.getPriority());
				}
				listeners =PaintingBreakEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"painting break",i.getPriority());
				}
				listeners =PaintingPlaceEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"painting place",i.getPriority());
				}
				//PLAYER EVENTS
				listeners =AsyncPlayerChatEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player chat",i.getPriority());
				}
				listeners =AsyncPlayerPreLoginEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player pre login",i.getPriority());
				}
				listeners =PlayerAnimationEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player animation",i.getPriority());
				}
				listeners =PlayerBedEnterEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player bed enter",i.getPriority());
				}
				listeners =PlayerBedLeaveEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player bed leave",i.getPriority());
				}
				listeners =PlayerBucketEmptyEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player bucket empty",i.getPriority());
				}
				listeners =PlayerBucketFillEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player bucket fill",i.getPriority());
				}
				listeners =PlayerChangedWorldEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player change world",i.getPriority());
				}
				listeners =PlayerCommandPreprocessEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player command preprocess",i.getPriority());
				}
				listeners =PlayerDropItemEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player drop item",i.getPriority());
				}
				listeners =PlayerEggThrowEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player egg throw",i.getPriority());
				}
				listeners =PlayerExpChangeEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player exp change",i.getPriority());
				}
				listeners =PlayerFishEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player fish",i.getPriority());
				}
				listeners =PlayerGameModeChangeEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player gamemode change",i.getPriority());
				}
				listeners =PlayerInteractEntityEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					log(i.getPlugin(),"player interact entity",i.getPriority());
				}
				listeners =PlayerInteractEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player interact",i.getPriority());
				}
				listeners =PlayerItemBreakEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player item break",i.getPriority());
				}
				listeners =PlayerItemHeldEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player item held",i.getPriority());
				}
				listeners =PlayerJoinEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player join",i.getPriority());
				}
				listeners =PlayerKickEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player kick",i.getPriority());
				}
				listeners =PlayerLevelChangeEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player level change",i.getPriority());
				}
				listeners =PlayerLoginEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player login",i.getPriority());
				}
				listeners =PlayerMoveEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player move",i.getPriority());
				}
				listeners =PlayerPickupItemEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					log(i.getPlugin(),"player pickup item",i.getPriority());
				}
				listeners =PlayerPortalEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player portal",i.getPriority());
				}
				listeners =PlayerPreLoginEvent.getHandlerList().getRegisteredListeners();
				listeners =PlayerQuitEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player quit",i.getPriority());
				}
				listeners =PlayerRegisterChannelEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player register channel",i.getPriority());
				}
				listeners =PlayerRespawnEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player respawn",i.getPriority());
				}
				listeners =PlayerShearEntityEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player shear",i.getPriority());
				}
				listeners =PlayerTeleportEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player teleport",i.getPriority());
				}
				listeners =PlayerToggleFlightEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player toggle flight",i.getPriority());
				}
				listeners =PlayerToggleSneakEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player toggle sneak",i.getPriority());
				}
				listeners =PlayerToggleSprintEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player toggle sprint",i.getPriority());
				}
				listeners =PlayerUnregisterChannelEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player unregister channel",i.getPriority());
				}
				listeners =PlayerVelocityEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"player velocity",i.getPriority());
				}
				//SERVER EVENTS
				listeners =MapInitializeEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"map initialize",i.getPriority());
				}
				listeners =PluginDisableEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"plguin disable",i.getPriority());
				}
				listeners =PluginEnableEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"plugin enable",i.getPriority());
				}
				listeners =RemoteServerCommandEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"remote server command",i.getPriority());
				}
				listeners =ServerCommandEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"server command",i.getPriority());
				}
				listeners =ServerListPingEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"server list ping",i.getPriority());
				}
				listeners =ServiceRegisterEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"service register",i.getPriority());
				}
				listeners =ServiceUnregisterEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"service unregister",i.getPriority());
				}
				//VEHICLE EVENTS
				listeners =VehicleBlockCollisionEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"vehicle block collision",i.getPriority());
				}
				listeners =VehicleCreateEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"vehicle create",i.getPriority());
				}
				listeners =VehicleDamageEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"vehicle damage",i.getPriority());
				}
				listeners =VehicleDestroyEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"vehicle destroy",i.getPriority());
				}
				listeners =VehicleEnterEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"vehcile enter",i.getPriority());
				}
				listeners =VehicleEntityCollisionEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"vehicle entity collision",i.getPriority());
				}
				listeners =VehicleExitEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"vehicle collision",i.getPriority());
				}
				listeners =VehicleMoveEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"vehicle move",i.getPriority());
				}
				listeners =VehicleUpdateEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"vehcile update",i.getPriority());
				}
				//WEATHER EVENTS
				listeners =LightningStrikeEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"lightning strike",i.getPriority());
				}
				listeners =ThunderChangeEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"thunder change",i.getPriority());
				}
				listeners =WeatherChangeEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"weather change",i.getPriority());
				}
				//WORLD EVENTS
				listeners =ChunkLoadEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"chunk load",i.getPriority());
				}
				listeners =ChunkPopulateEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"chunk populate",i.getPriority());
				}
				listeners =ChunkUnloadEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"chunk unload",i.getPriority());
				}
				listeners =PortalCreateEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"portal create",i.getPriority());
				}
				listeners =SpawnChangeEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"spawn change",i.getPriority());
				}
				listeners =StructureGrowEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"structure grow",i.getPriority());
				}
				listeners =WorldInitEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"world init",i.getPriority());
				}
				listeners =WorldLoadEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"world load",i.getPriority());
				}
				listeners =WorldSaveEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"world save",i.getPriority());
				}
				listeners =WorldUnloadEvent.getHandlerList().getRegisteredListeners();
				for(int x=0;x<listeners.length;x++){
					RegisteredListener i = listeners[x];
					if(i.getPlugin()==plugin){
						continue;
					}
					if(!timer){
						out.log(time);
						timer =true;
					}
					log(i.getPlugin(),"world unload",i.getPriority());
				}
		if(timer){
			out.log(time);
		}
	}
	@EventHandler
	public void onEnable(PluginEnableEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Plugin pl =e.getPlugin();
		if(pl==plugin){
			return;
		}
		out.log(time+pl.getName()+" "+plugin.translate("plugin.enable"));
	}
	@EventHandler
	public void onDisable(PluginDisableEvent e){
		if(!printed){
			printed=true;
			printListener();
		}
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Plugin pl =e.getPlugin();
		if(pl==plugin){
			return;
		}
		out.log(time+pl.getName()+" "+plugin.translate("plugin.disable"));
	}
	public void log(Plugin i,String event,EventPriority p){
		out.log(i.getName()+" "+plugin.translate("plugin.register.event")+" "+event+" "+plugin.translate("plugin.priority")+" "+p.name());
	}
	public void disable(){
		out.close();
	}

}
