package com.stackroute.movieapp.services;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyString;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exception.MovieAlreadyExistsException;
import com.stackroute.movieapp.exception.MovieNotFoundException;
import com.stackroute.movieapp.repositories.MovieRepositories;
import com.stackroute.movieapp.services.MovieService;
import com.stackroute.movieapp.services.MovieServiceImpl;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;

public class MovieServiceImplTest{
	
@Mock
private transient MovieRepositories movieRepository;

@Mock
NextSequenceService nextSequenceService;


private transient Movie movie1;
private transient Movie movie;
/**
 * injecting mocks in MovieServiceImpl object
 */
@InjectMocks
private transient MovieServiceImpl movieServiceImpl;

/**
 * variable to hold user defined movies list
 */
private transient Optional<Movie> options;

/**
 * Initializing the declarations
 */
@Before
public void setupMock() {
	MockitoAnnotations.initMocks(this);
	movie = new Movie( "POC", "good Movie", "www.abc.com", "2015-03-31","poster");
	options = Optional.of(movie);
	movie1= new Movie("Race3","sick movie","www.xyz.com","2015-03-28","poster1");
}

/**
 * testing mock creation
 */
@Test
public void testMockCreation() {
	assertNotNull("jpaRepository creation fails: use @injectMocks on movieServicempl", movieRepository);
}

/**
 * testing the save method
 * 
 * @throws MovieAlreadyExistsException
 */
/*@Test
public void testSaveMovie() throws Exception {

	when(movieRepository.save(movie)).thenReturn(movie);
	Movie status = movieServiceImpl.saveMovie(movie);
	assertEquals(movie, status);
}*/

/*
 * @Test public void testSaveMovieSuccess() throws MovieAlreadyExistException {
 * when(movieRepository.save(movie)).thenReturn(movie); Movie movie =
 * movieServiceImpl.saveMovie(movie); //
 * assertTrue("saving movie failed , the call to movieDAOImpl is returning false ,check this method"
 * ); verify(movieRepository, times(1)).save(movie); verify(movieRepository,
 * times(1)).findById(movie.getId()); }
 */
//@Test
//public void saveMovieTest() throws MovieAlreadyExistsException{
//	ArrayList<Movie> movieList= new ArrayList<Movie>();
//	movieList.add(movie);
//	Iterable<Movie> movieIterable=movieList;
//	when(movieRepository.findAll()).thenReturn(movieIterable);
//	when(movieRepository.save(movie1)).thenReturn(movie1);
//	when(nextSequenceService.getNextSequence(anyString())).thenReturn(1);
//	assertEquals(movie1, movieServiceImpl.saveMovie(movie1));
//	
//}


@Test
public void getMovieByIdTest() throws MovieNotFoundException {
	Optional<Movie> movieO = Optional.of(movie);
	when(movieRepository.findById(5)).thenReturn(movieO);
	assertEquals(movieO, movieServiceImpl.getMovieById(5));
}

@Test
public void updateMovieSuccessTest() throws MovieNotFoundException {
	//Optional<Movie> movieOd = Optional.of(movie);
	when(movieRepository.findById(anyInt())).thenReturn(options);
	when(movieRepository.save(movie)).thenReturn(movie);
	assertEquals(movie, movieServiceImpl.updateMovie(movie, anyInt()));
}

@Test
public void testDeleteMovie() throws Exception {
	when(movieRepository.findById(anyInt())).thenReturn(options);
	doNothing().when(movieRepository).deleteById(movie.getId());
	boolean status = movieServiceImpl.deleteMovie(movie.getId());
	
	assertEquals(true, status);
}

@Test(expected=MovieNotFoundException.class)
public void testDeleteMovieFailure() throws Exception{
	when(movieRepository.findById(anyInt())).thenReturn(Optional.empty());
	movieServiceImpl.deleteMovie(anyInt());
	
}

}