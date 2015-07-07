package main;

import java.io.FileReader;
import java.util.List;

import com.opencsv.CSVReader;

public class CSVSheet {
	private List<String[]> allElements;
	int totalSecond = 0;
	int totalMinute = 0;
	int totalHour = 0;
	int totalTime = 0;
	
	public CSVSheet(String path){
		try{
			CSVReader reader = new CSVReader(new FileReader(path));
			allElements = reader.readAll();
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void handleTime(){
		int initDate = 0;
		int initHour = 0;
		int initMinute = 0;
		int initSecond = 0;
		int initTime = 0;
		
		int finalHour = 0;
		int finalMinute = 0;
		int finalSecond = 0;
		int finalTime = 0;
		
		for(int i = 0; i < allElements.size(); i++){
			if(allElements.get(i)[0].equals("Task"))
				continue;
			
			//YYYYMMDD:HHMMSS
			String startDate = allElements.get(i)[1];
			String endDate = allElements.get(i)[2];
			
			if(i == 1){
				initDate = Integer.parseInt(startDate.substring(0, 8));
				initHour = Integer.parseInt(startDate.substring(9, 11));
				initMinute = Integer.parseInt(startDate.substring(11, 13));
				initSecond = Integer.parseInt(startDate.substring(13, 15));
				initTime = initSecond + initMinute * 60 + initHour * 3600;
			}
			
			int sHour = Integer.parseInt(startDate.substring(9, 11));
			int sMinute = Integer.parseInt(startDate.substring(11, 13));
			int sSecond = Integer.parseInt(startDate.substring(13, 15));
			int sDate = Integer.parseInt(startDate.substring(0, 8));
			int sTime = sSecond + sMinute * 60 + sHour * 3600;
			
			if(sDate != initDate && i != 1){
				totalTime += Math.abs(finalTime - initTime);
				int temp = totalTime;
				
				totalSecond += temp % 60;
				temp = temp / 60;
				totalMinute += temp % 60;
				temp = temp / 60;
				totalHour += temp;
				
				initDate = sDate;
				initHour = sHour;
				initMinute = sMinute;
				initSecond = sSecond;
				initTime = sTime;
			}
			
			finalHour = Integer.parseInt(endDate.substring(9, 11));
			finalMinute = Integer.parseInt(endDate.substring(11, 13));
			finalSecond = Integer.parseInt(endDate.substring(13, 15));
			finalTime = finalSecond + finalMinute * 60 + finalHour * 3600;
			
			if(i == allElements.size() - 1){
				totalTime += Math.abs(finalTime - initTime);
				int temp = totalTime;
				
				totalSecond += temp % 60;
				temp = temp / 60;
				totalMinute += temp % 60;
				temp = temp / 60;
				totalHour += temp;
			}
		}

		System.out.println("Approx. Working Time (Minute): " + totalTime / 60);
	}
	
	public int getTotalTime(){
		return this.totalTime;
	}
	
	public List<String[]> getAllElements(){
		return allElements;
	}
}
