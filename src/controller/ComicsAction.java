package controller;


import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dataBean.User;
import model.ComicsDAO;
import model.Model;
import model.PageDAO;
import model.SeriesDAO;
import model.UserDAO;

public class ComicsAction extends Action {
	PageDAO pageDAO;
	UserDAO userDAO;
	SeriesDAO seriesDAO;
	ComicsDAO comicsDAO;
	public ComicsAction(Model model) {
		pageDAO = model.getPageDAO();
		userDAO = model.getUserDAO();
		seriesDAO = model.getSeriesDAO();
		comicsDAO =model.getComicsDAO();
	}
	@Override
	public String getName() {
		return "comics.do";
	}
    public String performGet(HttpServletRequest request) {	
		HttpSession session = request.getSession();
    	try {
    		if(seriesDAO.isFirstChapter(seriesDAO.getComicsSeria(Integer.parseInt(request.getParameter("name"))),Integer.parseInt(request.getParameter("name")))) {
    			request.setAttribute("isFirst", true);
    		}
    		if(seriesDAO.isLastChapter(seriesDAO.getComicsSeria(Integer.parseInt(request.getParameter("name"))),Integer.parseInt(request.getParameter("name")))) {
    			request.setAttribute("isLast", true);
    		}
			if(session.getAttribute("user") != null) {
				boolean inSeria = userDAO.seriaInList((User)session.getAttribute("user"), seriesDAO.getComicsSeria(Integer.parseInt(request.getParameter("name"))));
				System.out.println(inSeria);
				if(inSeria) {
					userDAO.updateLastChapter((User)session.getAttribute("user"),seriesDAO.getComicsSeria(Integer.parseInt(request.getParameter("name"))),Integer.parseInt(request.getParameter("name")) );
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    	return performPost(request);
    }
    public String performPost(HttpServletRequest request) {
    	try {
    	request.setAttribute("id",request.getParameter("name"));		
    	request.setAttribute("pages",pageDAO.getComicsPages(Integer.parseInt(request.getParameter("name"))));
		request.setAttribute("comicses",comicsDAO.getComicsInSeria(seriesDAO.getComicsSeria(Integer.parseInt(request.getParameter("name")))));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
		return "comics.jsp";
    }
    
}
