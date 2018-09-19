package comp3506.assn2.application;

import comp3506.assn2.utils.Pair;

public class Trie {

	TrieNode root = new TrieNode('a');

	public Trie() {

	}

	public void addWord(String word, int lineNumber, int characterPosition) {
		TrieNode checkOutNode = root;
		for (int i = 0; i < word.length(); i++) {
			int index = word.toLowerCase().charAt(i) - 'a';
//			System.out.println(word);
			if (checkOutNode.children[index] != null) {
				checkOutNode = checkOutNode.children[index];
			}
			else {
				checkOutNode.children[index] = new TrieNode(word.toLowerCase().charAt(i));
				checkOutNode = checkOutNode.children[index];
			}
		}

		Pair<Integer, Integer> occurence = new Pair<Integer, Integer>(lineNumber, characterPosition);
		checkOutNode.isEnd();

		if (checkOutNode.occurences == null) {
			checkOutNode.occurences = new LinkedList<>();
		}
		checkOutNode.occurences.append(occurence);
	}

	public LinkedList<Pair> search(String word) {
		LinkedList<Pair> result = null;
		word = comp3506.assn2.utils.Misc.trimNonLetters(word);
		TrieNode checkOutNode = root;
		for (int i = 0; i < word.length(); i++) {
			int index = word.toLowerCase().charAt(i) - 'a';
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
