package comp3506.assn2.application;

import static org.junit.Assert.*;

import org.junit.Test;

public class Tries {

	@Test
	public void test() {
		Trie t = new Trie();
		t.addWord("a", 1, 1);
		t.addWord("aa", 1, 2);
		t.addWord("aa", 1, 3);
	}

}
