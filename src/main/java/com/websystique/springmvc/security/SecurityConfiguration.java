package com.websystique.springmvc.security;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.websystique.springmvc.service.UserService;

@Configuration
@EnableWebSecurity //(debug = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	PersistentTokenRepository tokenRepository;

	@Autowired
	UserService userService;
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		List<Object> result = userService.findMatcher("");
		Iterator<Object> itr = result.iterator();
		
		http.authorizeRequests()
		.antMatchers("/login**","/sol_cam_passs_","/resetp","/static/**").permitAll();
		
		while(itr.hasNext())
		{
			Object[] obj = (Object[]) itr.next();
			http.authorizeRequests().antMatchers(String.valueOf(obj[2]))
			.access("hasRole('"+(String.valueOf(obj[1]))+"')");
		}
		/*
		 *			Object[] obj = (Object[]) itr.next();
			String[] urlArr = String.valueOf(obj[2]).split("/");
			String url = "";
			if(urlArr.length >= 2)
			{
				for(int i = 2; i <= Integer.valueOf(String.valueOf(obj[3])); i++)
				{
					if(i < urlArr.length)
						url = url + "/"+urlArr[i];
					else
						break;
				}
				url = url +"/**";
			}
			//System.out.println(url);
			http.authorizeRequests().antMatchers(url)
			.access("hasRole('"+(String.valueOf(obj[1]))+"')");
			
		 * */
		
		/*http.authorizeRequests().antMatchers("/viajes/aut_embarques","/viajes/act_viaje","/viajes/set_aut_embarques")
					.access("hasRole('ADMIN') or hasRole('EMBARQUES')")
					
		.antMatchers("/viajes/set_reg_embarques","/viajes/set_aut_logistica","/viajes/aut_logistica")
					.access("hasRole('ADMIN') or hasRole('LOGISTICA')")
		
		.antMatchers("/viajes/detalle","/viajes/hisotia___")
					.access("hasRole('ADMIN') or hasRole('LOGISTICA') or hasRole('EMBARQUES')")
					
		.antMatchers("/ventas/**")
					.access("hasRole('ADMIN') or hasRole('VENTAS')")
					
		.antMatchers("/list","/newuser/**", "/delete-user-*", "/edit-user-*")
					.access("hasRole('ADMIN')"); */

		http.authorizeRequests().anyRequest().authenticated()
		.and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/principal").failureUrl("/login?error")
		.loginProcessingUrl("/login").usernameParameter("ssoId").passwordParameter("password")
		
		.and().logout().deleteCookies("JSESSIONID").invalidateHttpSession(true).permitAll()
		.and().rememberMe().key("uniqueAndSecret13").rememberMeParameter("remember-me").tokenRepository(tokenRepository)
		.tokenValiditySeconds(86400).and().csrf().disable().exceptionHandling().accessDeniedPage("/Access_Denied")
		.and().sessionManagement()
		//.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
		.maximumSessions(1)
		//.sessionRegistry(sessionRegistry())
		.expiredUrl("/login?expired").and().invalidSessionUrl("/login?logout")
		.and().sessionManagement().sessionFixation().migrateSession();
		//.and().addFilterAfter(new CustomFilter(),BasicAuthenticationFilter.class);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
		PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
				"remember-me", userDetailsService, tokenRepository);
		return tokenBasedservice;
	}

	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}
	
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}
	
	@Bean
	public SessionRegistry sessionRegistry() {
	    SessionRegistry sessionRegistry = new SessionRegistryImpl();
	    return sessionRegistry;
	}

}
