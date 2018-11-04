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
	
	/**
	 * Getter for the word
	 * O(1) it's a getter
	 * @return
	 */
	public String getWord() {
		return word;
	}
	
	/**
	 * Getter for the line number
	 * O(1) it's a getter
	 * @return
	 */
	public int getLineNumebr() {
		return lineNumber;
	}
	
	/**
	 * Getter for column
	 * O(1) it's a getter
	 * @return
	 */
	public int getStartingColumn() {
		return startingColumn;
	}
	
	/**
	 * To String for debugging
	 * O(1) just concatenating strings....
	 * @return
	 */
	public String toString() {
		return word + ":" + lineNumber + ":" + startingColumn;
	}
}
