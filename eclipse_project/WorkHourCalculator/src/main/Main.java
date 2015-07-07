package main;

import java.util.HashMap;
import java.util.Map;

public class Main {
	static String DEFAULT_FOLDER = "/Users/h2so4noel/Documents/TaskPit/materials/12_log_ENG/";
	private static String[] DATES = {"20130205", "20130206",
									 "20130207", "20130208",
									 "20130212", "20130213"};
	private static String[] USERS = {"fg", "ht", "iu", 
									 "kt", "lu", "Mk", 
									 "nm", "si", "sk", 
									 "so", "std", "sty", "yg"};
	private static Map<String, Integer> timeMap = new HashMap<String, Integer>();
	
	public static void main(String[] args) {
		for(int i = 0; i < DATES.length; i++){
			initTimeMap();
			for(int j = 0; j < USERS.length; j++){
				if(i == 0 && USERS[j] == "sty")
					continue;
				CSVSheet sheet = new CSVSheet(DEFAULT_FOLDER + DATES[i] + "/" + "TaskLog_" + USERS[j] + "_ENG.csv");
				System.out.println(">>>>>>>>now working: " + DATES[i] + " " + USERS[j]);
				sheet.handleTime();
				timeMap.put(USERS[j], sheet.getTotalTime());
			}
			String path = DEFAULT_FOLDER + DATES[i] + "/" + "WorkingHour_" + DATES[i] + "_ENG.csv";
			WriteCSV write = new WriteCSV(timeMap);
			write.write(path, DATES[i]);
		}
		printTimeMap();
	}
	
	private static void initTimeMap(){
		for(int i = 0; i < USERS.length; i++)
			timeMap.put(USERS[i], 0);
	}
	
	private static void printTimeMap(){
		for(int i = 0; i < USERS.length; i++)
			System.out.println(USERS[i] + " " + timeMap.get(USERS[i]) / 60);
	}

}
