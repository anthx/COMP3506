/**
 * 
 */
package comp3506.assn2.application;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import comp3506.assn2.utils.Occurence;
import comp3506.assn2.utils.Pair;

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
	
	//@Test
	public void testLoad() throws FileNotFoundException, IllegalArgumentException {
		AutoTester at = new AutoTester("files/shakespeare.txt", "files/shakespeare-index.txt", "files/stop-words.txt");
	}
	
	@Test
	public void testOccurance() {
		for (Occurence iterable_element : AutoTester.processLine("The Complete Works of William Shakespeare Called William", 0)) {
			System.out.println(iterable_element);
		};
	}
}
