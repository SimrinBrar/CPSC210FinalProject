package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

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
        testMovieList.addMovie(movie2);

    }

    @Test
    public void testGetTitleList() {
        ArrayList<String> testTitleList = new ArrayList<>();
        testTitleList.add("TestMovie1");
        testTitleList.add("TestMovie2");
        testTitleList.add("TestMovie3");
        assertArrayEquals(testTitleList, testMovieList.getTitleList());
    }

    private void assertArrayEquals(ArrayList<String> testTitleList, ArrayList<String> titleList) {
    }


    @Test
    public void testSelectMovie() {
        assertEquals(movie1, testMovieList.selectMovie("TestMovie1"));
        assertEquals(movie2, testMovieList.selectMovie("TestMovie2"));
    }

//    @Test
//    public void testAddMovie() {
//
//    }


}
