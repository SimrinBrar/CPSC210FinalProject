package model;

public class Movie {

    private int year;
    private int rating;
    private String title;

    //REQUIRES: rating is an integer between 1 and 5
    //EFFECTS: constructs a movie with title, release year and rating
    public Movie(String title, int year, int rating) {
        this.title = title;
        this.year = year;
        this.rating = rating;
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
//    public void setTitle(String title) { this.title = title}
//    public void setYear(int year) {this.year = year }
    public void setRating(int rating) {
        this.rating = rating;
    }
}

