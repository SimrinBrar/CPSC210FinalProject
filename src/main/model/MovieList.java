package model;

import java.util.ArrayList;
import java.util.List;

public class MovieList {

    private Movie movie;
    private ArrayList<Movie> movieList;

    //EFFECTS: creates a new list of movies
    public MovieList() {
        movieList = new ArrayList<>();
    }

    //getters
    public ArrayList<String> getMovieList() {
        ArrayList<String> lst = new ArrayList<>();
        for (Movie movie : movieList) {
            String t = movie.getTitle();
               lst.add(t);
        }
        return lst;
    }

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


}
