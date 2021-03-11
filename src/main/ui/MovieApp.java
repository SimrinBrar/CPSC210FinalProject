package ui;

import model.Movie;
import model.MovieList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Movie app
public class MovieApp {
    private static final String JSON_STORE = "./data/movielist.json";
    private MovieList myList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    //EFFECTS: runs MovieApp
    public MovieApp() throws FileNotFoundException {
        myList = new MovieList("My Movie List");
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runMovieApp();
    }

    //MODIFIES: this
    //EFFECTS: deals with user input
    private void runMovieApp() {
        boolean running = true;
        String command = null;

        while (running) {
            displayMenu();
            command = input.nextLine().trim();
            if (command.equals("")) {
                continue;
            }
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
        System.out.println("Type 'save' to save your current list to file");
        System.out.println("Type 'load' to load list from file");
        System.out.println("Type 'quit' to close movie app\n");
    }

    //EFFECTS: processes commands from main menu options
    private void processCommand(String command) {
        if (command.equals("view")) {
            processViewCommand();
        } else if (command.equals("add")) {
            processAddCommand();
        } else if (command.equals("save")) {
            processSaveCommand();
        } else if (command.equals("load")) {
            processLoadCommand();
        } else {
            System.out.println("not a valid command, try again");
        }
    }

    //EFFECTS: loads the movie list from file
    private void processLoadCommand() {
        try {
            myList = jsonReader.read();
            System.out.println("Loaded " + myList.getName() + "from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS: saves the movie list to file
    private void processSaveCommand() {
        try {
            jsonWriter.open();
            jsonWriter.write(myList);
            jsonWriter.close();
            System.out.println("saved " + myList.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("unable to write file to " + JSON_STORE);
        }
    }

    //EFFECTS: prints a list of my movies
    private void processViewCommand() {
        if (myList.getTitleList().isEmpty()) {
            System.out.println("there are no movies in your list");
        } else {
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
    }

    //EFFECTS: finds selected movie and returns it
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
