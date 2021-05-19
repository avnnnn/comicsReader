package formBean;

import org.formbeanfactory.FormBean;

public class RegistrationForm extends FormBean{
	private String email;
	private String password;
	private String confpassword;
	private String username;

	public String getEmail() {return email;}
	public String getPassword() {return password;}
	public String getConfpassword() {return confpassword;}
	public String getUsername() {return username;}
	
	public void setEmail(String email) {this.email = email;}
	public void setPassword(String password) {this.password = password;}
	public void setConfpassword(String confpassword) {this.confpassword = confpassword;}
	public void setUsername(String username) {this.username = username;}
	
    public void validate() {
        super.validate();
        if (email == null || email.length() == 0)
        	this.addFormError("Email is required");
        if( email.indexOf("@") == -1)
        	this.addFormError("Incorrect email format");
		if (password == null || password.length() == 0)
			this.addFormError("Password is required");
        if (confpassword == null || confpassword.length() == 0)
        	this.addFormError("Confirm password");
        if (username == null || username.length() == 0)
			this.addFormError("User Name is required");
        if(!password.equals(confpassword)) 
        	this.addFormError("Passwords not same");
		if (email.matches(".*[<>\"].*")) {
            this.addFormError("Email may not contain angle brackets or quotes");
        }
    }

	
}
