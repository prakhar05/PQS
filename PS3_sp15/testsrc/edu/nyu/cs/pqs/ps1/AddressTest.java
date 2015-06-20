package edu.nyu.cs.pqs.ps1;

import org.junit.Test;
import static org.junit.Assert.*;

public class AddressTest {

	// Not needed to test setters and getters, but just running a basic test to
	// get things started
	@Test
	public void test_SettersAndGetters() {
		Address addr = new Address("", "", "", "", "");
		addr.setStreet("Prospect Street");
		addr.setString("Glen Rock");
		addr.setState("NJ");
		addr.setZip("07452");
		addr.setCountry("USA");
		assertEquals("Prospect Street", addr.getStreet());
		assertEquals("Glen Rock", addr.getCity());
		assertEquals("NJ", addr.getState());
		assertEquals("07452", addr.getZip());
		assertEquals("USA", addr.getCountry());
	}

	@Test
	public void test_equals() {
		Address addr = new Address("Prospect Street", "Glen Rock", "NJ",
				"07452", "USA");
		Address addr2 = new Address("Prospect Street", "Glen Rock", "NJ",
				"07452", "USA");
		assertTrue(addr.equals(addr2));
	}

	@Test
	public void test_equalsNotAnAddressObject() {
		Address addr = new Address("Prospect Street", "Glen Rock", "NJ",
				"07452", "USA");
		assertFalse(addr.equals(""));
	}

	@Test
	public void test_toString() {
		Address addr = new Address("Prospect Street", "Glen Rock", "NJ",
				"07452", "USA");
		String addrString = addr.toString();
		String testString = new String(
				"Prospect Street\nGlen Rock, NJ 07452\nUSA");
		assertEquals(testString, addrString);
	}

	@Test
	public void test_hashCode() {
		Address addr = new Address("Ackerman Avenue", "Glen Rock", "NJ",
				"07555", "USA");
		Address addr2 = new Address("Ackerman Avenue", "Glen Rock", "NJ",
				"07555", "USA");
		int hash_addr = addr.hashCode();
		int hash_addr2 = addr2.hashCode();
		assertEquals(hash_addr, hash_addr2);
	}

}
