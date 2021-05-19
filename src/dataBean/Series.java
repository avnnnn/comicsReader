package dataBean;

public class Series {
	private int seria_id;
	private String s_name;
	private String  publisher_name;
	private int year;
	private String original_name;
	private String posterurl;

	public int getSeries_id() {
		return seria_id;
	}
	public void setSeries_id(int series_id) {
		this.seria_id= series_id;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getPublisher_name() {
		return publisher_name;
	}
	public void setPublisher_name(String publisher_name) {
		this.publisher_name = publisher_name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getOriginal_name() {
		return original_name;
	}
	public void setOriginal_name(String original_name) {
		this.original_name = original_name;
	}
	public String getPosterurl() {
		return posterurl;
	}
	public void setPosterurl(String posterurl) {
		this.posterurl = posterurl;
	}
}
