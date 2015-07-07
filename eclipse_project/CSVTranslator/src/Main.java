/**
 * Main class components. Initiate other class: ReadCSV and WriteCSV.
 * For class explanation please refer to each classes' javadoc.
 * @author h2so4noel
 */
public class Main {
	
	/**
	 * Main method for main class. This method initiates user and root folder name, read the CSV file
	 * and perform various operations including read, translate, pre-process, and write.
	 * @param args is not used.
	 */
	public static void main(String[] args) {
		String[] user = {	"fg", "ht", "iu", 
				 			"kt", "lu", "Mk", 
				 			"nm", "si", "sk", 
				 			"so", "std", "sty", "yg"};
		String root[] = {	"20130205", "20130206",
							"20130207", "20130208",
							"20130212", "20130213"};
		
		for(int j = 0; j < root.length; j++){
		//for(int j = 0; j < 1; j++){
			for(int i = 0; i < user.length; i++){
			//for(int i = 0; i < 1; i++){
				if(user[i] == "sty" && root[j] == "20130205")
					continue;
				
				ReadCSV tempCsv = new ReadCSV("/Users/h2so4noel/Documents/TaskPit/materials/12_log_ENG/" + root[j] + "/TaskLog_" + user[i] + "_ENG.csv");
				tempCsv.run();
				
				//System.out.println("READ >>> " + root[j] + " " + user[i]);
				
				PreProcessCSV csv = new PreProcessCSV(tempCsv);
				csv.process();
				ReadCSV newCsv = csv.createPreprocessedCSV();
				
				//System.out.println("PRE-PROCESS >>> " + root[j] + " " + user[i]);
				
				WriteCSV writeCsv = new WriteCSV(newCsv);
				writeCsv.write("/Users/h2so4noel/Documents/TaskPit/materials/12_log_ENG/" + root[j] + "/pre-processed/" + "/TaskLog_" + user[i] + "_ENG_processed.csv");
				
				System.out.println("WRITE >>> " + root[j] + " " + user[i]);
				//System.out.println("=================WRITE COMPLETED");
			}
		}
	}
		
}
