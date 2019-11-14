package es.uca.gii.iw.crusaito.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.flow.component.login.AbstractLogin.LoginEvent;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import es.uca.gii.iw.crusaito.security.CustomRequestCache;

@Secured({"Cliente, Admin, Gerente"})
@Route("LoginView")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class LoginView extends VerticalLayout {
   private static final long serialVersionUID = 1L;
   public static final String ROUTE = "login";
   LoginForm login = new LoginForm();
   HorizontalLayout loginLayout = new HorizontalLayout();
   private AuthenticationManager authenticationManager;
   private CustomRequestCache requestCache;
   
   	@Autowired
    public LoginView(AuthenticationManager authenticationManager, CustomRequestCache requestCache) {
   		
   		this.authenticationManager = authenticationManager;
   		this.requestCache = requestCache;
    	Header header = new Header();
		add(header);
		
		/*
    	this.setAlignItems(Alignment.CENTER); //te lo centra
    	this.setHorizontalComponentAlignment(Alignment.START, header);
        TextField userNameTextField = new TextField();
        userNameTextField.getElement().setAttribute("name", "username"); // 
        PasswordField passwordField = new PasswordField();
        passwordField.getElement().setAttribute("name", "password"); // 
        Button submitButton = new Button("Login");
        submitButton.setId("submitbutton"); // 
        UI.getCurrent().getPage().executeJavaScript("document.getElementById('submitbutton').addEventListener('click', () => document.getElementById('ironform').submit());"); // 

        FormLayout formLayout = new FormLayout(); // 
        formLayout.add(userNameTextField, passwordField, submitButton);

        Element formElement = new Element("form"); // 
        formElement.setAttribute("method", "post");
        formElement.setAttribute("action", "login");
        formElement.appendChild(formLayout.getElement());

        Element ironForm = new Element("iron-form"); // 
        ironForm.setAttribute("id", "ironform");
        ironForm.setAttribute("allow-redirect", true); // 
        ironForm.appendChild(formElement);

        getElement().appendChild(ironForm); // 
        
  
        setClassName("login-view");
        */
		
		login.addLoginListener(e -> Logear(e));
		add(login);
		    
		loginLayout.add(login);
		loginLayout.setWidthFull(); //Que ocupe el ancho de toda la página
		loginLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER); //Alineación horizontal
		add(loginLayout);
		
		Footer footer = new Footer();
		add(footer);
		
    }

	private void Logear(LoginEvent e) {
		boolean isAuthenticated = authenticate(e.getUsername(), e.getPassword());
	    if (isAuthenticated) {
	    	getUI().ifPresent(ui-> ui.navigate("MainView"));
	    } else {
	        login.setError(true);
	    }
	}
    
	private boolean authenticate(String username, String password) {
		try {
			final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			// login.close();
			// UI.getCurrent().navigate(requestCache.resolveRedirectUrl());
			return true;
		
		} catch (AuthenticationException ex) {
		 // login.setError(true);
			return false;
	}

  }
}
	