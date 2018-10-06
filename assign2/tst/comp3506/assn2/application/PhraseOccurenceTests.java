package comp3506.assn2.application;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import comp3506.assn2.utils.Pair;

public class PhraseOccurenceTests {

//	@Test
	public void testPhraseOccurrenceAbundance() throws FileNotFoundException, IllegalArgumentException {
		AutoTester at = new AutoTester("files/shakespeare.txt", "files/shakespeare-index.txt", "files/stop-words.txt");
		List<Pair<Integer, Integer>> actual = at.phraseOccurrence("abundance");
		
		Pair<Integer, Integer> a = new Pair<Integer, Integer>(17, 23);
		Pair<Integer, Integer> b = new Pair<Integer, Integer>(410, 18);
		Pair<Integer, Integer> c = new Pair<Integer, Integer>(669, 15);
		Pair<Integer, Integer> d = new Pair<Integer, Integer>(2431, 8);
		Pair<Integer, Integer> e = new Pair<Integer, Integer>(2825, 5);
		Pair<Integer, Integer> f = new Pair<Integer, Integer>(14881, 38);
		Pair<Integer, Integer> g = new Pair<Integer, Integer>(15826, 8);
		Pair<Integer, Integer> h = new Pair<Integer, Integer>(30644, 1);
		Pair<Integer, Integer> i = new Pair<Integer, Integer>(34751, 5);
		Pair<Integer, Integer> j = new Pair<Integer, Integer>(36884, 15);
		Pair<Integer, Integer> k = new Pair<Integer, Integer>(55500, 15);
		Pair<Integer, Integer> l = new Pair<Integer, Integer>(77493, 12);
		Pair<Integer, Integer> m = new Pair<Integer, Integer>(95607, 39);
		Pair<Integer, Integer> n = new Pair<Integer, Integer>(117651, 37);
		
		List<Pair<Integer, Integer>> expected = new ArrayList<Pair<Integer, Integer>>(14);
		
		expected.add(a);
		expected.add(b);
		expected.add(c);
		expected.add(d);
		expected.add(e);
		expected.add(f);
		expected.add(g);
		expected.add(h);
		expected.add(i);
		expected.add(j);
		expected.add(k);
		expected.add(l);
		expected.add(m);
		expected.add(n);
		
		assertEquals(expected, actual);
	}

//	@Test
	public void testPhraseOccurrenceTheQuestion() throws FileNotFoundException, IllegalArgumentException {
		AutoTester at = new AutoTester("files/test.txt", "files/shakespeare-index.txt", "files/stop-words.txt");
		List<Pair<Integer, Integer>> actual = at.phraseOccurrence("to be or not to be");
		
//		Pair<Integer, Integer> a = new Pair<Integer, Integer>(25779, 1);
		Pair<Integer, Integer> a = new Pair<Integer, Integer>(6, 1);
		
		List<Pair<Integer, Integer>> expected = new ArrayList<Pair<Integer, Integer>>(14);
		
		expected.add(a);
		
		assertEquals(expected, actual);
	}
	
//	@Test
	public void testPhraseOccurrenceNotTheQuestion() throws FileNotFoundException, IllegalArgumentException {
		AutoTester at = new AutoTester("files/test.txt", "files/shakespeare-index.txt", "files/stop-words.txt");
		List<Pair<Integer, Integer>> actual = at.phraseOccurrence("this question is not found");
		
//		Pair<Integer, Integer> a = new Pair<Integer, Integer>(25779, 1);
		Pair<Integer, Integer> a = new Pair<Integer, Integer>(0, 0);
		
		List<Pair<Integer, Integer>> expected = new ArrayList<Pair<Integer, Integer>>(0);
		
		assertEquals(expected, actual);
	}
	
//	@Test
	public void testPhraseOccurrenceOneWord() throws FileNotFoundException, IllegalArgumentException {
		AutoTester at = new AutoTester("files/test.txt", "files/shakespeare-index.txt", "files/stop-words.txt");
		List<Pair<Integer, Integer>> actual = at.phraseOccurrence("there");
		
		Pair<Integer, Integer> a = new Pair<Integer, Integer>(4, 31);
		
		List<Pair<Integer, Integer>> expected = new ArrayList<Pair<Integer, Integer>>(0);
		expected.add(a);
		
		assertEquals(expected, actual);
	}
}
