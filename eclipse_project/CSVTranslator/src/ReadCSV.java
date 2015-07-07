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
		row = row.replace("�@", "");
		if(row.length() > 15){
			// Time Format "2013�N02��05��09��04��49�b"
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
		if(word.equals("�o�^�O") || word.equals("�}���`���f�B�A") || word.equals("�Q�[��") || word.equals("����") || word.equals("�f�X�N�g�b�v") || word.equals("�v���[���ҏW") || word.equals("�f�[�^����"))
			word = "Other";
		else if(word.equals("DB�Ǘ�") || word.equals("�r�p�k�T�[�o") || word.equals("SQL�T�[�o"))
			word = "Database";
		else if(word.equals("�G�f�B�^"))
			word = "Editor";
		else if(word.equals("�����{��") || word.equals("�h�L�������g"))
			word = "Document";
		else if(word.equals("���[�h") || word.equals("���[�h�E�p���|"))
			word = "Word&Powerpoint";
		else if(word.equals("���[��"))
			word = "Email";
		else if(word.equals("�v���O���~���O") || word.equals("�f�o�b�O") || word.equals("�v���O���~���O�E�f�o�b�O"))
			word = "Programming&Debug";
		else if(word.equals("�u���E�U") || word.equals("�l�b�g���[�N"))
			word = "Browser";
		else if(word.equals("�t�@�C������"))
			word = "FileOperation";
		else if(word.equals("�e�X�g"))
			word = "Testing";
		else if(word.equals("�G�N�Z��"))
			word = "Excel";

		else if(word.equals("�^�X�N��"))
			word = "Task";
		else if(word.equals("�J�n����"))
			word = "StartTime";
		else if(word.equals("�I������"))
			word = "EndTime";
		else if(word.equals("���N���b�N"))
			word = "LeftClicks";
		else if(word.equals("�E�N���b�N"))
			word = "RightClicks";
		else if(word.equals("�Ō���"))
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