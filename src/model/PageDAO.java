package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataBean.Page;

public class PageDAO {
	Connection con;
	public PageDAO(Connection con) {
		this.con = con;
	}
	
	public ArrayList<Page> getComicsPages(int id) throws SQLException{
		ArrayList<Page> pages = new ArrayList<Page>();
		PreparedStatement ps = con.prepareStatement("Select * from pages where comics_id = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Page p;
		while(rs.next()) {
			p = new Page();
			p.setComics_id(rs.getInt("comics_id"));
			p.setPage(rs.getInt("page"));
			p.setUrl(rs.getNString("url"));
			pages.add(p);
		}
		rs.close();
		ps.close();
		return pages;
	}
	
	
}
