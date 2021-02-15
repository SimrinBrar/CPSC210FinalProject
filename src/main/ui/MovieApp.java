package ui;

import model.MovieList;
import model.Movie;

import java.util.Scanner;

public class MovieApp {
    private MovieList myList;
    private Scanner input;


    //EFFECTS: runs MovieApp
    public MovieApp() {
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
                running = false;
            } else
                processCommand(command);

        }
    }

    //Displays menu options
    private void displayMenu() {
        System.out.println("Choose an option:");
        System.out.println("Type 'new' to add a new movie");
        System.out.println("Type 'view' to view a list of your saved movies");
        System.out.println("Type 'quit' to close movie app");
    }

    private void processCommand(String command) {
        if (command.equals("view")) {
            printMovieList();
        }
        if (command.equals("new")) {
            newMovieTitle();
        }
    }

    //EFFECTS: prints a list of movies
    private void printMovieList() {
        if (myList == null) {
            System.out.println("There are no movies in your list");
        }
        System.out.println(myList.getTitleList());
    }

    private void newMovieTitle() {
        String command = null;
        System.out.println("Type the title name");
        Movie newMovie = new Movie("blank", 0000, 3);
        newMovie.setTitle(command);
        newMovieYear(newMovie);
    }

    private void newMovieYear(Movie newMovie) {
        int command = Integer.parseInt(null);
        System.out.println("Type the year the movie was released");
        newMovie.setYear(command);
        addToMyList(newMovie);
    }

    //EFFECTS: adds a movie to myList
    private void addToMyList(Movie m) {
        myList.addMovie(m);
    }


    private void init() {
        input = new Scanner(System.in);

    }


}
