package ui;

import model.MovieList;
import model.Movie;

import java.util.Scanner;

//Movie app
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

        initial();

        while (running) {
            displayMenu();
            command = input.nextLine();
            command = command.toLowerCase();
            if (command.equals("quit")) {
                running = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("you closed the movie app, Goodbye");
    }

    //MODIFIES: this
    //EFFECTS: initializes a list for the user's movies
    private void initial() {
        myList = new MovieList();
        input = new Scanner(System.in);
    }

    //EFFECTS: Displays menu options
    private void displayMenu() {
        System.out.println("Choose an option:");
        System.out.println("Type 'add' to add a new movie");
        System.out.println("Type 'view' to view a list of your saved movies");
        System.out.println("Type 'quit' to close movie app\n");
    }

    //EFFECTS: processes commands from main menu options
    private void processCommand(String command) {
        if (command.equals("view")) {
            processViewCommand();
        } else if (command.equals("add")) {
            processAddCommand();
        } else {
            System.out.println("not a valid command, try again");
        }
    }

    //EFFECTS: prints a list of my movies
    private void processViewCommand() {
        for (String m : myList.getTitleList()) {
            System.out.println(m);
        }
        System.out.println("type a movie title to view it's details and change it's rating\n");
        Movie selectedMovie = searchMovieList();
        if (selectedMovie == null) {
            System.out.println("this movie is not in your list");
        } else {
            System.out.println(selectedMovie.getTitle() + " was released in the year "
                    + selectedMovie.getYear());

            if (selectedMovie.getRating() == 0) {
                System.out.println("you have not rated this movie yet.");
            } else {
                System.out.println("you rated this movie " + selectedMovie.getRating() + " out of 5 stars");
            }

            addMovieRating(selectedMovie);
        }
    }

    //EFFECTS: finds selected movie returns it
    private Movie searchMovieList() {
        String select = input.nextLine();
        select = select.toLowerCase();
        return myList.selectMovie(select);

    }

    //MODIFIES: this
    //EFFECTS: updates a rating for a selected movie
    private void addMovieRating(Movie m) {
        System.out.println("to change this movie rating enter a number between 1 and 5\n");
        int select = input.nextInt();

        if (select == 1 || select == 2 || select == 3 || select == 4 || select == 5) {
            m.setRating(select);
        } else {
            System.out.println("not valid, rating was not changed");
            System.out.println(" ");
        }
    }


    //MODIFIES: this
    //EFFECTS: gives a title to a new movie and adds it to the user's list of movies
    public void processAddCommand() {
        System.out.println("Enter a movie title\n");
        String select = "";
        select = input.nextLine();
        select = select.toLowerCase();

        Movie newMovie = new Movie();
        myList.addMovie(newMovie);
        newMovie.setTitle(select);
        addMovieYear(newMovie);
    }

    //MODIFIES: this
    //EFFECTS: gives a release year to a new movie
    public void addMovieYear(Movie m) {
        System.out.println("Enter the year this movie was released\n");
        int select = input.nextInt();

        m.setYear(select);
    }


}
