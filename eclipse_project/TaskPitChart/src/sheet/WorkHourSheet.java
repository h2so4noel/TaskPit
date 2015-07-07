package sheet;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class WorkHourSheet {
	List<String[]> allElements;
	public WorkHourSheet(String path){
		try{
			CSVReader reader = new CSVReader(new FileReader(path));
			allElements = reader.readAll();
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<String[]> getAllElements(){
		return allElements;
	}
	
	public List<String> getUsers(){
		List<String> users = new ArrayList<String>();
		for(String[] s : allElements){
			if(s[0].equals("User"))
				continue;
			users.add(s[0]);
		}
		return users;
	}
	
	public int getSecond(String user){
		for(String[] s : allElements)
			if(s[0].toLowerCase().equals(user.toLowerCase()))
				return Integer.parseInt(s[1]);
		return 9999999;
	}
	
	public int getMinute(String user){
		for(String[] s : allElements)
			if(s[0].toLowerCase().equals(user.toLowerCase()))
				return Integer.parseInt(s[2]);
		return 9999999;
	}
}
