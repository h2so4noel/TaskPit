/**
 * A simple class to handle the Task name and values.
 * @author h2so4noel
 */
public class Task {
	
	private int count = 0;
	private int time = 0;
	private int lClick = 0;
	private int rClick = 0;
	private int keystrokes = 0;
	
	/**
	 * Constructor, nothing to initiate.
	 */
	public Task(){
	}
	
	/**
	 * Print everything.
	 * Seriously, everything.
	 */
	public void printValues(){
		System.out.println("Count: " + count);
		System.out.println("Time: " + time);
		System.out.println("L_Clicks: " + lClick);
		System.out.println("R_Clicks: " + rClick);
		System.out.println("Keystrokes: " + keystrokes);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getlClick() {
		return lClick;
	}

	public void setlClick(int lClick) {
		this.lClick = lClick;
	}

	public int getrClick() {
		return rClick;
	}

	public void setrClick(int rClick) {
		this.rClick = rClick;
	}

	public int getKeystrokes() {
		return keystrokes;
	}

	public void setKeystrokes(int keystrokes) {
		this.keystrokes = keystrokes;
	}
}
