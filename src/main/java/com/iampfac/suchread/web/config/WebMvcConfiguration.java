package com.iampfac.suchread.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@ComponentScan(value = "com.iampfac.suchread.web", excludeFilters = { @ComponentScan.Filter(value = Configuration.class, type = FilterType.ANNOTATION) })
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

}
