package model;


import java.sql.*;

import dataBean.Comics;
import dataBean.User;


public class UserDAO{
	Connection connection;
	public UserDAO(Connection con){
		connection = con;
	}
	public int insertUser(User user) throws SQLException  {
		CallableStatement cs = connection.prepareCall("{? = call add_user(?,?,?)}");
		cs.registerOutParameter(1, Types.INTEGER);
		cs.setNString(2,user.getUsername());
		cs.setNString(3,user.getPassword());
		cs.setNString(4,user.getEmail());
		cs.executeUpdate();
		int t = cs.getInt(1);
		System.out.println("USERIIIIID"+t);
		return t;
	}
	public User findUser(String email, String password) throws SQLException {
		User user = new User();
		CallableStatement cs = connection.prepareCall("{? = call get_user_id(?,?)}");
		cs.registerOutParameter(1, Types.INTEGER);
		cs.setNString(2, email);
		cs.setNString(3, password);
		cs.executeUpdate();
		if(cs.getInt(1) != 0) {
			PreparedStatement ps = connection.prepareStatement("Select * from users where user_id = ?");
			ps.setInt(1, cs.getInt(1));	
			ResultSet rs = ps.executeQuery();
			rs.next();
			user.setId(rs.getInt("user_id"));
			user.setEmail(rs.getNString("email"));
			user.setUsername(rs.getNString("username"));
			user.setPassword(rs.getNString("password"));
			cs.close();
			ps.close();
			rs.close();
			return user;
		}
		return null;
	}
	public void addToList(User user, int seria_id) throws SQLException {
		System.out.println("user_id: " + user.getId());
		System.out.println("seria_id: " + seria_id);
		CallableStatement cs= connection.prepareCall("{call add_to_list(?,?)}");
		System.out.println(user.getId());
		cs.setInt(1, user.getId());
		cs.setInt(2, seria_id);
		cs.executeUpdate();
		cs.close();
	}

	public void deleteSeries(User user, int id) throws SQLException {
		CallableStatement cs = connection.prepareCall("{call delete_from_list(?,?)}");
		cs.setInt(1,user.getId());
		cs.setInt(2,id);
		cs.execute();
		cs.close();
	}
	public boolean seriaInList(User user, int id) throws SQLException {
		System.out.println("user_id: " +user.getId());
		System.out.println("seria_id: " +id);
		CallableStatement cs = connection.prepareCall("{? = call seria_in_list(?,?)} ");
		cs.registerOutParameter(1, Types.INTEGER);
		cs.setInt(2, user.getId());
		cs.setInt(3, id);
		cs.executeUpdate();
		int res = cs.getInt(1);
		cs.close();
		if (res == 0) {
			return false;
		}
		return true;
	}
	public Comics getLastComics(User user,int series_id) throws SQLException {
		Comics c = new Comics();
		PreparedStatement ps = connection.prepareStatement("select * from comics where comics_id = (select last_chapter from user_list where user_id = ? and series_id =?)");
		ps.setInt(1, user.getId());
		ps.setInt(2, series_id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		c.setComics_id(rs.getInt("COMICS_ID"));
		c.setC_name(rs.getNString("c_name"));
		c.setOriginal_name(rs.getNString("original_name"));
		c.setPosterurl(rs.getNString("posterurl"));
		c.setSeries_id(rs.getInt("series_id"));
		c.setPublisher_id(rs.getInt("publisher_id"));
		rs.close();
		ps.close();
		return c;
	}
	public void updateLastChapter(User user, int series_id, int parseInt ) throws SQLException {
		CallableStatement cs = connection.prepareCall("{call update_user_list(?,?,?)");
		cs.setInt(1,parseInt);
		cs.setInt(2,user.getId());
		cs.setInt(3,series_id);
		cs.execute();
		cs.close();
		// TODO Auto-generated method stub
		
	}
}
