package comp3506.assn2.application;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import comp3506.assn2.utils.Pair;
import comp3506.assn2.utils.TestingPair;

public class PrefixOccurenceTests {

	private static Search searchApplication;
	private static Search searchApplicationThe;

	/**
	 * @param data
	 *            The list of Pairs to be converted to a list of TestingPairs.
	 */
	private List<TestingPair<Integer, Integer>> makeTestingPair(List<Pair<Integer, Integer>> data) {
		List<TestingPair<Integer, Integer>> result = new ArrayList<>();
		for (Pair<Integer, Integer> pair : data) {
			result.add(new TestingPair<Integer, Integer>(pair));
		}
		return result;
	}

	@BeforeClass
	public static void openFiles() {
		try {
			searchApplication = new AutoTester("files/test.txt", "files/shakespeare-index.txt", "files/stop-words.txt");
			searchApplicationThe = new AutoTester("files/testThe.txt", "files/shakespeare-index.txt", "files/stop-words.txt");
		} catch (FileNotFoundException | IllegalArgumentException e) {
			System.out.println("Opening files failed!");
			e.printStackTrace();
		}
	}
	
	

	@Test()
	public void testPrefixOccurrence() {
		List<TestingPair<Integer, Integer>> expected = new ArrayList<>();
		expected.addAll(
				Arrays.asList(new TestingPair<>(1, 1), new TestingPair<>(1, 5), new TestingPair<>(1, 10), new TestingPair<>(1, 16), new TestingPair<>(1, 22), new TestingPair<>(1, 28),
						new TestingPair<>(2, 1), new TestingPair<>(2, 7),//theme theory
						new TestingPair<>(3, 30), //the
						new TestingPair<>(5, 6), new TestingPair<>(5, 46)));// thereby The
		List<TestingPair<Integer, Integer>> searchResult = makeTestingPair(searchApplicationThe.prefixOccurrence("the"));
		assertThat("Locations of 'the' prefix were not expected.", searchResult,
				containsInAnyOrder(expected.toArray()));
		assertThat("Search for 'the' returned wrong number of results.", searchResult, hasSize(expected.size()));
	}

	@Test()
	public void testPrefixOccurrenceAnt() {
		List<TestingPair<Integer, Integer>> expected = new ArrayList<>();
		expected.addAll(
				Arrays.asList(new TestingPair<>(4, 1), new TestingPair<>(4, 6),
						new TestingPair<>(5, 3), new TestingPair<>(5,11), new TestingPair<>(5, 20)));// ant, anthony
		List<TestingPair<Integer, Integer>> searchResult = makeTestingPair(searchApplication.prefixOccurrence("ant"));
		assertThat("Locations of 'ant' prefix were not expected.", searchResult,containsInAnyOrder(expected.toArray()));
		assertThat("Search for 'ant' returned wrong number of results.", searchResult, hasSize(expected.size()));
	}
}
