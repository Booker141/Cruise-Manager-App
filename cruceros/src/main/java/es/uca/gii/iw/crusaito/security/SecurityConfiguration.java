package es.uca.gii.iw.crusaito.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import es.uca.gii.iw.crusaito.views.LoginView;

/**
 * Configures spring security, doing the following:
 * <li>Bypass security checks for static resources,</li>
 * <li>Restrict access to the application, allowing only logged in users,</li>
 * <li>Set up the login form</li>

 */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception { // 
	    return super.authenticationManagerBean();
	}

	@Bean
	public CustomRequestCache requestCache() { // 
	     return new CustomRequestCache();
	}
	
	
	
	private static final String LOGIN_PROCESSING_URL = "/" + LoginView.ROUTE;
	private static final String LOGIN_FAILURE_URL = "/" + LoginView.ROUTE;
	private static final String LOGIN_URL = "/" + LoginView.ROUTE;
	private static final String LOGOUT_SUCCESS_URL = "/" + LoginView.ROUTE;

	/**
	 * Require login to access internal pages and configure login form.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Not using Spring CSRF here to be able to use plain HTML for the login page
		http.csrf().disable()

				// Register our CustomRequestCache, that saves unauthorized access attempts, so
				// the user is redirected after login.
				.requestCache().requestCache(new CustomRequestCache())

				// Restrict access to our application.
				.and().authorizeRequests()

				// Allow all flow internal requests.
				.requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()

				// Allow all requests by logged in users.
				//.anyRequest().authenticated()

				// Configure the login page.
				.and().formLogin().loginPage(LOGIN_URL).permitAll().loginProcessingUrl(LOGIN_PROCESSING_URL)
				.failureUrl(LOGIN_FAILURE_URL)

				// Configure logout
				.and().logout().logoutSuccessUrl(LOGOUT_SUCCESS_URL);
	}

//	@Bean
//	@Override
//	public UserDetailsService userDetailsService() {
//		// typical logged in user with some privileges
//		UserDetails normalUser =
//				User.withUsername("user")
//						.password("{noop}password")
//						.roles("User")
//						.build();
//
//		// admin user with all privileges
//		UserDetails adminUser =
//				User.withUsername("admin")
//						.password("{noop}password")
//						.roles("User", "Admin")
//						.build();
//
//		// admin user with all privileges
//		UserDetails ivan =
//				User.withUsername("ivan")
//						.password("ivan")
//						.roles("User", "Admin")
//						.build();
//
//		return new InMemoryUserDetailsManager(normalUser, adminUser,ivan);
//	}

	/**
	 * Allows access to static resources, bypassing Spring security.
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
				// Vaadin Flow static resources
				"/VAADIN/**",

				// the standard favicon URI
				"/favicon.ico",

				// the robots exclusion standard
				"/robots.txt",

				// web application manifest
				"/manifest.webmanifest",
				"/sw.js",
				"/offline-page.html",

				// icons and images
				"/icons/**",
				"/images/**",

				// (development mode) static resources
				"/frontend/**",

				// (development mode) webjars
				"/webjars/**",

				// (development mode) H2 debugging console
				"/h2-console/**",

				// (production mode) static resources
				"/frontend-es5/**", "/frontend-es6/**");
	}
}
