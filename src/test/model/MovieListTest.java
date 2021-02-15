package model;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import model.MovieList;
import model.Movie;

import static org.junit.jupiter.api.Assertions.*;

public class MovieListTest {

    private MovieList testMovieList;
    private Movie movie1;
    private Movie movie2;
    private Movie movie3;

    @BeforeEach
    public void setup() {
        testMovieList = new MovieList();
        movie1 = new Movie("TestMovie1", 2001, 1);
        movie2 = new Movie("TestMovie2", 2002, 2);
        movie3 = new Movie("TestMovie3", 2003, 3);
        testMovieList.addMovie(movie1);
        testMovieList.addMovie(movie2);
        testMovieList.addMovie(movie3);

    }

    @Test
    public void testGetMovieList() {


    }




}
