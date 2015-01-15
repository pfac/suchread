package com.iampfac.suchread.web.security;

import org.springframework.security.core.Authentication;

public interface IAuthenticationRepository {

	public Authentication find(String username, String token);

	public void save(String username, String token, Authentication authentication);
}
