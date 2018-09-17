package comp3506.assn2.application;

import comp3506.assn2.utils.Pair;

public class TrieNode {
	TrieNode[] children = new TrieNode[26];
	LinkedList<Pair> occurences;
	String value;
	private char character;
	
	boolean isEnd;
	
	
	public TrieNode(char c) {
		isEnd = false;
		character = c;
	}
	
	void addChild() {
		
	}
	
	public void isEnd() {
		isEnd = true;
		occurences = new LinkedList<Pair>();
	}
	
}
