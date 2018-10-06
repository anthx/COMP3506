/**
 * 
 */
package comp3506.assn2.application;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import comp3506.assn2.utils.Occurence;
import comp3506.assn2.utils.Pair;
import comp3506.assn2.utils.Misc;

/**
 * @author anthony
 *
 */
public class FirstTests {

	/**
	 * Test method for {@link comp3506.assn2.application.AutoTester#AutoTester(java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws IllegalArgumentException 
	 * @throws FileNotFoundException 
	 */
	//@Test
	public void testAutoTesterOne() throws FileNotFoundException, IllegalArgumentException {
		AutoTester at = new AutoTester("files/shakespeare.txt", "files/shakespeare-index.txt", "files/stop-words.txt");
		for (String word : at.stopWords) {
			System.out.println(word);
		}
	}
	
	//@Test
	public void testAutoTesterTwo() throws FileNotFoundException, IllegalArgumentException {
		AutoTester at = new AutoTester("files/shakespeare.txt", "files/shakespeare-index.txt", "files/stop-words.txt");
		for (Pair section : at.sectionIndex) {
			System.out.print(section.getLeftValue());
			System.out.println(section.getRightValue());
		}
	}
	
//	@Test
	public void testLoad() throws FileNotFoundException, IllegalArgumentException {
		AutoTester at = new AutoTester("files/shakespeare.txt", "files/shakespeare-index.txt", "files/stop-words.txt");
		System.out.println("1");
	}
	
	//@Test
//	public void testOccurance() {
//		for (Occurence iterable_element : AutoTester.processLine("The Complete Works of William Shakespeare Called William", 0)) {
//			System.out.println(iterable_element);
//		}
//	}
	
	@Test
	public void testTrimWord() {
		String word = ".word,";
		String actual = comp3506.assn2.utils.Misc.trimNonLetters(word);
		assertEquals("word", actual);
	}
	
	@Test
	public void testTrimWord3() {
		String word = " .word, ";
		String actual = comp3506.assn2.utils.Misc.trimNonLetters(word);
		assertEquals("word", actual);
	}
	
	@Test
	public void testTrimWord4() {
		String word = " .word; ";
		String actual = comp3506.assn2.utils.Misc.trimNonLetters(word);
		assertEquals("word", actual);
	}
	
	@Test
	public void testTrimWord2() {
		String word = " increases,'";
		String actual = comp3506.assn2.utils.Misc.trimNonLetters(word);
		assertEquals("increases", actual);
	}

	@Test
	public void testTrimWord5() {
		String word = " increase, ";
		String actual = comp3506.assn2.utils.Misc.trimNonLetters(word);
		assertEquals("increase", actual);
	}
	
	@Test
	public void testTrimWordA() {
		String word = "a";
		String actual = comp3506.assn2.utils.Misc.trimNonLetters(word);
		assertEquals("a", actual);
	}
	
	@Test
	public void testTrimWordZ() {
		String word = "z";
		String actual = comp3506.assn2.utils.Misc.trimNonLetters(word);
		assertEquals("z", actual);
	}
	
//	@Test
	public void testTrimWordUppercase() {
		String word = "I'll";
		String actual = comp3506.assn2.utils.Misc.trimNonLetters(word);
		assertEquals("i'll", actual);
	}
}
