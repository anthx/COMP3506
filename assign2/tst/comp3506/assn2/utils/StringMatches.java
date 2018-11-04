package comp3506.assn2.utils;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class StringMatches {

	@Test
	public void test() {  
		Pattern p = Pattern.compile("\bis\b");
		Matcher m = p.matcher("This is be some test");
		assertTrue(m.find());
	}

}
