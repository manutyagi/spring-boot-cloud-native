package com.stackroute.movieapp.services;

import java.util.List;
import java.util.Optional;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exceptions.MovieAlreadyExistsException;

public interface MovieService {

	public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException;
	
	public Iterable<Movie> getAllMovies();
	
	public Optional<Movie> getMovieById(int id);
	
	public void deleteMovie(int id);
	
//	public List<Movie> getMovieByTitle(String title);
	
	public Movie updateMovie(Movie movie, int id);
}
