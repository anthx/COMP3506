package comp3506.assn2.application;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;

import comp3506.assn2.utils.Pair;

public class PhraseOccurenceTests {

	@Test
	public void testPhraseOccurrenceThine() throws FileNotFoundException, IllegalArgumentException {
		AutoTester at = new AutoTester("files/shakespeare.txt", "files/shakespeare-index.txt", "files/stop-words.txt");
		List<Pair<Integer, Integer>> actual = at.phraseOccurrence("thine");
		
	}

}
