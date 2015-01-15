package com.iampfac.suchread.web.security;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryAuthenticationRepository implements IAuthenticationRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InMemoryAuthenticationRepository.class);

	private class Key {
		private String username;
		private String token;

		public Key(String username, String token) {
			this.username = username;
			this.token = token;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (obj != null && obj instanceof Key) {
				return equals((Key) obj);
			}
			return false;
		}

		public boolean equals(Key key) {
			return StringUtils.equals(getUsername(), key.getUsername()) && StringUtils.equals(getToken(), key.getToken());
		}

		public String getToken() {
			return token;
		}

		public String getUsername() {
			return username;
		}

		@Override
		public int hashCode() {
			return Objects.hash(username, token);
		}

		@Override
		public String toString() {
			return String.format("{\"%s\":\"%s\"}", username, token);
		}
	}

	private static Map<Key, Authentication> authentications = new HashMap<Key, Authentication>();
	
	public Authentication find(String username, String token) {
		LOGGER.debug("Searching key for %s (token: %s)", username, token);
		Authentication auth = null;

		final Key key = new Key(username, token);
		synchronized (authentications) {
			if (authentications.containsKey(key)) {
				auth = authentications.get(key);
			}
		}

		LOGGER.info("Found key %s", key.toString());
		return auth;
	}

	public void save(String username, String token, Authentication authentication) {
		final Key key = new Key(username, token);
		synchronized (authentications) {
			authentications.put(key, authentication);
		}
	}

}
