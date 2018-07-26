package com.stackroute.movieapp.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Movie {
	@Id
	private int id;
	private String title;
	private String imdbId;
	private String poster;
	private String year;
	
	public Movie() {
		
	}
	public Movie(int id, String title, String imdbId, String poster, String year) {
		super();
		this.id=id;
		this.title = title;
		this.imdbId = imdbId;
		this.poster = poster;
		this.year = year;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImdbId() {
		return imdbId;
	}
	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", imdbId=" + imdbId + ", poster=" + poster + ", year=" + year
				+ "]";
	}
	
	
	
	
}
