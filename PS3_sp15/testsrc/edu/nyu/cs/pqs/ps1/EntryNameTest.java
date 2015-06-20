package edu.nyu.cs.pqs.ps1;

import org.junit.Test;
import static org.junit.Assert.*;

public class EntryNameTest {

	@Test
	public void test_entryNameConstructorFirstName() {
		EntryName name = new EntryName("Prakhar");
		assertEquals("Prakhar", name.getFirstName());
	}

	@Test
	public void test_entryNameConstructorFullName() {
		EntryName name = new EntryName("Prakhar", "Verma");
		assertEquals("Prakhar", name.getFirstName());
		assertEquals("Verma", name.getLastName());
	}

	@Test
	public void test_entryToString() {
		EntryName name = new EntryName("Prakhar", "Verma");
		String nameString = name.toString();
		assertEquals("Prakhar Verma", nameString);
	}

	@Test
	public void test_hashCode() {
		EntryName name = new EntryName("Prakhar", "Verma");
		EntryName name2 = new EntryName("Prakhar", "Verma");
		int hashcode = name.hashCode();
		int hashcode2 = name2.hashCode();
		assertEquals(hashcode, hashcode2);
	}

	// This test does not work because the hashcode function does not handle the
	// case
	// where there is no last name entered (there is no default value), so the
	// hashcode
	// method is unable to produce a hashcode for the case with no last name
	@Test
	public void test_hashCodeNoLastName() {
		EntryName name = new EntryName("Prakhar");
		EntryName name2 = new EntryName("Prakhar", "Verma");
		int hashcode = name.hashCode();
		int hashcode2 = name2.hashCode();
		assertTrue(hashcode != hashcode2);
	}

	@Test
	public void test_nullNameEntry() {
		EntryName name = new EntryName("");
		assertNotNull(name);
	}

	@Test
	public void test_equalsNotEntryNameObject() {
		EntryName name = new EntryName("Prakhar");
		Email email = new Email("pv594@nyu.edu");
		assertFalse(name.equals(email));
	}

	@Test
	public void test_equalsFullName() {
		EntryName name = new EntryName("James", "Hetfield");
		EntryName name2 = new EntryName("James", "Hetfield");
		assertTrue(name.equals(name2));
	}

	@Test
	public void test_equalsFirstName() {
		EntryName name = new EntryName("Dave");
		EntryName name2 = new EntryName("Dave");
		assertTrue(name.equals(name2));
	}

	@Test
	public void test_equalsLastName() {
		EntryName name = new EntryName("", "Mustaine");
		EntryName name2 = new EntryName("Mustaine");
		assertFalse(name.equals(name2));
	}

	@Test
	public void test_settersAndGetters() {
		EntryName name = new EntryName("", "");
		name.setFirstName("Allan");
		name.setLastName("Donald");
		assertEquals("Allan", name.getFirstName());
		assertEquals("Donald", name.getLastName());
	}

	@Test
	public void test_nullNames() {
		EntryName name = new EntryName(null, null);
		EntryName name2 = new EntryName(null, "Donald");
		EntryName name3 = new EntryName(null, null);
		assertFalse(name.equals(name2));
		assertFalse(name.equals(name3));
	}
}
