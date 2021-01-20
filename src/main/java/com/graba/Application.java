package com.graba;

import java.util.Arrays;
import java.util.Properties;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableCaching
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				//System.out.println(beanName);
			}

		};
	}
	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    
	    mailSender.setUsername("my.gmail@gmail.com");
	    mailSender.setPassword("password");
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	}
	
	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */
	
			/*
			 * @Configuration public class ThymeleafConfig {
			 * 
			 * @Bean public SpringResourceTemplateResolver templateResolver() {
			 * SpringResourceTemplateResolver templateResolver = new
			 * SpringResourceTemplateResolver(); templateResolver.setCacheable(false);
			 * templateResolver.setPrefix("classpath:/templates/");
			 * templateResolver.setSuffix(".html"); return templateResolver; }
			 * 
			 * @Bean public SpringTemplateEngine templateEngine() { SpringTemplateEngine
			 * springTemplateEngine = new SpringTemplateEngine();
			 * springTemplateEngine.addTemplateResolver(templateResolver()); return
			 * springTemplateEngine; }
			 * 
			 * @Bean public ThymeleafViewResolver viewResolver() { ThymeleafViewResolver
			 * viewResolver = new ThymeleafViewResolver();
			 * viewResolver.setTemplateEngine(templateEngine()); viewResolver.setOrder(1);
			 * return viewResolver; } }
			 */

}
