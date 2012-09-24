package org.ultralogger.sql.logger;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.ultralogger.sql.SQL;

public class EnchantmentLogger implements Listener,Runnable{

    private SQL manager;

    public EnchantmentLogger(SQL sql){
        manager=sql;
        Thread t = new Thread(this,"[UltraLogger] v1.6 - SQL Enchantment Logger");
	    t.start();
    }

	@EventHandler (priority = EventPriority.MONITOR)
	public void onEnchant(EnchantItemEvent e){
		Player p =e.getEnchanter();
        String enchants = e.getEnchantsToAdd().toString();
        enchants = enchants.replace("Enchantment","");
        enchants = enchants.replace("]","");
        enchants = enchants.replace(",","");
        enchants = enchants.replaceAll("\\[\\d\\d","");

        /*
         * TODO: Change enchantment String to something better readable for scripts
         */

		int cost = e.getExpLevelCost();
        manager.query("INSERT INTO `"+manager.getprefix()+"_enchantment`(`time`, `playername`, `id`, `enchantment`, `lvlused`,`x`,`y`,`z`) VALUES (NOW(), '"+ p +"','"+e.getItem()+"','"+enchants+"',"
                +cost+","+toSQLquery(e.getEnchantBlock().getLocation())+")");
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
        //Create the def enchantment table
        manager.query("CREATE TABLE IF NOT EXISTS `"+manager.getprefix()+"_enchantment`(`prim_key` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,`time` DATETIME NOT NULL,`playername` VARCHAR(255) NOT NULL," +
                " `id` VARCHAR(255) NOT NULL ,`enchantment` VARCHAR(255) NOT NULL, `lvlused` BIGINT NOT NULL,`x` INT NOT NULL,`y` INT NOT NULL, `z` INT NOT NULL,PRIMARY KEY (prim_key)) COLLATE='utf8_general_ci'");
	}
}
