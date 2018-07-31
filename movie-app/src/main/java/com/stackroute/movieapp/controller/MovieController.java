package com.stackroute.movieapp.controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.LogManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exception.MovieAlreadyExistsException;
import com.stackroute.movieapp.exception.MovieNotFoundException;
import com.stackroute.movieapp.services.MovieService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class MovieController {
	
	/*@Autowired
	@Qualifier("MovieServiceImpl")*/
	MovieService movieService;
	
	@Autowired
	Environment env;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//Comment the constructor so as to perform unit testing 
	
	@Autowired
	public MovieController(MovieService movieService) {
		super();
		this.movieService = movieService;
	}
	
	@RequestMapping(value = "/home" , method = RequestMethod.GET)
	public String displayRestaurant() {
		return "Hello";
	}
	@PostMapping("/saveMovie")
	public ResponseEntity<?> saveMovie(@RequestBody Movie movie){
		
		try {
	        logger.info("Movie = "+movie);
	        logger.debug("Inside saveMovie()");
	        movieService.saveMovie(movie);
	        System.out.println(env.getProperty("com.stackroute.username"));
	        return new ResponseEntity<Movie>(movie,HttpStatus.CREATED);
		} catch (MovieAlreadyExistsException e) {
			logger.error("The Movie already exists");
			return new ResponseEntity<Movie>(movie,HttpStatus.CONFLICT);
		}
		
	}
	
	@GetMapping("/getAllMovies")
	public ResponseEntity<?> getAllMovies(){
		logger.info("All movies = "+movieService.getAllMovies());
        logger.debug("Inside getAllMovies()");
		return new ResponseEntity<Iterable<Movie>>(movieService.getAllMovies(),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deleteMovie/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable int id){
		try {
			logger.info("Id"+id);
			logger.debug("Inside deleteMovie()");
			movieService.deleteMovie(id);
			return new ResponseEntity<String>("Deleted successfully",HttpStatus.OK);
			
		}catch (MovieNotFoundException e) {
			logger.error("The Movie does't exist");
			return new ResponseEntity<String>("Cannot be deleted beacause movie doesn't exist",HttpStatus.CONFLICT);
		}		
	}
	
	@PutMapping("updateMovie/{id}")
	public ResponseEntity<?> updateMovie(@RequestBody Movie movie ,@PathVariable int id){
		
			try {
				logger.info("Movie = "+movie+" Id"+id);
				logger.debug("Inside updateMovie()");
				movieService.updateMovie(movie, id); 
				return new ResponseEntity<Movie>(movieService.updateMovie(movie,id),HttpStatus.OK);
				
			} catch (MovieNotFoundException e) {
				logger.error("The Movie does't exist");
				return new ResponseEntity<String>("Cannot be updated,please check if the id is correct",HttpStatus.CONFLICT);	
			}	
		}
	
	@GetMapping("/getMovieById/{id}")
	public ResponseEntity<?>getMovieById(@PathVariable int id){
		Optional<Movie> movies;
		try {
			logger.info("Id"+id);
			logger.debug("Inside getMovieById()");
			movies = movieService.getMovieById(id);
			return new ResponseEntity<Optional<Movie>>(movies,HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			logger.error("The Movie does't exist");
			return new ResponseEntity<String>("No such movie found",HttpStatus.CONFLICT);
		}
		
	}
	
//	@GetMapping("/getMovieByTitle/{title}")
//	public ResponseEntity<?> getMovieByTitle(@PathVariable String title){
//		return new ResponseEntity<Movie>(movieService.getMovieByTitle(title),HttpStatus.OK);
//		
//	}
}
