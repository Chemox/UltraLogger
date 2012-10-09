package org.ultraLogger.more;

import java.util.ArrayList;
import java.util.Iterator;

public class BlockPattern {
	
	/**
	 * The string that separate each blocks of the pattern
	 */
	public static final String BLOCK_SEPARATOR =" ";
	/**
	 * The string that separate the id and/or data value of the block and the quantity
	 */
	public static final String QUANTITY_SEPARATOR =".";
	/**
	 * The string that separate the id and the data value of the block
	 */
	public static final String DATA_VALUE_SEPARATOR =":";
	
	/**
	 * A list that contains all the ids and data values
	 */
	private ArrayList<Integer> ids = new ArrayList<Integer>();
	private ArrayList<Integer> datas = new ArrayList<Integer>();
	
	private String name = "pattern";
	
	/**
	 * Create a new empty pattern
	 * @param name is the name of this pattern
	 */
	public BlockPattern(String name){
		setName(name);
	}
	
	
	/**
	 * Create a pattern from the specified string
	 * @param name is the name of this pattern
	 * @param s is the string used
	 */
	public BlockPattern(String name,String s){
		setName(name);
		String[] blocks = s.split(BLOCK_SEPARATOR);//A line looks like that : 5.1 5:34.9 6:7.3
		for(int index=0;index<blocks.length;index++){
			String block = blocks[index];
			boolean dataValue=false;
			if(!block.contains(QUANTITY_SEPARATOR))//If there is no quantity, it's not a block
				continue;
			int quant_sep = block.indexOf(QUANTITY_SEPARATOR);
			int quantity = Integer.parseInt(block.substring(quant_sep+1));
			if(quantity<1)
				continue;
			if(block.contains(DATA_VALUE_SEPARATOR))
				dataValue=true;
			int end_id_index =quant_sep;
			if(dataValue)
				end_id_index=block.indexOf(DATA_VALUE_SEPARATOR);
			int id = Integer.parseInt(block.substring(0, end_id_index));
			int data_val =-1;
			if(dataValue)
				data_val = Integer.parseInt(block.substring(end_id_index+1, quant_sep));
			for(int w=0;w<quantity;w++){
				addBlock(id,data_val);
			}
		}
	}
	
	public void addBlock(int id,int dataValue){
		ids.add(id);
		datas.add(dataValue);
	}
	


	/**
	 * @return the name of this pattern
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPatternDef(){
		String s = name+"=";
		Iterator<Integer> i2 = datas.iterator();
		String last = "";
		int lastID =-1;
		int lastData = -1;
		for(Iterator<Integer> i = ids.iterator();i.hasNext();){
			int id = i.next();
			int data = i2.next();
			if(id==lastID&&data==lastData){
				int quantity = Integer.parseInt(last.substring(last.indexOf(QUANTITY_SEPARATOR)+1))+1;
				if(data<=-1){
					last=id+QUANTITY_SEPARATOR+quantity+" ";
				}
				else{
					last=id+DATA_VALUE_SEPARATOR+data+QUANTITY_SEPARATOR+quantity+" ";
				}
			}
			else{
				s+=last;
				lastID=id;
				lastData=data;
				if(data<=-1){
					last=id+QUANTITY_SEPARATOR+"1 ";
				}
				else{
					last=id+DATA_VALUE_SEPARATOR+data+QUANTITY_SEPARATOR+"1 ";
				}
			}
		}
		s+=last;
		return s;
	}
	
	
	private int index = 0;
	private boolean flag = false;
	
	public int size(){
		return ids.size();
	}
	public boolean hasNext(){
		return (index+1)<size();
	}
	public int nextID(){
		if(flag){
			flag=!flag;
			if(!hasNext())
				return -1;
			index++;
			return ids.get(index);
		}
		else{
			flag=!flag;
			return ids.get(index);
		}	
	}
	public int nextData_Value(){
		if(flag){
			flag=!flag;
			if(!hasNext())
				return -1;
			index++;
			return datas.get(index);
		}
		else{
			flag=!flag;
			return datas.get(index);
		}
	}

}
