package ui;

import model.Movie;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieAppGUI {

    private JFrame frame;
    private JPanel panel;
    private JMenuItem load;
    private JMenuItem save;
    private JList list;
    private DefaultListModel listModel;
    private JButton addButton;
    private JSplitPane splitPane;
    private JPanel buttonPane;
    private JTextField newMovieTitle;
    private JTextField newMovieYear;
    private JTextField newMovieRating;


    public MovieAppGUI() {
        frame = new JFrame("Movie List");
        setList();
        setBottomPanel();
        frame.add(buttonPane, BorderLayout.PAGE_END);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(splitPane);
        SetMenuBar();
        frame.setSize(500, 500);
        frame.setVisible(true);
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
    }

    public void setList() {
        listModel = new DefaultListModel<>();
        JLabel label = new JLabel();
        panel = new JPanel();
        splitPane = new JSplitPane();
        list = new JList<>();
        list.setModel(listModel);
        splitPane.setLeftComponent(new JScrollPane(list));
        panel.add(label);
        splitPane.setRightComponent(panel);
    }

    private void setBottomPanel() {
        buttonPane = new JPanel();
        addButton =new JButton("Add");
        newMovieTitle = new JTextField(3);
        newMovieYear = new JTextField(2);
        newMovieRating = new JTextField(1);
        newMovieTitle.setToolTipText("Movie Title");
        newMovieYear.setToolTipText("Release Year");
        newMovieRating.setToolTipText("Rating 1 - 5");
        JLabel titleLabel = new JLabel("Title");
        JLabel yearLabel = new JLabel("Year");
        JLabel ratingLabel = new JLabel("Rating");
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(addButton);
        buttonPane.add(titleLabel);
        buttonPane.add(newMovieTitle);
        buttonPane.add(yearLabel);
        buttonPane.add(newMovieYear);
        buttonPane.add(ratingLabel);
        buttonPane.add(newMovieRating);
    }

    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        @Override
        public void actionPerformed(ActionEvent e) {
            String title = newMovieTitle.getText();
            int year = Integer.parseInt(newMovieYear.getText());
            int rating = Integer.parseInt((newMovieRating.getText()));
            if (title == "" || newMovieYear.getText() == "" || newMovieRating.getText() == "") {
                Toolkit.getDefaultToolkit().beep();
                resetTextFields();
            }
            int index = list.getSelectedIndex();
            if (index == -1) {
                index = 0;
            } else {
                index++;
            }
            Movie movie = new Movie();
            movie.setTitle(title);
            movie.setYear(year);
            movie.setRating(rating);
            listModel.insertElementAt(movie.getTitle(), index);
        }

        private void resetTextFields() {
            newMovieTitle.requestFocusInWindow();
            newMovieYear.requestFocusInWindow();
            newMovieRating.requestFocusInWindow();
            newMovieTitle.selectAll();
            newMovieYear.selectAll();
            newMovieRating.selectAll();
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


}
