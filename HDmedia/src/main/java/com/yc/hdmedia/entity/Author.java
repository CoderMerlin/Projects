package com.yc.hdmedia.entity;

public class Author {
	private int author_id;
	private String author_name;
	private String author_sex;
	private String author_time;
	private String author_area;
	private String author_photo;
	private String author_details;
	private int author_weight=0;
	private String author_type;
	private int status;
	@Override
	public String toString() {
		return "Author [author_id=" + author_id + ", author_name="
				+ author_name + ", author_sex=" + author_sex + ", autor_time="
				+ author_time + ", autor_area=" + author_area + ", autor_photo="
				+ author_photo + ", autor_details=" + author_details
				+ ", autor_weight=" + author_weight + ", autor_type="
				+ author_type + ", status=" + status + "]";
	}
	public int getAuthor_id() {
		return author_id;
	}
	public int getAuthor_ids() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public String getAuthor_sex() {
		return author_sex;
	}
	public void setAuthor_sex(String author_sex) {
		this.author_sex = author_sex;
	}
	public String getAuthor_time() {
		return author_time;
	}
	public void setAuthor_time(String autor_time) {
		this.author_time = autor_time;
	}
	public String getAuthor_area() {
		return author_area;
	}
	public void setAuthor_area(String autor_area) {
		this.author_area = autor_area;
	}
	public String getAuthor_photo() {
		return author_photo;
	}
	public void setAuthor_photo(String author_photo) {
		this.author_photo = author_photo;
	}
	public String getAuthor_details() {
		return author_details;
	}
	public void setAuthor_details(String author_details) {
		this.author_details = author_details;
	}
	public int getAuthor_weight() {
		return author_weight;
	}
	public void setAuthor_weight(int author_weight) {
		this.author_weight = author_weight;
	}
	public String getAuthor_type() {
		return author_type;
	}
	public void setAuthor_type(String author_type) {
		this.author_type = author_type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Author(int author_id, String author_name, String author_sex,
			String autor_time, String autor_area, String autor_photo,
			String autor_details, int autor_weight, String autor_type, int status) {
		super();
		this.author_id = author_id;
		this.author_name = author_name;
		this.author_sex = author_sex;
		this.author_time = autor_time;
		this.author_area = autor_area;
		this.author_photo = autor_photo;
		this.author_details = autor_details;
		this.author_weight = autor_weight;
		this.author_type = autor_type;
		this.status = status;
	}
	
	
	public Author() {
		super();
	}
	
	
	
}
