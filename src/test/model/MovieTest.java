package model;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
    private Movie testMovie;

    @BeforeEach
    void setup() {
        testMovie = new Movie("TestName", 2000, 3);
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
    public void testSetRating() {
        testMovie.setRating(5);
        assertEquals(5, testMovie.getRating());
    }

}