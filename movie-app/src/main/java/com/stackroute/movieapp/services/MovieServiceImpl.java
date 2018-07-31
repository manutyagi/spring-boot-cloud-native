package com.stackroute.movieapp.services;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exception.MovieAlreadyExistsException;
import com.stackroute.movieapp.exception.MovieNotFoundException;
import com.stackroute.movieapp.repositories.MovieRepositories;

@Service
//@Primary
public class MovieServiceImpl implements MovieService{
	
	MovieRepositories movieRepositories;
	
	@Autowired
	NextSequenceService nextSequenceService;
	
	@Autowired
	public MovieServiceImpl(MovieRepositories movieRepositories) {
		super();
		this.movieRepositories = movieRepositories;
	}
	
	public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException {
		Iterable<Movie> movies = getAllMovies();
		Iterator<Movie> iterator = movies.iterator();
		
		while(iterator.hasNext()) {
			Movie m = iterator.next();
			if(movie.equals(m)) {
				throw new MovieAlreadyExistsException("MovieAlreadyExistsException");
			}
		}	
		movie.setId(nextSequenceService.getNextSequence("counter"));
		return movieRepositories.save(movie);
	}
	
	public Iterable<Movie> getAllMovies(){
		return movieRepositories.findAll();
	}
	
	public boolean deleteMovie(int id) throws MovieNotFoundException {
		if(getMovieById(id)!=null) {
		 movieRepositories.deleteById(id);
		 return true;
		}else {
			throw new MovieNotFoundException("MovieNotFoundException");
		}	
	}
	
	public Movie updateMovie(Movie movie ,int id) throws MovieNotFoundException {
		Optional<Movie> movieToUpdate =getMovieById(id);
			if(movieToUpdate.isPresent()) {
				movie.setId(id);
				return movieRepositories.save(movie);
			}else {
				throw new MovieNotFoundException("MovieNotFoundException");
			}
		
		
	}
	
	public Optional<Movie> getMovieById(int id) throws MovieNotFoundException{
		Optional<Movie> movie = movieRepositories.findById(id);
		if(movie.isPresent()) {
			return movie;
		}else {
			throw new MovieNotFoundException("MovieNotFoundException");
		}
		
	}
	
//	public Movie getMovieByTitle(String title) {
//		return movieRepositories.getMovieByTitle(title);
//	}
}
