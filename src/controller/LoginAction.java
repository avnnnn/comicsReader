package controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.formbeanfactory.FormBeanFactory;

import dataBean.User;
import formBean.LoginForm;
import model.Model;
import model.UserDAO;

public class LoginAction extends Action {
    private FormBeanFactory<LoginForm> formBeanFactory = new FormBeanFactory<>(LoginForm.class);
	UserDAO userDAO;
	public LoginAction(Model model) {
		userDAO = model.getUserDAO();
	}
	@Override
	public String getName() {
		return "login.do";
	}
	public String performGet(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            return "home.do";
        }
        request.setAttribute("form", new LoginForm());	
        return "login.jsp";
    }
	public String performPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {
            LoginForm form = formBeanFactory.create(request);
            request.setAttribute("form", form);
            if (form.hasValidationErrors()) {
            	System.out.println(Arrays.toString(form.getFormErrors()));
                request.setAttribute("form", form);
                return "login.jsp";
            }            
            User user = userDAO.findUser(form.getEmail(), form.getPassword()); 	
            System.out.print(user);
            if(user == null) {
                System.out.print(user);

                request.setAttribute("nouser", "Email or password not correct");
            	return "login.jsp";

            }
             
            session.setAttribute("user", user);
            return "profile.do";
        } catch (Exception e) {
        	
            request.setAttribute("nouser", "Email or password not correct");
        	e.printStackTrace();
        	return "login.do";
        }
    }

}
