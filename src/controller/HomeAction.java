package controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.SeriesDAO;

public class HomeAction extends Action {
	SeriesDAO seriesDAO;
	public HomeAction(Model model) {
		seriesDAO = model.getSeriesDAO();
	}
	@Override
	public String getName() {
		return "home.do";
	}
    public String performGet(HttpServletRequest request) {	
    	return performPost(request);
    }
    public String performPost(HttpServletRequest request) {
    	
    	
    	try {
			request.setAttribute("series",seriesDAO.getTopSeries());
			request.setAttribute("random", seriesDAO.getRandomSeries());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return "home.jsp";
    }
    
}
