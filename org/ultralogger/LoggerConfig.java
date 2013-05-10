package org.ultralogger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import org.ultralogger.logger.FileLogger;

public class LoggerConfig {
	
	public static final File CONFIG = new File("./Log/config.yml");

	private ArrayList<String> properties = new ArrayList<String>();
	private ArrayList<String> values = new ArrayList<String>();
	
	private ArrayList<String> group_names = new ArrayList<String>();
	private ArrayList<String> group_values = new ArrayList<String>();
	
	private ArrayList<String> log_names = new ArrayList<String>();
	private ArrayList<String> log_values = new ArrayList<String>();
	
	private void add(String property, String value){
		properties.add(property);
		values.add(value);
	}
	private void add(String property){
		properties.add(property);
		values.add(null);
	}
	public String getValue(String property) {
		return values.get(properties.indexOf(property));
	}
	
	public boolean isSQLloggerEnabled(String name){
		return Boolean.parseBoolean(getValue("sql_"+name));
	}
	
	public boolean willOverwrite() {
		return Boolean.parseBoolean(getValue("overwrite"));
	}

	public int getMaxLines() {
		return Integer.parseInt(getValue("max_lines"));
	}

	public Date lastCreation() {
		return new Date(Long.parseLong(getValue("last_created")));
	}
	
	public void created(String s){
		int index =properties.indexOf("last_created");
		values.remove(index);
		values.add(index, s);
		save();
	}

	public int getFolderDuration() {
		return Integer.parseInt(getValue("folder_duration"));
	}
	
	public int getAutoSave() {
		return Integer.parseInt(getValue("auto_save"));
	}
	
	public boolean canCheckUpdates() {
		return Boolean.parseBoolean(getValue("check_updates"));
	}
	
	private void addDefaultValues(){
		add("#General properties");
		add("#Check or not if you are using latest build available");
		add("check_updates","true");
		add("#Enable or disable ingame use of an item to see what happened at the specified location");
		add("history_logger","true");
		add("#The id of the item with you can see the log of a location");
		add("item_revealer","280");
		add("#File logger properties ----------------------------------------------------------------------------------------------------");
		add("#Time in seconds beetween each auto-saves of the logs");
		add("auto_save","3600");
		add("#Overwrite or not the last logs");
		add("overwrite","false");
		add("#Maximum number of lines of a log file ( 0 for no limit ), it will erase the first 10% lines of the log");
		add("max_lines","0");
		add("#Do NOT modify this value please, it is used to know last time UL created the log folder");
		add("last_created","5000");
		add("#The number of day until UL creates a new log folder");
		add("folder_duration","7");
		add("#For more information on configuration see http://dev.bukkit.org/server-mods/ultralogger/pages/logger-configuration/");
		add("log_block","1@11");
		add("log_chat","69");
		add("log_command","70");
		add("log_craft","12@15");
		add("log_entity","&entity");
		add("log_inventory","&inventory");
		add("log_player","&player");
		add("log_vehicle","59@62");
		add("log_weather","63@65");
		add("log_world","66@68");
		add("#Event groups ----------------------------------------------------------------------------------------------------------------");
		add("group_entity","16@32");
		add("group_inventory","33@35");
		add("group_player","36@58+71+72");
		add("#SQL logger properties -------------------------------------------------------------------------------------------------------");
		add("#While host value is equal to \"blank\" or \"null\", SQL will be disabled");
		add("host","blank");
		add("port","3306");
		add("name","root");
		add("pass","1234");
		add("database","blank");
		add("table_prefix","UL");
		add("#SQL Loggers couldn't be fully configured so only enable/disable");
		add("sql_block","true");
		add("sql_chat","true");
		add("sql_command","true");
		add("sql_craft","true");
		add("sql_enchantment","true");
		add("sql_entity","true");
		add("sql_inventory","true");
		add("sql_player","true");
		add("sql_plugin","true");
		add("sql_vehicle","true");
		add("sql_weather","true");
		add("sql_world","true");
	}
	
	public ArrayList<FileLogger> createFileLoggers(Main plugin){
		load();
		ArrayList<FileLogger> loggers = new ArrayList<FileLogger>();
		for(int index =0; index<log_names.size(); index++){
			String name = log_names.get(index);
			name=name.replace("log_", "");
			String events = transformSimple(log_values.get(index));
			loggers.add(new FileLogger(plugin, this, name, events));
		}
		return loggers;
	}
	
	public ArrayList<String> getGroupsWithID(int id){
		ArrayList<String> names = new ArrayList<String>();
		for(int index =0; index<group_names.size(); index++){
			String name = group_names.get(index);
			String events = transformSimple(group_values.get(index));
			if(events.contains(""+id))
				names.add(name);
		}
		return names;
	}
	
 	public void load(){
		if(!CONFIG.exists())
			save();
		properties.clear();
		values.clear();
		group_names.clear();
		group_values.clear();
		log_names.clear();
		log_values.clear();
		try {
			BufferedReader in = new BufferedReader(new FileReader(CONFIG));
			String line;
			while( (line = in.readLine())!= null ){
				if(!line.contains("=")){
					properties.add(line);
					values.add(null);
				} 
				else{
					properties.add(line.substring(0, line.indexOf("=")));
					values.add(line.substring(line.indexOf("=")+1));
					if(line.startsWith("group_")){
						group_names.add(line.substring(0, line.indexOf("=")));
						group_values.add(line.substring(line.indexOf("=")+1));
					}
					else if(line.startsWith("log_")){
						log_names.add(line.substring(0, line.indexOf("=")));
						log_values.add(line.substring(line.indexOf("=")+1));
					}
				}
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void save(){
		if(!CONFIG.exists()){
			if(!new File("./Log/").exists())
				new File("./Log/").mkdir();
			addDefaultValues();
			try {
				CONFIG.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			PrintWriter out = new PrintWriter(CONFIG);
			for(int index = 0; index< properties.size(); index++){
				String property = properties.get(index);
				String value = values.get(index);
				if(value!=null)
					out.println(property+"="+value);
				else//Comments
					out.println(property);
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Transform a syntaxed string of events in a simple string of events
	 * e.g : 
	 * 1+5@12+49+&sample --> 1+5+6+7+8+9+10+11+12+49+59+60+61+70
	 * group_sample=59@61+70
	 * -----------------------
	 * It's quite easier for uses in other classes
	 */
	public String transformSimple(String events){
		String ids = "";
		while(events.startsWith("+"))
			events=events.substring(1);
		if(!events.contains("+")){
			String rep = events;
			if(rep.startsWith("&")){
				String group_name = "group_"+rep.substring(1);
				ids+="+"+transformSimple(group_values.get(group_names.indexOf(group_name)));
			}
			else if(rep.contains("@")){
				int min = Integer.parseInt(rep.substring(0, rep.indexOf("@")));
				int max = Integer.parseInt(rep.substring(rep.indexOf("@")+1));
				for(;min<max+1;min++){
					ids+="+"+min;
				}
			}
			else
				ids+="+"+rep;
			if(ids.startsWith("+"))
				ids=ids.substring(1);
			return ids;	
		}
		String[] sep = events.split("\\+");
		for(int x=0;x<sep.length;x++){
			String rep = sep[x];
			if(rep.startsWith("&")){
				String group_name = "group_"+rep.substring(1);
				ids+="+"+transformSimple(group_values.get(group_names.indexOf(group_name)));
			}
			else if(rep.contains("@")){
				int min = Integer.parseInt(rep.substring(0, rep.indexOf("@")));
				int max = Integer.parseInt(rep.substring(rep.indexOf("@")+1));
				for(;min<max+1;min++){
					ids+="+"+min;
				}
			}
			else
				ids+="+"+rep;
		}
		while(ids.startsWith("+"))
			ids=ids.substring(1);
		return ids;	
	}

}
