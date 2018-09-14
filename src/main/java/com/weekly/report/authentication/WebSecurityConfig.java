package com.weekly.report.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.weekly.report.filters.ErrorFilter;
import com.weekly.report.filters.PostFilter;
import com.weekly.report.filters.PreFilter;
import com.weekly.report.filters.RouteFilter;



/*In a normal web application, whenever a secured resource is accessed Spring Security check the security context for the current user and will decide either to forward him to login page (if the user is not authenticated), or to forward him to the resource not authorised page (if he doesn’t have the required permissions).

In our scenario this is different, because we don’t have pages to forward to, we need to adapt and override Spring Security to communicate using HTTP protocols status only, below I liste the things to do to make Spring Security works best :

The authentication is going to be managed by the normal form login, the only difference is that the response will be on JSON along with an HTTP status which can either code 200 (if the autentication passed) or code 401 (if the authentication failed) ;
Override the AuthenticationFailureHandler to return the code 401 UNAUTHORIZED ;
Override the AuthenticationSuccessHandler to return the code 20 OK, the body of the HTTP response contain the JSON data of the current authenticated user ;
Override the AuthenticationEntryPoint to always return the code 401 UNAUTHORIZED. This will override the default behavior of Spring Security which is forwarding the user to the login page if he don’t meet the security requirements, because on REST we don’t have any login page ;
Override the LogoutSuccessHandler to return the code 20 OK ;
Like a normal web application secured by Spring Security, before accessing a protected service, it is mandatory to first authenticate by submitting the password and username to the Login URL.

Note: The following solution requires Spring Security in version minimum 3.2.*/
@Configuration
@EnableWebSecurity
/*Note that we need to extend the WebSecurityConfigurerAdapter – without it, all the paths will be secured – so the users will be redirected to log in when they try to access any page. In our case here, the index and 
login pages are the only pages that can be accessed without authentication.*/
//@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	
	/*@Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (myGlobalParameterName == null ? 0 : myGlobalParameterName.hashCode());
//Objects.hashCode(o)
        return result;
}*/
	
	private static final String LOGIN_PATH = "/"+"login";



    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	
    	http.csrf().disable()
		.authorizeRequests().antMatchers("/appinfo").permitAll()
							.antMatchers("/registerUser").permitAll()
							.antMatchers("/findUser").permitAll()
							.antMatchers("/login**").permitAll()
							//TODO: sample call secured with ROLE_API
							//.antMatchers("/ping").hasAuthority("ROLE_API")
							.antMatchers(HttpMethod.GET, "/**").permitAll()
							.antMatchers(HttpMethod.PUT, "/**").permitAll()
							.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
							
							// Temporary solution to allow jenkins plugin to send data to the api
						    //TODO: Secure with API Key
							.antMatchers(HttpMethod.POST, "/build").permitAll()
							.antMatchers(HttpMethod.POST, "/deploy").permitAll()
							.antMatchers(HttpMethod.POST, "/performance").permitAll()
				            .antMatchers(HttpMethod.POST, "/artifact").permitAll()
				            .antMatchers(HttpMethod.POST, "/quality/test").permitAll()
				            .antMatchers(HttpMethod.POST, "/quality/static-analysis").permitAll()
                            //Temporary solution to allow Github web hook
                            .antMatchers(HttpMethod.POST, "/commit/github/v3").permitAll()
							.anyRequest().authenticated();
	//							.and()
						//	.addFilterBefore(standardLoginRequestFilter(), UsernamePasswordAuthenticationFilter.class)
							/*.addFilterBefore(ssoAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
							.addFilterBefore(ldapLoginRequestFilter(), UsernamePasswordAuthenticationFilter.class)
							.addFilterBefore(apiTokenRequestFilter(), UsernamePasswordAuthenticationFilter.class)
							.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)*/
//							.exceptionHandling().authenticationEntryPoint(new Http401AuthenticationEntryPoint("Authorization"));
       /* http.csrf().disable()
                .authenticationProvider(authenticationProvider())
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .formLogin()
                .permitAll()
                .loginProcessingUrl(LOGIN_PATH)
                .usernameParameter("USERNAME")
                .passwordParameter("PASSWORD")
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler)
                .and()
                .logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher(LOGIN_PATH, "DELETE"))
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()
                .sessionManagement()
                .maximumSessions(1);

        http.authorizeRequests().anyRequest().authenticated();*/
    }
    
    }