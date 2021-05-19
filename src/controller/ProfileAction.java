package controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dataBean.User;
import model.Model;
import model.SeriesDAO;
import model.UserDAO;

public class ProfileAction extends Action {
	UserDAO userDAO;
	SeriesDAO seriesDAO;
	public ProfileAction(Model model) {
		userDAO = model.getUserDAO();
		seriesDAO = model.getSeriesDAO();
	}
	@Override
	public String getName() {
		return "profile.do";
	}
    public String performGet(HttpServletRequest request) {	
    	HttpSession session = request.getSession();
    	if (session.getAttribute("user") == null) {
    		return "login.do";
    	}
    	else {
    		try {
				request.setAttribute("user_list", seriesDAO.getUserList(((User)session.getAttribute("user")).getId()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return performPost(request);
    }
    public String performPost(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	
    	if(request.getParameter("delete") != null) {
    		try {
				userDAO.deleteSeries((User)session.getAttribute("user"),Integer.parseInt(request.getParameter("name")));
				request.setAttribute("user_list", seriesDAO.getUserList(((User)session.getAttribute("user")).getId()));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
		return "profile.jsp";
    }
    
}
