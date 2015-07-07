package sheet;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class CSVSheet {
	private List<String[]> allElements;
	
	public CSVSheet(String path){
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
	
	public List<String> getElements(String s){
		List<String> temp = new ArrayList<String>();
		for(String[] e : allElements)
			if(e[0].toLowerCase().equals(s.toLowerCase()))
				for(int i = 1; i < e.length; i++)
					temp.add(e[i]);
		return null;
	}
	
	public void printAllElements(){
		for(String[] e : allElements){
			for(int i = 0; i < e.length; i++)
				System.out.print(e[i] + " ");
			System.out.println();
		}
	}
	
	public List<Double> getValues(String s){
		List<Double> temp = new ArrayList<Double>();
		for(String[] e : allElements)
			if(e[0].toLowerCase().equals(s.toLowerCase()))
				for(int i = 1; i < e.length; i++)
					temp.add(Double.parseDouble(e[i]));
		return temp;
	}
	
	public int getTime(String s){
		for(String[] e : allElements)
			if(e[0].toLowerCase().equals(s.toLowerCase()))
				return Integer.parseInt(e[1]);
		return 999999;
	}
	
	public List<Double> getTimes(){
		List<Double> temp = new ArrayList<Double>();
		for(String[] e : allElements){
			if(e[0].equals("Task"))
				continue;
			temp.add(Double.parseDouble(e[1]));
		}
		return temp;
	}
	
	public int getLeftClick(String s){
		for(String[] e : allElements)
			if(e[0].toLowerCase().equals(s.toLowerCase()))
				return Integer.parseInt(e[2]);
		return 999999;
	}
	
	public List<Double> getLeftClicks(){
		List<Double> temp = new ArrayList<Double>();
		for(String[] e : allElements)
			temp.add((double) Integer.parseInt(e[2]));
		return temp;
	}
	
	public int getRightClick(String s){
		for(String[] e : allElements)
			if(e[0].toLowerCase().equals(s.toLowerCase()))
				return Integer.parseInt(e[3]);
		return 999999;
	}
	
	public List<Double> getRightClicks(){
		List<Double> temp = new ArrayList<Double>();
		for(String[] e : allElements)
			temp.add((double) Integer.parseInt(e[3]));
		return temp;
	}
	
	public int getKeystroke(String s){
		for(String[] e : allElements)
			if(e[0].toLowerCase().equals(s.toLowerCase()))
				return Integer.parseInt(e[4]);
		return 999999;
	}
	
	public List<Double> getKeystrokes(){
		List<Double> temp = new ArrayList<Double>();
		for(String[] e : allElements)
			temp.add((double) Integer.parseInt(e[4]));
		return temp;
	}
}
