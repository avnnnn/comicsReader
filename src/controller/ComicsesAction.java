package controller;


import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dataBean.User;
import model.ComicsDAO;
import model.Model;
import model.SeriesDAO;
import model.UserDAO;

public class ComicsesAction extends Action {
	ComicsDAO comicsDAO;
	SeriesDAO seriesDAO;
	UserDAO userDAO;
	public ComicsesAction(Model model) {
		userDAO = model.getUserDAO();
		comicsDAO = model.getComicsDAO();
		seriesDAO = model.getSeriesDAO();
	}
	@Override
	public String getName() {
		return "comicses.do";
	}
    public String performGet(HttpServletRequest request) {	
    	HttpSession session = request.getSession();

    	if(session.getAttribute("user")!=null) {
    		try {
				request.setAttribute("inSeria", userDAO.seriaInList((User)session.getAttribute("user"), Integer.parseInt(request.getParameter("name"))));
	        	if((boolean)request.getAttribute("inSeria")== true) {

		    		try {
						request.setAttribute("last_chapter", userDAO.getLastComics((User)session.getAttribute("user"),Integer.parseInt(request.getParameter("name"))));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}

			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	return performPost(request);
    }
    public String performPost(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	if(request.getParameter("seria")!=null) {
    		try {
				userDAO.addToList((User)session.getAttribute("user"),Integer.parseInt(request.getParameter("name")));
				request.setAttribute("inSeria", true);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
    	}
    	if(request.getAttribute("inSeria")!= null) {
        	if((boolean)request.getAttribute("inSeria")== true) {

	    		try {
					request.setAttribute("last_chapter", userDAO.getLastComics((User)session.getAttribute("user"),Integer.parseInt(request.getParameter("name"))));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
    	}
    	try {
			request.setAttribute("comicses",comicsDAO.getComicsInSeria(Integer.parseInt(request.getParameter("name"))));
			request.setAttribute("seria", seriesDAO.getSeriaById(Integer.parseInt(request.getParameter("name"))));
			request.setAttribute("random", seriesDAO.getRandomSeries());
			request.setAttribute("publisherId",seriesDAO.getPublisherId(seriesDAO.getSeriaById(Integer.parseInt(request.getParameter("name"))).getPublisher_name()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(request.getParameter("delete") != null) {
    		try {
				userDAO.deleteSeries((User)session.getAttribute("user"),Integer.parseInt(request.getParameter("name")));
				request.setAttribute("inSeria", userDAO.seriaInList((User)session.getAttribute("user"), Integer.parseInt(request.getParameter("name"))));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
		return "comicses.jsp";
    }
    
}
