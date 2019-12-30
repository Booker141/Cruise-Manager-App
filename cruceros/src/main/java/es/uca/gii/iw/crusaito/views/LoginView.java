package es.uca.gii.iw.crusaito.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import es.uca.gii.iw.crusaito.security.CustomRequestCache;

@PageTitle("Login")
@Route(value = LoginView.ROUTE, layout = MainView.class)
public class LoginView extends VerticalLayout {
	
   private static final long serialVersionUID = 1L;
   public static final String ROUTE = "Login";
   
   private LoginOverlay login = new LoginOverlay();
   HorizontalLayout loginLayout = new HorizontalLayout();
   
   private AuthenticationManager authenticationManager;
   private CustomRequestCache requestCache;
   
   	@Autowired
    public LoginView(AuthenticationManager authenticationManager, CustomRequestCache requestCache) {
   		
   		this.authenticationManager = authenticationManager;
   		this.requestCache = requestCache;
   		
   		login.setOpened(true);
   		login.setTitle("Iniciar Sesión");
   		login.setDescription("¡Organiza tus vacaciones con nosotros!");
   		//login.setForgotPasswordButtonVisible(false);
   		
   		add(login);
   		
   		login.addLoginListener(e -> this.authenticate(e.getUsername(), e.getPassword()));
		
    }
    
	private void authenticate(String username, String password) {
		try {
			final Authentication authentication = authenticationManager.authenticate(
							new UsernamePasswordAuthenticationToken(username, password));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			login.close();
			UI.getCurrent().navigate(requestCache.resolveRedirectUrl());
		
		} catch (AuthenticationException ex) {
		 login.setError(true);
	}
  }
}
	