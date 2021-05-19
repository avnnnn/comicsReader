package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataBean.Series;




public class SeriesDAO {
	Connection connection;
    public SeriesDAO(Connection con )  {
    	connection = con;
    }
    
    public ArrayList<Series> getTopSeries() throws SQLException {
    	ArrayList<Series> series = new ArrayList<Series>();
    	Series s;
    	PreparedStatement ps = connection.prepareStatement(" Select * from ( SELECT series_id, s_name, publisher_id, year,original_name, posterurl, rank() over( partition by publisher_id order by year desc, s_name) rank from series where publisher_id in (1,2))where rank <= 12");
    	ResultSet rs = ps.executeQuery();
    	while(rs.next()) {
    		s = new Series();
    		s.setSeries_id(rs.getInt("SERIES_ID"));
    		s.setS_name(rs.getString("s_name"));
    		s.setPublisher_name(getPublisherName(rs.getInt("publisher_id")));
    		s.setYear(rs.getInt("year"));
    		s.setOriginal_name(rs.getNString("original_name"));
    		s.setPosterurl(rs.getString("posterurl"));
    		series.add(s);
    	}
    	rs.close();
    	ps.close();
    	return series;
    }
    public String getPublisherName(int id) throws SQLException {
    	PreparedStatement ps = connection.prepareStatement("SELECT p_name from publisher where publisher_id = ?");
    	ps.setInt(1,id);
    	ResultSet rs = ps.executeQuery();
    	rs.next();
    	String name = rs.getNString("p_name");
    	rs.close();
    	ps.close();
    	return name;
    }
    public int getPublisherId(String name) throws SQLException {
    	PreparedStatement ps = connection.prepareStatement("SELECT publisher_id from publisher where p_name = ?");
    	ps.setString(1,name);
    	ResultSet rs = ps.executeQuery();
    	rs.next();
    	int id = rs.getInt("publisher_id");
    	rs.close();
    	ps.close();
    	return id;
    }    
    public String getSeriaName(int id) throws SQLException {
    	PreparedStatement ps = connection.prepareStatement("SELECT s_name from series where series_id = ?");
    	ps.setInt(1,id);
    	ResultSet rs = ps.executeQuery();
    	rs.next();
    	String name = rs.getNString("s_name");
    	rs.close();
    	ps.close();
    	return name;
    }
	public ArrayList<Series> getUserList(int id) throws SQLException {
		ArrayList<Series> series = new ArrayList<Series>();
    	Series s;
    	PreparedStatement ps = connection.prepareStatement("Select * from series where series_id in (select series_id from user_list where user_id = ?)");
    	ps.setInt(1, id);
    	ResultSet rs = ps.executeQuery();
    	while(rs.next()) {
    		s = new Series();
    		s.setSeries_id(rs.getInt("SERIES_ID"));
    		s.setS_name(rs.getString("s_name"));
    		s.setPublisher_name(getPublisherName(rs.getInt("publisher_id")));
    		s.setYear(rs.getInt("year"));
    		s.setOriginal_name(rs.getNString("original_name"));
    		s.setPosterurl(rs.getString("posterurl"));
    		series.add(s);
    	}
    	rs.close();
    	ps.close();
    	return series;
	}
	public ArrayList<Series> getSeriesOfPublisher(int id,int page) throws SQLException {
		ArrayList<Series> series = new ArrayList<Series>();
    	Series s;
    	int start = (page-1) * 20;
    	int send = start + 20;
    	PreparedStatement ps = connection.prepareStatement(" select * from (select s.*, rownum rn from series s where s.publisher_id = ?) where rn > ? and rn < ?");
    	ps.setInt(1, id);
    	ps.setInt(2, start);
    	ps.setInt(3, send);
    	ResultSet rs = ps.executeQuery();
    	while(rs.next()) {
    		s = new Series();
    		s.setSeries_id(rs.getInt("SERIES_ID"));
    		s.setS_name(rs.getString("s_name"));
    		s.setPublisher_name(getPublisherName(rs.getInt("publisher_id")));
    		s.setYear(rs.getInt("year"));
    		s.setOriginal_name(rs.getNString("original_name"));
    		s.setPosterurl(rs.getString("posterurl"));
    		series.add(s);
    	}
    	rs.close();
    	ps.close();
    	return series;
	}
	   public Series getRandomSeries() throws SQLException {
		   Series s = new Series();
    	   PreparedStatement ps = connection.prepareStatement(" Select * from series order by dbms_random.random  fetch first 1 rows only");
    	   ResultSet rs = ps.executeQuery();
    	   rs.next();
    	   s.setOriginal_name(rs.getNString("original_name"));
    	   s.setPosterurl(rs.getNString("posterurl"));
    	   s.setPublisher_name(getPublisherName(rs.getInt("publisher_id")));
    	   s.setYear(rs.getInt("year"));
    	   s.setS_name(rs.getNString("s_name"));
    	   s.setSeries_id(rs.getInt("series_id"));
    	   rs.close();
    	   ps.close();
    	   return s;
	   }
	   public Series getSeriaById(int id) throws SQLException {
		   Series s = new Series();
    	   PreparedStatement ps = connection.prepareStatement(" Select * from series where series_id = ?");
    	   ps.setInt(1, id);
    	   ResultSet rs = ps.executeQuery();
    	   rs.next();
    	   s.setOriginal_name(rs.getNString("original_name"));
    	   s.setPosterurl(rs.getNString("posterurl"));
    	   s.setPublisher_name(getPublisherName(rs.getInt("publisher_id")));
    	   s.setYear(rs.getInt("year"));
    	   s.setS_name(rs.getNString("s_name"));
    	   s.setSeries_id(rs.getInt("series_id"));
    	   rs.close();
    	   ps.close();
    	   return s;

	   }


	public ArrayList<Series> getSeries(int page) throws SQLException {
		ArrayList<Series> series = new ArrayList<Series>();
    	Series s;
    	int start = (page-1) * 20;
    	int send = start + 20;
    	PreparedStatement ps = connection.prepareStatement("select * from (select s.*, rownum rn from series s) where rn > ? and rn < ? ");
    	ps.setInt(1, start);
    	ps.setInt(2, send);
    	ResultSet rs = ps.executeQuery();
    	while(rs.next()) {
    		s = new Series();
    		s.setSeries_id(rs.getInt("SERIES_ID"));
    		s.setS_name(rs.getString("s_name"));
    		s.setPublisher_name(getPublisherName(rs.getInt("publisher_id")));
    		s.setYear(rs.getInt("year"));
    		s.setOriginal_name(rs.getNString("original_name"));
    		s.setPosterurl(rs.getString("posterurl"));
    		series.add(s);
    	}
    	rs.close();
    	ps.close();
    	return series;
	}
	public int getSeriesCount(String  name) throws SQLException {
		int id;
		if (name == null) {
			id = 0;
		}
		else {
			id = Integer.parseInt(name);
		}
		PreparedStatement ps;
		ResultSet rs;
		int count;
		if(id != 0) {
			ps = connection.prepareCall("select count(*) as count from series where publisher_id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			rs.next();
			count = rs.getInt("count");
			rs.close();
			ps.close();
			return count;
		}
		ps = connection.prepareCall("select count(*) as count from series");
		rs = ps.executeQuery();
		rs.next();
		count = rs.getInt("count");
		rs.close();
		ps.close();
		return count;
		
	}

	public int[] getArrayOfPage(String parameter,int curPage) throws SQLException {
		int seriesCount = getSeriesCount(parameter);
		int[] pages = new int[5];
		int pagesCount = (int)Math.ceil(seriesCount/20.0);
		System.out.println("param " + parameter);
		System.out.println("seriesCount " + seriesCount);
		System.out.println("curPage " + curPage);
		
    	if(curPage == 1 || curPage <= 3) {
    		for(int i = 1; i <= 5; i++) {
    			System.out.println(i);
    			pages[i-1] = i;
    		}
    	}
    	else if(curPage >= pagesCount - 2) {
    		for(int i = 1; i <= 5; i++) {
    			System.out.println(i);
    			pages[i-1] = pagesCount -(5-i) ;

    		}
    	}
    	else {
    		for(int i = 1; i <= 5; i++) {
    			System.out.println(i);
    			pages[i-1] = curPage - (3 - i);
    		}
    	}
		return pages;
	}

	public ArrayList<Series> getSearchingSeries(String name) throws SQLException {
	ArrayList<Series> series = new ArrayList<Series>();
	name = name.trim();
	Series s;
	PreparedStatement ps = connection.prepareStatement("select * from series where REGEXP_LIKE(lower(original_name),lower(?)) or REGEXP_LIKE(lower(s_name),lower(?))");
	ps.setString(1,name);
	ps.setString(2,name);
	ResultSet rs = ps.executeQuery();
	while(rs.next()) {
		s = new Series();
		s.setSeries_id(rs.getInt("SERIES_ID"));
		s.setS_name(rs.getString("s_name"));
		s.setPublisher_name(getPublisherName(rs.getInt("publisher_id")));
		s.setYear(rs.getInt("year"));
		s.setOriginal_name(rs.getNString("original_name"));
		s.setPosterurl(rs.getString("posterurl"));
		series.add(s);
	}
	rs.close();
	ps.close();
	return series;
	}

	public int getComicsSeria(int parseInt) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("select series_id from comics where comics_id = ?");
		ps.setInt(1, parseInt);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int id = rs.getInt("series_id");
		rs.close();
		ps.close();
		return id;
	}

	public boolean isFirstChapter(int seria_Id, int comics_id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("select comics_id from comics where series_id = ? and rownum = 1");
		ps.setInt(1, seria_Id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		if(rs.getInt("comics_id")==comics_id) {
			return true;
		}

		return false;
	}
	public boolean isLastChapter(int seria_Id, int comics_id) throws SQLException {
		System.out.println("ISLASTCHAPTER SERIA" + seria_Id);
		System.out.println("ISLASTCHAPTER comics" + comics_id);
		PreparedStatement ps = connection.prepareStatement("select comics_id from (select comics_id from comics where series_id = ? order by comics_id desc ) where rownum = 1");
		ps.setInt(1, seria_Id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		System.out.println("ISLASTCHAPTER comics get" + rs.getInt("comics_id"));
		if(rs.getInt("comics_id")==comics_id) {
			return true;
		}

		return false;
	}

			
	


}
