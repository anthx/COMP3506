package comp3506.assn2.application;

import comp3506.assn2.utils.Pair;

public class Trie {

	TrieNode root = new TrieNode();

	public Trie() {

	}
	/**
	 * adds a new word to the Trie. If word is already in Trie, only add its position
	 * Time Complexity: O(n) (including a worst case constant for creating new objects - where n is the letters in the word. 
	 * Worst case where no prefix of that word has been added so need to add all new objects.
	 * already. Because it loops over each letter in the word. Doesn't care about alphabet size like in lecture notes because this
	 * implementation uses a fixed size array to store the child nodes and indexes into them directly. 
	 * @param word the word to add
	 * @param lineNumber the line number where it was found
	 * @param characterPosition the column number where it was found
	 * 
	 */
	public void addWord(String word, int lineNumber, int characterPosition) {
		TrieNode checkOutNode = root;

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

	/**
	 * Returns the TrieNode whose path forms the word given
	 * Time Complexity: O(n) where n is number of letters. 
	 * 
	 * @param word word to search for
	 * @return the end node
	 */
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
	/**
	 * Recursively finds children's occurrences of nodes that are also words.
	 * Time: O(n*M) where n is the length of suffixes and M is the number of children of this Node.
	 * @param node Node: starting node
	 * @param resultSet 
	 * @return
	 */
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
	/**
	 * Finds words and the words that the word given is a prefix of.
	 * O(n + (N *M)) basically time complexity of findNode() + traverseChildren() because it just uses those two.
	 * @param word
	 * @return
	 */
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
	
	/**
	 * Searches for a word given and returns the occurrences. 
	 * Time Complexity: O(n + n) where n is number of letters plus trimNonLetters (regex blackbox). 
	 * @param word
	 * @return
	 */
	public LinkedList<Pair<Integer, Integer>> search(String word) {
		LinkedList<Pair<Integer, Integer>> result = null;
		word = comp3506.assn2.utils.Misc.trimNonLetters(word);
		TrieNode checkOutNode = findNode(word);
		result = checkOutNode.occurences;
		return result;
	}
}

//Reference
// basic Trie structure and search and add from Geeks For Geeks. Modified for Java, this assignment, and to make more sense to me. 
//V. Rao Sanaka, "Trie | (Insert and Search)", Geeks For Geeks. [Online]. Available: https://www.geeksforgeeks.org/trie-insert-and-search/. [Accessed: 15- Sep- 2018].