package comp3506.assn2.application;

import comp3506.assn2.utils.Pair;

public class Trie {

	TrieNode root = new TrieNode('a');

	public Trie() {

	}

	public void addWord(String word, int lineNumber, int characterPosition) {
		TrieNode checkOutNode = root;
		word = word.replace("'", "");
		for (int i = 0; i < word.length(); i++) {
			int index = word.toLowerCase().charAt(i) - 'a';
//			System.out.println(word);
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
//			System.out.println("before append");
			resultSet.append(node.occurences);
//			System.out.println("after append");
		}
		else {
			for (TrieNode child : node.children) {
				if (child != null) {
//					System.out.println(child.character);
					traverseChildren(child, resultSet);
				}
			}
			
		}
		return null;
	}
	
	public LinkedList<Pair<Integer, Integer>> findPrefixes(String word) {
		LinkedList<Pair<Integer, Integer>> result = new LinkedList<>();
		TrieNode prefix = findNode(word);
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

//V. Rao Sanaka, "Trie | (Insert and Search)", Geeks For Geeks. [Online]. Available: https://www.geeksforgeeks.org/trie-insert-and-search/. [Accessed: 15- Sep- 2018].