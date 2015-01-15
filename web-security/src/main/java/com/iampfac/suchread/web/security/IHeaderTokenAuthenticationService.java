package com.iampfac.suchread.web.security;

import org.springframework.security.core.Authentication;

public interface IHeaderTokenAuthenticationService {

	public Authentication findAuthentication(String username, String token);

	public String saveAuthentication(String username, Authentication auth);

}
