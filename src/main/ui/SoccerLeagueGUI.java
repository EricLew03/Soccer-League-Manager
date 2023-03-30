package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
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



    private void initializeUI() {
        // Create a new window for the splash screen

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
        // Create a new JFrame
        JFrame matchFrame = new JFrame("Add Match");

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

    // EFFECTS: allows the user to add a new match
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
                JOptionPane.showMessageDialog(this, "The away team cannot be the same as the home team. Please select a different team.");
            }
        }
        return awayTeam;
    }

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



    private void doTeam() {
        JFrame frame = new JFrame("Team Options");
        JPanel panel = new JPanel(new GridLayout(4, 1));
        JLabel label = new JLabel("Select from:");

        JButton addTeamButton = new JButton("Add Team");
        addTeamButton.addActionListener(e -> addTeam());


        JButton viewTeamListButton = new JButton("List of Teams");
        viewTeamListButton.addActionListener(e -> viewTeamList());


        JButton viewTeamRecordButton = new JButton("Team Record");
        viewTeamRecordButton.addActionListener(e -> viewTeamRecord());


        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> frame.dispose());

        panel.add(label);
        panel.add(addTeamButton);
        panel.add(viewTeamListButton);
        panel.add(viewTeamRecordButton);
        panel.add(quitButton);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void addTeam() {
        JFrame frame = new JFrame("Add Team");
        JPanel panel = new JPanel(new GridLayout(2, 1));
        JLabel label = new JLabel("Enter the name of the new team:");
        JTextField textField = new JTextField();

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            String teamName = textField.getText();
            if (!teamName.isBlank()) {
                Team newTeam = new Team(teamName);
                league.addTeam(newTeam);
                JOptionPane.showMessageDialog(frame, "Team added successfully!");
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Team name cannot be blank.");
            }
        });

        panel.add(label);
        panel.add(textField);
        panel.add(addButton);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }


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

    public void viewTeamRecord() {
        List<Team> teams = league.getTeams();
        if (teams.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No teams found.");
            return;
        }

        String[] teamNames = teams.stream()
                .map(Team::getTeamName)
                .toArray(String[]::new);

        String teamName = (String) JOptionPane.showInputDialog(null, "Select team:",
                "Team Record", JOptionPane.QUESTION_MESSAGE, null, teamNames, teamNames[0]);

        if (teamName == null) {
            return;
        }

        Team selectedTeam = findTeamByName(teamName);

        List<Match> teamMatches = matchRecords.getMatchesForTeam(selectedTeam);

        if (teamMatches.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No match records found for team " + selectedTeam.getTeamName());
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Match records for team ").append(selectedTeam.getTeamName()).append(":\n");

        for (Match match : teamMatches) {
            sb.append(match.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private void displayStandings() {
        List<String> standings = league.getStandings();

        // Create a new JList to display the standings
        DefaultListModel<String> model = new DefaultListModel<String>();
        for (String standing : standings) {
            model.addElement(standing);
        }
        JList<String> standingsList = new JList<String>(model);
        standingsList.setFont(new Font("Arial", Font.PLAIN, 14));

        // Create a new JScrollPane to display the JList
        JScrollPane scrollPane = new JScrollPane(standingsList);
        scrollPane.setPreferredSize(new Dimension(400, 400));

        // Show the standings in a JOptionPane
        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "League Standings",
                JOptionPane.PLAIN_MESSAGE
        );
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

