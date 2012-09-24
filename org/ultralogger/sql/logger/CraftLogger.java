package org.ultralogger.sql.logger;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.BrewerInventory;
import org.ultralogger.sql.SQL;

public class CraftLogger implements Listener,Runnable{

    private SQL manager;

    public CraftLogger(SQL sql){
        manager=sql;
        Thread t = new Thread(this,"[UltraLogger] v1.6 - SQL Craft Logger");
	    t.start();
    }
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void onBrew(BrewEvent e){
		BrewerInventory i =e.getContents();
        String potion = i.getItem(0).getData()+","+ i.getItem(1).getData() +","+ i.getItem(2).getData();
        manager.query("INSERT INTO `"+manager.getprefix()+"_craft`(`time`,`event`,`recipe` ,`x` ,`y` ,`z`) VALUES(NOW(),'Brew','"+i.getIngredient().getType().name() +"+"+potion+"',"+toSQLquery(e.getBlock().getLocation())+")");
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void onCraft(CraftItemEvent e){
        manager.query("INSERT INTO `"+manager.getprefix()+"_craft`(`time`,`playername`,`result`,`event`,`x`,`y`,`z`) VALUES(NOW(),'"+e.getWhoClicked().getName()+"','"+e.getRecipe().getResult()+"','Craft',"+
                        toSQLquery(e.getView().getPlayer().getLocation())+ ")");
    }
	@EventHandler (priority = EventPriority.MONITOR)
	public void onSmelt(FurnaceSmeltEvent e){
        manager.query("INSERT INTO `"+manager.getprefix()+"_craft`(`time`,`result`,`event`,`recipe`,`x`,`y`,`z`) VALUES(NOW(),'"+e.getResult()+"','Smelt','"+
                e.getSource().toString()+"',"+toSQLquery(e.getBlock().getLocation())+ ")");
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
        //Create the def craft table
        manager.query("CREATE TABLE IF NOT EXISTS `"+manager.getprefix()+"_craft` (`prim_key` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,`time` DATETIME NOT NULL,`playername` VARCHAR(255)," +
                "`result` VARCHAR(255) ,`event` VARCHAR(255) NOT NULL,`recipe` VARCHAR(255),`x` INT NOT NULL,`y` INT NOT NULL, `z` INT NOT NULL,PRIMARY KEY (prim_key)) COLLATE='utf8_general_ci'");
	}
}
