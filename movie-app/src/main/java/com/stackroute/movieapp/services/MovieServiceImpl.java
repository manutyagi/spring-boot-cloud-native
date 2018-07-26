package com.stackroute.movieapp.services;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieapp.repositories.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService{
	
	
	private MovieRepository movieRepository;

	@Autowired
	public MovieServiceImpl(MovieRepository movieRepository) {
		super();
		this.movieRepository = movieRepository;
	}

	@Override
	public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException {
		Iterable<Movie> movies= getAllMovies();
        Iterator<Movie> iterator = movies.iterator();
        while(iterator.hasNext()) {
            Movie m = iterator.next();
            if(movie.equals(m)) {
                throw new MovieAlreadyExistsException("Movie already exists");
            }
        }
		return movieRepository.save(movie);
		
	}
	
	@Override
	public Iterable<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public Optional<Movie> getMovieById(int id) {
		return movieRepository.findById(id);
	}

	@Override
	public void deleteMovie(int id) {
		movieRepository.deleteById(id);
	}

	@Override
	public Movie updateMovie(Movie movie, int id) {
		movie.setId(id);
		return movieRepository.save(movie);
	}

	/*@Override
	public List<Movie> getMovieByTitle(String title) {
		return movieRepository.getMovieByTitle(title);
	}*/
	
	
}
