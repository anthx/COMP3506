package comp3506.assn2.application;

import comp3506.assn2.utils.Pair;

public class Trie {

	TrieNode root = new TrieNode();

	public Trie() {

	}

	public void addWord(String word, int lineNumber, int character) {
		TrieNode checkOutNode = root;
		for (int i = 0; i < word.length(); i++) {
			int index = word.toLowerCase().charAt(i) - 'a';
			if (index > 26 || index < 0) {
				System.out.println("4");
			}
			if (checkOutNode.children[index] != null) {
				checkOutNode = checkOutNode.children[index];
			}
			else {
				checkOutNode.children[index] = new TrieNode();
			}
		}
		checkOutNode.isEnd = true;
		Pair<Integer, Integer> occurence = new Pair<Integer, Integer>(lineNumber, character);
		checkOutNode.isEnd();
		checkOutNode.occurences.append(occurence);
	}

	private TrieNode traverse(TrieNode node, char c) {
		TrieNode thisNode;
		TrieNode result = null;
		return result;
	}

	public LinkedList<Pair> search(String word) {
		LinkedList<Pair> result = null;
		TrieNode checkOutNode = root;
		for (int i = 0; i < word.length(); i++) {
			int index = word.charAt(i) - 'a';
			if (checkOutNode.children[index] != null) {
				checkOutNode = checkOutNode.children[index];
			} else {
				// Not found
				return null;
			}
		}
		if (checkOutNode.isEnd) {
			result = checkOutNode.occurences;
		}
		return result;
	}
}
