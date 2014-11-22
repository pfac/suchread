package com.iampfac.suchread.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iampfac.suchread.api.Greeting;

@RestController
@RequestMapping("/")
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public HomeController() {
		logger.warn("HomeController built");
	}

	@RequestMapping(method = RequestMethod.GET)
	public Greeting getIndex() {
		return new Greeting();
	}
}
