package persistence;

import model.Movie;
import model.MovieList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//A reader that reads MovieList from JSON data stored in file
//Citation: code obtained from JsonSerializationDemo
//          URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads MovieList from file and returns it
    //throws IOException if an error occurs reading data from file
    public MovieList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMovieList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS:parses MovieList from JSON object and returns it
    private MovieList parseMovieList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        MovieList ml = new MovieList(name);
        addMovies(ml, jsonObject);
        return ml;
    }

    //MODIFIES: ml
    //EFFECTS: parses movies from JSON object and adds them to MovieList
    private void addMovies(MovieList ml, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("movies");
        for (Object json : jsonArray) {
            JSONObject nextMovie = (JSONObject) json;
            addMovie(ml, nextMovie);
        }
    }

    //MODIFIES: ml
    //EFFECTS: parses movie from JSON object and adds it to MovieList
    private void addMovie(MovieList ml, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        int year = jsonObject.getInt("year");
        int rating = jsonObject.getInt("rating");
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setYear(year);
        movie.setRating(rating);
        ml.addMovie(movie);

    }


}
