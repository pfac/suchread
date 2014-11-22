package com.iampfac.suchread.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.iampfac.suchread.config.external.SuchreadConfiguration;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SuchreadConfiguration configuration;
	
	@Override
	public void configure(WebSecurity web) {
		if (!configuration.isAuthenticationEnabled()) {
			web.ignoring().anyRequest();
		}
	}

}
