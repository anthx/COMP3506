package comp3506.assn2.utils;

public class Misc {

	public static String trimNonLetters(String word) {
		word = word + " ";
		// TODO maybe find a better way of fixing words without losing data.
		word = word.trim().toLowerCase().replaceAll("[^a-z]", "");
		// System.out.println(word);
		if (word.length() > 0) {
			try {
				int index = word.charAt(0) - 'a';
				if (index > 25 || index < 0) {
					word = word.substring(1);
				}
			} catch (Exception e) {
				// TODO: handle exception
//				System.out.println(word + " ");
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
