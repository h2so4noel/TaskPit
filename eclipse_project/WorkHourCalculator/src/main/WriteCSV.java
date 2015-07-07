package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class WriteCSV {
	Map<String, Integer> timeMap;
	private static String FILE_HEADER = "User,WorkingHour_SECOND,WorkingHour_MINUTE";
	private static String NEW_LINE = "\n";
	private static String COMMA = ",";
	private static String[] USERS = {"fg", "ht", "iu", 
		 							 "kt", "lu", "Mk", 
		 							 "nm", "si", "sk", 
		 							 "so", "std", "sty", "yg"};
	
	public WriteCSV(Map<String, Integer> timeMap){
		this.timeMap = timeMap;
	}
	
	public void write(String path, String date){
		try{
			FileWriter writer = new FileWriter(path);
			writer.append(FILE_HEADER);
			writer.append(NEW_LINE);
			for(int i = 0; i < USERS.length; i++){
				writer.append(USERS[i] + COMMA + timeMap.get(USERS[i]) + COMMA + (timeMap.get(USERS[i]) / 60));
				if(i == USERS.length - 1)
					continue;
				writer.append(NEW_LINE);
			}
			
			writer.flush();
			writer.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
