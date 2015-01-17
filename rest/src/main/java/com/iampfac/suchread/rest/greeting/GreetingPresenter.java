package com.iampfac.suchread.rest.greeting;

import com.iampfac.suchread.core.Greeting;

public class GreetingPresenter {

	private Greeting greeting;

	public GreetingPresenter(Greeting greeting) {
		this.greeting = greeting;
	}

	public String getMessage() {
		return greeting.getMessage();
	}
}
