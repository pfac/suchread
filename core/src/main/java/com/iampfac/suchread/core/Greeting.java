package com.iampfac.suchread.core;

import org.apache.commons.lang3.StringUtils;

public class Greeting {

	private static final String FORMAT = "Hello %s!";

	private static final String DEFAULT_NAME = "World";

	private String name = DEFAULT_NAME;

	public Greeting() {
		this(DEFAULT_NAME);
	}

	public Greeting(String name) {
		setName(name);
	}

	public String getMessage() {
		return String.format(FORMAT, getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (StringUtils.isNotBlank(name)) {
			this.name = name;
		}
	}

}
