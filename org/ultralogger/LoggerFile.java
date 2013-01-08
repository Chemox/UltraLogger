package org.ultralogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class LoggerFile {
	
	private BufferedWriter out;

	/**We create a new file
	 * 
	 * @param log
	 * @param is
	 * @param max
	 * @param yes
	 */
	public LoggerFile(File log, boolean is, int max,boolean yes) {
		if(yes){//Create a new folder
			DateFormat formatter =new SimpleDateFormat("dd-MMM-yy");//Current time
			String time =formatter.format(new Date(System.currentTimeMillis()));
			String path  =log.getAbsolutePath();
			String name =log.getName();
			log = new File(path.substring(0, path.lastIndexOf(name))+time+"/"+name);
			File folder = new File(path.substring(0, path.lastIndexOf(name))+time+"/");
			if(!folder.exists())
				folder.mkdir();
			if(!log.exists()){
				try {
					log.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else if (max>0){
				try {
					int ten = max/10;
					ArrayList<String> last = new ArrayList<String>();
					int lines = 0;
					BufferedReader r = new BufferedReader(new FileReader(log));
					String s= r.readLine();
					while(s!=null){
						last.add(s);
						lines++;
						if(last.size()>ten){
							last.remove(last.size()-1);
						}
						s=r.readLine();
					}
					r.close();
					if(lines>max){
						out = new BufferedWriter(new FileWriter(log));
						for(Iterator<String> i = last.iterator();i.hasNext();out.append(i.next()));
						out.flush();
						out.close();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			out = new BufferedWriter(new FileWriter(log,is));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**We retake our last file
	 * @param log
	 * @param is
	 * @param max
	 * @param last
	 */
	public LoggerFile(File log, boolean is, int max,Date last) {
		DateFormat formatter =new SimpleDateFormat("dd-MMM-yy");//Format de la date
		String time =formatter.format(last);//Date --> String
		String path  =log.getAbsolutePath();// Log path
		String name =log.getName();// Log file name
		log = new File(path.substring(0, path.lastIndexOf(name))+time+"/"+name);// The new log path
		File folder = new File(path.substring(0, path.lastIndexOf(name))+time+"/");// The folder path
		if(!folder.exists())//We create the folder
			folder.mkdir();
		if(!log.exists()){//We create the log now
			try {
				log.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if (max>0){//If we have already a file and we write at the end
			try {
				int ten = max/10;// 10% of the max lines
				ArrayList<String> lastr = new ArrayList<String>();// Our text line per line
				int lines = 0;
				BufferedReader r = new BufferedReader(new FileReader(log));// Our reader
				String s= r.readLine();
				while(s!=null){
					lastr.add(s);
					lines++;
					if(lastr.size()>ten)
						lastr.remove(lastr.size()-1);
					s=r.readLine();
				}
				r.close();
				if(lines>max){//If more than 10% of the lines ( so always true ^^ )
					out = new BufferedWriter(new FileWriter(log));
					for(Iterator<String> i = lastr.iterator();i.hasNext();out.append(i.next()));
					out.flush();
					out.close();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			out = new BufferedWriter(new FileWriter(log,is));// We create a writer that will write or not ( @is ) at the end of our file
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private int count =0;
	public static DateFormat d = DateFormat.getInstance();
	private ArrayList<String> msgs = new ArrayList<String>();
	
	public void log(String s){
		String time = d.format(new Date(System.currentTimeMillis()))+" ";
		msgs.add(time+s);
		count++;
		if(count==20){//Au bout de 20 logs on les écrits
			count=0;
			for(Iterator<String> i = msgs.iterator();i.hasNext();){
				String r = i.next();
				try {
					out.write(r);
					out.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			msgs.clear();
		}
	}

	public void close() {// On flush puis on ferme le stream
		try {
			for(Iterator<String> i = msgs.iterator();i.hasNext();){
				String r = i.next();
				try {
					out.write(r);
					out.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			msgs.clear();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
