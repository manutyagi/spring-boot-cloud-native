
package com.stackroute.movieapp.controllerstest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.movieapp.controllers.MovieController;
import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.services.MovieService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {
    @Autowired
    private MockMvc movieMockMvc;
    @MockBean
	private MovieService movieService;
	
    private Movie movie;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        movie = new Movie(6, "Jhon123", "Jhon Simon", "123456", "9872367384");
    }
    
    @Test
    public void getAllMoviesTest() throws Exception{
        when(movieService.getAllMovies()).thenReturn(null);
        movieMockMvc.perform(get("/api/v1/movies"))
				.andExpect(status().isOk());
    }
    
    @Test
    public void saveMovieTest() throws Exception{
        when(movieService.saveMovie(movie)).thenReturn(movie);
        movieMockMvc.perform(post("/api/v1/movie").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
				.andExpect(status().isCreated());
    }
    
    public static String asJsonString(final Object obj) {
		try {

			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}