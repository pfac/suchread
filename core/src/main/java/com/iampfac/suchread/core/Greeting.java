package com.iampfac.suchread.core;

public class Greeting {

	private static final String TEMPLATE = "Hello %s!";

	private String name = "World";

	public Greeting() {
	}

	public String getMessage() {
		return String.format(TEMPLATE, name);
	}

	public String getName() {
		return name;
	}

}
