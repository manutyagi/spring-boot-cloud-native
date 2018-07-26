package com.stackroute.movieapp.repositories;


import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stackroute.movieapp.domain.Movie;
@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer>{
	/*@Query("db.Hike.find({}, { \"difficulty\": 1})") 
    public List<Movie> getMovieByTitle(@Param("title") String title);*/
}
