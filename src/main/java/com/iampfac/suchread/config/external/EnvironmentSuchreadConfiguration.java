package com.iampfac.suchread.config.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

@Configuration
@PropertySources({
	@PropertySource("classpath:com/iampfac/suchread/default.properties"),
	@PropertySource(value = "classpath*:suchread-*.properties", ignoreResourceNotFound = true),
	@PropertySource(value = "file://${com.iampfac.suchread.configuration}", ignoreResourceNotFound = true)
})
public class EnvironmentSuchreadConfiguration implements SuchreadConfiguration {

	@Autowired
	private Environment env;
	
	public boolean isAuthenticationEnabled() {
		return isPropertyEnabled("com.iampfac.suchread.authentication.enabled");
	}
	
	// protected
	
	protected String getPropertyValue(final String key) {
		final String value = env.getProperty(key);
		if (value == null) {
			throw new MissingPropertyException(key);
		}
		return value;
	}
	
	protected boolean isPropertyEnabled(final String key) {
		final String value = getPropertyValue(key);
		if ("true".equalsIgnoreCase(value)) {
			return true;
		}
		if ("false".equalsIgnoreCase(value)) {
			return false;
		}
		throw new InvalidBooleanPropertyValueException(value, key);
	}
}
