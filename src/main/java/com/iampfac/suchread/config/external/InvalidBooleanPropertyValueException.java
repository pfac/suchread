package com.iampfac.suchread.config.external;

public class InvalidBooleanPropertyValueException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InvalidBooleanPropertyValueException(final String value, final String key) {
		super(String.format("Invalid value \"%s\" for property \"%s\".", value, key));
	}
	
}
