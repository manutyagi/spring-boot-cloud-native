package com.stackroute.movieapp.servicestest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.repositories.MovieRepository;
import com.stackroute.movieapp.services.MovieService;
import com.stackroute.movieapp.services.MovieServiceImpl;

@RunWith(SpringRunner.class)
public class MovieServiceTest {
	
    @MockBean
	private MovieRepository movieRepository;
	
    @InjectMocks
    private MovieServiceImpl movieService;
    
    private Movie movie;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        movie = new Movie(6, "Jhon123", "Jhon Simon", "123456", "9872367384");
    }
    
    @Test
    public void saveMovieTest() throws Exception{
    	when(movieRepository.save(movie)).thenReturn(movie);
    	assertEquals(movie, movieService.saveMovie(movie));
    }
    
    @Test
    public void getMovieById() throws Exception{
    	Optional<Movie> movieOp = Optional.of(movie);;
    	when(movieRepository.findById(6)).thenReturn(movieOp);
    	assertEquals(movieOp, movieService.getMovieById(6));
    }
}
