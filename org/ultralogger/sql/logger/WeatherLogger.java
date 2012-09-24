package org.ultralogger.sql.logger;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.ultralogger.sql.SQL;

public class WeatherLogger implements Listener,Runnable{

    private SQL manager;

    public WeatherLogger(SQL sql){
        manager=sql;
        Thread t = new Thread(this,"[UltraLogger] v1.6 - SQL Command Logger");
	    t.start();
    }

	@EventHandler (priority = EventPriority.MONITOR)
	public void onStrike(LightningStrikeEvent e){
		Entity i =e.getLightning();

        manager.query("INSERT INTO `"+manager.getprefix()+"_weather`(`time`, `world`, `event`, `x`, `y`, `z`) VALUES (NOW(),'"+e.getWorld().getName()+"', 'strike' ," +
                toSQLquery(i.getLocation())+")");
    }
	@EventHandler (priority = EventPriority.MONITOR)
	public void onThunder(ThunderChangeEvent e){
		boolean i =e.toThunderState();
		String thunder ="thunder.stop";
		if(i){
			thunder="thunder.start";
		}

        manager.query("INSERT INTO `"+manager.getprefix()+"_weather`(`time`, `world`, `event`) VALUES (NOW(),'"+e.getWorld().getName()+"','"+ thunder +"')");
	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void onChange(WeatherChangeEvent e){
		boolean i =e.toWeatherState();
		String thunder ="rain.stop";
		if(i){
			thunder="rain.start";
		}

        manager.query("INSERT INTO `"+manager.getprefix()+"_weather`(`time`, `world`, `event`) VALUES (NOW(),'"+e.getWorld().getName()+"','"+ thunder +"')");
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
        //Create the def weather table
        manager.query("CREATE TABLE IF NOT EXISTS `"+manager.getprefix()+"_weather`(`prim_key` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,`time` DATETIME NOT NULL,`world` VARCHAR(255) NOT NULL," +
                "`event` VARCHAR(255) NOT NULL,`x` INT,`y` INT, `z` INT,PRIMARY KEY (prim_key))");
	}
}
