package com.iampfac.suchread.config.external;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@Describe(EnvironmentalSuchreadConfiguration.class)
public class EnvironmentalSuchreadConfigurationUnitTest {

	private EnvironmentSuchreadConfiguration target;
	
	@Describe("configuration files")
	public class ConfigurationFiles {
		
		@Describe("com/iampfac/suchread/default.properties")
		public class DefaultFile {
			
			@It("exists and is valid")
			
			@It("defines 'suchread.authentication.enabled'")
		}
	}

}
