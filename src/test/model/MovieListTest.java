package model;

import exceptions.InvalidRatingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// tests for MovieList
public class MovieListTest {

    private MovieList testMovieList;
    private Movie movie1;
    private Movie movie2;
    private Movie movie3;

    @BeforeEach
    public void setup() throws InvalidRatingException {
        testMovieList = new MovieList("test movie list");
        movie1 = new Movie();
        movie1.setTitle("TestMovie1");
        movie1.setYear(2001);
        movie1.setRating(1);
        movie2 = new Movie();
        movie2.setTitle("TestMovie2");
        movie2.setYear(2002);
        movie2.setRating(5);
        movie3 = new Movie();
        movie3.setTitle("TestMovie3");
        movie3.setYear(2003);
        movie3.setRating(5);
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

    @Test
    public void testIsEmptyMovieList() {
        MovieList testMovieList2 = new MovieList("test movie list 2");
        assertTrue(testMovieList2.isEmptyMovieList());
        assertFalse(testMovieList.isEmptyMovieList());
    }

    @Test
    public void testReturnNextMovie() {
        MovieList testEmptyMovieList = new MovieList("test empty movie list");
        assertEquals(movie1, testMovieList.returnNextMovie());
        assertEquals(null, testEmptyMovieList.returnNextMovie());
    }


    @Test
    public void testFilterFiveStars() {
        MovieList filteredList = testMovieList.filterFiveStars();
        assertTrue(filteredList.movieListSize() == 2);
        assertTrue(filteredList.returnNextMovie() == movie2);

    }


}
