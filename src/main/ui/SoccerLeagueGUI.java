package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import model.MatchRecords;
import model.League;
import persistence.JsonReader;
import persistence.JsonWriter;

public class SoccerLeagueGUI extends JFrame implements ActionListener {
    private static final String LEAGUE_STORE = "./data/league.json";
    private static final String MATCH_STORE = "./data/match.json";
    private static final String TITLE = "Soccer League";
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    private JLabel headingLabel;
    private JPanel mainPanel;
    private JButton matchButton;
    private JButton teamButton;
    private JButton standingsButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton exitButton;

    private MatchRecords matchRecords;
    private League league;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public SoccerLeagueGUI() throws FileNotFoundException {
        matchRecords = new MatchRecords();
        league = new League();
        jsonWriter = new JsonWriter(LEAGUE_STORE, MATCH_STORE);
        jsonReader = new JsonReader(LEAGUE_STORE, MATCH_STORE);

        initializeUI();
        displayMenu();
    }

    private void initializeUI() {
        setTitle("Soccer League Manager");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.BLACK);
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));
        mainPanel.add(topPanel, BorderLayout.NORTH);

        ImageIcon icon = new ImageIcon("data/foot.jpg.png");
        int imageSize = 200;
        Image scaledImage = icon.getImage().getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        topPanel.add(imageLabel);

        JLabel titleLabel = new JLabel("Soccer League Manager");
        titleLabel.setFont(new Font("MV Boli", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 20, 20));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        int buttonWidth = 150;
        int buttonHeight = 50;

        teamButton = new JButton("Team");
        teamButton.addActionListener(this);
        teamButton.setBackground(Color.DARK_GRAY);
        teamButton.setForeground(Color.WHITE);
        teamButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        buttonPanel.add(teamButton);

        matchButton = new JButton("Match");
        matchButton.addActionListener(this);
        matchButton.setBackground(Color.DARK_GRAY);
        matchButton.setForeground(Color.WHITE);
        matchButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        buttonPanel.add(matchButton);

        standingsButton = new JButton("League");
        standingsButton.addActionListener(this);
        standingsButton.setBackground(Color.DARK_GRAY);
        standingsButton.setForeground(Color.WHITE);
        standingsButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        buttonPanel.add(standingsButton);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        saveButton.setBackground(Color.DARK_GRAY);
        saveButton.setForeground(Color.WHITE);
        saveButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        buttonPanel.add(saveButton);

        loadButton = new JButton("Load");
        loadButton.addActionListener(this);
        loadButton.setBackground(Color.DARK_GRAY);
        loadButton.setForeground(Color.WHITE);
        loadButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        buttonPanel.add(loadButton);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        exitButton.setBackground(Color.DARK_GRAY);
        exitButton.setForeground(Color.WHITE);
        exitButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        buttonPanel.add(exitButton);
    }




    private void displayMenu() {
        setVisible(true);
    }

    private void doMatch() {
        // code for match options goes here
    }

    private void doTeam() {
        // code for team options goes here
    }

    private void displayStandings() {
        List<String> standings = league.getStandings();
        String standingsStr = String.join("\n", standings);
        JOptionPane.showMessageDialog(this, standingsStr, "League Standings", JOptionPane.PLAIN_MESSAGE);
    }

    private void save() {
        try {
            jsonWriter.open();
            jsonWriter.writeLeague(league);
            jsonWriter.writeMatch(matchRecords);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "League saved to " + LEAGUE_STORE + " and " + MATCH_STORE,
                    "Save Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to write to file: " + LEAGUE_STORE + " and " + MATCH_STORE,
                    "Save Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void load() {
        try {
            league = jsonReader.readLeague();
            matchRecords = jsonReader.readMatches(league);
            JOptionPane.showMessageDialog(this, "League loaded from " + LEAGUE_STORE + " and " + MATCH_STORE,
                    "Load Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from file: " + LEAGUE_STORE + " and " + MATCH_STORE,
                    "Load Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == matchButton) {
            doMatch();
        } else if (e.getSource() == teamButton) {
            doTeam();
        } else if (e.getSource() == standingsButton) {
            displayStandings();
        } else if (e.getSource() == saveButton) {
            save();
        } else if (e.getSource() == loadButton) {
            load();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }
}

