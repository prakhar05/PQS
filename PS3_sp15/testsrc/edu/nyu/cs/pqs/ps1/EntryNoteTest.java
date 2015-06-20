package edu.nyu.cs.pqs.ps1;

import static org.junit.Assert.*;

import org.junit.Test;

public class EntryNoteTest {

	@Test
	public void test_creatingNoteAndEquals() {
		EntryNote note = new EntryNote("This is just a test note buddy");
		EntryNote note2 = new EntryNote("This is just a test note buddy");
		assertTrue(note.equals(note2));
	}

	@Test
	public void test_notNoteObject() {
		EntryNote note = new EntryNote("This is just a test note buddy");
		String note2 = "This is just a test note buddy";
		assertFalse(note.equals(note2));
	}

	@Test
	public void test_toString() {
		EntryNote note = new EntryNote("This is just a test note buddy");
		String note1 = note.toString();
		String note2 = "This is just a test note buddy";
		assertTrue(note1.equals(note2));
	}

	@Test
	public void test_hashcode() {
		EntryNote note = new EntryNote(
				"This is not a test of power\nThis is not a game");
		EntryNote note2 = new EntryNote(
				"This is not a test of power\nThis is not a game");
		int hash = note.hashCode();
		int hash2 = note2.hashCode();
		assertTrue(hash == hash2);
	}

	@Test
	public void test_settersAndGetters() {
		EntryNote note = new EntryNote("");
		note.setText("Take me to church");
		assertEquals("Take me to church", note.getText());
	}

}
