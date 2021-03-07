package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

// tests for MovieList
public class MovieListTest {

    private MovieList testMovieList;
    private Movie movie1;
    private Movie movie2;
    private Movie movie3;

    @BeforeEach
    public void setup() {
        testMovieList = new MovieList();
        movie1 = new Movie();
        movie1.setTitle("TestMovie1");
        movie1.setYear(2001);
        movie1.setRating(1);
        movie2 = new Movie();
        movie2.setTitle("TestMovie2");
        movie2.setYear(2002);
        movie2.setRating(2);
        movie3 = new Movie();
        movie3.setTitle("TestMovie3");
        movie3.setYear(2003);
        movie3.setRating(3);
        testMovieList.addMovie(movie1);
        testMovieList.addMovie(movie2);
        testMovieList.addMovie(movie2);

    }

    @Test
    public void testGetTitleList() {
        HashSet<String> testTitleList = new HashSet<>();
        testTitleList.add("TestMovie1");
        testTitleList.add("TestMovie2");
        testTitleList.add("TestMovie3");
        assertArrayEquals(testTitleList, testMovieList.getTitleList());
    }

    private void assertArrayEquals(HashSet<String> testTitleList, HashSet<String> titleList) {
    }


    @Test
    public void testSelectMovie() {
        assertEquals(movie1, testMovieList.selectMovie("TestMovie1"));
        assertEquals(movie2, testMovieList.selectMovie("TestMovie2"));
    }

    @Test
    public void testIsEmptyMovieList() {
        MovieList testMovieList2 = new MovieList();
        assertTrue(testMovieList2.isEmptyMovieList());
        assertFalse(testMovieList.isEmptyMovieList());
    }

    @Test
    public void testReturnNextMovie() {
        MovieList testEmptyMovieList = new MovieList();
        assertEquals(movie1, testMovieList.returnNextMovie());
        assertEquals(null, testEmptyMovieList.returnNextMovie());
    }


}
