package persistence;

import model.Movie;
import model.MovieList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//tests for JsonWriter
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            MovieList wr = new MovieList("My movie list");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
        }
    }

    @Test
    void testWriterEmptyMovieList() {
        try {
            MovieList ml = new MovieList("My movie list");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMovieList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMovieList.json");
            ml = reader.read();
            assertEquals("My movie list", ml.getName());
            assertEquals(0, ml.movieListSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    Movie testMovie1() {
        Movie m1 = new Movie();
        m1.setTitle("shrek 2");
        m1.setYear(2004);
        m1.setRating(4);
        return m1;
    }

    Movie testMovie2() {
        Movie m2 = new Movie();
        m2.setTitle("shrek");
        m2.setYear(2001);
        m2.setRating(5);
        return m2;
    }

    @Test
    void testWriterGeneralMovieList() {
        try {
            MovieList ml = new MovieList("My movie list");
            ml.addMovie(testMovie1());
            ml.addMovie(testMovie2());
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMovielist.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMovielist.json");
            ml = reader.read();
            assertEquals("My movie list", ml.getName());
            assertEquals(2, ml.movieListSize());
            checkMovie("shrek 2", 2004, 4, ml.getMovie(0));
            checkMovie("shrek", 2001, 5, ml.getMovie(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
