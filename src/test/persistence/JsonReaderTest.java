package persistence;

import model.MovieList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//tests for JsonReader
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MovieList ml = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
        }
    }

    @Test
    void testReaderEmptyMovieList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMovieList.json");
        try {
            MovieList ml = reader.read();
            assertEquals("My movie list", ml.getName());
            assertEquals(0, ml.movieListSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMovieList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMovieList.json");
        try {
            MovieList ml = reader.read();
            assertEquals("My movie list", ml.getName());
            assertEquals(2, ml.movieListSize());
            checkMovie("shrek", 2001, 5, ml.getMovie(0));
            checkMovie("shrek 2", 2004, 4, ml.getMovie(1));

        } catch (IOException e) {
            fail("couldn't read from fle");
        }


    }
}
