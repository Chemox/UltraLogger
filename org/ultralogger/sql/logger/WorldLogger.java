package org.ultralogger.sql.logger;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.event.world.SpawnChangeEvent;
import org.bukkit.event.world.StructureGrowEvent;
import org.ultralogger.sql.SQL;

public class WorldLogger implements Listener,Runnable{

    private SQL manager;

    public WorldLogger(SQL sql){
        manager=sql;
        Thread t = new Thread(this,"[UltraLogger] v1.6 - SQL Command Logger");
	    t.start();
    }

	@EventHandler (priority = EventPriority.MONITOR)
	public void onPortal(PortalCreateEvent e){
		Block i = e.getBlocks().get(0);

        manager.query("INSERT INTO `"+manager.getprefix()+"_world`(`time`, `world`, `event`,`x`,`y`, `z`) VALUES (NOW(),'"+e.getWorld().getName()+"', 'portal.create' ," +
                toSQLquery(i.getLocation())+")");
    }
	@EventHandler (priority = EventPriority.MONITOR)
	public void onGrow(StructureGrowEvent e){
		String name ="";
        boolean op;
        int gamemode;

        String bonemeal ="";
        if(e.isFromBonemeal()){
            bonemeal = "cause.bonemeal";
        }
        Location i = e.getLocation();

		if(e.getPlayer()!=null){
			name = e.getPlayer().getName();
			op = e.getPlayer().isOp();
		    gamemode = e.getPlayer().getGameMode().getValue();
            manager.query("INSERT INTO `"+manager.getprefix()+"_world`(`time`, `world`, `event`,`species`,`x`,`y`, `z`, entity_name, op, gamemode) VALUES (NOW(),'"+e.getWorld().getName()+"', 'player.structure.grow."+bonemeal+"','" +
                    e.getSpecies().name()+"',"+toSQLquery(i)+",'"+name+"',"+ op +","+ gamemode +")");
		}else{
            manager.query("INSERT INTO `"+manager.getprefix()+"_world`(`time`, `world`, `event`,`species`,`x`,`y`, `z`) VALUES (NOW(),'"+e.getWorld().getName()+"', 'structure.grow."+bonemeal+"','" +
                    e.getSpecies().name()+"',"+toSQLquery(i)+")");
        }
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void onSpawnChanges(SpawnChangeEvent e){
		Location i =e.getPreviousLocation();
		Location it = e.getWorld().getSpawnLocation();

        manager.query("INSERT INTO `"+manager.getprefix()+"_world`(`time`, `world`, `event`,`x`,`y`, `z`, `x2`, `y2`, `z2`) VALUES (NOW(),'"+e.getWorld().getName()+"', 'spawn.change' ," +
                toSQLquery(i)+","+toSQLquery(it)+")");
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
        //Create the def world table
        manager.query("CREATE TABLE IF NOT EXISTS `"+manager.getprefix()+"_world` (`prim_key` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,`time` DATETIME NOT NULL," +
                "`world` VARCHAR(255) NOT NULL,`event` VARCHAR(255) NOT NULL,`species` VARCHAR(255),`x` INT NOT NULL,`y` INT NOT NULL, `z` INT NOT NULL, `x2` INT, `y2` INT, `z2` INT, `entity_name` VARCHAR(255), `op` BOOLEAN, `gamemode` SMALLINT,PRIMARY KEY (prim_key))");

    
		
	}
}
