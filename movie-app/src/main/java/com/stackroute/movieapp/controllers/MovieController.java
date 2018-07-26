package com.stackroute.movieapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieapp.services.MovieService;
import com.stackroute.movieapp.services.MovieServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1")
public class MovieController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	private MovieService movieService;
	@Autowired
	public MovieController(MovieService movieService){
		this.movieService = movieService;
	}
	
	@PostMapping("/movie")
	public ResponseEntity<?> saveMovie(@RequestBody Movie movie) {
		try {
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
			headers.add("Access-Control-Allow-Origin", "*");
			return new ResponseEntity<Movie> (movieService.saveMovie(movie), headers, HttpStatus.CREATED);
		} catch (MovieAlreadyExistsException e) {
			return null;
		}
	}
	
	@GetMapping("/movies")
	public ResponseEntity<?> getAllMovies() {
		
	 
        /*logger.warn("This is a warn message");
        logger.error("This is an error message");*/
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Access-Control-Allow-Origin", "*");
		return new ResponseEntity<Iterable<Movie>> (movieService.getAllMovies(), headers, HttpStatus.OK);
	}
	
	
	@GetMapping("/movie/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable int id) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Access-Control-Allow-Origin", "*");
		return new ResponseEntity<Optional<Movie>> (movieService.getMovieById(id), headers, HttpStatus.OK);
	}
	
	/*@GetMapping("/movieByTitle/{title}")
	public ResponseEntity<?> getMovieByTitle(@PathVariable String title) {
		return new ResponseEntity<List<Movie>> (movieService.getMovieByTitle(title), HttpStatus.OK);
	}*/
	
	@DeleteMapping("/movie/{id}")
	public ResponseEntity<?> deleteMovieById(@PathVariable int id) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Access-Control-Allow-Origin", "*");
		movieService.deleteMovie(id);
		return new ResponseEntity<String> ("Deleted", headers, HttpStatus.OK);
	}
	
	@PutMapping("/movie/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable int id, @RequestBody Movie movie) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Access-Control-Allow-Origin", "*");
		return new ResponseEntity<Movie> (movieService.updateMovie(movie, id), headers, HttpStatus.OK);
	}
}
