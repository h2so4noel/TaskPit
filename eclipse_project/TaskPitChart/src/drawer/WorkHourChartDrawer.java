package drawer;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.DataUtil;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.LineChart;
import com.googlecode.charts4j.Plot;
import com.googlecode.charts4j.Plots;
import com.googlecode.charts4j.Shape;

import sheet.WorkHourSheet;

public class WorkHourChartDrawer {
	WorkHourSheet sheet;
	public WorkHourChartDrawer(WorkHourSheet sheet){
		this.sheet = sheet;
	}
	
	public String getLineChartURL(){
		List<Integer> workMinutes = getWorkMinuteList();
		
		Data data = DataUtil.scaleWithinRange(0, 1000, workMinutes);
		
		Plot plot = Plots.newPlot(data);
		plot.addShapeMarkers(Shape.CROSS, Color.BLUE, 5);
		LineChart chart = GCharts.newLineChart(plot);
		chart.setGrid(100, 10, 2, 2);
		chart.setSize(500, 450);
		chart.setTitle("User Workhour (Minute)");
		chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels(sheet.getUsers()));
		chart.addYAxisLabels(AxisLabelsFactory.newNumericAxisLabels(0, 500, 1000));
		return chart.toURLString();
	}
	
	private List<Integer> getWorkMinuteList(){
		List<Integer> workMinutes = new ArrayList<Integer>();
		List<String[]> allElements = sheet.getAllElements();
		for(int i = 0; i < allElements.size(); i++){
			if(i == 0)
				continue;
			workMinutes.add(Integer.parseInt(allElements.get(i)[2]));
		}
		return workMinutes;
	}
}
