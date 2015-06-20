package edu.nyu.cs.pqs.ps1;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class AddressBookTest {

	@Test
	public void test_createAddressBookAndAddEntry() {
		AddressBook addrBook = new AddressBook();
		AddressEntry entry = buildDefaultEntry((byte) 1);
		assertTrue(addrBook.addNewEntry(entry));
	}

	@Test
	public void test_removeAnEntryThatIsPresent() {
		AddressBook addrBook = new AddressBook();
		AddressEntry entry = buildDefaultEntry((byte) 1);
		addrBook.addNewEntry(entry);
		assertTrue(addrBook.removeOldEntry(entry));
	}

	@Test
	public void test_removeEntryThatIsNotPresent() {
		AddressBook addrBook = new AddressBook();
		AddressEntry entry = buildDefaultEntry((byte) 1);
		addrBook.addNewEntry(entry);
		AddressEntry searchEntry = buildDefaultEntry((byte) 2);
		assertFalse(addrBook.removeOldEntry(searchEntry));
	}

	@Test
	public void test_SearchingByDifferentFields() {
		AddressBook addrBook = new AddressBook();
		AddressEntry entry1 = buildDefaultEntry((byte) 1);
		AddressEntry entry2 = buildDefaultEntry((byte) 2);
		AddressEntry entry3 = buildDefaultEntry((byte) 3);
		addrBook.addNewEntry(entry1);
		addrBook.addNewEntry(entry2);
		addrBook.addNewEntry(entry3);
		addrBook.addNewEntry(entry3);

		// name from entry1
		EntryName name = new EntryName("Prakhar", "Verma");
		// Ph_no from entry2
		EntryPhoneNumber ph_no = new EntryPhoneNumber("123", "456", "7789");
		// email from entry3
		Email email = new Email("KanyeIsBoss@tidal.com");
		// address from entry1
		Address addr = new Address("Prospect Street", "Glen Rock", "NJ",
				"07452", "USA");
		// note from entry3
		EntryNote note = new EntryNote("Gold Digger");

		assertTrue(addrBook.search(name).contains(entry1));
		assertTrue(addrBook.search(ph_no).contains(entry2));
		assertTrue(addrBook.search(email).contains(entry3));
		assertTrue(addrBook.search(addr).contains(entry1));
		assertTrue(addrBook.search(note).size() == 2);
		assertTrue(addrBook.search(entry1).contains(entry1));
	}

	@Test
	public void test_searchUsingNullFieldEntries() {
		AddressBook addrBook = new AddressBook();
		AddressEntry entry4 = buildDefaultEntry((byte) 4);
		addrBook.addNewEntry(entry4);
		EntryName name = new EntryName("", "");
		EntryName name2 = new EntryName("");
		EntryName name3 = new EntryName("", "last");
		assertTrue(addrBook.search(name).size() == 1);
		assertTrue(addrBook.search(name3).size() == 0);
		// This test should have returned true but is returning false because a
		// name entry with
		// empty first name and empty last name is treated the same as name
		// entry with only first
		// name which is empty
		assertTrue(addrBook.search(name2).isEmpty());
	}

	@Test
	public void test_toString() {
		AddressBook addrBook = new AddressBook();
		AddressEntry entry1 = buildDefaultEntry((byte) 1);
		addrBook.addNewEntry(entry1);
		// add " " in the beginning because thats how it has been initialised in
		// the code
		String entryString = " " + entry1.toString();
		String addrBookString = addrBook.toString();
		assertTrue(entryString.equals(addrBookString));
	}

	@Test
	public void test_FileWriteAndRead() throws IOException {
		// This test is working incorrectly because the readAddressBookFromFile
		// is not implemented
		// correctly. So even though the saveAddressBookToFile gets covered,
		// readAddressBookFromFile
		// does not work correctly.
		AddressBook addrBook = new AddressBook();
		AddressBook newAddressBook = new AddressBook();
		addrBook.addNewEntry(buildDefaultEntry((byte) 1));
		addrBook.addNewEntry(buildDefaultEntry((byte) 2));
		addrBook.addNewEntry(buildDefaultEntry((byte) 3));
		File addrBookFile = addrBook.saveAddressBookToFile();
		newAddressBook.readAddressBookFromFile(addrBookFile);
		assertEquals(addrBook, newAddressBook);
	}

	private AddressEntry buildDefaultEntry(byte entryNumber) {
		if (entryNumber == 1) {
			EntryName name = new EntryName("Prakhar", "Verma");
			EntryPhoneNumber ph_no = new EntryPhoneNumber("111", "222", "3333");
			Email email = new Email("asdljkas@gmail.com");
			Address addr = new Address("Prospect Street", "Glen Rock", "NJ",
					"07452", "USA");
			EntryNote note = new EntryNote("Fight fire with fire");
			AddressEntry entry = new AddressEntry(name, ph_no, email, addr,
					note);
			return entry;
		} else if (entryNumber == 2) {
			EntryName name = new EntryName("Donald", "Duck");
			EntryPhoneNumber ph_no = new EntryPhoneNumber("123", "456", "7789");
			Email email = new Email("qwerty@gmail.com");
			Address addr = new Address("Ackerman Avenue", "Glen Rock", "NJ",
					"07452", "USA");
			EntryNote note = new EntryNote("Ride the lightning");
			AddressEntry entry = new AddressEntry(name, ph_no, email, addr,
					note);
			return entry;
		} else if (entryNumber == 3) {
			EntryName name = new EntryName("Kanye", "West");
			EntryPhoneNumber ph_no = new EntryPhoneNumber("999", "111", "2222");
			Email email = new Email("KanyeIsBoss@tidal.com");
			Address addr = new Address("West Boulevard", "Los Angeles", "CA",
					"91111", "USA");
			EntryNote note = new EntryNote("Gold Digger");
			AddressEntry entry = new AddressEntry(name, ph_no, email, addr,
					note);
			return entry;
		} else if (entryNumber == 4) {
			EntryName name = new EntryName("", "");
			EntryPhoneNumber ph_no = new EntryPhoneNumber("", "", "");
			Email email = new Email("");
			Address addr = new Address("", "", "", "", "");
			EntryNote note = new EntryNote("");
			AddressEntry entry = new AddressEntry(name, ph_no, email, addr,
					note);
			return entry;
		}
		return null;
	}

}
