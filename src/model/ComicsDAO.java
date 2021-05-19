package model;
import java.sql.*;
import java.util.ArrayList;
import dataBean.Comics;
public class ComicsDAO {
	Connection connection;
    public ComicsDAO(Connection con )  {
    	connection = con;
    }
    public ArrayList<Comics> getComicsInSeria(int seria) throws SQLException {
    	ArrayList<Comics> comicses = new ArrayList<Comics>();
    	Comics comics;
    	PreparedStatement ps = connection.prepareStatement("Select * from comics where series_id = ? order by comics_id ");
    	ps.setInt(1, seria);
    	ResultSet rs = ps.executeQuery();
    	while(rs.next()) {
    		comics = new Comics();
       		comics.setComics_id(rs.getInt("comics_id"));
    		comics.setC_name(rs.getNString("c_name"));
    		comics.setPublisher_id(rs.getInt("publisher_id"));
    		comics.setSeries_id(rs.getInt("series_id"));
    		comics.setOriginal_name(rs.getNString("original_name"));
    		comics.setPosterurl(rs.getNString("posterurl"));
    		comicses.add(comics);
    	}
    	return comicses;
    }
    public int getFirstComicsIdOfSeria(int id) throws SQLException {
    	int comicsId;
    	PreparedStatement ps = connection.prepareStatement("Select comics_id from comics where series_id = ?");
    	ps.setInt(1, id);
    	ResultSet rs = ps.executeQuery();
    	rs.next();
    	comicsId = rs.getInt("comics_id");
    	rs.close();
    	ps.close();
    	return comicsId;
    }
}
