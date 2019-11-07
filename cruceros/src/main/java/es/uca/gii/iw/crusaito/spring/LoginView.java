package es.uca.gii.iw.crusaito.spring;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Element;
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
    }
}
	


