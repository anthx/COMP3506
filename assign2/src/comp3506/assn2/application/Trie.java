package comp3506.assn2.application;

import comp3506.assn2.utils.Pair;

public class Trie {

	TrieNode root = new TrieNode();

	public Trie() {

	}
	/**
	 * adds a new word to the Trie. If word is already in Trie, only add its position
	 * Time Complexity: O(n) - where n is the letters in the word. Worst case where no prefix of that word has been added 
	 * already. Because it loops over each letter in the word.
	 * @param word the word to add
	 * @param lineNumber the line number where it was found
	 * @param characterPosition the column number where it was found
	 * 
	 */
	public void addWord(String word, int lineNumber, int characterPosition) {
		TrieNode checkOutNode = root;
		word = word.replace("'", "");
		for (int i = 0; i < word.length(); i++) {
			int index = word.toLowerCase().charAt(i) - 'a';

			if (checkOutNode.children[index] != null) {
				checkOutNode = checkOutNode.children[index];
			}
			else {
				checkOutNode.children[index] = new TrieNode(word.toLowerCase().charAt(i));
				checkOutNode.hasChildren = true;
				checkOutNode = checkOutNode.children[index];
			}
		}

		Pair<Integer, Integer> occurence = new Pair<Integer, Integer>(lineNumber, characterPosition);
		checkOutNode.isEnd();

		if (checkOutNode.occurences == null) {
			checkOutNode.occurences = new LinkedList<>();
		}
		checkOutNode.occurences.add(occurence);
	}

	TrieNode findNode(String word) {
		TrieNode result;
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
		result = checkOutNode;
		return result;
	}
	
	TrieNode traverseChildren(TrieNode node, LinkedList<Pair<Integer, Integer>> resultSet) {
		if (!node.hasChildren) {
			resultSet.append(node.occurences);
		}
		else {
			for (TrieNode child : node.children) {
				if (child != null) {
					traverseChildren(child, resultSet);
				}
			}
		}
		return null;
	}
	
	public LinkedList<Pair<Integer, Integer>> findPrefixes(String word) {
		LinkedList<Pair<Integer, Integer>> result = new LinkedList<>();
		TrieNode prefix = findNode(word);
		if (prefix == null) {
			return new LinkedList<>();
		}
		result.append(prefix.occurences);
		traverseChildren(prefix, result);
		return result;
	}
	
	public LinkedList<Pair<Integer, Integer>> search(String word) {
		LinkedList<Pair<Integer, Integer>> result = null;
		word = comp3506.assn2.utils.Misc.trimNonLetters(word);
		TrieNode checkOutNode = findNode(word);
		result = checkOutNode.occurences;
		return result;
	}
}

//Reference
// basic Trie structure and search and add from Geeks For Geeks. Modified for Java and to make more sense to me. 
//V. Rao Sanaka, "Trie | (Insert and Search)", Geeks For Geeks. [Online]. Available: https://www.geeksforgeeks.org/trie-insert-and-search/. [Accessed: 15- Sep- 2018].