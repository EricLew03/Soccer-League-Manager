package ui;

import java.io.FileNotFoundException;

public class GUI {

    public static void main(String[] args) {
        try {
            new SoccerLeagueGUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}

