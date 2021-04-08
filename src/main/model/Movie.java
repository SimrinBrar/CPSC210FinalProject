package model;

import exceptions.InvalidRatingException;
import org.json.JSONObject;
import persistence.Writable;

// represents a movie object
public class Movie implements Writable {

    private int year;
    private int rating;
    private String title;

    //EFFECTS: constructs a movie with title, release year and rating
    public Movie() {
        this.title = title;
        this.year = year;
        this.rating = rating;
        title = null;
        year = 0;
        rating = 0;
    }

    //getters
    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getRating() {
        return rating;
    }

    //setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    //EFFECTS: sets rating for movie and throws exception if it is not between 1 and 5
    public void setRating(int rating) throws InvalidRatingException {
        if (rating > 5 || rating < 1) {
            throw new InvalidRatingException();
        }

        this.rating = rating;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("year", year);
        json.put("rating", rating);
        return json;
    }
}

