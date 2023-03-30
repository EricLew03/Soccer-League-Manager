package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Match;
import model.MatchRecords;
import model.League;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

public class SoccerLeagueGUI extends JFrame implements ActionListener {
    private static final String LEAGUE_STORE = "./data/league.json";
    private static final String MATCH_STORE = "./data/match.json";

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

    private JWindow splashScreen;

    // EFFECTS : runs the soccer league app GUI
    public SoccerLeagueGUI() throws FileNotFoundException {
        matchRecords = new MatchRecords();
        league = new League();
        jsonWriter = new JsonWriter(LEAGUE_STORE, MATCH_STORE);
        jsonReader = new JsonReader(LEAGUE_STORE, MATCH_STORE);

        splashScreen();
        while (splashScreen.isVisible()) {
            try {
                Thread.sleep(100); // Wait for 100 milliseconds before checking again
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        initializeUI();
        displayMenu();
    }



    // EFFECTS: Displays a splash screen and adds a mouse listener
    //          to close the splash screen on mouse click.
    private void splashScreen() {
        splashScreen = new JWindow();
        JLabel splashLabel = new JLabel(new ImageIcon("data/foot.jpg.png"));
        splashScreen.getContentPane().add(splashLabel);
        splashScreen.pack();
        splashScreen.setLocationRelativeTo(null);
        splashScreen.setVisible(true);

        splashScreen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                splashScreen.dispose();
            }
        });
    }


    // MODIFIES: this
    // EFFECTS: Initializes the UI by creating the window, top panel, and button panel.
    private void initializeUI() {
        createWindow();
        createTopPanel();
        createButtonPanel();
    }


    // MODIFIES: this
    // EFFECTS: Creates the window with a specific title, size, location, and layout.
    //          Also, creates the main panel and adds it to the window.
    private void createWindow() {
        setTitle("Soccer League Manager");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);
    }


    // MODIFIES: this, mainPanel
    // EFFECTS: Creates the top panel with an image and a title, and
    //          adds it to the main panel.
    private void createTopPanel() {
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
    }


    // MODIFIES: this, mainPanel
    // EFFECTS: Creates the button panel with buttons for team, match,
    //          standings, save, load, and exit. Also, adds it to the main panel.
    private void createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 20, 20));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        int buttonWidth = 150;
        int buttonHeight = 50;

        teamButton = createButton("Team", buttonWidth, buttonHeight);
        matchButton = createButton("Match", buttonWidth, buttonHeight);
        standingsButton = createButton("League", buttonWidth, buttonHeight);
        saveButton = createButton("Save", buttonWidth, buttonHeight);
        loadButton = createButton("Load", buttonWidth, buttonHeight);
        exitButton = createButton("Exit", buttonWidth, buttonHeight);

        buttonPanel.add(teamButton);
        buttonPanel.add(matchButton);
        buttonPanel.add(standingsButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(exitButton);
    }

    // EFFECTS: Creates a button with a given label, width, and height, and
    //          adds an action listener to it.
    private JButton createButton(String label, int width, int height) {
        JButton button = new JButton(label);
        button.addActionListener(this);
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(width, height));
        return button;
    }


    // MODIFIES: this
    // EFFECTS: Displays the menu by setting the visibility of the window to true.
    private void displayMenu() {
        setVisible(true);
    }

    // EFFECTS: Creates a new JFrame named matchFrame
    private void doMatch() {
        JFrame matchFrame = new JFrame("Match options");

        JLabel addMatchLabel = new JLabel("Select from:");
        JButton addMatchButton = new JButton("Add match");
        JButton viewMatchRecordsButton = new JButton("Match records");
        JButton quitButton = new JButton("Quit");

        // add action listeners to the buttons to handle events
        addMatchButton.addActionListener(e -> addMatch());
        viewMatchRecordsButton.addActionListener(e -> viewMatchRecords());
        quitButton.addActionListener(e -> matchFrame.dispose());

        // add the components to the JFrame
        matchFrame.add(addMatchLabel);
        matchFrame.add(addMatchButton);
        matchFrame.add(viewMatchRecordsButton);
        matchFrame.add(quitButton);

        // set the layout manager to arrange the components
        matchFrame.setLayout(new GridLayout(4, 1));
        matchFrame.setSize(300, 200);
        matchFrame.setVisible(true);
    }

    // MODIFIES: matchRecords
    // EFFECTS: Prompts the user to select a home team and an away team for a new match.
    //          If there are less than two teams in the league, displays a message dialog stating that there must be at
    //          least two teams in the league to add a match.
    private void addMatch() {
        List<Team> teams = league.getTeams();

        // Check that there are at least two teams in the league
        if (teams.size() < 2) {
            JOptionPane.showMessageDialog(this, "There must be at least two teams in the league to add a match.");
            return;
        }

        Team homeTeam = selectHomeTeam();
        Team awayTeam = selectAwayTeam(homeTeam);
        Match match = createMatch(homeTeam, awayTeam);
        updateMatchResult(match);
        matchRecords.addMatch(match);
        JOptionPane.showMessageDialog(this, "Match added successfully!");
    }

    // EFFECTS: Prompt the user to select a home team and returns the selected team.
    private Team selectHomeTeam() {
        JOptionPane.showMessageDialog(this, "Select home team:");
        return selectTeam();
    }

    // EFFECTS: Prompt the user to select an away team and returns the selected team.
    private Team selectAwayTeam(Team homeTeam) {
        JOptionPane.showMessageDialog(this, "Select away team:");
        Team awayTeam = null;
        while (awayTeam == null || awayTeam == homeTeam) {
            awayTeam = selectTeam();
            if (awayTeam == homeTeam) {
                JOptionPane.showMessageDialog(this,
                        "The away team cannot be the same as the home team. Please select a different team.");
            }
        }
        return awayTeam;
    }

    // MODIFIES : matchRecords
    // EFFECTS: Creates a new Match with the given home and away teams and returns it.
    private Match createMatch(Team homeTeam, Team awayTeam) {
        return new Match(homeTeam, awayTeam);
    }

    // EFFECTS: Enter the home and away team goals for the given match, and updates the match result accordingly.
    private void updateMatchResult(Match match) {
        boolean validInput = false;
        while (!validInput) {
            try {
                int homeTeamGoals = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter home team goals:"));
                int awayTeamGoals = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter away team goals:"));
                match.updateResult(homeTeamGoals, awayTeamGoals);
                validInput = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input, please enter an integer");
            }
        }
    }

    // EFFECTS: prompts the user to select a team from the league
    private Team selectTeam() {
        List<Team> teams = league.getTeams();
        String[] teamNames = new String[teams.size()];
        for (int i = 0; i < teams.size(); i++) {
            teamNames[i] = teams.get(i).getTeamName();
        }
        String selectedTeamName = (String) JOptionPane.showInputDialog(this, "Select team:", "",
                JOptionPane.QUESTION_MESSAGE, null, teamNames, teamNames[0]);
        return findTeamByName(selectedTeamName);
    }

    // EFFECTS: Returns the team in the league with the given teamName, or null if no such team exists.
    private Team findTeamByName(String teamName) {
        for (Team team : league.getTeams()) {
            if (team.getTeamName().equals(teamName)) {
                return team;
            }
        }
        return null;
    }

    // EFFECTS: Displays a table of all match records
    private void viewMatchRecords() {
        List<Match> matches = matchRecords.getMatchRecords();
        Object[][] tableData = new Object[matches.size()][4];
        for (int i = 0; i < matches.size(); i++) {
            Match match = matches.get(i);
            tableData[i][0] = match.getHomeTeam().getTeamName();
            tableData[i][1] = match.getHomeGoals();
            tableData[i][2] = match.getAwayGoals();
            tableData[i][3] = match.getAwayTeam().getTeamName();

        }
        JTable table = new JTable(tableData, new String[]{"Home Team", "Home Goals", "Away Goals", "Away Team"});
        JScrollPane scrollPane = new JScrollPane(table);
        JFrame frame = new JFrame("Match Records");
        frame.add(scrollPane);
        frame.pack();
        frame.setVisible(true);
    }



    // EFFECTS: Creates a new JFrame named teamFrame
    private void doTeam() {
        JFrame teamFrame = new JFrame("Team Options");

        JLabel selectLabel = new JLabel("Select from:");
        JButton addTeamButton = new JButton("Add Team");
        JButton viewTeamListButton = new JButton("List of Teams");
        JButton viewTeamRecordButton = new JButton("Team Record");
        JButton quitButton = new JButton("Quit");

        // add action listeners to the buttons to handle events
        addTeamButton.addActionListener(e -> addTeam());
        viewTeamListButton.addActionListener(e -> viewTeamList());
        viewTeamRecordButton.addActionListener(e -> viewTeamRecord());
        quitButton.addActionListener(e -> teamFrame.dispose());

        // add the components to the JFrame
        teamFrame.add(selectLabel);
        teamFrame.add(addTeamButton);
        teamFrame.add(viewTeamListButton);
        teamFrame.add(viewTeamRecordButton);
        teamFrame.add(quitButton);

        // set the layout manager to arrange the components
        teamFrame.setLayout(new GridLayout(5, 1));
        teamFrame.setSize(300, 200);
        teamFrame.setVisible(true);
    }



    // MODIFIES : league
    // EFFECTS : Prompts the user to enter a new team name and creates a new team with that name.
    //           The new team is added to the league
    private void addTeam() {
        JFrame frame = new JFrame("Add Team");
        JPanel panel = new JPanel(new GridLayout(2, 1));
        JLabel label = new JLabel("Enter the name of the new team:");
        JTextField textField = new JTextField();

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            String teamName = textField.getText(); {
                Team newTeam = new Team(teamName);
                league.addTeam(newTeam);
                JOptionPane.showMessageDialog(frame, "Team added successfully!");
                frame.dispose();
            }
        });

        panel.add(label);
        panel.add(textField);
        panel.add(addButton);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    // EFFECTS : Retrieves a list of teams from the League class and displays the team names in a dialog box.
    public void viewTeamList() {
        List<Team> teams = league.getTeams();
        if (teams.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No teams found.");
        } else {
            JFrame frame = new JFrame("List of Teams");
            JTextArea textArea = new JTextArea(10, 30);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

            for (Team team : teams) {
                textArea.append(team.getTeamName() + "\n");
            }

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e -> frame.dispose());
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(closeButton);
            frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }

    // EFFECTS: Prompts the user to select a team and displays that team's match records in a dialog box.
    public void viewTeamRecord() {
        List<Team> teams = league.getTeams();
        if (teams.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No teams found.");
            return;
        }

        String teamName = selectTeamName(teams);
        if (teamName == null) {
            return;
        }

        Team selectedTeam = findTeamByName(teamName);

        List<Match> teamMatches = matchRecords.getMatchesForTeam(selectedTeam);
        if (teamMatches.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No match records found for team " + selectedTeam.getTeamName());
            return;
        }

        String matchRecords = getMatchRecordsForTeam(selectedTeam, teamMatches);
        JOptionPane.showMessageDialog(null, matchRecords);
    }


    // Effects: displays a dialog box to the user asking them to select a team from the list of Team objects provided,
    //          and returns the name of the selected team as a String.
    private String selectTeamName(List<Team> teams) {
        String[] teamNames = teams.stream().map(Team::getTeamName).toArray(String[]::new);
        return (String) JOptionPane.showInputDialog(
                null, "Select team:",
                "Team Record", JOptionPane.QUESTION_MESSAGE, null, teamNames, teamNames[0]
        );
    }


    // Effects: constructs a String containing the match records for the given team.
    private String getMatchRecordsForTeam(Team team, List<Match> matches) {
        StringBuilder sb = new StringBuilder();
        sb.append("Match records for team ").append(team.getTeamName()).append(":\n");
        for (Match match : matches) {
            sb.append(match.toString()).append("\n");
        }
        return sb.toString();
    }


    // Effects: creates a new JTextArea object
    private JTextArea createStandingsArea() {
        JTextArea standingsArea = new JTextArea(20, 30);
        standingsArea.setEditable(false);
        standingsArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        return standingsArea;
    }

    // Effects: iterates over the list of standings, splitting each standing into its component parts and determining
    //          the maximum length of the part at the specified columnIndex. The maximum length is
    //          returned as an integer.
    private int getMaxColumnLength(List<String> standings, int columnIndex) {
        int maxLength = 0;
        for (String standing : standings) {
            String[] parts = standing.split("\\s+");
            maxLength = Math.max(maxLength, parts[columnIndex].length());
        }
        return maxLength;
    }

    // Effects: formats the given column by left-padding it with spaces until
    //          its length is equal to maxLength. The resulting String is returned.
    private String formatColumn(String column, int maxLength) {
        return String.format("%-" + maxLength + "s", column);
    }

    // EFFECTS: formats the standing String by padding each column with spaces to match the maximum column length
    private void addStandingToArea(String standing, List<Integer> maxColumnLengths, JTextArea standingsArea) {
        String[] parts = standing.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            sb.append(formatColumn(parts[i], maxColumnLengths.get(i)));
            sb.append("  ");
        }
        standingsArea.append(sb.toString() + "\n");
    }

    // Modifies: the GUI by displaying the league standings.
    // Effects: retrieves the current standings from the league object
    private void displayStandings() {
        List<String> standings = league.getStandings();
        JTextArea standingsArea = createStandingsArea();

        // Determine the maximum length of each column
        List<Integer> maxColumnLengths = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            maxColumnLengths.add(getMaxColumnLength(standings, i));
        }

        // Add the standings to the JTextArea
        for (String standing : standings) {
            addStandingToArea(standing, maxColumnLengths, standingsArea);
        }

        // Create a new JScrollPane to display the JTextArea
        JScrollPane scrollPane = new JScrollPane(standingsArea);

        // Show the standings in a JOptionPane
        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "League Standings",
                JOptionPane.PLAIN_MESSAGE
        );
    }


    // Effects: saves the current state of the league and matchRecords objects
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


    // MODIFIES : league , matchrecords
    // Effects: loads the JSON data from the files specified and sets the corresponding
    //          fields in the LeagueManagerGUI class
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

