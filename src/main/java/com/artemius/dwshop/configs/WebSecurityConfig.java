package com.artemius.dwshop.configs;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
	@Override
/*	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/test","/index","/registration").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}*/
		protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf()
			.disable()
			.authorizeRequests()
				.antMatchers(HttpMethod.POST,"/merchInfo").permitAll()
				.antMatchers("/**","/index","/casual","/login","/register").permitAll()
				//.antMatchers("/cart","/purchase","/orders").hasRole("Role.CONSUMER")
				//.antMatchers("/delivery").hasRole("Role.DELIVERY")
				//.antMatchers("/adminium").hasRole("Role.ADMIN")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.jdbcAuthentication()
	                .dataSource(dataSource)
	                .passwordEncoder(NoOpPasswordEncoder.getInstance())
	                .usersByUsernameQuery("select username, password, active from account where username=?")
	                .authoritiesByUsernameQuery("select u.username, u.roles from account u where u.username=?");
	    }
}
