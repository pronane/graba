/*
 * package com.graba.security;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.builders.WebSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.security.crypto.password.PasswordEncoder;
 * 
 * @EnableWebSecurity
 * 
 * @Configuration public class SecurityConfig extends
 * WebSecurityConfigurerAdapter{
 * 
 * @Override public void configure(AuthenticationManagerBuilder auth) throws
 * Exception { auth .inMemoryAuthentication()
 * .withUser("paul").password(passwordEncoder().encode("password")).roles("USER"
 * ); }
 * 
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception { http
 * 
 * .authorizeRequests() .antMatchers("/", "/index").permitAll()
 * .anyRequest().authenticated() .and() .httpBasic(); }
 * 
 * @Bean public PasswordEncoder passwordEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 * @Override public void configure(WebSecurity web) throws Exception { web
 * .ignoring() .antMatchers("/resources/**","/templates/**", "/static/**",
 * "/css/**", "/js/**", "/images/**","/vendor/**","/fonts/**"); } }
 */