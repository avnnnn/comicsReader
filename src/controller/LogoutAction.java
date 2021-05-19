package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import formBean.LoginForm;

public class LogoutAction extends Action {
	public LogoutAction(Model model) {
    }

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "logout.do";
	}
	public String performGet(HttpServletRequest request) {
		return performPost(request);
	}
	public String performPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("user", null);

        request.setAttribute("form", new LoginForm());
        return "login.jsp";
    }


}
