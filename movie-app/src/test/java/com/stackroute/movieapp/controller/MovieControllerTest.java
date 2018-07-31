package com.stackroute.movieapp.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exception.MovieAlreadyExistsException;
import com.stackroute.movieapp.exception.MovieNotFoundException;
import com.stackroute.movieapp.services.MovieService;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {
    @Autowired
    private MockMvc movieMockMvc;
    
    private Movie movie;
    private Optional<Movie> optMovie; 
    
    @MockBean
    MovieService movieService;
    
//    @InjectMocks
//    private MovieController movieController;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        movie = new Movie("Sacred Games","2018","asadf","thriller","poster");
        optMovie = Optional.of(movie);
    }
    @Test
    public void testDisplayRestaurant() throws Exception{
        movieMockMvc.perform(get("/api/v1/home"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello"));
    }
    
    @Test
    public void getAllMoviesTest() throws Exception{
        when(movieService.getAllMovies()).thenReturn(null);
        movieMockMvc.perform(get("/api/v1/getAllMovies"))
                 .andExpect(status().isOk());
    }
    
    @Test
    public void testSaveMovieSuccess()throws Exception{    	
    	when(movieService.saveMovie(movie)).thenReturn(movie);
    	movieMockMvc.perform(post("/api/v1/saveMovie",movie)
    				.contentType(MediaType.APPLICATION_JSON)
    				.content(asJsonString(movie)))
    				.andExpect(status().isCreated());					 
    }
    
    @Test
	public void testSaveMovieFailure() throws MovieAlreadyExistsException, Exception {
		when(movieService.saveMovie(movie)).thenThrow(MovieAlreadyExistsException.class);
		movieMockMvc.perform(post("/api/v1/saveMovie").contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
				.andExpect(status().isConflict());
	}
    
    @Test
    public void testDeleteMovieSuccess() throws Exception {
        when(movieService.deleteMovie(anyInt())).thenReturn(true);
        movieMockMvc.perform(delete("/api/v1/deleteMovie/10").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
	public void testDeleteMovieFailure() throws MovieNotFoundException,Exception {
		when(movieService.deleteMovie(anyInt())).thenThrow(MovieNotFoundException.class);
		movieMockMvc.perform(delete("/api/v1/deleteMovie/10"))
				.andExpect(status().isConflict());
				
	}
    
    @Test
    public void testUpdateMovieSucess() throws Exception{
    	when(movieService.updateMovie(any(),anyInt())).thenReturn(movie);
    	
    	movieMockMvc.perform(put("/api/v1/updateMovie/10",movie).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(movie))).andExpect(status().isOk());
    }
    
    @Test
	public void testUpdateMovieFailure() throws MovieNotFoundException,Exception {
		when(movieService.updateMovie(any(),anyInt())).thenThrow(MovieNotFoundException.class);
		movieMockMvc.perform(put("/api/v1/updateMovie/10",movie).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(movie))).andExpect(status().isConflict());
		
	}
    
    @Test
    public void testGetMovieByIdSuccess() throws Exception{
    	 when(movieService.getMovieById(movie.getId())).thenReturn(optMovie);
    	 movieMockMvc.perform(get("/api/v1/getMovieById/{id}", movie.getId())).andExpect(status().isOk());

    }
    
    @Test
    public void testGetMovieByIdFailure() throws MovieNotFoundException,Exception {
        when(movieService.getMovieById(anyInt())).thenThrow(MovieNotFoundException.class);
        movieMockMvc.perform(get("/api/v1/getMovieById/1")).andExpect(status().isConflict());
    }
   
    public static String asJsonString(final Object obj) {
        try {

            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}