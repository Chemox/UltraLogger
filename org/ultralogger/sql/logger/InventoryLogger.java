package org.ultralogger.sql.logger;

import org.bukkit.Location;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.ultralogger.sql.SQL;

public class InventoryLogger implements Listener,Runnable{

    private SQL manager;

    public InventoryLogger(SQL sql){
        manager=sql;
        Thread t = new Thread(this,"[UltraLogger] v1.6 - SQL Inventory Logger");
	    t.start();
    }

	
	@EventHandler (priority = EventPriority.MONITOR)
	public void onOpen(InventoryOpenEvent e){
		String name = e.getPlayer().getName();
		boolean op = e.getPlayer().isOp();
        int gamemode = e.getPlayer().getGameMode().getValue();
		
		HumanEntity i =e.getPlayer();

        manager.query("INSERT INTO `"+manager.getprefix()+"_inventory`(`time`, `name`, `op`, `gamemode`, `event`, `InvTitel/Item`, `InvName/From`, `x`, `y`, `z`) VALUES (NOW(), '"+name+"'," + op + "," + gamemode +","+
                "'open','"+e.getView().getTitle()+"','"+e.getView().getType().name()+"',"+toSQLquery(i.getLocation())+")");

	}
	@EventHandler (priority = EventPriority.MONITOR)
	public void onClose(InventoryCloseEvent e){
        String name = e.getPlayer().getName();
        boolean op = e.getPlayer().isOp();
        int gamemode = e.getPlayer().getGameMode().getValue();
		
		HumanEntity i =e.getPlayer();

        manager.query("INSERT INTO `"+manager.getprefix()+"_inventory`(`time`, `name`, `op`, `gamemode`, `event`, `InvTitel/Item`, `InvName/From`, `x`, `y`, `z`) VALUES (NOW(), '"+name+"'," + op + "," + gamemode +","+
                "'close','"+e.getView().getTitle()+"','"+e.getView().getType().name()+"',"+toSQLquery(i.getLocation())+")");

	}

	private ItemStack last = null;
	private Inventory lastI = null;
	private int amount = -1;
	@EventHandler (priority = EventPriority.MONITOR)
	public void onClick(InventoryClickEvent e){
        String name = e.getWhoClicked().getName();
        boolean op = e.getWhoClicked().isOp();
        int gamemode = e.getWhoClicked().getGameMode().getValue();

		if(e.getCurrentItem()==null){ return;}
		HumanEntity i =e.getWhoClicked();
		ItemStack item =e.getCursor();
		boolean in = e.getView().getBottomInventory().getSize()<=e.getRawSlot();
		
		if(last!=null&&lastI!=null&&e.getView().getTopInventory().getType()==lastI.getType()&&item.getTypeId()==0){
			last.setAmount(amount);
			if(in){

                manager.query("INSERT INTO `"+manager.getprefix()+"_inventory`(`time`, `name`, `op`, `gamemode`, `event`, `InvTitel/Item`, `InvName/From`, `x`, `y`, `z`) VALUES (NOW(), '"+name+"'," + op + "," + gamemode +","+
                        "'put','"+last.toString()+"','"+lastI.getType().name()+"',"+toSQLquery(i.getLocation())+")");

			}
			else{

                manager.query("INSERT INTO `"+manager.getprefix()+"_inventory`(`time`, `name`, `op`, `gamemode`, `event`, `InvTitel/Item`, `InvName/From`, `x`, `y`, `z`) VALUES (NOW(), '"+name+"'," + op + "," + gamemode +","+
                        "'put','"+last.toString()+"','"+e.getView().getType().name()+"',"+toSQLquery(i.getLocation())+")");

			}
			last=e.getCurrentItem();
			amount=item.getAmount();
			lastI=e.getView().getTopInventory();
		}
		else{
			last=item;
			amount=item.getAmount();
			lastI=e.getView().getTopInventory();

            manager.query("INSERT INTO `"+manager.getprefix()+"_inventory`(`time`, `name`, `op`, `gamemode`, `event`, `InvTitel/Item`, `InvName/From`, `x`, `y`, `z`) VALUES (NOW(), '"+name+"'," + op + "," + gamemode +","+
                    "'click','"+item.toString()+"','"+e.getView().getTitle()+"',"+toSQLquery(i.getLocation())+")");

		}
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
        //Create the def inventory table
        manager.query("CREATE TABLE IF NOT EXISTS `"+manager.getprefix()+"_inventory`(`prim_key` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,`time` DATETIME NOT NULL,`name` VARCHAR(255) NOT NULL,`op` BOOLEAN, `gamemode` INT," +
                "`event` VARCHAR(255) NOT NULL, `InvTitel/Item` VARCHAR(255) NOT NULL, `InvName/From` VARCHAR(255) NOT NULL, `x` INT NOT NULL,`y` INT NOT NULL, `z` INT NOT NULL, PRIMARY KEY (prim_key))");
	}

}
