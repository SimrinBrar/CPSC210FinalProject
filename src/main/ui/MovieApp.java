package ui;

import model.MovieList;
import model.Movie;

import java.util.Scanner;

public class MovieApp {
    private MovieList myList;
    private Scanner input;


    //EFFECTS: runs MovieApp
    public MovieApp() {
        myList = new MovieList();
        runApp();
    }

    //MODIFIES: this
    //EFFECTS: deals with user input
    private void runApp() {
        String command = null;
        Boolean running = true;

        init();

        while (running) {
            displayMenu();
            command = input.next();
            if (command.equals("quit")) {
                System.out.println("you closed the movie app, Goodbye");
                running = false;
            } else {
                processCommand(command);
            }
        }
    }

    //EFFECTS: Displays menu options
    private void displayMenu() {
        System.out.println("Choose an option:");
        System.out.println("Type 'new' to add a new movie");
        System.out.println("Type 'list' to view a list of your saved movies");
        System.out.println("Type 'quit' to close movie app");
    }

    //EFFECTS: processes commands from mai menu options
    private void processCommand(String command) {
        if (command.equals("list")) {
            printMovieList();
        } else if (command.equals("new")) {
            newMovieTitle();
        }
    }

    //EFFECTS: prints a list of my movies
    private void printMovieList() {
        if (myList == null) {
            System.out.println("There are no movies in your list");
            runApp();
        }
        System.out.println(myList.getTitleList());
        findMovie();
    }

    //EFFECTS: allows user to enter a title to a new movie
    private void newMovieTitle() {
        String command = null;
        System.out.println("Enter the movie title (no spaces)");
        Movie newMovie = new Movie("blank", 0000, 0);
        command = input.next();
        newMovie.setTitle(command);
        newMovieYear(newMovie);
    }

    //EFFECTS: allows user to set the year to a new movie
    private void newMovieYear(Movie newMovie) {
        System.out.println("Enter the year the movie was released");
        int year = input.nextInt();
        newMovie.setYear(year);
        addToMyList(newMovie);
    }

    //MODIFIES: this
    //EFFECTS: adds a movie to myList
    private void addToMyList(Movie newMovie) {
        myList.addMovie(newMovie);
        runApp();
    }

    //EFFECTS: finds a movie in myList of movies
    private void findMovie() {
        String command = null;
        System.out.println("Enter a movie title to view it's details");
        command = input.next();
        Movie newMovie = myList.selectMovie(command);
        if (newMovie == null) {
            System.out.println("NO MOVIE WAS FOUND");
            System.out.println("");
            System.out.println("Returned to main menu:");
            runApp();
        }
        System.out.println(newMovie.getTitle());
        System.out.println("This movie was released in the year " + newMovie.getYear());
        if (newMovie.getRating() == 0) {
            System.out.println("this movie has no rating");
            addRating(newMovie);
        } else {
            System.out.println("you rated this movie " + newMovie.getRating() + " out of 5 stars");
            addRating(newMovie);
        }
    }

    //MODIFIES: this
    //EFFECTS: allows the user to add a rating which can be changed later
    private void addRating(Movie newMovie) {
        System.out.println("To update this rating, enter a number between 1 and 5");
        System.out.println("or type 0 to return to main menu");
        int rating = input.nextInt();
        if (rating == 0) {
            System.out.println("returned to main menu:");
            runApp();
        }
        newMovie.setRating(rating);
        runApp();
    }


    //EFFECTS: initializes scanner
    private void init() {
        input = new Scanner(System.in);
    }


}
