package org.ultralogger.sql.logger;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.ultralogger.sql.SQL;

public class BlockLogger implements Listener,Runnable{

    private SQL manager;

    public BlockLogger(SQL sql){
       manager=sql;
       Thread t = new Thread(this,"[UltraLogger] v1.6 - SQL Block Logger");
       t.start();
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onBreak(BlockBreakEvent e){
        String name = e.getPlayer().getName();
        boolean op = e.getPlayer().isOp();
        int gamemode = e.getPlayer().getGameMode().getValue();
        Block i = e.getBlock();

        manager.query("INSERT INTO `"+manager.getprefix()+"_block`(`time`, `id`, `event`, `x`, `y`, `z`, `entity_name`, `op`, `gamemode`) VALUES (NOW(), "+i.getTypeId()+", 'break' ," +
                toSQLquery(i.getLocation())+",'"+name+"',"+ op + "," + gamemode +")");
    }
    @EventHandler (priority = EventPriority.MONITOR)
    public void onBurn(BlockBurnEvent e){
        Block i = e.getBlock();
        manager.query("INSERT INTO `"+manager.getprefix()+"_block`(`time`, `id`, `event`, `x`, `y`, `z`, `entity_name`) VALUES (NOW(), "+i.getTypeId()+", 'burn' ," +
                ""+toSQLquery(i.getLocation())+",NULL)");
    }
    @EventHandler (priority = EventPriority.MONITOR)
    public void onDispense(BlockDispenseEvent e){
        Block i = e.getBlock();
        manager.query("INSERT INTO `"+manager.getprefix()+"_block`(`time`, `id`, `event`, `x`, `y`, `z`, `entity_name`) VALUES (NOW(), "+i.getTypeId()+", 'dispense' ," +
                ""+toSQLquery(i.getLocation())+",NULL)");
    }
    @EventHandler (priority = EventPriority.MONITOR)
    public void onForm(BlockFormEvent e){
        Block i = e.getBlock();
        manager.query("INSERT INTO `"+manager.getprefix()+"_block`(`time`, `id`, `event`, `x`, `y`, `z`, `entity_name`) VALUES (NOW(), "+i.getTypeId()+", 'form' ," +
                ""+toSQLquery(i.getLocation())+",NULL)");
    }
    @EventHandler (priority = EventPriority.MONITOR)
    public void onGrow(BlockGrowEvent e){
        Block i = e.getBlock();
        manager.query("INSERT INTO `"+manager.getprefix()+"_block`(`time`, `id`, `event`, `x`, `y`, `z`, `entity_name`) VALUES (NOW(), "+i.getTypeId()+", 'grow' ," +
                ""+toSQLquery(i.getLocation())+",NULL)");
    }
    @EventHandler (priority = EventPriority.MONITOR)
    public void onPlace(BlockPlaceEvent e){
        String name = e.getPlayer().getName();
        boolean op = e.getPlayer().isOp();
        int gamemode = e.getPlayer().getGameMode().getValue();
        Block i = e.getBlock();
        manager.query("INSERT INTO `"+manager.getprefix()+"_block`(`time`, `id`, `event`, `x`, `y`, `z`, `entity_name`,op,gamemode) VALUES (NOW(), "+i.getTypeId()+", 'place' ," +
                toSQLquery(i.getLocation())+",'"+name+"',"+ op + "," + gamemode +")");
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
	     //Create the def block table
	     manager.query("CREATE TABLE IF NOT EXISTS `"+manager.getprefix()+"_block`(`prim_key` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,`time` DATETIME NOT NULL,`id` SMALLINT UNSIGNED NOT NULL," +
	                "`event` VARCHAR(255) NOT NULL,`x` INT NOT NULL,`y` INT NOT NULL, `z` INT NOT NULL,`entity_name` VARCHAR(255), `op` BOOLEAN, `gamemode` SMALLINT,PRIMARY KEY (prim_key))");
	}

}
