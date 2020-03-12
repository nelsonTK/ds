package com.data.structures.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.data.structures.ds.Trie1;
import com.data.structures.utils.StringHelper;

class Trie1Test {

	@Test
	void has_word_positive() {
		Trie1 t = new Trie1();
		String word = "a";
		
		t.addWord(word);
		
		assertTrue(t.hasWord(word));
	}
	
	@Test
	void has_words_positive() {
		Trie1 t = new Trie1();
		String abc = "abc";
		String abo = "abo";
		String baba = "baba";
		t.addWord(abc);
		t.addWord(abo);
		t.addWord(baba);

		assertTrue(t.hasWord(abc));
		assertTrue(t.hasWord(abo));
		assertTrue(t.hasWord(baba));
	}

	@Test
	void has_partial_word_negative() {
		Trie1 t = new Trie1();
		String word = "aol";
		String test = "ao";
		
		t.addWord(word);
		
		assertFalse(t.hasWord(test));
	}
	
	@Test
	void has_partial_words_positive() {
		Trie1 t = new Trie1();
		String abc = "abc";
		String abo = "abo";
		String baba = "baba";
		String bab = "bab";
		t.addWord(abc);
		t.addWord(abo);
		t.addWord(baba);

		assertFalse(t.hasWord(bab));
	}
	
	@Test
	void has_more_than_word_negative() {
		Trie1 t = new Trie1();
		String word = "aol";
		String test = "aolu";
		
		t.addWord(word);
		
		assertFalse(t.hasWord(test));
	}

	@Test
	void has_n_words_positive() {
		System.out.println("++++++ has_n_words_positive ++++++");
		ArrayList<String> list = StringHelper.generateData(100);
		Trie1 t = new Trie1();
		for (String s : list) {
			System.out.println(s);
			t.addWord(s);
			assertTrue(t.hasWord(s));
		}
	}
	
	@Test
	void has_n_words_negative() {
		System.out.println("++++++ has_n_words_positive ++++++");
		ArrayList<String> list = StringHelper.generateData(100);
		Trie1 t = new Trie1();
		String fail = "fail";
		for (String s : list) {
			System.out.println(s);
			t.addWord(s);
		}
		assertFalse(t.hasWord(fail));
	}

	@Test 
	void remove_smaller_word_first_keep_bigger_word_positive() {
		Trie1 t = new Trie1();
		t.addWord("word");
		t.addWord("wordy");
		assertTrue(t.hasWord("word"));
		assertTrue(t.hasWord("wordy"));
		t.removeWord("word");
		assertFalse(t.hasWord("word"));
		assertTrue(t.hasWord("wordy"));
	}
	
	@Test 
	void remove_bigger_word_first_keep_smaller_word_positive() {
		Trie1 t = new Trie1();
		t.addWord("word");
		t.addWord("wordy");
		assertTrue(t.hasWord("word"));
		assertTrue(t.hasWord("wordy"));
		t.removeWord("wordy");
		assertFalse(t.hasWord("wordy"));
		assertTrue(t.hasWord("word"));
	}
}
