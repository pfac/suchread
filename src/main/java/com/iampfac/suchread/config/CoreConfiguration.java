package com.iampfac.suchread.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(value = "com.iampfac.suchread", excludeFilters = { @ComponentScan.Filter(value = Configuration.class, type = FilterType.ANNOTATION) })
public class CoreConfiguration {

}
