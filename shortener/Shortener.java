package com.URLshortener.shortener;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Shortener {
	private String longURL;
	private String shortURL;
	private int id;
	
	public String getLongURL() {
		return longURL;
	}
	public void setLongURL(String longURL) {
		this.longURL = longURL;
	}
	public String getShortURL() {
		return shortURL;
	}
	public void setShortURL(String shortURL) {
		this.shortURL = shortURL;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Shortener [longURL=" + longURL + ", shortURL=" + shortURL + ", id=" + id + "]";
	}
	
}
