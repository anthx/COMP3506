package comp3506.assn2.application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
				if (line.length() > 0) {
					listOfLines.add(line);
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

	@Override
	public int wordCount(String word) throws IllegalArgumentException {
		
		int result;
		if (theTrie.search(word) == null) {
			result = 0;
		} else {
			result = theTrie.search(word).size;
		}

		return result;
	}
	
	@Override
	public List<Pair<Integer, Integer>> prefixOccurrence(String prefix ) throws IllegalArgumentException {
		LinkedList<Pair<Integer, Integer>> result = theTrie.findPrefixes(prefix);
		List<Pair<Integer, Integer>> resultList = new ArrayList<>();
		while (result.hasNext()) {
			Pair<Integer, Integer> toAdd = result.next().getElement();
			resultList.add(toAdd);
		}
		return resultList;
	}

//	@Override
//	public List<Pair<Integer, Integer>> phraseOccurrence(String phrase) throws IllegalArgumentException {
//		// TODO Auto-generated method stub
//		
//	}
}
