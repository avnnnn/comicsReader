package controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import model.Model;
import model.PublisherDAO;
import model.SeriesDAO;

public class PublishersAction extends Action {

	SeriesDAO seriesDAO;
	PublisherDAO publisherDAO;
	public PublishersAction(Model model) {
		seriesDAO = model.getSeriesDAO();
		publisherDAO = model.getPublisherDAO();
		
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "publisher.do";
	}
	 public String performGet(HttpServletRequest request) {
		 try {
			request.setAttribute("publishers", publisherDAO.getPublishers());
			request.setAttribute("random", seriesDAO.getRandomSeries());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return "publishers.jsp";
				 
    }



}
