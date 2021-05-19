package controller;

import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.formbeanfactory.FormBeanFactory;

import dataBean.User;
import formBean.RegistrationForm;
import model.Model;
import model.UserDAO;


public class RegistrationAction extends Action{

	 private FormBeanFactory<RegistrationForm> formBeanFactory = new FormBeanFactory<>(RegistrationForm.class);

	    private UserDAO userDAO;

	    public RegistrationAction(Model model) {
	        userDAO = model.getUserDAO();
	    }

	    public String getName() {
	        return "register.do";
	    }

	    public String performGet(HttpServletRequest request) {
	        // If user is already logged in, redirect to todolist.do
	        HttpSession session = request.getSession();
	        if (session.getAttribute("user") != null) {
	            return "home.do";
	        }

	        // Otherwise, just display the login page.
	        request.setAttribute("form", new RegistrationForm());
	        return "register.jsp";
	    }
	    public String performPost(HttpServletRequest request) {
	        HttpSession session = request.getSession();
	    	RegistrationForm form = formBeanFactory.create(request);
            request.setAttribute("form", form);
            
            System.out.println(form.getEmail());
            System.out.println(form.getPassword());
            System.out.println(form.getUsername());


            if (form.hasValidationErrors()) {
            	System.out.println(Arrays.toString(form.getFormErrors()));
                return "register.jsp";
            }
            User newUser = new User();
            newUser.setEmail(form.getEmail());
            newUser.setPassword(form.getPassword());
            newUser.setUsername(form.getUsername());
            int id;
            try {
				if((id = userDAO.insertUser(newUser))!=0) {
				    newUser.setId(id);
				    System.out.print("USERID "+newUser.getId());
				    session.setAttribute("user", newUser);
				    return ("home.do");
				}
				else {
				    form.addFormError("A user with this name already exists");
				    System.out.println(2);
				    request.setAttribute("form", form);

				    return "register.jsp";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				form.addFormError("A user with this name already exists");
			    System.out.println(2);
			    request.setAttribute("form", form);

			    return "register.jsp";
			} 
	    }

}
