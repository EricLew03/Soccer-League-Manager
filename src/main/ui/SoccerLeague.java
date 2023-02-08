package ui;

import model.Match;
import model.MatchRecords;
import model.Team;

import java.util.Scanner;

// Soccer League applicaton
public class SoccerLeague {
    private Match match;
    private MatchRecords matchrecords;
    private Team team;
    private Scanner input;


    // EFFECTS: runs the soccer application
    public SoccerLeague() {
        runLeague();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runLeague() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");


    }

    private void processCommand(String command) {
        if (command.equals("m")) {
            doMatch();
        } else if (command.equals("t")) {
            doTeam();
        } else if (command.equals("l")) {
            doLeague();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");

    }





    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tt -> team");
        System.out.println("\tm -> match");
        System.out.println("\tl -> league");
        System.out.println("\tq -> quit");
    }

    private void doMatch() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add match");
        System.out.println("\tm -> match records");
        System.out.println("\tq -> quit");
    }

    private void doTeam() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add Team");
        System.out.println("\tt -> team record");
        System.out.println("\tl -> list of teams");
        System.out.println("\tq -> quit");
    }

    private void doLeague() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add match");
        System.out.println("\tm -> match records");
        System.out.println("\tq -> quit");
    }

}



