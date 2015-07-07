import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Read the CSV file and translate to English. Also store total clicks and keystrokes.
 * Total clicks and keys pressed have getters to use outside of the class.
 * @author h2so4noel
 */
public class ReadCSV {
	
	List<String> lines = new ArrayList<String>();
	String[] columnName;

	String filePath = "";
	private static final String COMMA_SPLITTER = ",";
	
	/**
	 * Read up the CSV file from the path in parameter.
	 * @param path is the path thrown in to be read up.
	 */
	public ReadCSV(String path){
		this.filePath = path;
	}
	
	/**
	 * Inspect the CSV file, read, and translate from Japanese to English.
	 */
	public void run(){
		int temp = 0;
		String csvFile = filePath;
		BufferedReader br = null;
		String line = "";
		
		try{
			br = new BufferedReader(new FileReader(csvFile));
			while((line = br.readLine()) != null){
				String tempString = "";
				if(temp == 0){
					columnName = line.split(COMMA_SPLITTER);
					for(int i = 0; i < columnName.length; i++){
						//columnName[i] = translateColumnName(columnName[i]);
						//System.out.print(columnName[i] + " ");
						if(i == columnName.length - 1)
							tempString += columnName[i];
						else
							tempString += columnName[i] + ",";
					}
					temp++;
					continue;
				}
				String[] tempRows = line.split(COMMA_SPLITTER);
				for(int i = 0; i < tempRows.length; i++){
					//tempRows[i] = translate(tempRows[i]);
					//System.out.print(tempRows[i] + " ");
					if(i == tempRows.length - 1)
						tempString += tempRows[i];
					else
						tempString += tempRows[i] + ",";
				}
				//System.out.println();
				lines.add(tempString);
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		//printLines();
		//printTotalData();
		//printColumnName();
	}
	
	/**
	 * Translate the first row of the CSV file, which is the column name, into English.
	 * @param row is the first String row of the whole sheet.
	 * @return the translated row.
	 */
	private String translateColumnName(String row){
		row = row.replace("\"", "");
		row.trim();
		row = jpToEng(row);
		return row;
	}
	
	/**
	 * Translate the selected row from Japanese to English.
	 * @param row is the String of row to be translated.
	 * @return translated row.
	 */
	private String translate(String row){
		row = row.replace("\"", "");
		row = row.replace("　", "");
		if(row.length() > 15){
			// Time Format "2013年02月05日09時04分49秒"
			return row.substring(0, 4) + row.substring(5, 7) + row.substring(8, 10) + ":" + row.substring(11, 13) + row.substring(14, 16) + row.substring(17, 19);
		}
		else{
			row = jpToEng(row);
			return row;
		}
	}
	
	/**
	 * Translate from Japanese to English with fixed word and format.
	 * @param word is the word to be translated.
	 * @return translated word.
	 */
	private String jpToEng(String word){
		if(word.equals("登録外") || word.equals("マルチメディア") || word.equals("ゲーム") || word.equals("調査") || word.equals("デスクトップ") || word.equals("プレゼン編集") || word.equals("データ分析"))
			word = "Other";
		else if(word.equals("DB管理") || word.equals("ＳＱＬサーバ") || word.equals("SQLサーバ"))
			word = "Database";
		else if(word.equals("エディタ"))
			word = "Editor";
		else if(word.equals("文書閲覧") || word.equals("ドキュメント"))
			word = "Document";
		else if(word.equals("ワード") || word.equals("ワード・パワポ"))
			word = "Word&Powerpoint";
		else if(word.equals("メール"))
			word = "Email";
		else if(word.equals("プログラミング") || word.equals("デバッグ") || word.equals("プログラミング・デバッグ"))
			word = "Programming&Debug";
		else if(word.equals("ブラウザ") || word.equals("ネットワーク"))
			word = "Browser";
		else if(word.equals("ファイル操作"))
			word = "FileOperation";
		else if(word.equals("テスト"))
			word = "Testing";
		else if(word.equals("エクセル"))
			word = "Excel";

		else if(word.equals("タスク名"))
			word = "Task";
		else if(word.equals("開始時刻"))
			word = "StartTime";
		else if(word.equals("終了時刻"))
			word = "EndTime";
		else if(word.equals("左クリック"))
			word = "LeftClicks";
		else if(word.equals("右クリック"))
			word = "RightClicks";
		else if(word.equals("打鍵回数"))
			word = "Keystrokes";
		return word;
	}
	
	/**
	 * Print the first row of the CSV file, which is the column name of this sheet.
	 */
	public void printColumnName(){
		for(int i = 0; i < columnName.length; i++){
			if(i == columnName.length - 1){
				System.out.println(columnName[i]);
				break;
			}
			System.out.print(columnName[i] + ",");
		}
	}
	
	/**
	 * Print all lines in this CSV.
	 */
	public void printLines(){
		for(String e : lines)
			System.out.println(e);
	}
	
	public List<String> getLines(){
		return lines;
	}
	
	public void setLines(List<String> lines){
		this.lines = lines;
	}
	
	public String[] getColumnName(){
		return columnName;
	}
	
	public void setColumnName(String[] columnName){
		this.columnName = columnName;
	}
}