package com.openclassrooms.project5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
				.authorizeRequests()
				//.antMatchers("/css/**", "/", "/home").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
		
				.and()
				.formLogin().loginPage("/login").failureUrl("/login-error")
		
		     	.and()
		     	.logout()
		     	.logoutUrl("/logout")
		     	.logoutSuccessUrl("/")
      ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
        authBuilder
                .inMemoryAuthentication()
                .withUser(User.withDefaultPasswordEncoder().username("admin").password("password").roles("ADMIN"));
    }
}
