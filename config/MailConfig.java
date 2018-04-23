package com.school.harvard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
@Configuration
public class MailConfig {
	/*@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	     
	    mailSender.setUsername("er.rakeshrana@gmail.com");
	    mailSender.setPassword("Reyaansh@123");
	     
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("spring.mail.transport.protocol", "smtp");
	    props.put("sprimg.mail.smtp.auth", "true");
	    props.put("spring.mail.smtp.starttls.enable", "true");
	    props.put("spring.mail.smtp.ssl.enable", "true");
	    props.put("spring.mail.debug", "true");
	     
	    return mailSender;
	}*/
	@Bean
    public CorsFilter corsFilter() {
       // config.setAllowCredentials(true);
       
        return new CorsFilter();
    }
}

