package ui;

import exceptions.InvalidRatingException;
import model.Movie;
import model.MovieList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

//GUI for Movie App
public class MovieAppGUI implements ListSelectionListener {

    private static final String JSON_STORE = "./data/movielist.json";
    private JFrame frame;
    private JPanel rightPanel;
    private JPanel bottomPanel;
    private JMenuItem load;
    private JMenuItem save;
    private JList list;
    private DefaultListModel listModel;
    private JButton addButton;
    private JButton fiveStarsButton;
//    private JButton allMoviesButton;
    private JSplitPane splitPane;
    private JTextField titleTextField;
    private JTextField yearTextField;
    private JTextField ratingTextField;
    private MovieList movieList;
    private JLabel fiveStarImage;
    private JLabel allMoviesLabel;
    private JLabel titleLabel;
    private JLabel yearLabel;
    private JLabel ratingLabel;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    //EFFECTS: creates the GUI and shows it
    public MovieAppGUI() {
        frame = new JFrame("Movie List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        movieList = new MovieList("My Movie List");
        bottomPanelGUI();
        rightPanelGUI();
        listGUI();
        menuBarGUI();
        frame.add(splitPane);
        frame.setSize(500, 560);
        frame.setVisible(true);

    }

    //EFFECTS: creates and shows GUI for bottom panel
    private void bottomPanelGUI() {
        bottomPanel = new JPanel();
        addButton = new JButton("Add");
        addButton.addActionListener(addButtonActionListener);
        titleTextField = new JTextField(1);
        yearTextField = new JTextField(1);
        ratingTextField = new JTextField(1);
        titleTextField.setToolTipText("Movie title");
        yearTextField.setToolTipText("Release year");
        ratingTextField.setToolTipText("Rating between 1 and 5");
        JLabel titleLabel = new JLabel("Title");
        JLabel yearLabel = new JLabel("Year");
        JLabel ratingLabel = new JLabel("Rating");
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
        bottomPanel.add(addButton);
        bottomPanel.add(titleLabel);
        bottomPanel.add(titleTextField);
        bottomPanel.add(yearLabel);
        bottomPanel.add(yearTextField);
        bottomPanel.add(ratingLabel);
        bottomPanel.add(ratingTextField);
        frame.add(bottomPanel, BorderLayout.PAGE_END);
    }

    //MODIFIES: this
    //EFFECTS: action listener for "Add" button
    ActionListener addButtonActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = titleTextField.getText();
            int year = Integer.parseInt(yearTextField.getText());
            int rating = Integer.parseInt(ratingTextField.getText());

            Movie newMovie = new Movie();

            setNewMovieRating(rating, newMovie);
            newMovie.setTitle(title);
            newMovie.setYear(year);
            movieList.addMovie(newMovie);

            if (title.equals("")) {
                Toolkit.getDefaultToolkit().beep();
                titleTextField.requestFocusInWindow();
                titleTextField.selectAll();
                return;
            }
            listModel.insertElementAt(titleTextField.getText(), listModel.getSize());
            titleTextField.requestFocusInWindow();
            ratingTextField.requestFocusInWindow();
            yearTextField.requestFocusInWindow();
            titleTextField.setText("");
            yearTextField.setText("");
            ratingTextField.setText("");
        }
    };

    private void setNewMovieRating(int rating, Movie newMovie) {
        try {
            newMovie.setRating(rating);
        } catch (InvalidRatingException invalidRatingException) {
            JOptionPane.showMessageDialog(null,
                    "The rating you entered is invalid. No rating was set for this movie",
                    "INVALID RATING", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //EFFECTS: creates and shows GUI for right panel
    private void rightPanelGUI() {
        JLabel rightPanelLabel = new JLabel();
        rightPanel = new JPanel();
        splitPane = new JSplitPane();
        rightPanel.add(rightPanelLabel);
        splitPane.setRightComponent(rightPanel);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        JLabel selectMovieLabel = new JLabel("Select a movie to see it's details...");
        fiveStarsButton = new JButton("5 star movies");
//        allMoviesButton = new JButton("All movies");
        allMoviesLabel = new JLabel("<- Here are all your movies");
        rightPanel.add(selectMovieLabel, 1);
        rightPanel.add(fiveStarsButton, 2);
        rightPanel.add(allMoviesLabel, 3);
//        rightPanel.add(allMoviesButton, 4);
        titleLabel = new JLabel();
        yearLabel = new JLabel();
        ratingLabel = new JLabel();
        fiveStarsButton.addActionListener(fiveStarsActionListener);
//        allMoviesButton.addActionListener(allMoviesActionListener);
    }

    //MODIFIES: this
    //EFFECTS: creates action listener for "5 star movies" button
    ActionListener fiveStarsActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            listModel.removeAllElements();
            fiveStarImage = new JLabel();
            if (rightPanel.getComponent(3) == allMoviesLabel) {
                rightPanel.remove(allMoviesLabel);
                fiveStarImage.setIcon(new ImageIcon("./data/FiveStars.jpg"));
                rightPanel.add(fiveStarImage, 3);
                rightPanel.validate();
            }

            movieList = movieList.filterFiveStars();

            ArrayList<String> bestMovies = movieList.getTitleList();
            for (String title : bestMovies) {
                listModel.addElement(title);
            }
        }
    };

//    //EFFECTS: creates action listener for "All movies" button
//    ActionListener allMoviesActionListener = new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            rightPanel.remove(fiveStarImage);
//            rightPanel.add(allMoviesLabel, 4);
//            rightPanel.validate();
//            listModel.removeAllElements();
//            ArrayList<String> fullList = movieList.getTitleList();
//            for (String movie : fullList) {
//                listModel.addElement(movie);
//            }
//        }
//
//
//    };

    //EFFECTS: takes selected movie and displays title, year, rating on right panel
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (list.getSelectedIndex() != -1) {
                Movie selectedMovie = movieList.getMovie(list.getSelectedIndex());
                rightPanel.add(titleLabel, 4);
                rightPanel.add(yearLabel, 5);
                rightPanel.add(ratingLabel, 6);
                titleLabel.setText("Title: " + selectedMovie.getTitle());
                yearLabel.setText("Release year: " + selectedMovie.getYear());
                String rating;
                if (selectedMovie.getRating() == 0) {
                    rating = "No Rating";
                } else {
                    rating = Integer.toString(selectedMovie.getRating());
                }
                ratingLabel.setText("Rating (1-5) : " + rating);
                rightPanel.validate();
            }
        }
    }

    //EFFECTS: creates and shows GUI for list
    public void listGUI() {
        listModel = new DefaultListModel<>();
        list = new JList<>();
        list.setModel(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        splitPane.setLeftComponent(new JScrollPane(list));
    }

    //EFFECTS: creates and shows GUI for top menu bar
    public void menuBarGUI() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("File");
        menuBar.add(menu1);
        frame.setJMenuBar(menuBar);
        save = new JMenuItem("Save");
        load = new JMenuItem("Load");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        menu1.add(save);
        menu1.add(load);
        save.addActionListener(saveActionListener);
        load.addActionListener(loadActionListener);
    }

    //EFFECTS: action listener for "save" menu item
    ActionListener saveActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            jsonWriter.write(movieList);
            jsonWriter.close();
        }
    };

    //EFFECTS: action listener for "load" menu item
    ActionListener loadActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                movieList = jsonReader.read();
            } catch (IOException | InvalidRatingException ioException) {
                ioException.printStackTrace();
            }
            ArrayList<String> movieTitles = movieList.getTitleList();
            int place = -1;
            for (String title : movieTitles) {
                place++;
                listModel.insertElementAt(title, place);
            }
        }
    };

}
