package org.ultralogger.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class CommandManager {
	
public static final File file = new File("./Log/silent_commands.txt");
	
	private ArrayList<String> aliases = new ArrayList<String>();
	private String comments = "#Define here the commands you want UL not to log.  e.g : \r\nsample\r\n# this will not log any commands starting by /sample";
	
	public CommandManager(){
		load();
	}

	private void load() {
		if(!file.exists())
			save();
		aliases.clear();
		comments = "";
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String line = "";
		while( line != null){
			if(line.startsWith("#"))
				comments+="\n"+line;
			else if(line.length()>0)
				aliases.add(line);
			try {
				line = in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void save() {
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		PrintWriter out = null;
		try {
			out = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		out.println(comments);
		for(Iterator<String> i = aliases.iterator(); i.hasNext(); out.println(i.next()));
		out.close();
	}
	
	public boolean canLogCommand(String cmd){
		String msg = cmd+" ";
		while(msg .contains(" ")){
			msg = msg.substring(0, msg.lastIndexOf(" "));
			if(aliases.contains(msg))
				return false;
		}
		return true;
	}

}