package com.openclassrooms.project5.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/home").setViewName("home");

		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/registration").setViewName("registration");
	}

	// The LocaleResolver interface has implementations that determine the current locale based on the session, cookies, the Accept-Language header, or a fixed value.
	// FOR(messages.propetis)
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	// We need to add an interceptor bean that will switch to a new locale based on the value of the lang parameter appended to a request
	// To take effect, this bean needs to be added to the application's interceptor registry.
	// FOR(messages.propetis)
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}
    
	//To achieve this, our @Configuration class has to implement the WebMvcConfigurer interface and override the addInterceptors() method
	// FOR(messages.propetis)
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
}
