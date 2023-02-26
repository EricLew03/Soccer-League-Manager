package ui;

import model.Match;
import model.MatchRecords;
import model.Team;
import model.League;
import model.ListOfTeams;

import java.util.List;
import java.util.Scanner;

// Soccer League application
public class SoccerLeague {
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

    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tm -> match options");
        System.out.println("\tt -> team options");
        System.out.println("\tl -> league options");
        System.out.println("\te -> exit");
        System.out.print("Enter your selection: ");
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
                league.getStandings();
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
        Team homeTeam = new Team(homeTeamName);
        System.out.println("Enter away team name:");
        String awayTeamName = input.next();
        Team awayTeam = new Team(awayTeamName);

        Match match = new Match(homeTeam, awayTeam);

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

        matchRecords.addMatch(match);
        System.out.println("Match added successfully!");
    }

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

    private void addTeam() {
        System.out.println("Enter the name of the new team:");
        String teamName = input.next();
        Team newTeam = new Team(teamName);
        league.addTeam(newTeam);
        System.out.println("Team added successfully!");
    }

    public List<Team> viewTeamList() {
        return league.getTeams();
    }

    public void viewTeamRecord(){
    }
}


