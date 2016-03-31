package com.denissys.springboot.taskmanager.validate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ConnectivityValidateTest {

	@Test
	public void isValid_relativeAddressValid_returnFalse() {
		assertFalse(new ConnectivityValidate().isValid("localhost"));
	}
	
	@Test
	public void isValid_webAddressValid_returnTrue() {
		assertTrue(new ConnectivityValidate().isValid("http://localhost"));
	}
	
	@Test
	public void isValid_webAddressWithPortValid_returnTrue() {
		assertTrue(new ConnectivityValidate().isValid("http://localhost:8080"));
	}
	
	@Test
	public void isValid_webAddressWithPathValid_returnTrue() {
		assertTrue(new ConnectivityValidate().isValid("http://localhost/xpto"));
	}
	
	@Test
	public void isValid_webAddressWithPortAndPathValid_returnTrue() {
		assertTrue(new ConnectivityValidate().isValid("http://localhost:8080/xpto"));
	}
	
	@Test
	public void isValid_addressInvalid_returnFalse() {
		assertFalse(new ConnectivityValidate().isValid("localhostttt"));
	}
	
	@Test
	public void isValid_addressInvalid2_returnFalse() {
		assertFalse(new ConnectivityValidate().isValid("http://babababababa"));
	}

}
