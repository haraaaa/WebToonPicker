package com.pluu.webtoon.vo;

public class Webtoon{
	
	private String title;
	private String author;
	private String platform;
	private String day;
	private String tag1;
	private String tag2;
	private String tag3;
	private String hits;
	private String grade;
	private String address;
	private int total;
	
	public Webtoon() {	}


	public Webtoon(String title, String author, String platform, String day, String tag1, String tag2, String tag3,
			String hits, String grade, String address) {
		super();
		this.title = title;
		this.author = author;
		this.platform = platform;
		this.day = day;
		this.tag1 = tag1;
		this.tag2 = tag2;
		this.tag3 = tag3;
		this.hits = hits;
		this.grade = grade;
		this.address = address;
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

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTag1() {
		return tag1;
	}

	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}

	public String getTag2() {
		return tag2;
	}

	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}

	public String getTag3() {
		return tag3;
	}

	public void setTag3(String tag3) {
		this.tag3 = tag3;
	}

	public String getHits() {
		return hits;
	}

	public void setHits(String hits) {
		this.hits = hits;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public String toString() {
		return "title="+title+", author="+author+", platform="+platform+", day="+day
				+", tag1="+tag1+", tag2="+tag2+", tag3="+tag3+", hits="+hits
				+", grade="+grade+", address="+address;
	}

}
