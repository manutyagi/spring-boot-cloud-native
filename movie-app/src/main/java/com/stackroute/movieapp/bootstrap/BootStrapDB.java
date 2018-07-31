//package com.stackroute.movieapp.bootstrap;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.event.ContextRefreshedEvent;
//
//import com.stackroute.movieapp.domain.Movie;
//import com.stackroute.movieapp.repositories.MovieRepositories;
//
//@Configuration
//public class BootStrapDB implements ApplicationListener<ContextRefreshedEvent>{
////public class BootStrapDB implements CommandLineRunner{
//    //In command line runner we make a new SQL file and put our SQL there .
//	private MovieRepositories movieRepo;
//	
//	@Autowired
//	public BootStrapDB(MovieRepositories movieRepository) {
//		this.movieRepo=movieRepository;
//	}
//	@Override
//	public void onApplicationEvent(ContextRefreshedEvent event) {
//		// TODO Auto-generated method stub
//		Movie.builder().title("Raazhi2").year("2018").imdbId("wwknjkdw").type("thriller").build();
//		Movie movie1=new Movie("abcd","2018","Poster.jpg","comedy");
//		movieRepo.save(movie1);
//		
//	}
//
//	
//}