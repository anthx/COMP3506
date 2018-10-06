package comp3506.assn2.application;

import java.util.ArrayList;
import java.util.List;

import comp3506.assn2.utils.Misc;
import comp3506.assn2.utils.Occurence;
import comp3506.assn2.utils.Pair;
import comp3506.assn2.application.AutoTester;

public class PhraseOccurence {
	
	private LinkedList<String> listOfLines;

	public PhraseOccurence(LinkedList<String> listOfLines) {
		this.listOfLines = listOfLines;
	}
	
	List<Pair<Integer, Integer>> search(String phrase) {
		List<Pair<Integer, Integer>> result = new ArrayList<Pair<Integer, Integer>>();

		String[] words = phrase.trim().split(" ");
		LinkedList[] wordsResults = new LinkedList[words.length];
		int occurencesOfLeastFrequentWordIndex = 0;
		int occurencesOfLeastFrequentWord = 0;
		for (int j = 0; j < words.length; j++) {
			String word = words[j];
//			if (AutoTester.goodToAddWord(word)) {
//				word = comp3506.assn2.utils.Misc.trimNonLetters(word);
//				wordsResults[j] = theTrie.search(word);
//				if (wordsResults[j] == null) {
//					return new ArrayList<Pair<Integer, Integer>>();
//				}
//			}
			// find which word has least occurrences
			if (j == 0) {
				occurencesOfLeastFrequentWord = wordsResults[j].size;

			} else {
				if (wordsResults[j].size < occurencesOfLeastFrequentWordIndex) {
					occurencesOfLeastFrequentWord = wordsResults[j].size;
					occurencesOfLeastFrequentWordIndex = j;
				}
			}
		}

		// loop over all lines
		int lineNumber = 1;
		while (listOfLines.hasNext()) {
			String thisLine = (String) listOfLines.next().getElement();
			
			if (thisLine.contains(words[occurencesOfLeastFrequentWordIndex])) {
//				System.out.println(thisLine);
				// each line, check the phrase exists
				Occurence[] wordsOnLine = Misc.processLine(thisLine, lineNumber);
				
				//For each instance of the first word in the phrase, on the line:
				for (int wolIndex = 0; wolIndex < wordsOnLine.length; wolIndex++) {
					System.out.println(wolIndex);
					Occurence word = wordsOnLine[wolIndex];
					System.out.println(words[0]);
					System.out.println(word.getWord());
					if (word.getWord() == words[0]) {
						for (int j = 1; j < words.length; j++) {
							if (wordsOnLine[wolIndex+1].getWord() != words[j+1]) {
								break;
							}
						}
						//checked the whole phrase, matches
						Pair<Integer, Integer> thisResult = new Pair<Integer, Integer>(lineNumber, word.getStartingColumn());
						result.add(thisResult);
					}
				}
//				for (int j = 0; j < words.length; j++) {
//					String wordPhrase = wordsOnLine[j].getWord();
//					// TODO Right here we need to check the work only contains
//					// alpha numeric characters
//					wordPhrase = comp3506.assn2.utils.Misc.trimNonLetters(wordPhrase);
//					if (j == 0) {
//						phrasestartingColumn += wordPhrase.length() + 1;
//					}
//					
//
//				}
			}
			lineNumber++;
		}
		return result;
	}
}
