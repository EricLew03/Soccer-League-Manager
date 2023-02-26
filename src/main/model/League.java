package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class League {
    private List<Team> teams;

    // EFFECTS : construct a soccer league, the list is initialised with 0 teams
    public League() {
        this.teams = new ArrayList<>();
    }

    // REQUIRES : cannot add a team that is already present in the league
    // MODIFIES : this
    // EFFECTS : add a new team into the league
    public void addTeam(Team team) {
        teams.add(team);
    }

    public ArrayList<Team> getTeams() {
        return teams.getTeams();
    }

    // REQUIRES: number of team in the league >= 2
    // MODIFIES: This
    // EFFECTS: sort the teams in descending order based on the number of points they have
    //          in case, two teams have the same amount of points, the team with the higher
    //          number of wins is moved up.
    public List<Team> getStandings() {
        Collections.sort(teams, (a, b) -> {
            int pointsA = (a.getPoints());
            int pointsB = (b.getPoints());
            if (pointsA != pointsB) {
                return Integer.compare(pointsB, pointsA);
            }
            return Integer.compare(b.getWins(), a.getWins());
        });
        return teams;
    }

}

