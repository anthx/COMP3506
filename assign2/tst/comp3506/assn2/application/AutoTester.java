package comp3506.assn2.application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import comp3506.assn2.utils.Occurence;
import comp3506.assn2.utils.Pair;
import comp3506.assn2.utils.Triple;
import comp3506.assn2.utils.Misc;

/**
 * Hook class used by automated testing tool. The testing tool will instantiate
 * an object of this class to test the functionality of your assignment. You
 * must implement the constructor stub below and override the methods from the
 * Search interface so that they call the necessary code in your application.
 * 
 * @author
 * @param <R>
 * @param <L>
 */
public class AutoTester implements Search {

	String[] stopWords = new String[500];
	Pair<String, Integer>[] sectionIndex = new Pair[100];
	Trie theTrie;
	LinkedList<String> listOfLines = new LinkedList<String>();

	/**
	 * Create an object that performs search operations on a document. If
	 * indexFileName or stopWordsFileName are null or an empty string the
	 * document should be loaded and all searches will be across the entire
	 * document with no stop words. All files are expected to be in the files
	 * sub-directory and file names are to include the relative path to the
	 * files (e.g. "files\\shakespeare.txt").
	 * 
	 * @param documentFileName
	 *            Name of the file containing the text of the document to be
	 *            searched.
	 * @param indexFileName
	 *            Name of the file containing the index of sections in the
	 *            document.
	 * @param stopWordsFileName
	 *            Name of the file containing the stop words ignored by most
	 *            searches.
	 * @throws FileNotFoundException
	 *             if any of the files cannot be loaded. The name of the file(s)
	 *             that could not be loaded should be passed to the
	 *             FileNotFoundException's constructor.
	 * @throws IllegalArgumentException
	 *             if documentFileName is null or an empty string.
	 */
	public AutoTester(String documentFileName, String indexFileName, String stopWordsFileName)
			throws FileNotFoundException, IllegalArgumentException {

		// Reads Stop Words
		try {
			FileReader reader = new FileReader(stopWordsFileName);
			BufferedReader bufferedReader = new BufferedReader(reader);

			String line;
			int i = 0;
			while ((line = bufferedReader.readLine()) != null) {
				stopWords[i] = line.trim();
				i++;
			}
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		// read index file
		try {
			FileReader reader = new FileReader(indexFileName);
			BufferedReader bufferedReader = new BufferedReader(reader);

			String line;
			int i = 0;
			while ((line = bufferedReader.readLine()) != null) {
				String[] linePair = line.trim().split(",");
				String leftValue = linePair[0];
				int rightValue = Integer.parseInt(linePair[1]);
				sectionIndex[i] = new Pair<String, Integer>(leftValue, rightValue);
				i++;
			}
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		// read actual text file
		try {
			FileReader reader = new FileReader(documentFileName);
			BufferedReader bufferedReader = new BufferedReader(reader);

			String line;
			int lineNumber = 1;
			theTrie = new Trie();
			while ((line = bufferedReader.readLine()) != null) {
				listOfLines.add(line);
				if (line.length() > 0) {
					Occurence[] wordsOnLine = Misc.processLine(line, lineNumber);
					if (wordsOnLine != null && wordsOnLine.length > 0) {
						for (Occurence word : wordsOnLine) {
							if (word != null) {
								theTrie.addWord(word.getWord(), lineNumber, word.getStartingColumn());
							}
						}
					}
				}

				lineNumber++;
			}
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Determines the number of times the word appears in the document.
	 * 
	 * @param word The word to be counted in the document.
	 * @return The number of occurrences of the word in the document.
	 * @throws IllegalArgumentException if word is null or an empty String.
	 */
	@Override
	public int wordCount(String word) throws IllegalArgumentException {
		if (word == null || word.length() == 0) {
			throw new IllegalArgumentException();
		}
		int result;
		if (theTrie.search(word) == null) {
			result = 0;
		} else {
			result = theTrie.search(word).size;
		}
		return result;
	}
	
	/**
	 * Finds all occurrences of the prefix in the document.
	 * A prefix is the start of a word. It can also be the complete word.
	 * For example, "obscure" would be a prefix for "obscure", "obscured", "obscures" and "obscurely".
	 * 
	 * @param prefix The prefix of a word that is to be found in the document.
	 * @return List of pairs, where each pair indicates the line and column number of each occurrence of the prefix.
	 *         Returns an empty list if the prefix is not found in the document.
	 * @throws IllegalArgumentException if prefix is null or an empty String.
	 */
	@Override
	public List<Pair<Integer, Integer>> prefixOccurrence(String prefix ) throws IllegalArgumentException {
		if (prefix == null || prefix.length() == 0) {
			throw new IllegalArgumentException();
		}
		LinkedList<Pair<Integer, Integer>> result = theTrie.findPrefixes(prefix);
		List<Pair<Integer, Integer>> resultList = new ArrayList<>();
		while (result.hasNext()) {
			Pair<Integer, Integer> toAdd = result.next().getElement();
			resultList.add(toAdd);
		}
		return resultList;
	}
	
	@Override
	/**
	 * Searches the document for lines that contain all the words in the 'words' parameter.
	 * Implements simple "and" logic when searching for the words.
	 * The words do not need to be contiguous on the line.
	 * O(L * W) L = number of lines, W = number of words - because a loop on lines and loop on word for each.
	 * @param words Array of words to find on a single line in the document.
	 * @return List of line numbers on which all the words appear in the document.
	 *         Returns an empty list if the words do not appear in any line in the document.
	 * @throws IllegalArgumentException if words is null or an empty array 
	 *                                  or any of the Strings in the array are null or empty.
	 */
	public List<Integer> wordsOnLine(String[] words) throws IllegalArgumentException {
		if (words == null || words.length == 0) {
			throw new IllegalArgumentException();
		}
		List<Integer> result = new ArrayList<Integer>();
		Integer lineNumber = 1;
		while (listOfLines.hasNext()) {
			String line = listOfLines.next().getElement().toLowerCase();
			boolean thisLineContainsAll = allWordsOnLine(words, line);
			if (thisLineContainsAll) {
				result.add(lineNumber);
			}
			lineNumber++;
		}
		listOfLines.reset();
		return result;
	}
	/**
	 * Checks if line contains all the words in the array passed in.
	 * O(W) where W = number of words in array.
	 * @param words to check
	 * @param line to check against
	 * @return
	 */
	private boolean allWordsOnLine(String[] words, String line) {
		for (String word : words) {
			if (word == null || word.length() == 0) {
				throw new IllegalArgumentException();
			}
			Pattern p = Pattern.compile(word);
			Matcher m = p.matcher(line);
			if (!m.find()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Searches the document for lines that contain any of the words in the 'words' parameter.
	 * Implements simple "or" logic when searching for the words.
	 * The words do not need to be contiguous on the line.
	 * O(L * W) L = number of lines, W = number of words - because a loop on words inside loop on word.
	 * @param words Array of words to find on a single line in the document.
	 * @return List of line numbers on which any of the words appear in the document.
	 *         Returns an empty list if none of the words appear in any line in the document.
	 * @throws IllegalArgumentException if words is null or an empty array 
	 *                                  or any of the Strings in the array are null or empty.
	 */
	@Override
	public List<Integer> someWordsOnLine(String[] words) throws IllegalArgumentException {
		if (words == null || words.length == 0) {
			throw new IllegalArgumentException();
		}
		List<Integer> result = new ArrayList<Integer>();
		Integer lineNumber = 1;
		while (listOfLines.hasNext()) {
			String line = listOfLines.next().getElement().toLowerCase();
			boolean thisLineContainsSome = someWordsChecker(words, line);
			if (thisLineContainsSome) {
				result.add(lineNumber);
			}
			lineNumber++;
		}
		listOfLines.reset();
		return result;
	}

	/**
	 * Checks if line contains some of the words
	 * O(W) where W = number of words in array.
	 * @param words
	 * @param line
	 * @param thisLineContainsSome
	 * @return
	 */
	private boolean someWordsChecker(String[] words, String line) {
		for (String word : words) {
			if (word == null || word.length() == 0) {
				throw new IllegalArgumentException();
			}
			Pattern p = Pattern.compile(word);
			Matcher m = p.matcher(line);
			if (m.find()) {
				return true;
			}
		}
		return false;
	}

}
