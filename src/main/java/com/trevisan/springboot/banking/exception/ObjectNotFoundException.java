package com.trevisan.springboot.banking.exception;

/**
 * @author Harlem Trevisan 
 */

public class ObjectNotFoundException extends RuntimeException {

	public ObjectNotFoundException(String objName, Long id) {
		super("Could not find " + objName + " : " + id);
	}
}
