/*package com.stackroute.movieapp.repositoriestest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.repositories.MovieRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MovieRepositoryTest {

    *//**
     * referencing MovieRepository object
     *//*
    @Autowired
    private transient MovieRepository repo;

    public void setRepo(final MovieRepository repo) {
        this.repo = repo;
    }

    @Test
    public void testSaveMovie() throws Exception {
        repo.save(new Movie(100, "POC", "good Movie", "www.abc.com", "2015-03-23"));
        final Optional<Movie> movie = repo.findById(100);
        assertThat(movie.get().getId()).isEqualTo(100);
    }
}*/