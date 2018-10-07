package comp3506.assn2.application;

import comp3506.assn2.utils.Pair;

public class TrieNode {
	TrieNode[] children = new TrieNode[26];
	LinkedList<Pair<Integer, Integer>> occurences;
	String value;
	char character;
	boolean hasChildren;
	
	boolean isEnd;
	
	
	public TrieNode(char c) {
		isEnd = false;
		character = c;
		hasChildren = false;
	}
	
	public TrieNode() {
		hasChildren = false;
		isEnd = false;
	}

	void addChild() {
		
	}
	
	public void isEnd() {
		isEnd = true;
	}
	
}
