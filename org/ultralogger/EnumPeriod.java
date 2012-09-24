package org.ultralogger;

public enum EnumPeriod {
	DAY,WEEK,MONTH,NEVER;
	
	public String toString(){
		switch(this){
		case DAY:
			return "DAY";
		case WEEK:
			return"WEEK";
		case MONTH:
			return"MONTH";
		case NEVER:
			return "NEVER";
		}
		return null;
	}
	public static EnumPeriod fromString(String s){
		if(s.equalsIgnoreCase("DAY")){
			return DAY;
		}
		if(s.equalsIgnoreCase("WEEK")){
			return WEEK;
		}
		if(s.equalsIgnoreCase("MONTH")){
			return MONTH;
		}
		if(s.equalsIgnoreCase("NEVER")){
			return NEVER;
		}
		return null;
	}

}
