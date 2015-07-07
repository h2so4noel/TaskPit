package drawer;

import java.util.ArrayList;
import java.util.List;

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
import com.googlecode.charts4j.Plot;
import com.googlecode.charts4j.Plots;

public class ChartDrawer {
	private static String[] KEYS = {"Other", "Database", "Editor", "Document", 
									"Word&Powerpoint", "Email", "Programming&Debug",
									"Browser", "FileOperation", "Testing", 
									"Excel", "TaskPit"};
	private static String[] USERS = {"fg", "ht", "iu", "kt", 
									 "lu", "Mk", "nm", "si", 
									 "sk", "so", "std", "sty", "yg"};
	
	private CSVSheet[] sheets;
	
	public ChartDrawer(CSVSheet ... sheets){
		this.sheets = sheets;
	}
	
	public String getTimeBarChartURL(){
		List<Data> dataList = new ArrayList<Data>();
		
		for(int i = 0; i < KEYS.length; i++){
			List<Double> temp = new ArrayList<Double>();
			for(int j = 0; j < sheets.length; j++)
				temp.add((double) sheets[j].getTime(KEYS[i]));
			Data tempData = DataUtil.scaleWithinRange(getMinTimes(), getMaxTimes() * 3.5, temp);
			dataList.add(tempData);
		}
		
		BarChart chart = GCharts.newBarChart(initTimeBarChartPlot(dataList));
        
		return initTimeBarChart(chart);
	}
	
	private String initTimeBarChart(BarChart chart){
		AxisStyle axisStyle = AxisStyle.newAxisStyle(Color.BLACK, 13, AxisTextAlignment.CENTER);
        AxisLabels time = AxisLabelsFactory.newAxisLabels("Minutes", 50.0);
        time.setAxisStyle(axisStyle);
        AxisLabels user = AxisLabelsFactory.newAxisLabels("User", 50.0);
        user.setAxisStyle(axisStyle);
		
        chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels(USERS));
        chart.addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(getMinTimes(), getMaxTimes() * 3.5));
        chart.addYAxisLabels(time);
        chart.addXAxisLabels(user);
        
        chart.setSize(650, 450);
        chart.setBarWidth(25);
        chart.setSpaceWithinGroupsOfBars(8);
        chart.setDataStacked(true);
        chart.setTitle("Time Spent on Tasks", Color.BLACK, 16);
        chart.setGrid(100, 10, 3, 2);
        //chart.setGrid(xAxisStepSize, yAxisStepSize, lengthOfLineSegment, lengthOfBlankSegment);
        
        return chart.toURLString();
	}
	
	private List<BarChartPlot> initTimeBarChartPlot(List<Data> dataList){
		List<BarChartPlot> plotList = new ArrayList<BarChartPlot>();
		List<Color> colorList = new ArrayList<Color>();
		colorList = fillColorList(colorList);
		
		for(int i = 0; i < 12; i++){
			BarChartPlot plot = Plots.newBarChartPlot(dataList.get(i), colorList.get(i), KEYS[i]);
			plotList.add(plot);
		}
		
		return plotList;
	}
	
	private List<Color> fillColorList(List<Color> colorList){
		colorList.add(Color.ORANGE);
		colorList.add(Color.ORANGERED);
		colorList.add(Color.LIMEGREEN);
		colorList.add(Color.BLACK);
		colorList.add(Color.BISQUE);
		colorList.add(Color.DARKSLATEBLUE);
		colorList.add(Color.RED);
		colorList.add(Color.GRAY);
		colorList.add(Color.GREENYELLOW);
		colorList.add(Color.DEEPSKYBLUE);
		colorList.add(Color.DARKBLUE);
		colorList.add(Color.DARKSALMON);
		
		return colorList;
	}
	
	private int getMax(){
		int max = 0;
		for(int i = 0; i < sheets.length; i++)
			for(int j = 0; j < KEYS.length; j++){
				List<Double> values = sheets[i].getValues(KEYS[j]);
				for(Double d : values)
					if(d >= max)
						max = d.intValue();
			}
		return max;
	}
	
	private int getMaxTimes(){
		int max = 0;
		for(int i = 0; i < sheets.length; i++)
			for (Double d : sheets[i].getTimes())
				if(d.intValue() >= max)
					max = d.intValue();
		return max;
	}
	
	private int getMin(){
		int min = 999999;
		for(int i = 0; i < sheets.length; i++)
			for(int j = 0; j < KEYS.length; j++){
				List<Double> values = sheets[i].getValues(KEYS[j]);
				for(Double d : values)
					if(d <= min && d != 0)
						min = d.intValue();
			}
		return min;
	}
	
	private int getMinTimes(){
		int min = 999999;
		for(int i = 0; i < sheets.length; i++)
			for (Double d : sheets[i].getTimes())
				if(d <= min && d != 0)
					min = d.intValue();
		return min;
	}
	
	public void setSheet(CSVSheet ... sheets){
		this.sheets = sheets;
	}
}
