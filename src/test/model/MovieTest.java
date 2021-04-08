package model;

import exceptions.InvalidRatingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

// tests for Movie
class MovieTest {
    private Movie testMovie;

    @BeforeEach
    void setup() throws InvalidRatingException {
        testMovie = new Movie();

    }

    @Test
    public void testMovieConstructor() {
        assertEquals(null, testMovie.getTitle());
        assertEquals(0, testMovie.getYear());
        assertEquals(0, testMovie.getRating());
    }

    @Test
    public void testGetTitle() {
        testMovie.setTitle("TestName");
        assertEquals("TestName", testMovie.getTitle());
    }

    @Test
    public void testGetYear() {
        testMovie.setYear(2000);
        assertEquals(2000, testMovie.getYear());
    }

    @Test
    public void testGetRating() throws InvalidRatingException {
        testMovie.setRating(3);
        assertEquals(3, testMovie.getRating());
    }

    @Test
    public void testSetTitle() {
        testMovie.setTitle("NewTestTitle");
        assertEquals("NewTestTitle", testMovie.getTitle());
    }

    @Test
    public void testSetYear() {
        testMovie.setYear(1999);
        assertEquals(1999, testMovie.getYear());
    }

    @Test
    public void testSetRatingValidRating() {
        try {
            testMovie.setRating(5);
        } catch (InvalidRatingException e) {
            fail("rating could not be set");
        }
        assertEquals(5, testMovie.getRating());
    }

    @Test
    public void testSetRatingInvalidRating() {
        try {
            testMovie.setRating(-1);
            fail("rating was set, even though it wasn't supposed to");
        } catch (InvalidRatingException e) {
            //expected
        }

    }


}