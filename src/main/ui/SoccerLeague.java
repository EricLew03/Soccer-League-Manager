package ui;

import model.Match;
import model.MatchRecords;
import model.Team;
import model.League;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.List;
import java.util.Scanner;

// Soccer League application
public class SoccerLeague {
    private MatchRecords matchRecords;
    private League league;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the soccer league application
    public SoccerLeague() {
        matchRecords = new MatchRecords();
        league = new League();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        runLeague();
    }

    // MODIFIES: this
    // EFFECTS: processes user input and executes corresponding commands
    private void runLeague() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMenu();
            command = input.next().toLowerCase();

            if (command.equals("e")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }


    // EFFECTS: displays the menu
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tm -> match options");
        System.out.println("\tt -> team options");
        System.out.println("\tl -> league options");
        System.out.println("\te -> exit");
        System.out.print("Enter your selection: ");
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void processCommand(String command) {
        switch (command) {
            case "m":
                doMatch();
                break;
            case "t":
                doTeam();
                break;
            case "l":
                List<String> standings = league.getStandings();
                for (String row : standings) {
                    System.out.println(row);
                }
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }



    // MODIFIES: this
    // EFFECTS: allows the user to manage match options
    private void doMatch() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add match");
        System.out.println("\tm -> match records");
        System.out.println("\tq -> quit");

        String command = input.next().toLowerCase();
        switch (command) {
            case "a":
                addMatch();
                break;
            case "m":
                viewMatchRecords();
                break;
            case "q":
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    // MODIFIES: this, matchRecords
    // EFFECTS: allows the user to add a new match
    private void addMatch() {
        List<Team> teams = league.getTeams();

        // Check that there are at least two teams in the league
        if (teams.size() < 2) {
            System.out.println("There must be at least two teams in the league to add a match.");
            return;
        }

        Team homeTeam = selectHomeTeam();
        Team awayTeam = selectAwayTeam(homeTeam);
        Match match = createMatch(homeTeam, awayTeam);
        updateMatchResult(match);
        matchRecords.addMatch(match);
        System.out.println("Match added successfully!");
    }

    // EFFECTS: Prompt the user to select a home team and returns the selected team.
    private Team selectHomeTeam() {
        System.out.println("Select home team:");
        return selectTeam();
    }

    // EFFECTS: Prompt the user to select an away team and returns the selected team.
    private Team selectAwayTeam(Team homeTeam) {
        System.out.println("Select away team:");
        Team awayTeam = null;
        while (awayTeam == null || awayTeam == homeTeam) {
            awayTeam = selectTeam();
            if (awayTeam == homeTeam) {
                System.out.println("The away team cannot be the same as the home team."
                        + " Please select a different team.");
            }
        }
        return awayTeam;
    }

    // EFFECTS: Creates a new Match with the given home and away teams and returns it.
    private Match createMatch(Team homeTeam, Team awayTeam) {
        return new Match(homeTeam, awayTeam);
    }

    // EFFECTS: Enter the home and away team goals for the given match,
    //          and updates the match result accordingly.
    private void updateMatchResult(Match match) {
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println("Enter home team goals:");
                int homeTeamGoals = input.nextInt();
                System.out.println("Enter away team goals:");
                int awayTeamGoals = input.nextInt();
                match.updateResult(homeTeamGoals, awayTeamGoals);
                validInput = true;
            } catch (Exception e) {
                System.out.println("Invalid input, please enter an integer");
                input.next();
            }
        }
    }

    // EFFECTS: prompts the user to select a team from the league
    private Team selectTeam() {
        List<Team> teams = league.getTeams();
        for (int i = 0; i < teams.size(); i++) {
            System.out.println(i + 1 + ": " + teams.get(i).getTeamName());
        }

        int selection = -1;
        while (selection < 0 || selection >= teams.size()) {
            try {
                selection = input.nextInt() - 1;
                if (selection < 0 || selection >= teams.size()) {
                    System.out.println("Invalid selection, please enter a number between 1 and " + teams.size());
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please enter a number");
                input.next();
            }
        }

        return teams.get(selection);
    }

    // EFFECTS: displays the match records
    private void viewMatchRecords() {
        List<Match> matchRecords2 = matchRecords.getMatchRecords();

        if (matchRecords2.isEmpty()) {
            System.out.println("No match records found.");
        } else {
            System.out.println("Match records:");
            for (Match match : matchRecords2) {
                System.out.println(match.toString());
            }
        }
    }

    // EFFECTS: displays the team options menu
    private void doTeam() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add Team");
        System.out.println("\tt -> team record");
        System.out.println("\tl -> list of teams");
        System.out.println("\tq -> quit");

        String command = input.next().toLowerCase();
        switch (command) {
            case "a":
                addTeam();
                break;
            case "t":
                viewTeamRecord();
                break;
            case "l":
                viewTeamList();
                break;
            case "q":
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    // MODIFIES: this, league
    // EFFECTS: prompts the user to add a new team to the league
    private void addTeam() {
        System.out.println("Enter the name of the new team:");
        String teamName = input.next();
        Team newTeam = new Team(teamName);
        league.addTeam(newTeam);
        System.out.println("Team added successfully!");
    }

    // EFFECTS: Displays all the teams in the league
    public void viewTeamList() {
        List<Team> teams = league.getTeams();
        if (teams.isEmpty()) {
            System.out.println("No teams found.");
        } else {
            displayTeamList(teams);
        }
    }

    // EFFECTS: Displays the match record of a specific team in the league
    public void viewTeamRecord() {
        List<Team> teams = league.getTeams();
        if (teams.isEmpty()) {
            System.out.println("No teams found.");
            return;
        }

        displayTeamList(teams);

        System.out.println("Enter the name of the team to view its match records:");
        String teamName = input.next();
        Team selectedTeam = findTeamByName(teams, teamName);

        if (selectedTeam == null) {
            System.out.println("No team found with the name " + teamName);
            return;
        }

        List<Match> teamMatches = matchRecords.getMatchesForTeam(selectedTeam);

        if (teamMatches.isEmpty()) {
            System.out.println("No match records found for team " + selectedTeam.getTeamName());
            return;
        }

        System.out.println("Match records for team " + selectedTeam.getTeamName() + ":");
        for (Match match : teamMatches) {
            System.out.println(match.toString());
        }
    }

    private void displayTeamList(List<Team> teams) {
        System.out.println("List of teams:");
        for (Team team : teams) {
            System.out.println(team.getTeamName());
        }
    }

    // EFFECTS: Returns the team with the given name, or null if no team is found
    private Team findTeamByName(List<Team> teams, String name) {
        for (Team team : teams) {
            if (team.getTeamName().equalsIgnoreCase(name)) {
                return team;
            }
        }
        return null;
    }

}


