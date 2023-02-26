package model;

import model.Team;

import java.util.ArrayList;

public class ListOfTeams {
    private ArrayList<Team> listOfTeams;

    public ListOfTeams() {
        this.listOfTeams = new ArrayList<>();
    }

    public void addTeam(Team team) {
        this.listOfTeams.add(team);
    }

    public ArrayList<Team> getTeams() {
        return listOfTeams;
    }
}