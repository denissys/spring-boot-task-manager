package com.denissys.springboot.taskmanager.validate;

import java.net.InetAddress;
import java.net.URI;

import org.junit.Assert;

public class ConnectivityValidate {

	private static final int TIMEOUT_IN_MS = 10;

	public boolean isValid(final String address) {
		boolean isValid = true;
	    try {
	    	Assert.assertNotNull("Invalid address", address);
	    	final URI uri = new URI(address);
	    	final String host = uri.getHost();
	    	Assert.assertNotNull("Invalid host", host);
			
	    	isValid = InetAddress.getByName(host).isReachable(TIMEOUT_IN_MS);
	    } catch (AssertionError e) {
	    	isValid = false;
	    } catch (Exception e) {
	    	isValid = false;
	    }
	    return isValid;
	}
	
}
