package comp3506.assn2.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProcessLinesTests {

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
		Occurence theExpected = new Occurence("the", 1, 5);
		Occurence whereinExpected = new Occurence("videlicet", 1, 46);
		assertEquals(theExpected.toString(), actual[4].toString());
		
		assertEquals(whereinExpected.toString(), actual[13].toString());
		
	}
	
	@Test
	public void simpleLineRepeats() {
		Occurence[] actual = Misc.processLine("ant anthony anth anthony-anthony anth", 1);
		Occurence antExpected = new Occurence("ant", 1, 1);
		Occurence anthony2Expected = new Occurence("anthony", 1, 26);
		assertEquals(antExpected.startingColumn, actual[0].startingColumn);
		assertEquals(antExpected.word, actual[0].word);
		
		assertEquals(anthony2Expected.word, actual[4].word);
		assertEquals(anthony2Expected.startingColumn, actual[4].startingColumn);
		
		
	}

	@Test
	public void moreRepeats() {
		Occurence[] actual = Misc.processLine("the, they, their, then, then, there, there, that", 1);
		Occurence a = new Occurence("the", 1, 1);
		Occurence b = new Occurence("their", 1, 12);
		Occurence c = new Occurence("there", 1, 31);
		Occurence d = new Occurence("there", 1, 38);
		
		assertEquals(a.toString(), actual[0].toString());
		assertEquals(b.toString(), actual[2].toString());
		assertEquals(c.toString(), actual[5].toString());
		assertEquals(d.toString(), actual[6].toString());
	}
}
