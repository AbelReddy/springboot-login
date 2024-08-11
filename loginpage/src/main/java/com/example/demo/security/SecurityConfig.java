package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.model.MyAppUserService;

@Configuration // this tells spring that it has important files for configuration
@EnableWebSecurity // this activates the security
public class SecurityConfig {
	
	
	private final MyAppUserService appUserService;
	
	SecurityConfig(MyAppUserService appUserService){
		this.appUserService = appUserService;
	}
	
public UserDetailsService userDetailService() {
	return appUserService;
}

@Bean
public PasswordEncoder passwordEncoder() {
	
	return new BCryptPasswordEncoder();
}


@Bean
 public AuthenticationProvider authenicationProvider() {
	
	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	provider.setUserDetailsService(appUserService);
	provider.setPasswordEncoder(passwordEncoder());
	return provider;
}	
	
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity ) throws Exception {
		return httpSecurity
				.csrf(csrf -> csrf.disable()) 
				.formLogin(httpForm -> {
					httpForm.loginPage("/login").permitAll(); //httpForm.loginPage("/login"): Specifies a custom login page located at /login. Users who are not authenticated will be redirected to this page.
					httpForm.defaultSuccessUrl("/index");
					httpForm.failureUrl("/login?error=true");
				})
				.authorizeHttpRequests( registry -> {         // by default spring enables authentication	
					registry.requestMatchers("/req/signup", "/css/**", "/js/**").permitAll();
					registry.anyRequest().authenticated();
				})
				
				.build();
		
	}

}
