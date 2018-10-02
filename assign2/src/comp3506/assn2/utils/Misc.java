package comp3506.assn2.utils;

public class Misc {

	public static String trimNonLetters(String word) {
		word = word + " ";
		word = word.trim().toLowerCase();
//		System.out.println(word);
		if (word.length() > 0) {
			try {
				int index = word.charAt(0) - 'a';
				if (index > 25 || index < 0) {
					word = word.substring(1);
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(word + " ");
			}
		}

		if (word.length() > 1) {
			int indexEnd = word.charAt(word.length() - 1) - 'a';
			// System.out.println(indexEnd);
			if (indexEnd > 25 || indexEnd < 0) {
				// System.out.println(word.charAt(word.length() - 1));
				word = word.substring(0, word.length() - 1);
				// System.out.println(word.charAt(word.length() - 1));
			}
		}
		return word;
	}
	
	public static Occurence[] processLine(String line, int lineNumber) {
		String[] words = line.trim().split(" ");
		Occurence[] result = new Occurence[words.length];
		int startingColumn = 1;
		for (int j = 0; j < words.length; j++) {
			String word = words[j];
			// TODO Right here we need to check the work only contains alpha
			// numeric characters and is not in Stop Words.
			if (goodToAddWord(word)) {

				String TrimmedWword = comp3506.assn2.utils.Misc.trimNonLetters(word);
				// if (true) {
				// System.out.println(word);
				// }
				Occurence occurence = new Occurence(TrimmedWword, lineNumber, startingColumn);
				// if (word)) {
				// System.out.println(word);
				// }
				result[j] = occurence;
			}
			startingColumn += word.length() + 1;

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
		// if (stopWords.length > 0 || stopWords != null) {
		// for (String stopWord : stopWords) {
		// if (word == stopWord) {
		// return false;
		// }
		// }
		// }
		return true;
	}
}
