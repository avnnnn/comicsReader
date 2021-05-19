package dataBean;

public class Comics {
	private int comics_id;
	private String c_name;
	private int publisher_id;
	private int series_id;
	private String original_name;
	private String posterurl;

	
	public int getComics_id() {return comics_id;}
	public String getC_name() {return c_name;}
	public int getPublisher_id() {return publisher_id;}
	public int getSeries_id() {return series_id;}
	public String getOriginal_name() {return original_name;}
	public String getPosterurl() {return posterurl;}
	
	public void setComics_id(int comics_id) {this.comics_id = comics_id;}
	public void setC_name(String c_name) {this.c_name = c_name;}
	public void setPublisher_id(int publisher_id) {this.publisher_id = publisher_id;}
	public void setSeries_id(int series_id) {this.series_id = series_id;}
	public void setOriginal_name(String original_name) {this.original_name = original_name;}
	public void setPosterurl(String posterurl) {this.posterurl = posterurl;}
	
	
	public String toString() {
		String ret = "comix_id: " + comics_id + "\n"
				+ "c_name: " + c_name;
		return ret;
	}
}
