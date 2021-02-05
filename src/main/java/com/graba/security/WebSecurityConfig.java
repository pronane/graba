
package com.graba.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.graba.repository.CustomerRepository;
import com.graba.service.GrabaUserDetailsService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private CustomerRepository userRepository;

	// @Autowired // CustomAuthenticationProvider customAuthProvider;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication().withUser("memuser").password(passwordEncoder().encode("pass")).roles("USER");
		//auth.inMemoryAuthentication().withUser("paul").password(passwordEncoder().encode("password")).roles("USER");
		auth.authenticationProvider(authenticationProvider());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/**","/oauth2/**", "/register", "/confirm").permitAll()
				.antMatchers("/customer/**/", "/askquestion/**", "/adddress/**", "/change_password", "/cart","/h2-console/**")
				.permitAll().anyRequest().permitAll().and()
				
				  .formLogin() .loginPage("/login") //.usernameParameter("username")
				  .permitAll() .and()
				 
				.logout().permitAll()
			   .and().rememberMe().tokenRepository(persistentTokenRepository());

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**","/fragments/**","/templates/**", "/static/**", "/css/**", "/js/**", "/images/**", "/vendor/**",
				"/fonts/**","/h2-console/**","/images/bombay_palace/**");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);

		return tokenRepository;
	}

	@Bean
	public GrabaUserDetailsService userDetailsService() {
		GrabaUserDetailsService grabaUserDetailsService = new GrabaUserDetailsService();
		grabaUserDetailsService.setUserRepository(userRepository);
		return new GrabaUserDetailsService();
	}
	
}
