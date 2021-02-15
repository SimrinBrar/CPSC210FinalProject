package ui;

import model.MovieList;
import model.Movie;

import java.util.Scanner;
import java.util.ArrayList;

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
        boolean running = true;
        String command = null;

        init();

        while (running) {
            displayMenu();
            command = input.next();

            if (command.equals("view")) {
                printMovieList();
                running = false;
            }
            if (command.equals("new")) {
                newMovie();
                running = false;

            }


        }


    }

    //Displays menu options
    private void displayMenu() {
        System.out.println("Choose an option:");
        System.out.println("Type 'new' to add a new movie");
        System.out.println("Type 'view' to view a list of your saved movies");
    }

    //EFFECTS: prints a list of movies
    private void printMovieList() {
        if (myList == null) {
            System.out.println("There are no movies in your list");
        }
        System.out.println(myList.getTitleList());
    }

    private void newMovie() {
        boolean running = true;

        while (running) {
            System.out.println("type the title name");
        }
    }

    //EFFECTS: adds a movie to myList
    private void addToMyList(Movie m) {
        myList.addMovie(m);
    }


    private void init() {
        input = new Scanner(System.in);

    }


}
