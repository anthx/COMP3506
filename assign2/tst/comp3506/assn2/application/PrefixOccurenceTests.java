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
		} catch (FileNotFoundException | IllegalArgumentException e) {
			System.out.println("Opening files failed!");
			e.printStackTrace();
		}
	}

	@Test()
	public void testPrefixOccurrence() {
		List<TestingPair<Integer, Integer>> expected = new ArrayList<>();
		expected.addAll(
				Arrays.asList(new TestingPair<>(5, 1), new TestingPair<>(5, 6), new TestingPair<>(5, 12), new TestingPair<>(5, 19),
						new TestingPair<>(5, 25), new TestingPair<>(5, 36), new TestingPair<>(5, 42),
						new TestingPair<>(6, 1), new TestingPair<>(6, 8),
						new TestingPair<>(7, 30),
						new TestingPair<>(9, 6)));// thereby
		List<TestingPair<Integer, Integer>> searchResult = makeTestingPair(
				searchApplication.prefixOccurrence("the"));
		assertThat("Locations of 'the' prefix were not expected.", searchResult,
				containsInAnyOrder(expected.toArray()));
		assertThat("Search for 'the' returned wrong number of results.", searchResult, hasSize(expected.size()));
	}

	@Test()
	public void testPrefixOccurrenceAnt() {
		List<TestingPair<Integer, Integer>> expected = new ArrayList<>();
		expected.addAll(
				Arrays.asList(new TestingPair<>(4, 1), new TestingPair<>(4, 6), new TestingPair<>(4, 14),
						new TestingPair<>(5, 3), new TestingPair<>(5,11), new TestingPair<>(5, 20)));// ant, anthony
		List<TestingPair<Integer, Integer>> searchResult = makeTestingPair(
				searchApplication.prefixOccurrence("ant"));
		assertThat("Locations of 'ant' prefix were not expected.", searchResult,
				containsInAnyOrder(expected.toArray()));
		assertThat("Search for 'the' returned wrong number of results.", searchResult, hasSize(expected.size()));
	}
}
