package model;
import java.sql.*;
import java.util.ArrayList;

import dataBean.Publisher;

public class PublisherDAO{
	Connection connection;
	public PublisherDAO(Connection con){
		connection = con;
	}

	public Publisher  getPublisherById(int id) throws SQLException {
		Publisher publisher = new Publisher();
		PreparedStatement ps = connection.prepareStatement("Select * from publisher where publisher_id = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		publisher.setPublisher_id(rs.getInt("publisher_id"));
		publisher.setOriginal_name(rs.getNString("original_name"));
		publisher.setDescription(rs.getNString("description"));
		publisher.setP_name(rs.getNString("p_name"));
		rs.close();
		ps.close();
		return publisher;
		
		
	}
	public ArrayList<Publisher> getPublishers() throws SQLException{
		ArrayList<Publisher> publishers = new ArrayList<Publisher>();
		PreparedStatement ps = connection.prepareStatement("Select * from publisher order by publisher_id"); 
		ResultSet rs = ps.executeQuery();
		Publisher p;
		while(rs.next()) {
			p = new Publisher();
			p.setPublisher_id(rs.getInt("publisher_id"));
			p.setP_name(rs.getNString("p_name"));
			p.setDescription(rs.getNString("description"));
			p.setOriginal_name(rs.getString("original_name"));
			publishers.add(p);
		}
		return publishers;
	}
	

}
