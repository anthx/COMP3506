package comp3506.assn2.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProcessLines {

	@Test
	public void simpleLine() {
		Occurence[] actual = Misc.processLine("That Rome holds of his name; wherein obscurely", 1);
		Occurence romeExpected = new Occurence("rome", 1, 6);
		Occurence whereinExpected = new Occurence("wherein", 1, 30);
		assertEquals(romeExpected.startingColumn, actual[1].startingColumn);
		assertEquals(romeExpected.word, actual[1].word);
		
		assertEquals(whereinExpected.word, actual[6].word);
		assertEquals(whereinExpected.startingColumn, actual[6].startingColumn);
		
//		assertEquals(whereinExpected.word, actual[7].word);
//		assertEquals(whereinExpected.startingColumn, actual[7].startingColumn);
		
	}
	
	@Test
	public void spacedLine() {
		Occurence[] actual = Misc.processLine("    the vulgar,- O base and obscure vulgar!- videlicet, He came, saw,", 1);
		Occurence romeExpected = new Occurence("the", 1, 5);
		Occurence whereinExpected = new Occurence("videlicet", 1, 46);
		assertEquals(romeExpected.startingColumn, actual[0].startingColumn);
		assertEquals(romeExpected.word, actual[0].word);
		
		assertEquals(whereinExpected.word, actual[7].word);
		assertEquals(whereinExpected.startingColumn, actual[7].startingColumn);
		
	}

}
