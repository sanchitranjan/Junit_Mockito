package com.example.employee_service.Config;
//package Config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//public class MySecurityConfig {
//
//	//authentication 
//	@Bean
//	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//		UserDetails admin = User.withUsername("Sanchit")
//								.password(encoder.encode("Password1"))
//								.roles("ADMIN")
//								.build();
//		
//		UserDetails user = User.withUsername("John")
//								.password(encoder.encode("Password2"))
//								.roles("USER")
//								.build();
//		
//		return new InMemoryUserDetailsManager(admin, user);
//	}
//	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        return http
//            .csrf(csrf-> csrf.disable())
//            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
//                    .requestMatchers(HttpMethod.GET, "http://localhost:8081/api/v1/employees").permitAll()
//                    .requestMatchers("/api/v1/employees/**").authenticated()
//                    .anyRequest().permitAll() 
//            )
//            .formLogin(
//                    form -> form
//                            .loginPage("/api/v1/login")
//                            .permitAll()
//            )
//            .build();
//
//		
////		return http.csrf().disable().authorizeHttpRequests(authorizeRequests ->
////	                authorizeRequests.requestMatchers("/api/v1/employees").permitAll()
////	                .requestMatchers("/api/v1/employees/**").authenticated())
////				.build();
//		
//		
////		return http.csrf().disable()
////					.authorizeHttpRequests()
////					.requestMatchers("/api/v1/employees")
////					.permitAll()
////					.and()
////					.authorizeHttpRequests()
////					.requestMatchers("/api/v1/employees/**")
////					.authenticated()
////					.build();
//	}
//	
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//}
