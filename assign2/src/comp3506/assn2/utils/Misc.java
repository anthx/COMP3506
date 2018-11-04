package comp3506.assn2.utils;

public class Misc {
	/**
	 * trims non letters
	 * O(L) where L = length of word. Regex black box which should be slower for a longer string.
	 * @param word
	 * @return
	 */
	public static String trimNonLetters(String word) {
		word = word.trim().toLowerCase().replaceAll("[^a-z]", "");
		return word;
	}

	/**
	 * Finds column occurrences of each word on the line.
	 * O(W) where W = words in line. Plus some constants which get collapsed. 
	 * @param line
	 * @param lineNumber
	 * @return
	 */
	public static Occurence[] processLine(String line, int lineNumber) {
		String[] words = line.toLowerCase().split("[ -]");
		Occurence[] result = new Occurence[words.length];
		int startingColumn = 0;
		int startingSearchColumn = 0;
		for (int j = 0; j < words.length; j++) {
			String word = words[j];

			String TrimmedWword = comp3506.assn2.utils.Misc.trimNonLetters(word);
			startingColumn = line.toLowerCase().indexOf(TrimmedWword, startingSearchColumn);
			
			Occurence occurence = new Occurence(TrimmedWword, lineNumber, startingColumn + 1);
			
			startingSearchColumn =+ word.length() + startingColumn;

			result[j] = occurence;

		}
		return result;
	}

	static boolean goodToAddWord(String word) {
		word = comp3506.assn2.utils.Misc.trimNonLetters(word);
		if (word.length() < 1) {
			return false;
		}
		for (char character : word.toCharArray()) {
			if (!Character.isLetter(character)) {
				return false;
			}
		}

		return true;
	}
}
