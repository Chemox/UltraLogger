package org.ultralogger.more;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class Translater {
	private File file;
	private Properties prop;

	
	public Translater(File f){
		if(!f.exists()){
			File dir = f.getParentFile();
			if(!dir.exists()){
				dir.mkdir();
			}
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			PrintWriter p = null;
			try {
				p = new PrintWriter(f);
				p.println("config.created=Succesfully created config file!\nconfig.failed=Failed to create a config file\nupdate=New Update available\ndownload=Download it from " +
						"\nlast=Latest\ntry.command=tried to issue command\ncommand=issued command\nin=in\nwith=with\nfor=for\nfrom=from\nto=to\nby=by\nwas=was" +
						"\ncause=caused by\nblock.break=destroyed\nblock.burn=was destroyed by the fire\nblock.dispense=was dispensed\nblock.form=was formed" +
						"\nblock.grow=grow to data\nblock.place=placed\npaint.place=placed a painting\npaint.break=destroyed a painting\ncraft.item=crafted" +
						"\nbrew=brewing\nportal.create=created a portal\nfurnace.smelt=smelted\nenchant.iem=enchanted\ncause.bonemeal=caused by bonemeal" +
						"\nstructure.grow=has just grown\nspawn.change=changed spawn\nstrike=striking\nthunder.start=thunder hast started" +
						"\nthunder.stop=thunder has stopped\nrain.start=rain has started\nrain.stop=rain has stopped\nvehicle.place=was placed" +
						"\nvehicle.destroy=was destroyed\nvehicle.enter=enters a\nvehicle.exit=exits a\ninventory.open=has just opened a" +
						"\ninventory.close=has just closed a\ninventory.put=has just put\ninventory.from=from his inventory to\ninventory.to=to his inventory" +
						"\ninventory.click=has just clicked on\ncreature.spawn=spawned\ncreature.lightning=was strucked by lightning" +
						"\ncreature.boke.door=broke the door\ndamaged=was damaged of\nhalf.heart=half-heart(s)\nentity.death=died\nentity.explode=exploded" +
						"\nentity.interact=interacted with\nentity.regain=regain\nentity.tame=was tamed by\nexpbottle.throw=an exp bottle was thrown" +
						"\nentity.thrown=was thrown\nprojectile.hit=hits an object\nbed.enter=enters in a bed\nbed.leave=leaves a bed" +
						"\nbucket.empty=empties a bucket\nbucket.fill=fills a bucket\nplayer.change.world=changed world\nplayer.drop.item=dropped" +
						"\nplayer.throw.egg=throw an egg\nplayer.earn.xp=earns\nplayer.fishing=is fishing\nplayer.fish=fishes a" +
						"\nplayer.change.gamemode=changed to gamemode\nplayer.interact=interacts\nplayer.with.hand=with his hand\nplayer.break.item=broke his" +
						"\nplayer.connected=is now connected\nplayer.pickup.item=pickups\nplayer.teleport=was teleported\nplayer.disconnect=is now disconnected" +
						"\nplayer.respawn=respawn\nplayer.shear=sheared\nplayer.food.change=food level has changed to\nplayer.to.lvl=up to lvl" +
						"\nplayer.from.lvl= from lvl\nplayer.start.fly=is now flying\nplayer.stop.fly=stopped flying\nplayer.start.sprint=is now sprinting" +
						"\nplayer.stop.sprint=stopped sprinting\nplayer.start.sneak=is now sneaking\nplayer.stop.sneak=stopped sneaking" +
						"\nplugin.register.event=is registering Event\nplugin.priority=with priority\nplugin.enable=has been enabled" +
						"\nplugin.disable=has been disabled");
				p.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		this.setFile(f);
		prop = new Properties();
		try {
			prop.load(new FileReader(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String translate(String key){
		return prop.getProperty(key,key);
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}

}
