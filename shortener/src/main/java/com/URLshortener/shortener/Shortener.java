package com.URLshortener.shortener;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Shortener {
	private String url;
	private int id;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Shortener [URL=" + url + ", id=" + id + "]";
	}
	
}
