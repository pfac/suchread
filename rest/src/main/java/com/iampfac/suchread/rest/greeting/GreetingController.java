package com.iampfac.suchread.rest.greeting;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iampfac.suchread.core.Greeting;

@RestController
@RequestMapping("/api")
public class GreetingController {

	@RequestMapping(method = RequestMethod.GET)
	public GreetingPresenter index(
			@RequestParam(value = "n", required = false) String name) {
		final Greeting greeting = new Greeting(name);
		return new GreetingPresenter(greeting);
	}
}
