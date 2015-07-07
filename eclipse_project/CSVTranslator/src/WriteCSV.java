import java.io.FileWriter;
import java.io.IOException;

/**
 * Write a new CSV with newly thrown in data and rows.
 * Require an existing ReadCSV class before writing a real file.
 * @author h2so4noel
 */
public class WriteCSV {
	String[] columnName;
	String fileHeader = "";
	ReadCSV csv;
	private static final String NEW_LINE = "\n";
	
	/**
	 * Constructor that receive parameter as a ReadCSV class.
	 * @param csv is that ReadCSV class to be write into a new file.
	 */
	public WriteCSV(ReadCSV csv){
		this.columnName = csv.columnName;
		this.csv = csv;
	}
	
	/**
	 * Write a new .CSV file to the path in parameter.
	 * Requires a file path in String format.
	 * @param filePath
	 */
	public void write(String filePath){
		createHeader();
		try{
			FileWriter writer = new FileWriter(filePath);
			writer.append(fileHeader);
			writer.append(NEW_LINE);
			int iter = 0;
			for(String e : csv.getLines()){
				//System.out.println(e);
				writer.append(e);
				if(iter == csv.getLines().size() - 1)
					continue;
				writer.append(NEW_LINE);
				iter++;
			}
			
			writer.flush();
			writer.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the column name of the file.
	 */
	private void createHeader(){
		for(int i = 0; i < columnName.length; i++){
			if(i == columnName.length - 1)
				fileHeader += columnName[i];
			else
				fileHeader += columnName[i] + ",";
		}
		//System.out.println(fileHeader);
	}
	
	public String[] getColumnName(){
		return columnName;
	}
}
