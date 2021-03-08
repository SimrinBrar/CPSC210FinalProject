package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;


//represents a list of movie objects
public class MovieList implements Writable {
    private ArrayList<Movie> movieList;
    private String name;

    //EFFECTS: creates an empty list of movies
    public MovieList(String name) {
        this.name = name;
        movieList = new ArrayList<>();
    }

    //getters
    public String getName() {
        return name;
    }

    public Movie getMovie(int n) {
        return movieList.get(n);
    }

    //EFFECTS: returns a list of movie titles
    public ArrayList<String> getTitleList() {
        ArrayList<String> lst = new ArrayList<>();
        for (Movie movie : movieList) {
            String t = movie.getTitle();
            lst.add(t);
        }
        return lst;
    }

    //EFFECTS: returns the number of movies in a movie list
    public int movieListSize() {
        return movieList.size();
    }

    //MODIFIES: this
    //EFFECTS: adds a movie to a list
    public void addMovie(Movie addedMovie) {
        movieList.add(addedMovie);
    }

    //EFFECTS: takes in a string and searches list for movie titles match that string.
    // returns movie if found and null if not found
    public Movie selectMovie(String selectedMovie) {
        for (Movie movie : movieList) {
            if (movie.getTitle().equals(selectedMovie)) {
                return movie;
            }
        }
        return null;
    }

    //EFFECTS: returns false if there are movies in list and
    //         returns true otherwise
    public boolean isEmptyMovieList() {
        return movieList.isEmpty();
    }

    //EFFECTS:returns the next movie in the array list and null if
    //        there is no next movie
    public Movie returnNextMovie() {
        if (!movieList.isEmpty()) {
            return movieList.iterator().next();
        } else {
            return null;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("movies", moviesToJSON());
        return json;
    }

    private JSONArray moviesToJSON() {
        JSONArray jsonArray = new JSONArray();

        for (Movie m : movieList) {
            jsonArray.put(m.toJson());
        }
        return jsonArray;
    }
}
