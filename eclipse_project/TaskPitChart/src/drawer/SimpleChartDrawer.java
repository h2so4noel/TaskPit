package drawer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sheet.CSVSheet;

import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.AxisStyle;
import com.googlecode.charts4j.AxisTextAlignment;
import com.googlecode.charts4j.BarChart;
import com.googlecode.charts4j.BarChartPlot;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.DataUtil;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.Plots;

public class SimpleChartDrawer {
	
	/**
	 * #: Task
	 * 0: Other
	 * 1: Database
	 * 2: Editor
	 * 3: Document
	 * 4: Word&Powerpoint
	 * 5: Email
	 * 6: Programming&Debug
	 * 7: Browser
	 * 8: FileOperation
	 * 9: Testing
	 * 10: Excel
	 * 11: TaskPit
	 */
	private static String[] KEYS = {"Other", "Database", "Editor", "Document", "Word&Powerpoint", "Email", "Programming&Debug",
							"Browser", "FileOperation", "Testing", "Excel", "TaskPit"};
	private static String[] KEYS_REVERSED = {"TaskPit", "Excel", "Testing", "FileOperation", "Browser", "Programming&Debug",
									"Email", "Word&Powerpoint", "Document", "Editor", "Database", "Other"};
	
	private CSVSheet sheet;
	private Map<String, List<Double>> map = new HashMap<String, List<Double>>();
	
	private List<Double> times = new ArrayList<Double>();
	private List<Double> leftClicks = new ArrayList<Double>();
	private List<Double> rightClicks = new ArrayList<Double>();
	private List<Double> keystrokes = new ArrayList<Double>();
	
	private double min = 0;
	private double max = 0;
	
	private String barChartUrl = "";
	
	public SimpleChartDrawer(CSVSheet sheet){
		this.sheet = sheet;
		addElementValues();
		addTaskValues();
		getMax();
		getMin();
		printElements();
		printValues();
	}
	
	public String getBarChartUrl() {
		if(!barChartUrl.equals(""))
			return barChartUrl;
		
		Data keysData = DataUtil.scaleWithinRange(min, max + 200, keystrokes);
		
		BarChartPlot keysPlot = Plots.newBarChartPlot(keysData, Color.CADETBLUE);
		
		BarChart chart = GCharts.newBarChart(keysPlot);
		AxisStyle axisStyle = AxisStyle.newAxisStyle(Color.BLACK, 12, AxisTextAlignment.CENTER);
		
		//AxisLabels taskLabel = AxisLabelsFactory.newAxisLabels("Task", 50.0);
		//taskLabel.setAxisStyle(axisStyle);
		AxisLabels tasks = AxisLabelsFactory.newAxisLabels(KEYS_REVERSED);
		tasks.setAxisStyle(axisStyle);
		
		AxisLabels timeLabel = AxisLabelsFactory.newAxisLabels("Keystrokes", 50.0);
		timeLabel.setAxisStyle(axisStyle);
		AxisLabels times = AxisLabelsFactory.newNumericRangeAxisLabels(min, max);
		times.setAxisStyle(axisStyle);
		
		chart.addYAxisLabels(tasks);
		//chart.addYAxisLabels(taskLabel);
		chart.addXAxisLabels(times);
		chart.addXAxisLabels(timeLabel);
		chart.setHorizontal(true);
		chart.setSize(400, 370);
		chart.setSpaceBetweenGroupsOfBars(2);
		
		chart.setTitle("Sample Chart");
		chart.setGrid((50.0 / max) * 20, 600, 3, 2);
		
		barChartUrl = chart.toURLString();
		return barChartUrl;
	}
	
	private void addElementValues(){
		for(int i = 0; i < KEYS.length; i++)
			map.put(KEYS[i], sheet.getValues(KEYS[i]));
	}
	
	private void addTaskValues(){
		for(int i = 0; i < KEYS.length; i++){
			List<Double> tempList = map.get(KEYS[i]);
			times.add(tempList.get(0));
			leftClicks.add(tempList.get(1));
			rightClicks.add(tempList.get(2));
			keystrokes.add(tempList.get(3));
		}
	}
	
	private void getMax(){
		for(int i = 0; i < KEYS.length; i++){
			List<Double> tempList = map.get(KEYS[i]);
			for(double d : tempList)
				if(d >= max)
					max = d;
		}
	}
	
	private void getMin(){
		for(int i = 0; i < KEYS.length; i++){
			List<Double> tempList = map.get(KEYS[i]);
			for(double d : tempList)
				if(d <= min)
					min = d;
		}
	}
	
	private void printValues(){
		System.out.println("::::::::::::::TIME MINUTES");
		for(Double d : times){
			System.out.print(d.intValue() + " ");
		}
		System.out.println();
		
		System.out.println("::::::::::::::LEFT CLICKS");
		for(Double d : leftClicks){
			System.out.print(d.intValue() + " ");
		}
		System.out.println();
		
		System.out.println("::::::::::::::RIGHT CLICKS");
		for(Double d : rightClicks){
			System.out.print(d.intValue() + " ");
		}
		System.out.println();
		
		System.out.println("::::::::::::::KEYSTROKES");
		for(Double d : keystrokes){
			System.out.print(d.intValue() + " ");
		}
		System.out.println();
	}
	
	private void printElements(){
		for(int i = 0; i < KEYS.length; i++){
			System.out.println("=========" + KEYS[i]);
			for(Double d : map.get(KEYS[i])){
				int temp = d.intValue();
				System.out.print(temp + " ");
			}
			System.out.println();
		}
		System.out.println("::::::::::::::MAX");
		System.out.println((int) max);
		System.out.println("::::::::::::::MIN");
		System.out.println((int) min);
	}
}
