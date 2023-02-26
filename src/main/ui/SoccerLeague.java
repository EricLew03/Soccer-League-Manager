package ui;

import model.Match;
import model.MatchRecords;
import model.Team;
import model.League;

import java.util.Scanner;

// Soccer League application
public class SoccerLeague {
    private Team team;
    private MatchRecords matchRecords;
    private League league;
    private Scanner input;

    // EFFECTS: runs the soccer application
    public SoccerLeague() {
        matchRecords = new MatchRecords();
        league = new League();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        runLeague();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
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

    private void processCommand(String command) {
        switch (command) {
            case "m":
                doMatch();
                break;
            case "t":
                doTeam();
                break;
            case "l":
                doLeague();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

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

    private void addMatch() {
        System.out.println("Enter home team name:");
        String homeTeamName = input.next();
        System.out.println("Enter away team name:");
        String awayTeamName = input.next();

        Match match = new Match(matchRecords, homeTeamName, awayTeamName);
        System.out.println("Enter home team goals:");
        int homeTeamGoals = input.nextInt();
        match.setHomeTeamGoals(homeTeamGoals);
        System.out.println("Enter away team goals:");
        int awayTeamGoals = input.nextInt();
        match.setAwayTeamGoals(awayTeamGoals);

        matchRecords.addMatch(match);
        System.out.println("Match added successfully!");
        league.getStandings();
    }

    private void viewMatchRecords() {
        System.out.println(matchRecords.toString());
    }

    private void doTeam() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add Team");
        System.out.println("\tt -> team record");
        System.out.println("\tl -> list of teams");
        System.out.println("\tq -> quit");

        String command = input.next().toLowerCase();
        switch (command) {
            case "a":
                league.addTeam(team);
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

    private void doLeague() {
        System.out.println("\nSelect from:");
        System.out.println("\tv -> view league standings");
        System.out.println("\tq -> quit");

        String command = input.next().toLowerCase();
        switch (command) {
            case "v":
                league.getStandings();
                break;
        }
    }
}


