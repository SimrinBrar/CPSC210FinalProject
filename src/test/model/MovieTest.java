package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

// tests for Movie
class MovieTest {
    private Movie testMovie;

    @BeforeEach
    void setup() {
        testMovie = new Movie();
    }

    @Test
    public void testMovieConstructor() {
        assertEquals("TestName", testMovie.getTitle());
        assertEquals(2000, testMovie.getYear());
        assertEquals(3, testMovie.getRating());
    }

    @Test
    public void testGetTitle() {
        assertEquals("TestName", testMovie.getTitle());
    }

    @Test
    public void testGetYear() {
        assertEquals(2000, testMovie.getYear());
    }

    @Test
    public void testGetRating() {
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
    public void testSetRating() {
        testMovie.setRating(5);
        assertEquals(5, testMovie.getRating());
    }


}