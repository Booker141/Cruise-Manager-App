package es.uca.gii.iw.crusaito.spring;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("LoginView")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class LoginView extends VerticalLayout {
   private static final long serialVersionUID = 1L;
   public static final String ROUTE = "login";

    public LoginView() {
   
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
		
		LoginForm login = new LoginForm();
		login.addLoginListener(e -> {
		    boolean isAuthenticated = authenticate(e);
		    if (isAuthenticated) {
		    	getUI().ifPresent(ui-> ui.navigate("MainView"));
		    } else {
		        login.setError(true);
		    }
		});
		
		HorizontalLayout loginLayout = new HorizontalLayout();
		loginLayout.add(login);
		loginLayout.setWidthFull(); //Que ocupe el ancho de toda la página
		loginLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER); //Alineación horizontal
		add(loginLayout);
		
		Footer footer = new Footer();
		add(footer);
		
    }
    
    // Mover mas tarde estos metodos a otra clase de comprobaciones
	public boolean authenticate(Object e) { return true; }
    
}
	