package controller;


import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import model.ComicsDAO;
import model.Model;
import model.PublisherDAO;
import model.SeriesDAO;

public class SeriesAction extends Action {
	ComicsDAO comicsDAO;
	SeriesDAO seriesDAO;
	PublisherDAO publisherDAO;
	public SeriesAction(Model model) {
		publisherDAO = model.getPublisherDAO();
		comicsDAO = model.getComicsDAO();
		seriesDAO = model.getSeriesDAO();
	}
	@Override
	public String getName() {
		return "series.do";
	}
    public String performGet(HttpServletRequest request) {	
    	return performPost(request);
    }
    public String performPost(HttpServletRequest request) {
    	System.out.println(request.getParameter("page"));
    	try {
    		if(request.getParameter("searching_seria_name") != null){
    			request.setAttribute("series", seriesDAO.getSearchingSeries(request.getParameter("searching_seria_name")));
    			System.out.print(request.getParameter("searching_seria_name"));
    		}
    		else if(request.getParameter("name") != null) {
				if(request.getParameter("page")==null) {
					request.setAttribute("series",seriesDAO.getSeriesOfPublisher(Integer.parseInt(request.getParameter("name")),1));
					request.setAttribute("pages",seriesDAO.getArrayOfPage(request.getParameter("name"),1));
					request.setAttribute("publisher", publisherDAO.getPublisherById(Integer.parseInt(request.getParameter("name"))));
				}
				else {
					request.setAttribute("series",seriesDAO.getSeriesOfPublisher(Integer.parseInt(request.getParameter("name")),Integer.parseInt(request.getParameter("page"))));
					request.setAttribute("pages",seriesDAO.getArrayOfPage(request.getParameter("name"), Integer.parseInt(request.getParameter("page"))));

				}
			}
			else {
				if(request.getParameter("page")==null) {
					request.setAttribute("series",seriesDAO.getSeries(1));
					request.setAttribute("pages",seriesDAO.getArrayOfPage(null,1));

				}
				else {
					request.setAttribute("series",seriesDAO.getSeries(Integer.parseInt(request.getParameter("page"))));
					request.setAttribute("pages",seriesDAO.getArrayOfPage(null, Integer.parseInt(request.getParameter("page"))));

				}
			}
	    	request.setAttribute("random", seriesDAO.getRandomSeries());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
		return "series.jsp";
    }
    
}
