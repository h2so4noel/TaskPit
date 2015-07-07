package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import drawer.ChartDrawer;
import drawer.WorkHourChartDrawer;
import sheet.CSVSheet;
import sheet.WorkHourSheet;
import ui.SwingUI;

public class Main {
	static String DEFAULT_FOLDER = "/Users/h2so4noel/Documents/TaskPit/materials/12_log_ENG/";
	private static String[] USERS = {"fg", "ht", "iu", 
									 "kt", "lu", "Mk", 
									 "nm", "si", "sk", 
									 "so", "std", "sty", "yg"};
	private static String[] DATES = {"20130205", "20130206",
		 							 "20130207", "20130208",
		 							 "20130212", "20130213"};
	private static Map<String, List<CSVSheet>> sheetMap = new HashMap<String, List<CSVSheet>>();
	
	public static void main(String[] args) {
		initSheetMap();
		
		CSVSheet[] sheetArray1 = sheetMap.get(DATES[0]).toArray(new CSVSheet[sheetMap.get(DATES[0]).size()]);
		
		WorkHourSheet workHourSheet;
		workHourSheet = new WorkHourSheet(DEFAULT_FOLDER + DATES[0] + "/WorkingHour_" + DATES[0] + "_ENG.csv");
		
		ChartDrawer drawer = new ChartDrawer(sheetArray1);
		WorkHourChartDrawer workHourDrawer = new WorkHourChartDrawer(workHourSheet);
		
		SwingUI ui = new SwingUI();
		ui.setURL(drawer.getTimeBarChartURL());
		ui.addChart(workHourDrawer.getLineChartURL());
		ui.run();
	}
	
	private static void initSheetMap(){
		for(int j = 0; j < DATES.length; j++){
			List<CSVSheet> sheetList = new ArrayList<CSVSheet>();
			for(int i = 0; i < USERS.length; i++){
				if(USERS[i].equals("sty") && j == 0)
					continue;
				CSVSheet tempSheet;
				tempSheet = new CSVSheet(DEFAULT_FOLDER + DATES[j] +"/" + "pre-processed/TaskLog_" + USERS[i] + 
						"_ENG_processed.csv");
				sheetList.add(tempSheet);
			}
			sheetMap.put(DATES[j], sheetList);
		}
	}
}
