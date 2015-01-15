package com.iampfac.suchread.web.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.GenericFilterBean;

/**
 * 
 * @author pfac
 *
 */
public class HeaderTokenAuthenticationFilter extends GenericFilterBean {

	private static final Logger logger = LoggerFactory.getLogger(HeaderTokenAuthenticationFilter.class);
	
	private static final String HEADER_USERNAME_KEY = "X-Username";
	private static final String HEADER_AUTHTOKEN_KEY = "X-Auth-Token";

	private IHeaderTokenAuthenticationService service;

	@Autowired
	public HeaderTokenAuthenticationFilter(IHeaderTokenAuthenticationService service) {
		this.service = service;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
	}
	
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		attemptAuthentication(request);
		chain.doFilter(request, response);
		refreshAuthentication(response);
	}
	
	// private
	
	protected void attemptAuthentication(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || !auth.isAuthenticated()) {
			String username = request.getHeader(HEADER_USERNAME_KEY);
			String token = request.getHeader(HEADER_AUTHTOKEN_KEY);
			
			logger.debug(String.format("Authenticating user \"%s\" with token \"%s\"", username, token));
			auth = service.findAuthentication(username, token);
			if (auth != null) {
				logger.debug("Authentication found, setting security context");
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
	}

	protected void refreshAuthentication(HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated()) {
			UserDetails details = (UserDetails) auth.getPrincipal();
			String username = details.getUsername();
			String token = service.saveAuthentication(username, auth);
			logger.debug(String.format("Authentication saved for user \"%s\" with token \"%s\"", username, token));

			response.setHeader(HEADER_USERNAME_KEY, username);
			response.setHeader(HEADER_AUTHTOKEN_KEY, token);
		}
	}
	
}
