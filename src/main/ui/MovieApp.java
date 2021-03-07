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
        myList = new MovieList();
        runApp();
    }

    //MODIFIES: this
    //EFFECTS: deals with user input
    private void runApp() {
        boolean running = true;
        String command = null;

        input = new Scanner(System.in);

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

    //EFFECTS: Displays menu options
    private void displayMenu() {
        System.out.println("Choose an option:");
        System.out.println("Type 'add' to add a new movie");
        System.out.println("Type 'view' to view a list of your saved movies");
        System.out.println("Type 'quit' to close movie app");
    }

    //EFFECTS: processes commands from main menu options
    private void processCommand(String command) {
        if (command.equals("view")) {
            processViewCommand();
        } else if (command.equals("add")) {
            processAddCommand();
        } else {
            System.out.println("not valid, try again...");
        }
    }

    //EFFECTS: prints a list of my movies
    private void processViewCommand() {
        for (String m: myList.getTitleList()) {
            System.out.println(m);
        }
        searchMovieList();
    }

    //EFFECTS: finds selected movie and displays details
    private void searchMovieList() {
        String select = "";

        while (select == "") {
            System.out.println("type a movie title to view it's details");
            select = input.nextLine();
            select = select.toLowerCase();
        }
        Movie selectedMovie = myList.selectMovie((select));
        if (selectedMovie == null ) {
            System.out.println("this movie is not in your list");
        } else {
            System.out.println(selectedMovie.getTitle() +" was released in the year "
                    + selectedMovie.getYear());
            if (selectedMovie.getRating() == 0) {
                System.out.println("you have not rated this movie yet.");
            } else {
                System.out.println("you rated this movie " + selectedMovie.getRating() + " out of 5 stars");
            }
            addMovieRating(selectedMovie);
        }
    }

    //MODIFIES: this
    //EFFECTS: updates a rating for a selected movie
    private void addMovieRating(Movie m) {
        int select = 0;

        while (select == 0) {
            System.out.println("to change this movie rating enter a number between 1 and 5");
            select = input.nextInt();
        }
        if (select == 1 || select == 2 || select == 3 || select == 4 || select == 5) {
            m.setRating(select);
        } else {
            System.out.println("not valid, rating was not changed");
            System.out.println(" ");
        }


    }

    public void processAddCommand() {
        addMovieTitle();
    }

    //MODIFIES: this
    //EFFECTS: gives a title to a new movie and adds it to the user's list of movies
    public void addMovieTitle() {
        String select = "";

        while (select == "") {
            System.out.println("Enter a movie title");
            select = input.nextLine();
            select = select.toLowerCase();
        }
        Movie newMovie = new Movie();
        myList.addMovie(newMovie);
        newMovie.setTitle(select);
        addMovieYear(newMovie);
    }

    //MODIFIES: this
    //EFFECTS: gives a release year to a new movie
    public void addMovieYear(Movie m) {
        int select = 0 ;

        while (select == 0) {
            System.out.println("Enter the year this movie was released");
            select = input.nextInt();
        }

        m.setYear(select);

    }

    //    private String getListOfTitles() {
//        while (!myList.isEmptyMovieList()) {
//            System.out.println(myList.returnNextMovie().getTitle());
//        }
//        return null;
//    }

//
    //EFFECTS: allows user to enter a title to a new movie
//    private void processAddCommand() {
//        String command = null;
//        System.out.println("Enter the movie title");
//        Movie newMovie = new Movie();
//        command = input.nextLine();
//        newMovie.setTitle(command);
//        newMovieYear(newMovie);
//    }
//
//    //EFFECTS: allows user to set the year to a new movie
//    private void newMovieYear(Movie newMovie) {
//        System.out.println("Enter the year the movie was released");
//        int year = input.nextInt();
//        newMovie.setYear(year);
//        addToMyList(newMovie);
//    }
//
//    //MODIFIES: this
//    //EFFECTS: adds a movie to myList
//    private void addToMyList(Movie newMovie) {
//        myList.addMovie(newMovie);
//        runApp();
//    }
//
//
//    //MODIFIES: this
//    //EFFECTS: allows the user to add a rating which can be changed later
//    private void addRating(Movie newMovie) {
//        System.out.println("To update this rating, enter a number between 1 and 5");
//        System.out.println("or type 0 to return to main menu");
//        int rating = input.nextInt();
//        if (rating == 0) {
//            System.out.println("returned to main menu:");
//            runApp();
//        }
//        newMovie.setRating(rating);
//        runApp();
//    }


}
