package ui;

import model.Match;
import model.MatchRecords;
import model.Team;
import model.League;

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
        List<Team> teams = league.getTeams();

        // Check that there are at least two teams in the league
        if (teams.size() < 2) {
            System.out.println("There must be at least two teams in the league to add a match.");
            return;
        }

        System.out.println("Select home team:");
        Team homeTeam = selectTeam();
        System.out.println("Select away team:");

        Team awayTeam = null;
        while (awayTeam == null || awayTeam == homeTeam) {
            awayTeam = selectTeam();
            if (awayTeam == homeTeam) {
                System.out.println("The away team cannot be the same as the home team. Please select a different team.");
            }
        }

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

    public void viewTeamList() {
        List<Team> teams = league.getTeams();
        if (teams.isEmpty()) {
            System.out.println("No teams found.");
        } else {
            System.out.println("List of teams:");
            for (Team team : teams) {
                System.out.println(team.getTeamName());
            }
        }
    }

    public void viewTeamRecord() {
        List<Team> teams = league.getTeams();
        if (teams.isEmpty()) {
            System.out.println("No teams found.");
        } else {
            System.out.println("List of teams:");
            for (Team team : teams) {
                System.out.println(team.getTeamName());
            }

            System.out.println("Enter the name of the team to view its match records:");
            String teamName = input.next();
            Team selectedTeam = null;
            for (Team team : teams) {
                if (team.getTeamName().equalsIgnoreCase(teamName)) {
                    selectedTeam = team;
                    break;
                }
            }
            if (selectedTeam == null) {
                System.out.println("No team found with the name " + teamName);
            } else {
                List<Match> teamMatches = matchRecords.getMatchesForTeam(selectedTeam);
                if (teamMatches.isEmpty()) {
                    System.out.println("No match records found for team " + selectedTeam.getTeamName());
                } else {
                    System.out.println("Match records for team " + selectedTeam.getTeamName() + ":");
                    for (Match match : teamMatches) {
                        System.out.println(match.toString());
                    }
                }
            }
        }
    }

}


