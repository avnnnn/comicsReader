package model;

import java.sql.*;

import javax.servlet.ServletConfig;



public class Model {
	private PublisherDAO publisherDAO;
	private UserDAO userDAO;
	private ComicsDAO comicsDAO;
	private SeriesDAO seriesDAO;
	private PageDAO pageDAO;
	public Model(ServletConfig config) {
		String jdbcURL = config.getInitParameter("jdbcURL");
		String jdbcPass = config.getInitParameter("jdbcPass");
		String jdbcUser = config.getInitParameter("jdbcUser");
		
        Connection con;
		try {
		    Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(jdbcURL,jdbcPass,jdbcUser);
			publisherDAO = new PublisherDAO(con);
	        seriesDAO = new SeriesDAO(con);
	        userDAO = new UserDAO(con);
	        comicsDAO = new ComicsDAO(con);
	        pageDAO = new PageDAO(con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	public PublisherDAO getPublisherDAO()  { return publisherDAO; }
	public SeriesDAO getSeriesDAO()  { return seriesDAO; }
	public ComicsDAO getComicsDAO()  { return comicsDAO; }
	public UserDAO getUserDAO()  { return userDAO; }
	public PageDAO getPageDAO()  { return pageDAO; }
}
