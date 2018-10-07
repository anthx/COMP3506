package comp3506.assn2.application;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

public class WordCountTests {
	AutoTester at;

//	@Test
	public void testWordCountShakespeare() throws FileNotFoundException, IllegalArgumentException {
		at = new AutoTester("files/shakespeare.txt", "files/shakespeare-index.txt", "files/stop-words.txt");
		assertEquals("Not right word count", 3, at.wordCount("Shakespeare"));
	}

//	@Test
	public void testWordCountThor() throws FileNotFoundException, IllegalArgumentException {
		at = new AutoTester("files/shakespeare.txt", "files/shakespeare-index.txt", "files/stop-words.txt");
		assertEquals("Not right word count", 0, at.wordCount("Thor"));
	}
	
	@Test
	public void testWordCountHerself() throws FileNotFoundException, IllegalArgumentException {
		at = new AutoTester("files/shakespeare.txt", "files/shakespeare-index.txt", "files/stop-words.txt");
		assertEquals("Not right word count", 113, at.wordCount("herself"));
	}
	
	@Test
	public void testWordCountAbundance() throws FileNotFoundException, IllegalArgumentException {
		at = new AutoTester("files/shakespeare.txt", "files/shakespeare-index.txt", "files/stop-words.txt");
		assertEquals("Not right word count", 14, at.wordCount("abundance"));
	}
	
	@Test
	public void testWordCountFinis() throws FileNotFoundException, IllegalArgumentException {
		at = new AutoTester("files/shakespeare.txt", "files/shakespeare-index.txt", "files/stop-words.txt");
		assertEquals("Not right word count", 2, at.wordCount("finis"));
	}
	
	@Test
	public void testWordCountTest() throws FileNotFoundException, IllegalArgumentException {
		AutoTester at = new AutoTester("files/test.txt", "files/shakespeare-index.txt", "files/stop-words.txt");
		assertEquals("Not right word count", 2, at.wordCount("b"));
	}
	
	@Test
	public void testWordCountTestAnthony() throws FileNotFoundException, IllegalArgumentException {
		AutoTester at = new AutoTester("files/test.txt", "files/shakespeare-index.txt", "files/stop-words.txt");
		assertEquals("Not right word count", 3, at.wordCount("anthony"));
	}
	
	@Test
	public void testWordCountTestAnthonix() throws FileNotFoundException, IllegalArgumentException {
		AutoTester at = new AutoTester("files/test.txt", "files/shakespeare-index.txt", "files/stop-words.txt");
		assertEquals("Not right word count", 1, at.wordCount("anthonix"));
	}
}
