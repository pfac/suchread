package com.iampfac.suchread.config.external;

public class MissingPropertyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MissingPropertyException(final String key) {
		super(String.format("Missing requested property \"%s\".", key));
	}
	
}
