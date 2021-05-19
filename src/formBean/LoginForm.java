package formBean;


import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;

@FieldOrder("email,password")
public class LoginForm extends FormBean {
    private String email;
    private String password;
    
    public String getEmail()  { return email; }
    public String getPassword()  { return password; }
	
    public void setEmail(String s)  { email = s.trim(); }
    @InputType("password")
    public void setPassword(String s)  { password = s.trim(); }
    
    public void validate() {
        super.validate();
        if (email == null || email.length() == 0)
        	this.addFormError("Email is required");
        
		if (password == null || password.length() == 0)
			this.addFormError("Password is required");
        
		if (email.matches(".*[<>\"].*")) {
            this.addFormError("Email may not contain angle brackets or quotes");
        }
    }
}
