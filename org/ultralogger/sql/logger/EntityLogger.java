package org.ultralogger.sql.logger;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.ItemStack;
import org.ultralogger.sql.SQL;

public class EntityLogger implements Listener,Runnable{

    private SQL manager;

    /*
     * TODO: Change table Lay-out or split into multiple tables to make it more efficient for full logging and scripts
     */

    public EntityLogger(SQL sql){
        manager=sql;
        Thread t = new Thread(this,"[UltraLogger] v1.6 - SQL Entity Logger");
	    t.start();
}

	@EventHandler (priority = EventPriority.MONITOR)
	public void onEvent1(CreatureSpawnEvent e){
		Entity i =e.getEntity();
       manager.query("INSERT INTO `"+manager.getprefix()+"_entity`(`time`,`world`,`entityname`,`event`,`reason`,`x`,`y`,`z`) VALUES (NOW(),'"+i.getWorld().getName()+"','"+i.toString()+"','Spawn','"+e.getSpawnReason().name()+"',"+toSQLquery(i.getLocation())+")");
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void onEvent2(CreeperPowerEvent e){
		Entity i =e.getEntity();
        manager.query("INSERT INTO `"+manager.getprefix()+"_entity`(`time`,`world`,`entityname`,`event`,`x`,`y`,`z`) VALUES (NOW(),'"+i.getWorld().getName()+"','"+i.toString()+"','Lightning',"+toSQLquery(i.getLocation())+")");
	}
    @EventHandler (priority = EventPriority.MONITOR)
    public void onEvent2a(PigZapEvent e){
        Entity i =e.getEntity();
        manager.query("INSERT INTO `"+manager.getprefix()+"_entity`(`time`,`world`,`entityname`,`event`,`x`,`y`,`z`) VALUES (NOW(),'"+i.getWorld().getName()+"','"+i.toString()+"','PigZap',"+toSQLquery(i.getLocation())+")");
    }
	@EventHandler (priority = EventPriority.MONITOR)
	public void onEvent3(EntityBreakDoorEvent e){
		Entity i =e.getEntity();
        manager.query("INSERT INTO `"+manager.getprefix()+"_entity`(`time`,`world`,`entityname`,`event`,`x`,`y`,`z`) VALUES (NOW(),'"+i.getWorld().getName()+"','"+i.toString()+"','BrokeDoor',"+toSQLquery(i.getLocation())+")");
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void onEvent4(EntityDamageEvent e){
		Entity i =e.getEntity();
		int dmg = e.getDamage();
        manager.query("INSERT INTO `"+manager.getprefix()+"_entity`(`time`,`world`,`entityname`,`event`,`amount`,`reason`,`x`,`y`,`z`) VALUES (NOW(),'"+i.getWorld().getName()+"','"+i.toString()+"','Damage(HalfHearth)',"+ dmg +",'"+e.getCause().name()+"',"+toSQLquery(i.getLocation())+")");
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void onEvent5(EntityDeathEvent e){
		Entity i =e.getEntity();
        manager.query("INSERT INTO `"+manager.getprefix()+"_entity`(`time`,`world`,`entityname`,`event`,`x`,`y`,`z`) VALUES (NOW(),'"+i.getWorld().getName()+"','"+i.toString()+"','Death',"+toSQLquery(i.getLocation())+")");
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void onEvent6(EntityExplodeEvent e){
		Entity i =e.getEntity();
        manager.query("INSERT INTO `"+manager.getprefix()+"_entity`(`time`,`world`,`entityname`,`event`,`x`,`y`,`z`) VALUES (NOW(),'"+i.getWorld().getName()+"','"+i.toString()+"','Explode',"+toSQLquery(i.getLocation())+")");
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void onEvent7(EntityInteractEvent e){
		Block i =e.getBlock();
        manager.query("INSERT INTO `"+manager.getprefix()+"_entity`(`time`,`world`,`entityname`,`event`,`reason`,`x`,`y`,`z`) VALUES (NOW(),'"+i.getWorld().getName()+"','"+i.toString()+"','Spawn','"+"Item="+ new ItemStack(i.getTypeId())+"',"+toSQLquery(i.getLocation())+")");

    }
	@EventHandler (priority = EventPriority.MONITOR)
	public void onEvent8(EntityRegainHealthEvent e){
		Entity i =e.getEntity();
        manager.query("INSERT INTO `"+manager.getprefix()+"_entity`(`time`,`world`,`entityname`,`event`,`amount`,`reason`,`x`,`y`,`z`) VALUES (NOW(),'"+i.getWorld().getName()+"','"+i.toString()+"','Regen(HalfHearth)',"+ e.getAmount() +",'"+e.getRegainReason().name()+"',"+toSQLquery(i.getLocation())+")");
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void onEvent9(EntityTameEvent e){
		Entity i =e.getEntity();
        manager.query("INSERT INTO `"+manager.getprefix()+"_entity`(`time`,`world`,`entityname`,`event`,`reason`,`x`,`y`,`z`) VALUES (NOW(),'"+i.getWorld().getName()+"','"+i.toString()+"','Spawn','"+"Owner="+e.getOwner().getName()+"',"+toSQLquery(i.getLocation())+")");
    }
	@EventHandler (priority = EventPriority.MONITOR)
	public void onEvent10(ExpBottleEvent e){
		Entity i =e.getEntity();
        manager.query("INSERT INTO `"+manager.getprefix()+"_entity`(`time`,`world`,`entityname`,`event`,`x`,`y`,`z`) VALUES (NOW(),'"+i.getWorld().getName()+"','"+i.toString()+"','TrowXP',"+toSQLquery(i.getLocation())+")");
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void onEvent11(PotionSplashEvent e){
		Entity i =e.getPotion();
        manager.query("INSERT INTO `"+manager.getprefix()+"_entity`(`time`,`world`,`entityname`,`event`,`x`,`y`,`z`) VALUES (NOW(),'"+i.getWorld().getName()+"','"+i.toString()+"','TrowPotion',"+toSQLquery(i.getLocation())+")");
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void onEvent12(ProjectileHitEvent e){
		Entity i =e.getEntity();
        manager.query("INSERT INTO `"+manager.getprefix()+"_entity`(`time`,`world`,`entityname`,`event`,`x`,`y`,`z`) VALUES (NOW(),'"+i.getWorld().getName()+"','"+i.toString()+"','ProjectileHit',"+toSQLquery(i.getLocation())+")");
	}

    /*
     * TODO: Add more entity events
     * TODO: Move to another file where it can be called from as it is used in multiple Loggers
     */

    public String toSQLquery(Location loc){
        String s =loc.getBlockX()+","+loc.getBlockY()+","+loc.getBlockZ();
        return s;
    }

	@Override
	public void run() {
        manager.register(this);
        //Create the def entity table
        manager.query("CREATE TABLE IF NOT EXISTS `"+manager.getprefix()+"_entity` (`prim_key` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,`time` DATETIME NOT NULL,`world` VARCHAR(255) NOT NULL," +
                "`entityname` VARCHAR(255) NOT NULL,`event` VARCHAR(255) NOT NULL,`amount` BIGINT,`reason` VARCHAR(255),`x` INT NOT NULL,`y` INT NOT NULL, `z` INT NOT NULL,PRIMARY KEY (prim_key)) COLLATE='utf8_general_ci'");	
	}
}
