package comp3506.assn2.application;

import comp3506.assn2.utils.Pair;

public class TrieNode {
	TrieNode[] children = new TrieNode[26];
	LinkedList<Pair> occurences;
	String value;
	
	boolean isEnd;
	
	
	public TrieNode() {
		isEnd = false;
	}
	
	void addChild() {
		
	}
	
	public void isEnd() {
		isEnd = true;
		occurences = new LinkedList<Pair>();
	}
	
}
