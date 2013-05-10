package org.ultralogger.logger;

import java.util.Iterator;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreeperPowerEvent;
import org.bukkit.event.entity.EntityBreakDoorEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityCreatePortalEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.event.world.SpawnChangeEvent;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.ultralogger.LoggerConfig;
import org.ultralogger.Main;

public class FileLogger implements Listener{
	
	private LogFile file;
	private String events;
	private Main plugin;
	
	public FileLogger(Main plugin,LoggerConfig config, String name, String events){
		file = new LogFile(name, config);
		this.events=events;
		this.plugin=plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	public void disable() {
		file.close();
	}

	public void save() {
		file.close();
		file.open();
	}
	/*
	 * BLOCK EVENTS (11)
	 */
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void block_break(BlockBreakEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		Block i = event.getBlock();
		file.log(printPlayer(event.getPlayer())+" destroyed "+new ItemStack(i.getTypeId()).toString()+" in "+printLoc(i.getLocation()));
	}
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void block_burn(BlockBurnEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Block i = event.getBlock();
		file.log(new ItemStack(i.getTypeId()).toString()+" has burnt in "+printLoc(i.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void block_dispense(BlockDispenseEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Block i = event.getBlock();
		file.log(new ItemStack(i.getTypeId()).toString()+" was dispensed in "+printLoc(i.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void block_fade(BlockFadeEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Block i = event.getBlock();
		file.log(new ItemStack(i.getTypeId()).toString()+" has fade in "+printLoc(i.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void block_form(BlockFormEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Block i = event.getBlock();
		file.log(new ItemStack(i.getTypeId()).toString()+" was formed in "+printLoc(i.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void block_grow(BlockGrowEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Block i = event.getBlock();
		file.log(new ItemStack(i.getTypeId()).toString()+" data "+i.getData()+" grown to data "+event.getNewState().getRawData()+" in "+printLoc(i.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void block_ignite(BlockIgniteEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Block i = event.getBlock();
		if(event.getPlayer()!=null)
			file.log("Fire was ignited in "+printLoc(i.getLocation())+" due to "+event.getCause().name()+" by "+printPlayer(event.getPlayer()));
		else
			file.log("Fire was ignited in "+printLoc(i.getLocation())+" due to "+event.getCause().name());
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void block_place(BlockPlaceEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		Block i = event.getBlock();
		file.log(printPlayer(event.getPlayer())+" placed "+new ItemStack(i.getTypeId()).toString()+" in "+printLoc(i.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void block_spread(BlockSpreadEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Block i = event.getBlock();
		file.log(new ItemStack(i.getTypeId()).toString()+" data "+i.getData()+" spread to data "+event.getNewState().getRawData()+" in "+printLoc(i.getLocation()));
	}
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void block_entity_form(EntityBlockFormEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Block i = event.getBlock();
		file.log(new ItemStack(i.getTypeId()).toString()+" was formed in "+printLoc(i.getLocation())+" by "+event.getEntity().toString());
	}
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void sign_change(SignChangeEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		Block i = event.getBlock();
		Player p = event.getPlayer();
		if(p==null)
			file.log(new ItemStack(i.getTypeId()).toString()+" was changed in "+printLoc(i.getLocation()));
		else
			file.log(printPlayer(p)+" has edited a sign in "+printLoc(i.getLocation()));
	}
	
	/*
	 * CHAT & COMMAND EVENTS (2)
	 */
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_chat(AsyncPlayerChatEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		file.log(printPlayer(event.getPlayer())+" --> "+event.getMessage());
	}
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_command(PlayerCommandPreprocessEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		String display =" tried command --> ";
		String msg = event.getMessage()+" ";
		String command = msg;
		if(!plugin.getCommandManager().canLogCommand(event.getMessage()))
			return;
		if(msg.contains("/") && msg.contains(" "))
			command = msg.substring(msg.indexOf("/")+1, msg.indexOf(" "));
		if(event.getPlayer().getServer().getPluginCommand(command)!=null || event.getPlayer().getServer().getCommandAliases().containsKey(command) ||
				event.getPlayer().getServer().getCommandAliases().containsValue(command)){
			display =" issued command --> ";
		}
		file.log(printPlayer(event.getPlayer())+display+msg);
	}
	
	/*
	 * CRAFT EVENTS (4)
	 */
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void brew(BrewEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		BrewerInventory i =event.getContents();
		file.log("That was brew "+i.getItem(0)+","+i.getItem(1)+","+i.getItem(2)+" in "+printLoc(event.getBlock().getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void craft_item(CraftItemEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Player p =(Player) event.getView().getPlayer();
		if(p!=null){
			if(plugin.isEventForbidden(p, event.getClass()))
				return;
			file.log(printPlayer(p)+" crafted "+event.getRecipe().getResult().toString()+" in "+printLoc(p.getLocation()));
		} else
			file.log("Due to something that was crafted : "+event.getRecipe().getResult().toString());
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void furnace_smelt(FurnaceSmeltEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		file.log("That was smelt in a furnace : "+event.getResult().toString()+" in "+printLoc(event.getBlock().getLocation()));
	}
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void enchant_item(EnchantItemEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getEnchanter(), event.getClass()))
			return;
		String enchants ="";
		for(Iterator<Enchantment> i =event.getEnchantsToAdd().keySet().iterator();i.hasNext();){
			enchants+=i.next().getName()+",";
		}
		enchants=enchants.substring(0, enchants.lastIndexOf(","));
		int cost = event.getExpLevelCost();
		file.log(printPlayer(event.getEnchanter())+" has enchanted "+event.getItem().toString()+" for "+cost+" xp(s) with "+enchants);
	}
	
	/*
	 * ENTITY EVENTS (17)
	 */
	@EventHandler (priority = EventPriority.MONITOR)
	public void entity_spawn(CreatureSpawnEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Entity i =event.getEntity();
		if(i==null)
			return;
		file.log("["+i.getWorld().getName()+"] "+i.toString()+" has spawned ("+event.getSpawnReason().name()+") "+" in "+printLoc(i.getLocation()));

	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void creeper_power(CreeperPowerEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Entity i =event.getEntity();
		if(i==null)
			return;
		file.log("["+i.getWorld().getName()+"] "+i.toString()+" was striked by lightning in "+printLoc(i.getLocation()));

	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void entity_break_door(EntityBreakDoorEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Entity i =event.getEntity();
		if(i==null)
			return;
		file.log("["+i.getWorld().getName()+"] "+i.toString()+" broke a door in "+printLoc(i.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void entity_change_block(EntityChangeBlockEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Entity i = event.getEntity();
		Block b = event.getBlock();
		if(i==null)
			return;
		file.log("["+i.getWorld().getName()+"] "+i.toString()+" changed "+b.getType()+" into "+event.getTo()+" in "+printLoc(i.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void entity_combust(EntityCombustEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Entity i =event.getEntity();
		if(i==null)
			return;
		file.log("["+i.getWorld().getName()+"] "+i.toString()+" is combusting for "+event.getDuration()+" tick(s) in "+printLoc(i.getLocation()));

	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void entity_create_portal(EntityCreatePortalEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Entity i =event.getEntity();
		if(i==null)
			return;
		file.log("["+i.getWorld().getName()+"] "+i.toString()+" created a portal in "+printLoc(i.getLocation()));

	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void entity_damage(EntityDamageEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Entity i =event.getEntity();
		if(i==null)
			return;
		file.log("["+i.getWorld().getName()+"] "+i.toString()+" was damaged of "+event.getDamage()+" half heart(s) in "+printLoc(i.getLocation())+" due to "+
			event.getCause().name());

	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void entity_death(EntityDeathEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Entity i =event.getEntity();
		if(i==null)
			return;
		file.log("["+i.getWorld().getName()+"] "+i.toString()+" died in "+printLoc(i.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void entity_explode(EntityExplodeEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Entity i = event.getEntity();
		if(i!=null)
			file.log("["+i.getWorld().getName()+"] "+i.toString()+" has exploded in "+printLoc(i.getLocation()));
		else
			file.log("["+event.getLocation().getWorld().getName()+"] Something has exploded in "+printLoc(event.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void entity_interact(EntityInteractEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Block i =event.getBlock();
		if(i==null)
			return;
		file.log("["+i.getWorld().getName()+"] "+event.getEntity().toString()+" interacted with "+new ItemStack(i.getTypeId())+" in "+printLoc(i.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void entity_enter_portal(EntityPortalEnterEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Entity i = event.getEntity();
		if(i==null)
			return;
		file.log("["+i.getWorld().getName()+"] "+event.getEntity().toString()+" entered a portal in "+printLoc(i.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void entity_tame(EntityTameEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Entity i =event.getEntity();
		if(i==null)
			return;
		if(plugin.isEventForbidden(plugin.getServer().getPlayer(event.getOwner().getName()), event.getClass()))
			return;
		file.log("["+i.getWorld().getName()+"] "+i.toString()+" was tamed by "+event.getOwner().getName()+" in "+printLoc(i.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void entity_teleport(EntityTeleportEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Entity i = event.getEntity();
		if(i==null)
			return;
		file.log("["+i.getWorld().getName()+"] "+event.getEntity().toString()+" was teleported from "+printLoc(event.getFrom())+" to "+printLoc(event.getTo()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void exp_bottle(ExpBottleEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Entity i =event.getEntity();
		if(i==null)
			return;
		file.log("["+i.getWorld().getName()+"] An exp bottle was thrown "+" in "+printLoc(i.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void explosion_prime(ExplosionPrimeEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Entity i = event.getEntity();
		if(i==null)
			return;
		file.log("["+i.getWorld().getName()+"] "+event.getEntity().toString()+" has exploded in "+printLoc(i.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void food_level_change(FoodLevelChangeEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Entity i = event.getEntity();
		if(i==null || !(i instanceof Player))
			return;
		if(plugin.isEventForbidden((Player) i, event.getClass()))
			return;
		file.log(printPlayer((Player)i)+"'s food level changed to "+event.getFoodLevel()+" in "+printLoc(i.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void item_spawn(ItemSpawnEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Item i = event.getEntity();
		if(i==null)
			return;
		file.log("["+i.getWorld().getName()+"] "+i.getItemStack().toString()+" has spawned in "+printLoc(i.getLocation()));
	}
	
	/*
	 * INVENTORY EVENTS (3)
	 */
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void inventory_open(InventoryOpenEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		HumanEntity i =event.getPlayer();
		if(plugin.isEventForbidden((Player) event.getPlayer(), event.getClass()))
			return;
		file.log(printPlayer((Player) event.getPlayer())+" opened "+event.getView().getTitle()+" ("+event.getView().getType().name()+") in "+printLoc(i.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void inventory_close(InventoryCloseEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		HumanEntity i =event.getPlayer();
		if(plugin.isEventForbidden((Player) event.getPlayer(), event.getClass()))
			return;
		file.log(printPlayer((Player) event.getPlayer())+" closed "+event.getView().getTitle()+" ("+event.getView().getType().name()+") in "+printLoc(i.getLocation()));
	}
	
	private ItemStack lastItem = null;
	private Inventory lastInv = null;
	private int amount = -1;
	@EventHandler (priority = EventPriority.MONITOR)
	public void inventory_click(InventoryClickEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(event.getCurrentItem()==null)
			return;
		HumanEntity i =event.getWhoClicked();
		if(plugin.isEventForbidden((Player) event.getWhoClicked(), event.getClass()))
			return;
		ItemStack item =event.getCursor();
		/*
		 * True if it's to his inventory and false if it's from 
		 * ( in fact we get the slot id of where he put the item and if he put in the top inv we guess that's from its inv and vice-versa )
		 */
		boolean toInv = event.getView().getBottomInventory().getSize()<=event.getRawSlot();
		//If he stores something in
		if(lastItem!=null && lastInv!=null && event.getView().getTopInventory().getType()==lastInv.getType() && item.getTypeId()==0){
			//If he doesn't put the entire stack
			lastItem.setAmount(amount);
			if(toInv){
				file.log(printPlayer((Player) event.getWhoClicked())+" put "+lastItem.toString()+" from "+lastInv.getType().name()+
						" to his inventory in "+printLoc(i.getLocation()));
			}
			else{
				file.log(printPlayer((Player) event.getWhoClicked())+" put "+lastItem.toString()+" from his inventory to "+event.getView().getType().name()+
						" in "+printLoc(i.getLocation()));
			}
			//We store our informations
			lastItem=item;
			amount=item.getAmount();
			lastInv=event.getView().getTopInventory();
		}
		else{
			//We store our informations
			lastItem=item;
			amount=item.getAmount();
			lastInv=event.getView().getTopInventory();
			
			file.log(printPlayer((Player) event.getWhoClicked())+" clicked on "+item.toString()+" from "+event.getView().getTitle()+
					" in "+printLoc(i.getLocation()));
		}
		
	}
	
	/*
	 * PLAYER EVENTS (24)(Interact and InteractEntity == 1 event)
	 */
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_login(PlayerLoginEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		Player player = event.getPlayer();
		String name = player.getName();
		if(Main.isAdmin(player))
			name="[Admin] "+name;
		file.log(name+"("+event.getAddress().toString()+") tried to login, that's resulted to "+event.getResult().name());
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_bed_enter(PlayerBedEnterEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		Block bed = event.getBed();
		file.log(printPlayer(event.getPlayer())+" enters a  bed in "+printLoc(bed.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_bed_leave(PlayerBedLeaveEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		Block bed = event.getBed();
		file.log(printPlayer(event.getPlayer())+" leaves a  bed in "+printLoc(bed.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_bucket_empty(PlayerBucketEmptyEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		Block where = event.getBlockClicked();
		file.log(printPlayer(event.getPlayer())+" empties a bucket in "+printLoc(where.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_bucket_fill(PlayerBucketFillEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		Block where = event.getBlockClicked();
		file.log(printPlayer(event.getPlayer())+" filled a bucket in "+printLoc(where.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_change_world(PlayerChangedWorldEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		file.log(printPlayer(event.getPlayer())+" changed world from "+event.getFrom().getName()+" to "+event.getPlayer().getWorld().getName());
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_drop_item(PlayerDropItemEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		Item i = event.getItemDrop();
		file.log(printPlayer(event.getPlayer())+" has dropped "+i.getItemStack().toString()+" in "+printLoc(i.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_egg_throw(PlayerEggThrowEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		Egg i =event.getEgg();
		file.log(printPlayer(event.getPlayer())+" thrown an egg in "+printLoc(i.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_exp_change(PlayerExpChangeEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		int amount =event.getAmount();
		file.log(printPlayer(event.getPlayer())+" earned "+amount+" xp(s) !");
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_game_mode_change(PlayerGameModeChangeEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		file.log(printPlayer(event.getPlayer())+" changed to gamemode "+event.getNewGameMode().name());
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_item_break(PlayerItemBreakEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		ItemStack i =event.getBrokenItem();
		file.log(printPlayer(event.getPlayer())+" broke his "+i.toString());
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_join(PlayerJoinEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		file.log(printPlayer(event.getPlayer())+" has joined");
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_kick(PlayerKickEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		file.log(printPlayer(event.getPlayer())+" was kicked due to "+event.getReason()+" with message : "+event.getLeaveMessage());
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_level_change(PlayerLevelChangeEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		file.log(printPlayer(event.getPlayer())+"'s lvl changed to lvl"+event.getNewLevel()+" from lvl"+event.getOldLevel());
	}
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_pickup_item(PlayerPickupItemEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		Item i =event.getItem();
		file.log(printPlayer(event.getPlayer())+" picked up item "+i.getItemStack().toString()+" in "+printLoc(i.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_portal(PlayerPortalEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		Location i = event.getFrom();
		Location f = event.getTo();
		if(f==null)
			return;
		String s =printPlayer(event.getPlayer())+" was teleported from "+printLoc(i)+" to "+printLoc(f);
		if(event.getCause() != null)
			s+="due to "+event.getCause().name();
		file.log(s);
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_quit(PlayerQuitEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		file.log(printPlayer(event.getPlayer())+" is now disconnected ");
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_respawn(PlayerRespawnEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		Location i = event.getRespawnLocation();
		file.log(printPlayer(event.getPlayer())+" respawned in "+printLoc(i));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_teleport(PlayerTeleportEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		Location i = event.getFrom();
		Location f = event.getTo();
		file.log(printPlayer(event.getPlayer())+" was teleported from "+printLoc(i)+" to "+printLoc(f)+" due to "+event.getCause().name());
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_toggle_fly(PlayerToggleFlightEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		if(event.isFlying())
			file.log(printPlayer(event.getPlayer())+" started flying");
		else
			file.log(printPlayer(event.getPlayer())+" stopped flying");
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_toggle_sprint(PlayerToggleSprintEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		if(event.isSprinting())
			file.log(printPlayer(event.getPlayer())+" started sprinting");
		else
			file.log(printPlayer(event.getPlayer())+" stopped sprinting");
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_toggle_sneak(PlayerToggleSneakEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		if(event.isSneaking())
			file.log(printPlayer(event.getPlayer())+" started sneaking");
		else
			file.log(printPlayer(event.getPlayer())+" stopped sneaking");
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_death(PlayerDeathEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getEntity(), event.getClass()))
			return;
		file.log(printPlayer(event.getEntity())+" died  : "+event.getDeathMessage()+". -- in"+printLoc(event.getEntity().getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_fish(PlayerFishEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		if(event.getState()==State.CAUGHT_ENTITY){
			Entity i =event.getCaught();
			if(i!=null)
				file.log(printPlayer(event.getPlayer())+" fished "+i.toString()+printLoc(i.getLocation()));
		}
		else{
			Player i =event.getPlayer();
			file.log(printPlayer(event.getPlayer())+" is fishing("+event.getState().name()+") in "+printLoc(i.getLocation()));
		}
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_interact_entity(PlayerInteractEntityEvent event){
		if(!EventManager.isEnabled(PlayerInteractEvent.class, events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), PlayerInteractEvent.class))
			return;
		Entity i = event.getRightClicked();
		file.log(printPlayer(event.getPlayer())+" interacted with "+i.toString()+" in "+printLoc(i.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void player_interact(PlayerInteractEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
			return;
		if(event.getAction()==Action.LEFT_CLICK_AIR || event.getAction()==Action.RIGHT_CLICK_AIR){
			Player i = event.getPlayer();
			if(event.hasItem())
				file.log(printPlayer(event.getPlayer())+" interacted ("+event.getAction().name()+") in "+printLoc(i.getLocation())+" with "+event.getItem().toString());
			else
				file.log(printPlayer(event.getPlayer())+" interacted ("+event.getAction().name()+") in "+printLoc(i.getLocation())+" with his hand");
		}
		else{
			Block i = event.getClickedBlock();
			if(event.hasItem())
				file.log(printPlayer(event.getPlayer())+" interacted ("+event.getAction().name()+")"+" in "+printLoc(i.getLocation())+" with "+event.getItem().toString());
			else
				file.log(printPlayer(event.getPlayer())+" interacted ("+event.getAction().name()+")"+" in "+printLoc(i.getLocation())+" with his hand");
		}
	}
	/*
	 * VEHICLE EVENTS (4)
	 */
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void vehicle_create(VehicleCreateEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Location loc = event.getVehicle().getLocation();
		file.log(event.getVehicle().toString()+" was placed in "+printLoc(loc));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void vehicle_destroy(VehicleDestroyEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Location loc = event.getVehicle().getLocation();
		String entity ="";
		if(event.getAttacker()!=null)
			entity=" by "+event.getAttacker().toString();
		file.log(event.getVehicle().toString()+" was destroyed"+entity+" in "+printLoc(loc));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void vehicle_enter(VehicleEnterEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Location loc = event.getVehicle().getLocation();
		String entity =event.getEntered().toString();
		file.log(entity+" entered "+event.getVehicle().toString()+" in "+printLoc(loc));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void vehicle_exit(VehicleExitEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Location loc = event.getVehicle().getLocation();
		String entity =event.getExited().toString();
		file.log(entity+" exited "+event.getVehicle().toString()+" in "+printLoc(loc));
	}
	
	/*
	 * WEATHER EVENTS (3)
	 */
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void lightning_strike(LightningStrikeEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Entity i =event.getLightning();
		file.log("["+event.getWorld().getName()+"] Lightning has striked in "+printLoc(i.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void thunder_change(ThunderChangeEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		boolean start =event.toThunderState();
		String thunder ="thunder has stopped";
		if(start)
			thunder="thunder has started";
		file.log("["+event.getWorld().getName()+"] "+thunder);
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void weather_change(WeatherChangeEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		boolean start =event.toWeatherState();
		String rain ="rain has stopped";
		if(start)
			rain="rain has started";
		file.log("["+event.getWorld().getName()+"] "+rain);
	}
	
	/*
	 * WORLD EVENTS (3)
	 */
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void onPortal(PortalCreateEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Block i = event.getBlocks().get(0);
		file.log("["+event.getWorld().getName()+"] A portal was created in "+printLoc(i.getLocation()));
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void onGrow(StructureGrowEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		String bonemeal ="";
		if(event.isFromBonemeal()){
			bonemeal = " due to bonemeal ";
			if(event.getPlayer()!=null){
				if(plugin.isEventForbidden(event.getPlayer(), event.getClass()))
					return;
				bonemeal+=" given by "+printPlayer(event.getPlayer());
			}
		}
		Location i = event.getLocation();
		file.log("["+event.getWorld().getName()+"] a "+event.getSpecies().name()+" grown in "+printLoc(i)+bonemeal);
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void onSpawnChanges(SpawnChangeEvent event){
		if(!EventManager.isEnabled(event.getClass(), events))
			return;
		Location from =event.getPreviousLocation();
		Location to = event.getWorld().getSpawnLocation();
		file.log("["+event.getWorld().getName()+"] Spawn was moved from "+printLoc(from)+"to "+printLoc(to));
	}
	
	/*
	 * STATIC USEFUL METHODS
	 */
	
	public static String printPlayer(Player player) {
		if(player==null)
			return "Someone";
		String name = player.getName();
		if(Main.isAdmin(player))
			name="[Admin] "+name;
		name="{"+player.getWorld().getName()+"}("+player.getGameMode().name()+")"+name;
		return name;
	}

	public static String printLoc(Location loc){
		return "{"+loc.getWorld().getName()+"}["+(int)loc.getX()+","+(int)loc.getY()+","+(int)loc.getZ()+"]";
	}

}
