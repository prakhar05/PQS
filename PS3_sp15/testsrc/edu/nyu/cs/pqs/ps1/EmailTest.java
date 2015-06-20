package edu.nyu.cs.pqs.ps1;

import org.junit.Test;
import static org.junit.Assert.*;

public class EmailTest {

	@Test
	public void test_emailConstructor() {
		Email email = new Email("pv594@nyu.edu");
		assertEquals("pv594@nyu.edu", email.getEmail());
	}

	@Test
	public void test_equals() {
		Email email1 = new Email("asdf@google.com");
		Email email2 = new Email("");
		email2.setEmail("asdf@google.com");
		assertTrue(email1.equals(email2));
	}

	@Test
	public void test_equalsNotAnEmailObject() {
		Email email = new Email("pv594@nyu.edu");
		assertFalse(email.equals(""));
	}

	@Test
	public void test_toString() {
		Email email = new Email("pv594@nyu.edu");
		String emailString = email.toString();
		assertEquals("pv594@nyu.edu", emailString);
	}

	@Test
	public void test_hashCode() {
		Email email = new Email("pv594@nyu.edu");
		Email email2 = new Email("Pv594@nyu.edu");
		int hashcode = email.hashCode();
		int hashcode2 = email2.hashCode();
		assertTrue((hashcode != hashcode2));
	}

}
