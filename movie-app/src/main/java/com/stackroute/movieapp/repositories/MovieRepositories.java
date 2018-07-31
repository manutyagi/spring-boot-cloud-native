package com.stackroute.movieapp.repositories;

import org.springframework.data.mongodb.repository.Query;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stackroute.movieapp.domain.Movie;

@Repository
public interface MovieRepositories extends CrudRepository<Movie, Integer>{
//	@Query("Select m from Movie m where m.title = ?title")
//	public Movie getMovieByTitle(@Param("title") String title);
	
	
//	@Query("From Movie m where m.title = :title")
//	 public Movie getMovieByTitle(@Param("title") String title);
}
