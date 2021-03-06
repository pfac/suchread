package com.iampfac.suchread.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@EnableWebMvc
@ComponentScan("com.iampfac.suchread.web")
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

}
