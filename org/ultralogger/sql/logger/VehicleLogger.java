package org.ultralogger.sql.logger;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.ultralogger.sql.SQL;

public class VehicleLogger implements Listener,Runnable{

    private SQL manager;

    public VehicleLogger(SQL sql){
        manager=sql;
        Thread t = new Thread(this,"[UltraLogger] v1.6 - SQL Command Logger");
	    t.start();
    }

	@EventHandler (priority = EventPriority.MONITOR)
	public void onCreated(VehicleCreateEvent e){
		Location loc = e.getVehicle().getLocation();

        manager.query("INSERT INTO `"+manager.getprefix()+"_vehicle`(`time`, `vehicle`, `event`, `x`, `y`, `z`) VALUES (NOW(),'"+e.getVehicle().toString()+"', 'place' ," +
                toSQLquery(loc)+")");
    }
	@EventHandler (priority = EventPriority.MONITOR)
	public void onDestroyed(VehicleDestroyEvent e){
		Location loc = e.getVehicle().getLocation();
		String entity ="";
		if(e.getAttacker()!=null){
			entity= e.getAttacker().getClass().getName();
		}

        manager.query("INSERT INTO `"+manager.getprefix()+"_vehicle`(`time`, `vehicle`, `entity`, `event`, `x`, `y`, `z`) VALUES (NOW(),'"+e.getVehicle().toString()+"','"+ entity + "','destroy' ," +
                toSQLquery(loc)+")");
    }
	@EventHandler (priority = EventPriority.MONITOR)
	public void onEnter(VehicleEnterEvent e){
		Location loc = e.getVehicle().getLocation();
		String entity =e.getEntered().getClass().getName();

        manager.query("INSERT INTO `"+manager.getprefix()+"_vehicle`(`time`, `vehicle`, `entity`, `event`, `x`, `y`, `z`) VALUES (NOW(),'"+e.getVehicle().toString()+"','"+ entity + "','enter' ," +
                toSQLquery(loc)+")");
    }
	@EventHandler (priority = EventPriority.MONITOR)
	public void onExit(VehicleExitEvent e){
		Location loc = e.getVehicle().getLocation();
		String entity =e.getExited().getClass().getName();

        manager.query("INSERT INTO `"+manager.getprefix()+"_vehicle`(`time`, `vehicle`, `entity`, `event`, `x`, `y`, `z`) VALUES (NOW(),'"+e.getVehicle().toString()+"','"+ entity + "','exit' ," +
                toSQLquery(loc)+")");
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
        //Create the def vehicle table
        manager.query("CREATE TABLE IF NOT EXISTS `"+manager.getprefix()+"_vehicle`(`prim_key` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,`time` DATETIME NOT NULL," +
                "`vehicle` VARCHAR(255) NOT NULL,`entity` VARCHAR(255),`event` VARCHAR(255) NOT NULL,`x` INT NOT NULL,`y` INT NOT NULL, `z` INT NOT NULL,PRIMARY KEY (prim_key))");
		
	}
}
