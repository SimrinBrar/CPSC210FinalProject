package ui;

import model.Movie;
import model.MovieList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MovieAppGUI implements ListSelectionListener {

    private static final String JSON_STORE = "./data/movielist.json";
    private JFrame frame;
    private JPanel sidePanel;
    private JMenuItem load;
    private JMenuItem save;
    private JList list;
    private DefaultListModel listModel;
    private JButton addButton;
    private JButton topRatedButton;
    private JButton fullListButton;
    private JSplitPane splitPane;
    private JPanel bottomPanel;
    private JTextField newMovieTitle;
    private JTextField newMovieYear;
    private JTextField newMovieRating;
    private MovieList movieList;
    private JLabel selectMovieLabel;
    private JLabel image;
    private JLabel allMoviesLabel;
    private JLabel titleLabel;
    private JLabel yearLabel;
    private JLabel ratingLabel;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;



    public MovieAppGUI() {
        frame = new JFrame("Movie List");
        setBottomPanel();
        frame.add(bottomPanel, BorderLayout.PAGE_END);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        selectMovieLabel = new JLabel("select movie to see it's details");
        allMoviesLabel = new JLabel("<- here are all your movies");
        movieList = new MovieList("My Movie List");
        setList();
        setSidePanel();
        frame.add(splitPane);
        frame.validate();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        SetMenuBar();
        frame.setSize(500, 560);
        frame.setVisible(true);

    }

    private void setSidePanel() {
        splitPane.setRightComponent(sidePanel);
        sidePanel.setLayout(new BoxLayout(sidePanel,BoxLayout.PAGE_AXIS));
        sidePanel.add(selectMovieLabel);
        topRatedButton = new JButton("click for 5 star movies");
        fullListButton = new JButton("all movie");
        sidePanel.add(topRatedButton, 2);
        sidePanel.add(fullListButton, 3);
        sidePanel.add(allMoviesLabel, 4);
        titleLabel = new JLabel();
        yearLabel = new JLabel();
        ratingLabel = new JLabel();
        sidePanel.validate();
        topRatedButton();
        allMoviesButton();
    }

    private void allMoviesButton() {
        fullListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sidePanel.remove(sidePanel.getComponent(4));
                sidePanel.validate();
                sidePanel.add(allMoviesLabel, 4);
                sidePanel.validate();
                listModel.removeAllElements();
                ArrayList<String> fullList = movieList.getTitleList();
                for (String movie: fullList) {
                    listModel.addElement(movie);
                }
            }
        });
    }

    private void topRatedButton() {
        topRatedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.removeAllElements();
                image = new JLabel();
                if (sidePanel.getComponent(4) == allMoviesLabel) {
                    sidePanel.remove(allMoviesLabel);
                    sidePanel.validate();
                    image.setIcon(new ImageIcon("./data/FiveStars.jpg"));
                    sidePanel.add(image, 4);
                    sidePanel.validate();
                }

                ArrayList<Movie> bestMovies = movieList.topRatedMovies();
                for (Movie movie : bestMovies) {
                    listModel.addElement(movie.getTitle());
                }

            }
        });
    }

    public void SetMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("File");
        menuBar.add(menu1);
        frame.setJMenuBar(menuBar);
        save = new JMenuItem("Save");
        load = new JMenuItem("Load");
        menu1.add(save);
        menu1.add(load);
        save.addActionListener(saveActionListener);
        load.addActionListener(loadActionListener);
    }

    ActionListener loadActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                movieList = jsonReader.read();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            ArrayList<String> movieTitles = movieList.getTitleList();
            int place = -1;
            for (String title: movieTitles) {
                place++;
                listModel.insertElementAt(title, place);
            }
        }
    };

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


    private void setBottomPanel() {
        bottomPanel = new JPanel();
        addButton = new JButton("Add");
        AddListener addListener = new AddListener(addButton);
        addButton.addActionListener(addListener);
        newMovieTitle = new JTextField(3);
        newMovieYear = new JTextField(2);
        newMovieRating = new JTextField(1);
        newMovieTitle.setToolTipText("Movie Title");
        newMovieYear.setToolTipText("Release Year");
        newMovieRating.setToolTipText("Rating 1 - 5");
        JLabel titleLabel = new JLabel("Title");
        JLabel yearLabel = new JLabel("Year");
        JLabel ratingLabel = new JLabel("Rating");
        layoutButtonPane(titleLabel, yearLabel, ratingLabel);
    }

    private void layoutButtonPane(JLabel titleLabel, JLabel yearLabel, JLabel ratingLabel) {
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
        bottomPanel.add(addButton);
        bottomPanel.add(titleLabel);
        bottomPanel.add(newMovieTitle);
        bottomPanel.add(yearLabel);
        bottomPanel.add(newMovieYear);
        bottomPanel.add(ratingLabel);
        bottomPanel.add(newMovieRating);
    }

    public void setList() {
        listModel = new DefaultListModel<>();
        JLabel label = new JLabel();
        sidePanel = new JPanel();
        splitPane = new JSplitPane();
        list = new JList<>();
        list.setModel(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        splitPane.setLeftComponent(new JScrollPane(list));
        sidePanel.add(label);

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false ) {
            if (list.getSelectedIndex() != -1) {
                Movie selectedMovie = movieList.getMovie(list.getSelectedIndex());
                sidePanel.add(titleLabel, 5);
                sidePanel.add(yearLabel, 6);
                sidePanel.add(ratingLabel, 7);
                titleLabel.setText("title: " + selectedMovie.getTitle());
                yearLabel.setText("release year: " + selectedMovie.getYear());
                ratingLabel.setText("Rating (1-5) : " + selectedMovie.getRating());
                sidePanel.validate();
            }
        }
    }

    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String title = newMovieTitle.getText();
            int year = Integer.parseInt(newMovieYear.getText());
            int rating = Integer.parseInt(newMovieRating.getText());
            createNewMovie(title, year, rating);

            if (title.equals("")) {
                Toolkit.getDefaultToolkit().beep();
                newMovieTitle.requestFocusInWindow();
                newMovieTitle.selectAll();
                return;
            }

            listModel.insertElementAt(newMovieTitle.getText(), listModel.getSize());

            newMovieTitle.requestFocusInWindow();
            newMovieRating.requestFocusInWindow();
            newMovieYear.requestFocusInWindow();
            newMovieTitle.setText("");
            newMovieYear.setText("");
            newMovieRating.setText("");
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    //EFFECTS: creates a new movie object and adds it to a list of movies
    private void createNewMovie(String title, int year, int rating) {
        Movie newMovie = new Movie();
        newMovie.setTitle(title);
        newMovie.setYear(year);
        newMovie.setRating(rating);
        movieList.addMovie(newMovie);
    }
}
