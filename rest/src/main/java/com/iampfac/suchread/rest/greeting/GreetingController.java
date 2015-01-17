package com.iampfac.suchread.rest.greeting;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iampfac.suchread.core.Greeting;

@RestController
@RequestMapping("/api")
public class GreetingController {

	@RequestMapping(method = RequestMethod.GET)
	public Greeting index() {
		return new Greeting();
	}
}
