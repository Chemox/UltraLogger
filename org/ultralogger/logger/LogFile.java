package org.ultralogger.logger;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.ultralogger.LoggerConfig;

public class LogFile {
	
	public static final String FOLDER = "./Log/";
	//Folder name formatter
	public static final DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
	//Time formatter (in logs)
	public static final DateFormat dateFormatter = DateFormat.getInstance();
	
	private File file;
	private BufferedWriter out;
	private boolean streamOpen = false;
	
	//We re-create a buffer
	private int count =0;
	
	private LoggerConfig config;
	private String date =null;
	private ArrayList<String> buffer = new ArrayList<String>();
	
	public LogFile(String filename, LoggerConfig config){
		String path = FOLDER;
		this.config=config;
	//Date Check
		Date date = config.lastCreation();
		Date now = new Date(System.currentTimeMillis());
		int diff = (int) ((now.getTime()-date.getTime())/(86400000));//86 400 000ms is the duration of a day in ms (1000*60*60*24)
		if(diff>config.getFolderDuration()){
		//We need to create a new file
			path+=formatter.format(now)+"/";
			this.date=now.getTime()+"";
			new File(path).mkdir();
		}else
			path+=formatter.format(date)+"/";
	//Now we have our file
		file = new File(path+filename+".log");
		if(!file.exists()){
			if(!new File(path).exists())
				new File(path).mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			open();
			return;
		}
	//Overwrite check
		if(config.willOverwrite()){
			file.delete();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			open();
		}
	//Lines check
		int lines = 0;
		try {
			lines = countLines(file.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		int maxLines = config.getMaxLines();
		if(maxLines!=0 && lines>maxLines){
		//We should delete lines : from the beginning to make lines fill 90% of Max Lines
			int startLine = (int) (lines-0.9*maxLines);	
			try {			
				ArrayList<String> buffer = new ArrayList<String>();
				int line = 0;
				BufferedReader in = new BufferedReader(new FileReader(file));// Our reader
				String s ;
				while((s = in.readLine())!=null){
					line++;
					if(line>startLine)
						buffer.add(s);
				}
				in.close();
			//Now we re-write
				out = new BufferedWriter(new FileWriter(file));
				for(Iterator<String> i = buffer.iterator();i.hasNext();out.append(i.next()+"\r\n"));
				out.flush();
				out.close();
				out = null;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		open();
	}
	
	public void log(String s){
		if(!streamOpen)
			return;
		String time = dateFormatter.format(new Date(System.currentTimeMillis()))+" ";
		buffer.add(time+s);
		count++;
		if(count==20){//Buffer of 20 lines
			count=0;
			for(Iterator<String> i = buffer.iterator();i.hasNext();){
				String msg = i.next();
				try {
					out.write(msg);
					out.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			buffer.clear();
		}
	}
	public void open(){
		streamOpen = true;
		try {
			out = new BufferedWriter(new FileWriter(file, !config.willOverwrite()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void close() {// We flush and we close the stream
		if(date!=null)
			config.created(date);
		try {
			for(Iterator<String> i = buffer.iterator();i.hasNext();){
				String msg = i.next();
				try {
					out.write(msg);
					out.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			buffer.clear();
			streamOpen=false;
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	//Static Method to count lines
	
	public int countLines(String filename) throws IOException {
	    InputStream is = new BufferedInputStream(new FileInputStream(filename));
	    try {
	        byte[] c = new byte[1024];
	        int count = 0;
	        int readChars = 0;
	        boolean endsWithoutNewLine = false;
	        while ((readChars = is.read(c)) != -1) {
	            for (int i = 0; i < readChars; ++i) {
	                if (c[i] == '\n')
	                    ++count;
	            }
	            endsWithoutNewLine = (c[readChars - 1] != '\n');
	        }
	        if(endsWithoutNewLine)
	            ++count;
	        return count;
	    } finally {
	        is.close();
	    }
	}
}
