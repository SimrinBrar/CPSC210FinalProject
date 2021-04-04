package ui;


import java.io.FileNotFoundException;

public class MainConsole {
    public static void main(String[] args) {
        try {
            new MovieApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }

    }

}
