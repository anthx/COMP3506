package comp3506.assn2.utils;
/**
 * Helper class to help me store word occurences before dumping them to the data structure
 * @author anthony
 *
 */
public class Occurence {
	String word;
	int lineNumber;
	int startingColumn;
	
	public Occurence(String word, int lineNumber, int startingColumn) {
		this.word = word;
		this.startingColumn = startingColumn;
		this.lineNumber = lineNumber;
	}
	
	public String getWord() {
		return word;
	}
	
	public int getLineNumebr() {
		return lineNumber;
	}
	
	public int getStartingColumn() {
		return startingColumn;
	}
	
	public String toString() {
		return word + ":" + startingColumn;
	}
}
