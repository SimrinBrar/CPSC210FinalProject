package model;

import java.util.HashSet;

//represents a list of movie objects
public class MovieList {
    private HashSet<Movie> movieList;

    //EFFECTS: creates an empty list of movies
    public MovieList() {
        movieList = new HashSet<>();
    }

    //EFFECTS: returns a list of movie titles
    public HashSet<String> getTitleList() {
        HashSet<String> lst = new HashSet<>();
        for (Movie movie : movieList) {
            String t = movie.getTitle();
            lst.add(t);
        }
        return lst;
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

    //EFFECTS:returns the next movie in the hashset and null if
    //        there is no next movie
    public Movie returnNextMovie() {
        if (!movieList.isEmpty()) {
            return movieList.iterator().next();
        } else {
            return null;
        }
    }
}
