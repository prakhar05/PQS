package edu.nyu.cs.pqs.ps1;

import org.junit.Test;
import static org.junit.Assert.*;

public class EntryPhoneNumberTest {

	@Test
	public void test_EntryPhoneNumberConstructor() {
		EntryPhoneNumber ph_no = new EntryPhoneNumber("201", "422", "5555");
		assertEquals("201", ph_no.getAreaCode());
	}

	@Test
	public void test_equals() {
		EntryPhoneNumber ph_no = new EntryPhoneNumber("201", "484", "5555");
		EntryPhoneNumber ph_no2 = new EntryPhoneNumber("201", "484", "5555");
		assertTrue(ph_no.equals(ph_no2));
	}

	@Test
	public void test_equalsNotAPhoneNumber() {
		EntryPhoneNumber ph_no = new EntryPhoneNumber("201", "444", "5555");
		assertFalse(ph_no.equals(""));
	}

	@Test
	public void test_hashcode() {
		EntryPhoneNumber ph_no = new EntryPhoneNumber("111", "222", "3332");
		EntryPhoneNumber ph_no2 = new EntryPhoneNumber("111", "222", "3333");
		int hashcode = ph_no.hashCode();
		int hashcode2 = ph_no2.hashCode();
		assertTrue(hashcode != hashcode2);
	}

	@Test
	public void test_toString() {
		EntryPhoneNumber ph_no = new EntryPhoneNumber(null, null, null);
		ph_no.setAreaCode("111212");
		ph_no.setPrefix("1");
		ph_no.setNumber("1");
		String phNumber = ph_no.toString();
		assertEquals("111212-1-1", phNumber);
	}

	@Test
	public void test_Getters() {
		EntryPhoneNumber ph_no = new EntryPhoneNumber(null, null, null);
		assertNull(ph_no.getAreaCode());
		assertNull(ph_no.getNumber());
		assertNull(ph_no.getPrefix());
	}
}
