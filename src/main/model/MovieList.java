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
    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    //EFFECTS: adds a movie to a list
    public void addMovie(Movie addedMovie) {
        movieList.add(addedMovie);
    }

    //EFFECTS: takes in a string and searches list for movie titles match that string.
    // returns movie if found and null if not found
    public Movie selectMovie(ArrayList<Movie> movieList, String selectedMovie) {
        for (Movie movie : movieList) {
            if (movie.getTitle().equals(selectedMovie)) {
                return movie;
            }
        }
        return null;
    }


}
