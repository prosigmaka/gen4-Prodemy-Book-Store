package com.example.onlinebookstore.configuration;

import com.example.onlinebookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	//needed for DaoAuthenticationProvider
	@Autowired
	private UserService userService;
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//				.authorizeRequests()
//				.antMatchers("/registration**", "/login**", "/js/**", "/css/**", "/fonts/**", "/images/**", "/assets/**",
//						"/*", "/aboutus*", "/product*", "/academic*", "/comic*", "/family*", "/novel*").permitAll()
//				.anyRequest().authenticated()
////				.antMatchers("/home-company*").hasRole("ADMIN")
//				.and()
//				.formLogin()
//				.loginPage("/login").permitAll()
////				.defaultSuccessUrl("/")
//				.and()
//				.logout()
//				.invalidateHttpSession(true)
//				.clearAuthentication(true)
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//
//				//once user click logout button, user will go to login page with "logout" page
//				.logoutSuccessUrl("/login?logout").permitAll();
//	}

	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //to integrate spring data JPA and hibernate in spring security, we need to provide this
	//spring security provide UserDetailsService interface and to use this, we need to provide userService
	//so that, UserService need to extends UserDetailService interface
	//and in UserServiceImpl, we need to override loadUserByUsername
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    //to pass authenticationProvider
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/registration**", "/login**", "/js/**", "/css/**", "/fonts/**", "/images/**", "/assets/**",
						"/home*", "/aboutus*", "/academic*", "/comic*", "/family*", "/novel*").permitAll()
				.antMatchers("/product*").hasAuthority("ROLE_ADMIN")
				.antMatchers("/hc*").hasAuthority("ROLE_ADMIN")
//				.antMatchers("/home**").hasAuthority("ROLE_USER")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login").permitAll()
//				.defaultSuccessUrl("/")
				.and()
				.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

				//once user click logout button, user will go to login page with "logout" page
				.logoutSuccessUrl("/login?logout").permitAll();
	}

}
