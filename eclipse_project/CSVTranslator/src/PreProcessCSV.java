import java.util.List;
import java.util.ArrayList;

/**
 * Class to handles pre-processing data from datalog obtained by CSV file format.
 * @author h2so4noel
 */
public class PreProcessCSV {
	
	private ReadCSV csv;
	
	private int totalHour = 0;
	private int totalMin = 0;
	private int totalSec = 0;
	
	private int totalTime = 0;
	
	private int defection = 0;
	private String defectionString = "";
	
	private Task other;
	private Task database;
	private Task editor;
	private Task document;
	private Task word;
	private Task email;
	private Task program;
	private Task browser;
	private Task file;
	private Task test;
	private Task excel;
	private Task taskpit;
	
	static final String COMMA_SPLITTER = ",";
	
	/**
	 * Constructor class that initiates all attributes from given ReadCSV format.
	 * @param csv is the csv to be used to obtain attributes here.
	 */
	public PreProcessCSV(ReadCSV csv){
		this.csv = csv;
		other = new Task();
		database = new Task();
		editor = new Task();
		document = new Task();
		word = new Task();
		email = new Task();
		program = new Task();
		browser = new Task();
		file = new Task();
		test = new Task();
		excel = new Task();
		taskpit = new Task();
	}
	
	/**
	 * Begin the pre-processing method using the CSV files imported from constructor.
	 * Iterate through the String in the List.
	 */
	public void process(){
		for(String e: csv.lines){
			String[] temp = e.split(",");
			checkTask(temp);
			System.out.println("Hour:   " + totalHour);
			System.out.println("Min:    " + totalMin);
			System.out.println("Second: " + totalSec);
			System.out.println("Time:   " + totalTime);
		}
	}
	
	/**
	 * This method will create a new CSV file after preprocessing is completed.
	 * Return the new CSV file after pre-processed
	 * @return temp as a ReadCSV file.
	 */
	public ReadCSV createPreprocessedCSV(){
		ReadCSV temp = this.csv;
		List<String> lines = new ArrayList<String>();
		String[] columnName = {"Task", "Time_MINUTE", "LeftClick", "RightClick","KeyStrokes"};
		String[] taskName = {"Other", "Database", "Editor", "Document", "Word&Powerpoint", "Email", "Programming&Debug",
							"Browser", "FileOperation", "Testing", "Excel", "TaskPit"};
		temp.setColumnName(columnName);
		for(int i = 0; i < taskName.length; i++){
			String line = "";
			line += taskName[i] + COMMA_SPLITTER;
			line += (getTask(taskName[i]).getTime() / 60) + COMMA_SPLITTER;
			line += getTask(taskName[i]).getlClick() + COMMA_SPLITTER;
			line += getTask(taskName[i]).getrClick() + COMMA_SPLITTER;
			line += getTask(taskName[i]).getKeystrokes();
			lines.add(line);
		}
		temp.setLines(lines);
		temp.setColumnName(columnName);
		return temp;
	}
	
	/**
	 * Get a specific tasks by throwing in a String.
	 * @param task is the String name of the task.
	 * @return Task of the corresponding name.
	 */
	private Task getTask(String task){
		if(task.equals("Other"))
			return other;
		else if(task.equals("Database"))
			return database;
		else if(task.equals("Editor"))
			return editor;
		else if(task.equals("Document"))
			return document;
		else if(task.equals("Word&Powerpoint"))
			return word;
		else if(task.equals("Email"))
			return email;
		else if(task.equals("Programming&Debug"))
			return program;
		else if(task.equals("Browser"))
			return browser;
		else if(task.equals("FileOperation"))
			return file;
		else if(task.equals("Testing"))
			return test;
		else if(task.equals("Excel"))
			return excel;
		else if(task.equals("TaskPit"))
			return taskpit;
		else
			return null;
	}
	
	/**
	 * Check the name of the thrown array of String.
	 * If the task is corresponding to the name existed, handle that task by invoking another method.
	 * If the task is not existed, add up to the defection count and String.
	 * @param task is the task to be checked.
	 */
	private void checkTask(String[] task){
		if(task[0].equals("Other"))
			handleTask(other, task);
		else if(task[0].equals("Database"))
			handleTask(database, task);
		else if(task[0].equals("Editor"))
			handleTask(editor, task);
		else if(task[0].equals("Document"))
			handleTask(document, task);
		else if(task[0].equals("Word&Powerpoint"))
			handleTask(word, task);
		else if(task[0].equals("Email"))
			handleTask(email, task);
		else if(task[0].equals("Programming&Debug"))
			handleTask(program, task);
		else if(task[0].equals("Browser"))
			handleTask(browser, task);
		else if(task[0].equals("FileOperation"))
			handleTask(file, task);
		else if(task[0].equals("Testing"))
			handleTask(test, task);
		else if(task[0].equals("Excel"))
			handleTask(excel, task);
		else if(task[0].equals("TaskPit"))
			handleTask(taskpit, task);
		else{
			defectionString += task[0];
			defection++;
		}
	}
	
	/**
	 * Handle the thrown in task by adding up counts, clicks, keystrokes and time.
	 * The time is handled by invoking another method.
	 * @param task is the task to be handle.
	 * @param value is the value of that task to be added up in summation.
	 */
	private void handleTask(Task task, String[] value){
		task.setCount(task.getCount() + 1);
		task.setlClick(task.getlClick() + Integer.parseInt(value[3]));
		handleTime(task, value);
		task.setrClick(task.getrClick() + Integer.parseInt(value[4]));
		task.setKeystrokes(task.getKeystrokes() + Integer.parseInt(value[5]));
	}
	
	/**
	 * Handle the time of the task. Substring the hour, minute, and second and sum up individually.
	 * @param task is the task to handle the time.
	 * @param value is the value to be added.
	 */
	private void handleTime(Task task, String[] value){
		//YYYYMMDD:HHMMSS
		int sec1 = Integer.parseInt(value[1].substring(13, 15));
		int min1 = Integer.parseInt(value[1].substring(11, 13));
		int hour1 = Integer.parseInt(value[1].substring(9, 11));
		int time1 = sec1 + (min1 * 60) + (hour1 * 3600);
		
		int sec2 = Integer.parseInt(value[2].substring(13, 15));
		int min2 = Integer.parseInt(value[2].substring(11, 13));
		int hour2 = Integer.parseInt(value[2].substring(9, 11));
		int time2 = sec2 + (min2* 60) + (hour2 * 3600);
		
		int timeDif = Math.abs(time2 - time1);
		
		int secDif = timeDif % 60;
		timeDif = timeDif / 60;
		int minDif = timeDif % 60;
		timeDif = timeDif / 60;
		int hourDif = timeDif;
		
		this.totalSec += secDif;
		this.totalMin += minDif;
		this.totalHour += hourDif;
		this.totalTime += (secDif) + minDif * 60 + (hourDif * 3600);
		
		int totalSec = (secDif) + minDif * 60 + (hourDif * 3600);
		totalSec = totalSec + task.getTime();
		
		task.setTime(totalSec);
	}
	
	/**
	 * Print all the summation of values in the CSV file.
	 */
	private void printValues(){
		System.out.println("######OTHER");
		other.printValues();
		System.out.println("######DATABASE");
		database.printValues();
		System.out.println("######EDITOR");
		editor.printValues();
		System.out.println("######DOCUMENT");
		document.printValues();
		System.out.println("######WORD&POWERPOINT");
		word.printValues();
		System.out.println("######EMAIL");
		email.printValues();
		System.out.println("######PROGRAMMING&DEBUG");
		program.printValues();
		System.out.println("######BROWSER");
		browser.printValues();
		System.out.println("######FILE_OPERATION");
		file.printValues();
		System.out.println("######TESTING");
		test.printValues();
		System.out.println("######EXCEL");
		excel.printValues();
		System.out.println("######TASKPIT");
		taskpit.printValues();
		System.out.println("######DEFECTION_DETECTED");
		System.out.println(defection);
		System.out.println(defectionString);
	}
	
	public int getTotalime(){
		return totalTime;
	}
	
	public int getTotalHour(){
		return totalHour;
	}
	
	public int getTotalMinute(){
		return totalMin;
	}
	
	public int getTotalSecond(){
		return totalSec;
	}
}
