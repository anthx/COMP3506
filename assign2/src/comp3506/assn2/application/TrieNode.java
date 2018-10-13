package comp3506.assn2.application;

import comp3506.assn2.utils.Pair;

/**
 * Space Complexity: O(2 bits + 1 char + array of Node pointers)
 * @author anthony
 *
 */
public class TrieNode {
	TrieNode[] children = new TrieNode[26];
	LinkedList<Pair<Integer, Integer>> occurences;
	String value;
	char character;
	boolean hasChildren;
	
	boolean isEnd;
	
	/**
	 * Constructor
	 * @param c character it represents to help debugging.
	 */
	public TrieNode(char c) {
		isEnd = false;
		character = c;
		hasChildren = false;
	}
	
	/**
	 * Constructor
	 */
	public TrieNode() {
		hasChildren = false;
		isEnd = false;
	}
	
	public void isEnd() {
		isEnd = true;
	}
	
}
