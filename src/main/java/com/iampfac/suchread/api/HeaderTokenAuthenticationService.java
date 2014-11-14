package com.iampfac.suchread.api;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class HeaderTokenAuthenticationService implements IHeaderTokenAuthenticationService {

	private static final int TOKEN_LENGTH = 50;

	private IAuthenticationRepository repository;

	@Autowired
	public HeaderTokenAuthenticationService(IAuthenticationRepository repository) {
		this.repository = repository;
	}

	public Authentication findAuthentication(String username, String token) {
		return repository.find(username, token);
	}

	public String saveAuthentication(String username, Authentication auth) {
		String token = generateToken();
		repository.save(username, token, auth);
		return token;
	}

	// protected

	protected String generateToken() {
		return RandomStringUtils.randomAlphanumeric(TOKEN_LENGTH);
	}
}
