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
}