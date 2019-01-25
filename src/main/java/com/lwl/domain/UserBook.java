package com.lwl.domain;

public class UserBook {

	private long id;
	private String title;
	private String author;
	private int totalPage;
	
	public UserBook() {}
	public UserBook(String title,String author,int totalPage) {
		this.title = title;
		this.author = author;
		this.totalPage = totalPage;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	@Override
	public String toString() {
		return "UserBook [id=" + id + ", title=" + title + ", author=" + author + ", totalPage=" + totalPage + "]";
	}
	
}
