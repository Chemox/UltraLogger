package org.ultralogger.sql.logger;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.inventory.ItemStack;
import org.ultralogger.sql.SQL;

public class PlayerLogger implements Listener,Runnable{

    private SQL manager;

    public PlayerLogger(SQL sql){
        manager=sql;
        Thread t = new Thread(this,"[UltraLogger] v1.6 - SQL Player Logger");
	    t.start();
    }

	@EventHandler (priority = EventPriority.MONITOR)
	public void onEnterBed(PlayerBedEnterEvent e){
		String name = e.getPlayer().getName();
		boolean op = e.getPlayer().isOp();
        int gamemode = e.getPlayer().getGameMode().getValue();
		Block bed = e.getBed();

        manager.query("INSERT INTO `"+manager.getprefix()+"_player`(`time`, `event`, `x`, `y`, `z`, `entity_name`, `op`,`gamemode`) VALUES (NOW(), "+"'bed.enter' ," +
                toSQLquery(bed.getLocation())+",'"+name+"',"+ op + "," + gamemode +")");
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void onLeaveBed(PlayerBedLeaveEvent e){
		if(e.getBed()==null||e.getPlayer()==null){return;}
		String name = e.getPlayer().getName();
		boolean op = e.getPlayer().isOp();
        int gamemode=e.getPlayer().getGameMode().getValue();
		Block bed = e.getBed();

        manager.query("INSERT INTO `" + manager.getprefix() + "_player`(`time`, `event`, `x`, `y`, `z`, `entity_name`, `op`, `gamemode`) VALUES (NOW(), " + "'bed.leave' ," +
                toSQLquery(bed.getLocation()) + ",'" + name + "'," + op + "," + gamemode + ")");
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void onEmptyBucket(PlayerBucketEmptyEvent e){
        String name = e.getPlayer().getName();
        boolean op = e.getPlayer().isOp();
        int gamemode=e.getPlayer().getGameMode().getValue();
		Block bed = e.getBlockClicked();

        manager.query("INSERT INTO `"+manager.getprefix()+"_player`(`time`, `event`, `x`, `y`, `z`, `entity_name`, `op`,`gamemode`) VALUES (NOW(), "+"'bucket.empty' ," +
                toSQLquery(bed.getLocation())+",'"+name+"',"+ op + "," + gamemode +")");
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void onFillBucket(PlayerBucketFillEvent e){
        String name = e.getPlayer().getName();
        boolean op = e.getPlayer().isOp();
        int gamemode=e.getPlayer().getGameMode().getValue();
		Block bed = e.getBlockClicked();

        manager.query("INSERT INTO `" + manager.getprefix() + "_player`(`time`, `event`, `x`, `y`, `z`, `entity_name`, `op`, `gamemode`) VALUES (NOW(), " + "'bucket.fill' ," +
                toSQLquery(bed.getLocation()) + ",'" + name + "'," + op + "," + gamemode + ")");
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void onChangeWorld(PlayerChangedWorldEvent e){
        String name = e.getPlayer().getName();
        boolean op = e.getPlayer().isOp();
        int gamemode=e.getPlayer().getGameMode().getValue();

        manager.query("INSERT INTO `" + manager.getprefix() + "_player`(`time`, `event`,`From/Cause/Amount/Message`,`To/NewAmount/Item`, `x`, `y`, `z`, `entity_name`, `op`, `gamemode`) VALUES (NOW(), " + "'player.change.world' ,'" +
                e.getFrom().getName() + "','" + e.getPlayer().getWorld().getName() + "'," + toSQLquery(e.getPlayer().getLocation()) + ",'" + name + "'," + op + "," + gamemode + ")");
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void onDropItem(PlayerDropItemEvent e){
        String name = e.getPlayer().getName();
        boolean op = e.getPlayer().isOp();
        int gamemode=e.getPlayer().getGameMode().getValue();
		Item i =e.getItemDrop();

        manager.query("INSERT INTO `" + manager.getprefix() + "_player`(`time`, `event`,`From/Cause/Amount/Message`, `x`, `y`, `z`, `entity_name`, `op`, `gamemode`) VALUES (NOW(), "+"'player.drop' ,'" +
                i.getItemStack().toString()+"',"+toSQLquery(e.getPlayer().getLocation())+",'"+name+"',"+ op + "," + gamemode +")");

	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void onThrowEgg(PlayerEggThrowEvent e){
        String name = e.getPlayer().getName();
        boolean op = e.getPlayer().isOp();
        int gamemode=e.getPlayer().getGameMode().getValue();

        manager.query("INSERT INTO `"+manager.getprefix()+"_player`(`time`, `event`, `x`, `y`, `z`, `entity_name`, `op`,`gamemode`) VALUES (NOW(), "+"'player.throw.egg' ," +
                toSQLquery(e.getPlayer().getLocation())+",'"+name+"',"+ op + "," + gamemode +")");
	}
    @EventHandler (priority = EventPriority.MONITOR)
	public void onExpChange(PlayerExpChangeEvent e){
        String name = e.getPlayer().getName();
        boolean op = e.getPlayer().isOp();
        int gamemode=e.getPlayer().getGameMode().getValue();
		int i =e.getAmount();

        manager.query("INSERT INTO `" + manager.getprefix() + "_player`(`time`, `event`,`From/Cause/Amount/Message`, `x`, `y`, `z`, `entity_name`, `op`, `gamemode`) VALUES (NOW(), "+"'player.earns.xp' ," +
                i+","+toSQLquery(e.getPlayer().getLocation())+",'"+name+"',"+ op + "," + gamemode +")");
	}
    @EventHandler (priority = EventPriority.MONITOR)
	public void onFish(PlayerFishEvent e){
        String name = e.getPlayer().getName();
        boolean op = e.getPlayer().isOp();
        int gamemode=e.getPlayer().getGameMode().getValue();

		if(e.getState()==State.CAUGHT_ENTITY){
			Entity i =e.getCaught();
			if(i!=null){

                manager.query("INSERT INTO `" + manager.getprefix() + "_player`(`time`, `event`,`From/Cause/Amount/Message`, `x`, `y`, `z`, `entity_name`, `op`, `gamemode`) VALUES (NOW(), "+"'player.fishing' ,'" +
                        i.toString()+"',"+toSQLquery(e.getPlayer().getLocation())+",'"+name+"',"+ op + "," + gamemode +")");
			}
		}
		else{
            manager.query("INSERT INTO `" + manager.getprefix() + "_player`(`time`, `event`,`From/Cause/Amount/Message`, `x`, `y`, `z`, `entity_name`, `op`, `gamemode`) VALUES (NOW(), "+"'player.fish' ,'" +
                    e.getState().name()+"',"+toSQLquery(e.getPlayer().getLocation())+",'"+name+"',"+ op + "," + gamemode +")");
		}
	}
    @EventHandler (priority = EventPriority.MONITOR)
	public void onChangeGameMode(PlayerGameModeChangeEvent e){
        String name = e.getPlayer().getName();
        boolean op = e.getPlayer().isOp();
        int gamemode=e.getPlayer().getGameMode().getValue();

        manager.query("INSERT INTO `" + manager.getprefix() + "_player`(`time`, `event`,`From/Cause/Amount/Message`, `x`, `y`, `z`, `entity_name`, `op`, `gamemode`) VALUES (NOW(), "+"'player.change.gamemode' ,'" +
                e.getNewGameMode().name()+"',"+toSQLquery(e.getPlayer().getLocation())+",'"+name+"',"+ op + "," + gamemode +")");
	}
    @EventHandler (priority = EventPriority.MONITOR)
	public void onInteractEntity(PlayerInteractEntityEvent e){
        String name = e.getPlayer().getName();
        boolean op = e.getPlayer().isOp();
        int gamemode=e.getPlayer().getGameMode().getValue();
		Entity i = e.getRightClicked();

        manager.query("INSERT INTO `" + manager.getprefix() + "_player`(`time`, `event`,`From/Cause/Amount/Message`, `x`, `y`, `z`, `entity_name`, `op`, `gamemode`) VALUES (NOW(), "+"'entity.interact' ,'" +
                i.toString()+"',"+toSQLquery(e.getPlayer().getLocation())+",'"+name+"',"+ op + "," + gamemode +")");
	}
    @EventHandler (priority = EventPriority.MONITOR)
	public void onInteract(PlayerInteractEvent e){
        String name = e.getPlayer().getName();
        boolean op = e.getPlayer().isOp();
        int gamemode=e.getPlayer().getGameMode().getValue();

		if(e.getAction()==Action.LEFT_CLICK_AIR||e.getAction()==Action.RIGHT_CLICK_AIR){
			if(e.hasItem()){

                manager.query("INSERT INTO `"+manager.getprefix()+"_player`(`time`, `event`,`From/Cause/Amount/Message`, `To/NewAmount/Item`,`x`, `y`, `z`, `entity_name`, `op`, `gamemode`) VALUES (NOW(), "+"'player.interact.','"+
                        e.getAction().name()+"','"+ e.getItem().getData().toString() +"',"+toSQLquery(e.getPlayer().getLocation())+",'"+name+"',"+ op + "," + gamemode +")");

			}
			else{
                manager.query("INSERT INTO `"+manager.getprefix()+"_player`(`time`, `event`,`From/Cause/Amount/Message`, `To/NewAmount/Item`,`x`, `y`, `z`, `entity_name`, `op`, `gamemode`) VALUES (NOW(), "+"'player.interact','"+
                        e.getAction().name()+"','"+ "hand" +"',"+toSQLquery(e.getPlayer().getLocation())+",'"+name+"',"+ op + "," + gamemode +")");
			}
		}
		else{
			if(e.hasItem()){
                manager.query("INSERT INTO `" + manager.getprefix() + "_player`(`time`, `event`,`From/Cause/Amount/Message`,`To/NewAmount/Item`, `x`, `y`, `z`, `entity_name`, `op`, `gamemode`) VALUES (NOW(), " + "'player.interact','"+
                        e.getAction().name() + "','" + e.getItem().getData().toString() + "'," + toSQLquery(e.getPlayer().getLocation()) + ",'" + name + "'," + op + "," + gamemode + ")");
            }
			else{
                manager.query("INSERT INTO `" + manager.getprefix() + "_player`(`time`, `event`,`From/Cause/Amount/Message`,`To/NewAmount/Item`, `x`, `y`, `z`, `entity_name`, `op`, `gamemode`) VALUES (NOW(), " + "'player.interact','"+
                        e.getAction().name()+"','"+ "hand" + "'," + toSQLquery(e.getPlayer().getLocation()) + ",'" + name + "'," + op + "," + gamemode + ")");
            }
		}
	}
    @EventHandler (priority = EventPriority.MONITOR)
	public void onBreakItem(PlayerItemBreakEvent e){
        String name = e.getPlayer().getName();
        boolean op = e.getPlayer().isOp();
        int gamemode=e.getPlayer().getGameMode().getValue();
		ItemStack i =e.getBrokenItem();

        manager.query("INSERT INTO `" + manager.getprefix() + "_player`(`time`, `event`,`From/Cause/Amount/Message`, `x`, `y`, `z`, `entity_name`, `op`, `gamemode`) VALUES (NOW(), "+"'player.break.item' ,'" +
                i.toString()+"',"+toSQLquery(e.getPlayer().getLocation())+",'"+name+"',"+ op + "," + gamemode +")");
	}
    @EventHandler (priority = EventPriority.MONITOR)
	public void onEvent14(PlayerJoinEvent e){
        String name = e.getPlayer().getName();
        boolean op = e.getPlayer().isOp();
        int gamemode=e.getPlayer().getGameMode().getValue();

        manager.query("INSERT INTO `"+manager.getprefix()+"_player`(`time`, `event`, `x`, `y`, `z`, `entity_name`, `op`,`gamemode`) VALUES (NOW(), "+"'player.connected' ," +
                toSQLquery(e.getPlayer().getLocation())+",'"+name+"',"+ op + "," + gamemode +")");

	}
    @EventHandler (priority = EventPriority.MONITOR)
	public void onEvent15(PlayerPickupItemEvent e){
        String name = e.getPlayer().getName();
        boolean op = e.getPlayer().isOp();
        int gamemode=e.getPlayer().getGameMode().getValue();
		Item i =e.getItem();

        manager.query("INSERT INTO `" + manager.getprefix() + "_player`(`time`, `event`,`From/Cause/Amount/Message`, `x`, `y`, `z`, `entity_name`, `op`, `gamemode`) VALUES (NOW(), "+"'player.pickup.item' ,'" +
                i.getItemStack().toString()+"',"+toSQLquery(e.getPlayer().getLocation())+",'"+name+"',"+ op + "," + gamemode +")");
	}
    @EventHandler (priority = EventPriority.MONITOR)
	public void onEvent16(PlayerPortalEvent e){
        String name = e.getPlayer().getName();
        boolean op = e.getPlayer().isOp();
        int gamemode=e.getPlayer().getGameMode().getValue();
		Location i = e.getFrom();
		Location f = e.getTo();

        manager.query("INSERT INTO `"+manager.getprefix()+"_player`(`time`, `event`,`From/Cause/Amount/Message`, `x`, `y`, `z`, `x2`, `y2`, `z2`,`entity_name`, `op`, `gamemode`) VALUES (NOW(), "+"'player.teleport' ,'" +
                e.getCause().name()+"',"+toSQLquery(i)+toSQLquery(f)+",'"+name+"',"+ op + "," + gamemode +")");
    }
    @EventHandler (priority = EventPriority.MONITOR)
	public void onEvent17(AsyncPlayerPreLoginEvent e){
		String name = e.getName()+" ("+e.getAddress().getHostAddress()+")";
		if(e.getKickMessage()!=null&&e.getKickMessage()!=""){

            manager.query("INSERT INTO `"+manager.getprefix()+"_player`(`time`, `event`,`From/Cause/Amount/Message`, `entity_name`) VALUES (NOW(), "+"'"+e.getResult().name()+"' ,'" +
                    manager.StringCheck(e.getKickMessage())+"','"+name+"'"+")");
		}
		else{
            manager.query("INSERT INTO `"+manager.getprefix()+"_player`(`time`, `event`, `entity_name`) VALUES (NOW(), "+"'"+e.getResult().name()+"' ,'" +
                    name+"')");
		}
	}
    @EventHandler (priority = EventPriority.MONITOR)
	public void onEvent18(PlayerQuitEvent e){
        String name = e.getPlayer().getName();
        boolean op = e.getPlayer().isOp();
        int gamemode=e.getPlayer().getGameMode().getValue();

        manager.query("INSERT INTO `"+manager.getprefix()+"_player`(`time`, `event`, `x`, `y`, `z`, `entity_name`, `op`,`gamemode`) VALUES (NOW(), "+"'player.disconnect' ," +
                toSQLquery(e.getPlayer().getLocation())+",'"+name+"',"+ op + "," + gamemode +")");
	}
    @EventHandler (priority = EventPriority.MONITOR)
	public void onEvent19(PlayerRespawnEvent e){
        String name = e.getPlayer().getName();
        boolean op = e.getPlayer().isOp();
        int gamemode=e.getPlayer().getGameMode().getValue();
		Location i = e.getRespawnLocation();

        manager.query("INSERT INTO `"+manager.getprefix()+"_player`(`time`, `event`, `x`, `y`, `z`, `entity_name`, `op`,`gamemode`) VALUES (NOW(), "+"'player.respawn' ," +
                toSQLquery(i)+",'"+name+"',"+ op + "," + gamemode +")");
	}
    @EventHandler (priority = EventPriority.MONITOR)
	public void onEvent20(PlayerShearEntityEvent e){
        String name = e.getPlayer().getName();
        boolean op = e.getPlayer().isOp();
        int gamemode=e.getPlayer().getGameMode().getValue();
		Entity i = e.getEntity();

        manager.query("INSERT INTO `" + manager.getprefix() + "_player`(`time`, `event`,`From/Cause/Amount/Message`, `x`, `y`, `z`, `entity_name`, `op`, `gamemode`) VALUES (NOW(), "+"'player.shear' ,'" +
                i.toString()+"',"+toSQLquery(e.getPlayer().getLocation())+",'"+name+"',"+ op + "," + gamemode +")");
	}
    @EventHandler (priority = EventPriority.MONITOR)
	public void onEvent21(PlayerTeleportEvent e){
        String name = e.getPlayer().getName();
        boolean op = e.getPlayer().isOp();
        int gamemode=e.getPlayer().getGameMode().getValue();
		Location i = e.getFrom();
		Location f = e.getTo();

        manager.query("INSERT INTO `" + manager.getprefix() + "_player`(`time`, `event`,`From/Cause/Amount/Message`, `x`, `y`, `z`,`x2`,`y2`,`z2`, `entity_name`, `op`, `gamemode`) VALUES (NOW(), "+"'player.teleport' ,'" +
         e.getCause().name()+"',"+toSQLquery(i)+","+toSQLquery(f)+",'"+name+"',"+ op + "," + gamemode +")");

    }
    @EventHandler (priority = EventPriority.MONITOR)
	public void onEvent22(PlayerToggleFlightEvent e){
        String name = e.getPlayer().getName();
        boolean op = e.getPlayer().isOp();
        int gamemode=e.getPlayer().getGameMode().getValue();
		
		if(e.isFlying()){

            manager.query("INSERT INTO `"+manager.getprefix()+"_player`(`time`, `event`, `x`, `y`, `z`, `entity_name`, `op`,`gamemode`) VALUES (NOW(), "+"'player.start.fly' ," +
                    toSQLquery(e.getPlayer().getLocation())+",'"+name+"',"+ op + "," + gamemode +")");
		}
		else{
            manager.query("INSERT INTO `"+manager.getprefix()+"_player`(`time`, `event`, `x`, `y`, `z`, `entity_name`, `op`,`gamemode`) VALUES (NOW(), "+"'player.stop.fly' ," +
                    toSQLquery(e.getPlayer().getLocation())+",'"+name+"',"+ op + "," + gamemode +")");
		}
	}
    @EventHandler (priority = EventPriority.MONITOR)
	public void onEvent23(PlayerToggleSprintEvent e){
        String name = e.getPlayer().getName();
        boolean op = e.getPlayer().isOp();
        int gamemode=e.getPlayer().getGameMode().getValue();
		
		if(e.isSprinting()){
            manager.query("INSERT INTO `"+manager.getprefix()+"_player`(`time`, `event`, `x`, `y`, `z`, `entity_name`, `op`,`gamemode`) VALUES (NOW(), "+"'player.start.sprint' ," +
                    toSQLquery(e.getPlayer().getLocation())+",'"+name+"',"+ op + "," + gamemode +")");
		}
		else{
            manager.query("INSERT INTO `" + manager.getprefix() + "_player`(`time`, `event`, `x`, `y`, `z`, `entity_name`, `op`, `gamemode`) VALUES (NOW(), " + "'player.stop.sprint' ," +
                    toSQLquery(e.getPlayer().getLocation()) + ",'" + name + "'," + op + "," + gamemode + ")");
		}
	}
    @EventHandler (priority = EventPriority.MONITOR)
	public void onEvent24(PlayerToggleSneakEvent e){
        String name = e.getPlayer().getName();
        boolean op = e.getPlayer().isOp();
        int gamemode=e.getPlayer().getGameMode().getValue();
		
		if(e.isSneaking()){
            manager.query("INSERT INTO `" + manager.getprefix() + "_player`(`time`, `event`, `x`, `y`, `z`, `entity_name`, `op`, `gamemode`) VALUES (NOW(), " + "'player.start.sneak' ," +
                    toSQLquery(e.getPlayer().getLocation()) + ",'" + name + "'," + op + "," + gamemode + ")");
		}
		else{
            manager.query("INSERT INTO `" + manager.getprefix() + "_player`(`time`, `event`, `x`, `y`, `z`, `entity_name`, `op`, `gamemode`) VALUES (NOW(), " + "'player.stop.sneak' ," +
                    toSQLquery(e.getPlayer().getLocation()) + ",'" + name + "'," + op + "," + gamemode + ")");
		}
	}
    @EventHandler (priority = EventPriority.MONITOR)
	public void onEvent25(PlayerDeathEvent e){
        String name = e.getEntity().getName();
        boolean op = e.getEntity().isOp();
        int gamemode=e.getEntity().getGameMode().getValue();

        manager.query("INSERT INTO `"+manager.getprefix()+"_player`(`time`, `event`, `From/Cause/Amount/Message`, `x`, `y`, `z`, `entity_name`, `op`, `gamemode`) VALUES (NOW(), "+"'player.death' ,'" +
                e.getDeathMessage()+"',"+toSQLquery(e.getEntity().getLocation())+",'"+name+"',"+ op + "," + gamemode +")");
	}
    @EventHandler (priority = EventPriority.MONITOR)
	public void onEvent26(PlayerLevelChangeEvent e){
        String name = e.getPlayer().getName();
        boolean op = e.getPlayer().isOp();
        int gamemode=e.getPlayer().getGameMode().getValue();

        manager.query("INSERT INTO `" + manager.getprefix() + "_player`(`time`, `event`, `From/Cause/Amount/Message`, `To/NewAmount/Item`, `x`, `y`, `z`, `entity_name`, `op`, `gamemode`) VALUES (NOW(), " + "'player.lvl' ,'" +
                e.getOldLevel() + "','" + e.getNewLevel() + "'," + toSQLquery(e.getPlayer().getLocation()) + ",'" + name + "'," + op + "," + gamemode + ")");
	}
    @EventHandler (priority = EventPriority.MONITOR)
	public void onEvent26(FoodLevelChangeEvent e){
        String name = e.getEntity().getName();
        boolean op = e.getEntity().isOp();
        int gamemode=e.getEntity().getGameMode().getValue();

        manager.query("INSERT INTO `"+manager.getprefix()+"_player`(`time`, `event`, `From/Cause/Amount/Message`, `x`, `y`, `z`, `entity_name`, `op`, `gamemode`) VALUES (NOW(), "+"'player.food.change' ,'" +
                e.getFoodLevel()+"',"+toSQLquery(e.getEntity().getLocation())+",'"+name+"',"+ op + "," + gamemode +")");
	}

    /*
    * TODO: Move to another file where it can be called from as it is used in multiple Loggers
    */

    public String toSQLquery(Location loc){
        String s =loc.getBlockX()+","+loc.getBlockY()+","+loc.getBlockZ();
        return s;
    }

	@Override
	public void run() {
		manager.register(this);
        //Create the def player table
        manager.query("CREATE TABLE IF NOT EXISTS `"+manager.getprefix()+"_player`(`prim_key` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,`time` DATETIME NOT NULL," +
                "`event` VARCHAR(255) NOT NULL,`From/Cause/Amount/Message` VARCHAR(255),`To/NewAmount/Item` VARCHAR(255),`x` INT,`y` INT, `z` INT,`x2` INT, `y2` INT, `z2` INT,`entity_name` VARCHAR(255), `op` BOOLEAN, `gamemode` SMALLINT,PRIMARY KEY (prim_key))");		
	}

}