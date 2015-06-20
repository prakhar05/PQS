package edu.nyu.cs.pqs.ps1;

import static org.junit.Assert.*;

import org.junit.Test;

public class AddressEntryTest {

	@Test
	public void test_BuildingAnEntry_EqualityCheckNotInstanceOf() {
		AddressEntry entry = buildDefaultEntry();
		assertFalse(entry.equals(""));
	}

	@Test
	public void test_checkEquality() {
		AddressEntry entry = buildDefaultEntry();
		AddressEntry entry2 = buildDefaultEntry();
		AddressEntry entry3 = entry2;
		assertTrue(entry.equals(entry2));
		assertTrue(entry.equals(entry3));

	}

	@Test
	public void test_hashcode() {
		AddressEntry entry = buildDefaultEntry();

		// build another entry with one character difference
		EntryName name = new EntryName("Prakhar", "Verma");
		EntryPhoneNumber ph_no = new EntryPhoneNumber("111", "222", "3333");
		Email email = new Email("asdljkas@gmail.com");
		Address addr = new Address("Prospect Street", "Glen Rock", "NJ",
				"07452", "USA");
		EntryNote note = new EntryNote("fight fire with fire");
		AddressEntry entry2 = new AddressEntry(name, ph_no, email, addr, note);

		// hashcodes
		int hashcode = entry.hashCode();
		int hashcode2 = entry2.hashCode();
		assertTrue(hashcode != hashcode2);
	}

	@Test
	public void test_toString() {
		AddressEntry entry = buildDefaultEntry();
		String phoneBookEntry = entry.toString();
		assertTrue(phoneBookEntry != null);
	}

	private AddressEntry buildDefaultEntry() {
		EntryName name = new EntryName("Prakhar", "Verma");
		EntryPhoneNumber ph_no = new EntryPhoneNumber("111", "222", "3333");
		Email email = new Email("asdljkas@gmail.com");
		Address addr = new Address("Prospect Street", "Glen Rock", "NJ",
				"07452", "USA");
		EntryNote note = new EntryNote("Fight fire with fire");
		AddressEntry entry = new AddressEntry(null, null, null, null, null);
		entry.setEntryName(name);
		entry.setAddress(addr);
		entry.setEmail(email);
		entry.setEntryNote(note);
		entry.setEntryPhoneNumber(ph_no);
		return entry;
	}

}
