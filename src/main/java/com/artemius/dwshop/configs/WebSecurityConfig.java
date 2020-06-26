package com.artemius.dwshop.configs;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.artemius.dwshop.entities.Role;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
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
				.antMatchers(HttpMethod.POST,"/**").permitAll()
				.antMatchers("/index**","/casual**","/login**","/registration**").permitAll()
				.antMatchers("/cart","/purchase","/orders").hasAnyAuthority(Role.CONSUMER.name(),Role.ADMIN.name())
				.antMatchers("/delivery**").hasAnyAuthority(Role.DELIVERY.name())
				.antMatchers("/adminium","/adminium").hasAnyAuthority(Role.ADMIN.name())
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.successHandler(new AuthenticationSuccessHandler() {
				    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				            Authentication authentication) throws IOException, ServletException {
				        redirectStrategy.sendRedirect(request, response, "/");
				    }
				})
				.and()
			.logout()
				.permitAll();
					
	}

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.jdbcAuthentication()
	                .dataSource(dataSource)
	                .passwordEncoder(new Pbkdf2PasswordEncoder())
	                .usersByUsernameQuery("select username, password, active from account where username=?")
	               // .groupAuthoritiesByUsername("select u.username, u.roles from account u where u.username=?");
	                .authoritiesByUsernameQuery("select u.username, u.roles from account u where u.username=?");
	    }
	    
	  
	    public void configure(WebSecurity web) throws Exception {
	      web.ignoring().antMatchers("/scripts/**","/styles/**","/fonts/**","/slick/**","/graphics/**");
	    }
	    
}
