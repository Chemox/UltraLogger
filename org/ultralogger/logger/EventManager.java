package org.ultralogger.logger;

import java.util.HashMap;

import org.bukkit.event.block.*;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.event.vehicle.*;
import org.bukkit.event.weather.*;
import org.bukkit.event.world.*;

@SuppressWarnings("rawtypes")
public class EventManager {
	
	
	public static boolean isEnabled(Class clas, String events){
		if(events==null || clas==null)
			return false;
		HashMap<Integer, Class> eventList = getEvents();
		if(events.startsWith("+"))
			events=events.substring(1);
		if(!events.contains("+")){
			int id = Integer.parseInt(events);
			Class c = eventList.get(id);
			if(c == clas)
				return true;
			else
				return false;
		}
		String[] ids = events.split("\\+");
		for( int index =0; index<ids.length; index++){
			int id = Integer.parseInt(ids[index]);
			Class c = eventList.get(id);
			if(c == clas)
				return true;
		}
		return false;
	}
	
	public static int getID(Class clas){
		HashMap<Integer, Class> eventList = getEvents();
		for(int x=0;x<73;x++){
			Class test = eventList.get(x);
			if(clas==test)
				return x;
		}
		return -1;
	}
	
	public static HashMap<Integer, Class> getEvents(){
		HashMap<Integer, Class> events = new HashMap<Integer, Class>();
		//BLOCK EVENTS (11)
		events.put(1, BlockBreakEvent.class);
		events.put(2, BlockBurnEvent.class);
		events.put(3, BlockDispenseEvent.class);
		events.put(4, BlockFadeEvent.class);
		events.put(5, BlockFormEvent.class);
		events.put(6, BlockGrowEvent.class);
		events.put(7, BlockIgniteEvent.class);
		events.put(8, BlockPlaceEvent.class);
		events.put(9, BlockSpreadEvent.class);
		events.put(10, EntityBlockFormEvent.class);
		events.put(11, SignChangeEvent.class);
		//CRAFT EVENTS (4)
		events.put(12, BrewEvent.class);
		events.put(13, CraftItemEvent.class);
		events.put(14, FurnaceSmeltEvent.class);
		events.put(15, EnchantItemEvent.class);
		//ENTITY EVENTS (17)
		events.put(16, CreatureSpawnEvent.class);
		events.put(17, CreeperPowerEvent.class);
		events.put(18, EntityBreakDoorEvent.class);
		events.put(19, EntityChangeBlockEvent.class);
		events.put(20, EntityCombustEvent.class);
		events.put(21, EntityCreatePortalEvent.class);
		events.put(22, EntityDamageEvent.class);
		events.put(23, EntityDeathEvent.class);
		events.put(24, EntityExplodeEvent.class);
		events.put(25, EntityInteractEvent.class);
		events.put(26, EntityPortalEnterEvent.class);
		events.put(27, EntityTameEvent.class);
		events.put(28, EntityTeleportEvent.class);
		events.put(29, ExpBottleEvent.class);
		events.put(30, ExplosionPrimeEvent.class);
		events.put(31, FoodLevelChangeEvent.class);
		events.put(32, ItemSpawnEvent.class);
		//INVENTORY EVENTS (3)
		events.put(33, InventoryOpenEvent.class);
		events.put(34, InventoryCloseEvent.class);
		events.put(35, InventoryClickEvent.class);
		//PLAYER EVENTS (24)
		events.put(36, PlayerLoginEvent.class);
		events.put(37, PlayerBedEnterEvent.class);
		events.put(38, PlayerBedLeaveEvent.class);
		events.put(39, PlayerBucketEmptyEvent.class);
		events.put(40, PlayerBucketFillEvent.class);
		events.put(41, PlayerChangedWorldEvent.class);
		events.put(42, PlayerDropItemEvent.class);
		events.put(43, PlayerEggThrowEvent.class);
		events.put(44, PlayerExpChangeEvent.class);
		events.put(45, PlayerGameModeChangeEvent.class);
		events.put(46, PlayerItemBreakEvent.class);
		events.put(47, PlayerJoinEvent.class);
		events.put(48, PlayerKickEvent.class);
		events.put(49, PlayerLevelChangeEvent.class);
		events.put(50, PlayerPickupItemEvent.class);
		events.put(51, PlayerPortalEvent.class);
		events.put(52, PlayerQuitEvent.class);
		events.put(53, PlayerRespawnEvent.class);
		events.put(54, PlayerTeleportEvent.class);
		events.put(55, PlayerToggleFlightEvent.class);
		events.put(56, PlayerToggleSneakEvent.class);
		events.put(57, PlayerToggleSprintEvent.class);
		events.put(58, PlayerDeathEvent.class);
		events.put(71, PlayerInteractEvent.class);
		events.put(72, PlayerFishEvent.class);
		//VEHICLE EVENTS (4)
		events.put(59, VehicleCreateEvent.class);
		events.put(60, VehicleDestroyEvent.class);
		events.put(61, VehicleEnterEvent.class);
		events.put(62, VehicleExitEvent.class);
		//WEATHER EVENTS (3)
		events.put(63, LightningStrikeEvent.class);
		events.put(64, ThunderChangeEvent.class);
		events.put(65, WeatherChangeEvent.class);
		//WORLD EVENTS (3)
		events.put(66, PortalCreateEvent.class);
		events.put(67, SpawnChangeEvent.class);
		events.put(68, StructureGrowEvent.class);
		//CHAT EVENTS (2) ( COMMANDS & CHAT MESSAGES )
		events.put(69, AsyncPlayerChatEvent.class);
		events.put(70, PlayerCommandPreprocessEvent.class);
		return events;
	}

	public static HashMap<Class, Integer> getIds(){
		HashMap<Class, Integer> events = new HashMap<Class, Integer>();
		//BLOCK EVENTS (11)
		events.put(BlockBreakEvent.class, 1);
		events.put(BlockBurnEvent.class, 2);
		events.put(BlockDispenseEvent.class, 3);
		events.put(BlockFadeEvent.class, 4);
		events.put(BlockFormEvent.class, 5);
		events.put(BlockGrowEvent.class, 6);
		events.put(BlockIgniteEvent.class, 7);
		events.put(BlockPlaceEvent.class, 8);
		events.put(BlockSpreadEvent.class, 9);
		events.put(EntityBlockFormEvent.class, 10);
		events.put(SignChangeEvent.class, 11);
		//CRAFT EVENTS (4)
		events.put(BrewEvent.class, 12);
		events.put(CraftItemEvent.class, 13);
		events.put(FurnaceSmeltEvent.class, 14);
		events.put(EnchantItemEvent.class, 15);
		//ENTITY EVENTS (17)
		events.put(CreatureSpawnEvent.class, 16);
		events.put(CreeperPowerEvent.class, 17);
		events.put(EntityBreakDoorEvent.class, 18);
		events.put(EntityChangeBlockEvent.class, 19);
		events.put(EntityCombustEvent.class, 20);
		events.put(EntityCreatePortalEvent.class, 21);
		events.put(EntityDamageEvent.class, 22);
		events.put(EntityDeathEvent.class, 23);
		events.put(EntityExplodeEvent.class, 24);
		events.put(EntityInteractEvent.class, 25);
		events.put(EntityPortalEnterEvent.class, 26);
		events.put(EntityTameEvent.class, 27);
		events.put(EntityTeleportEvent.class, 28);
		events.put(ExpBottleEvent.class, 29);
		events.put(ExplosionPrimeEvent.class, 30);
		events.put(FoodLevelChangeEvent.class, 31);
		events.put(ItemSpawnEvent.class, 32);
		//INVENTORY EVENTS (3)+10-3
		events.put(InventoryOpenEvent.class, 33);
		events.put(InventoryCloseEvent.class, 34);
		events.put(InventoryClickEvent.class, 35);
		//PLAYER EVENTS (22)
		events.put(PlayerLoginEvent.class, 36);
		events.put(PlayerBedEnterEvent.class, 37);
		events.put(PlayerBedLeaveEvent.class, 38);
		events.put(PlayerBucketEmptyEvent.class, 39);
		events.put(PlayerBucketFillEvent.class, 40);
		events.put(PlayerChangedWorldEvent.class, 41);
		events.put(PlayerDropItemEvent.class, 42);
		events.put(PlayerEggThrowEvent.class, 43);
		events.put(PlayerExpChangeEvent.class, 44);
		events.put(PlayerGameModeChangeEvent.class, 45);
		events.put(PlayerItemBreakEvent.class, 46);
		events.put(PlayerJoinEvent.class, 47);
		events.put(PlayerKickEvent.class, 48);
		events.put(PlayerLevelChangeEvent.class, 49);
		events.put(PlayerPickupItemEvent.class, 50);
		events.put(PlayerPortalEvent.class, 51);
		events.put(PlayerQuitEvent.class, 52);
		events.put(PlayerRespawnEvent.class, 53);
		events.put(PlayerTeleportEvent.class, 54);
		events.put(PlayerToggleFlightEvent.class, 55);
		events.put(PlayerToggleSneakEvent.class, 56);
		events.put(PlayerToggleSprintEvent.class, 57);
		events.put(PlayerDeathEvent.class, 58);
		events.put(PlayerInteractEvent.class, 71);
		events.put(PlayerFishEvent.class, 72);
		//VEHICLE EVENTS (4)
		events.put(VehicleCreateEvent.class, 59);
		events.put(VehicleDestroyEvent.class, 60);
		events.put(VehicleEnterEvent.class, 61);
		events.put(VehicleExitEvent.class, 62);
		//WEATHER EVENTS (3)
		events.put(LightningStrikeEvent.class, 63);
		events.put(ThunderChangeEvent.class, 64);
		events.put(WeatherChangeEvent.class, 65);
		//WORLD EVENTS (3)
		events.put(PortalCreateEvent.class, 66);
		events.put(SpawnChangeEvent.class, 67);
		events.put(StructureGrowEvent.class, 68);
		//CHAT EVENTS (2) ( COMMANDS & CHAT MESSAGES )
		events.put(AsyncPlayerChatEvent.class, 69);
		events.put(PlayerCommandPreprocessEvent.class, 70);
		return events;
	}
}
