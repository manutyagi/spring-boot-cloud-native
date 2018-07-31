package com.stackroute.movieapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Entity

@Document
public class Movie {

//	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private int id;
	
	private String title;
	private String year;
	private String imdbId;
	private String type;
	private String poster;
	
	public Movie() {
		
	}

	public Movie(String title, String year, String imdbId, String type,String poster) {
		super();
		this.title = title;
		this.year = year;
		this.imdbId = imdbId;
		this.type = type;
		this.poster = poster;
	}
	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
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
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getImdbId() {
		return imdbId;
	}
	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Movie [title=" + title + ", year=" + year + ", imdbId=" + imdbId + ", type=" + type
				+ "poster="+poster+"]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj==this) {
			return true;
		}
		
		if(!(obj instanceof Movie)) {
			return false;
		}
		Movie mov = (Movie)obj;
		return getTitle().equals(mov.getTitle())&&getImdbId().equals(mov.getImdbId())&&
				getType().equals(mov.getType())&&getYear().equals(mov.getYear())&&getPoster().equals(mov.getPoster());
	}
}
