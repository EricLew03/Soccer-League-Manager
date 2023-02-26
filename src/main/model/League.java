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

    public List<Team> getTeams() {
        return teams;
    }
    // REQUIRES: number of team in the league >= 2
    // MODIFIES: This
    // EFFECTS: sort the teams in descending order based on the number of points they have
    //          in case, two teams have the same amount of points, the team with the higher
    //          number of wins is moved up.

    public List<String> getStandings() {
        Collections.sort(teams, (a, b) -> {
            int pointsA = a.getPoints();
            int pointsB = b.getPoints();
            if (pointsA != pointsB) {
                return Integer.compare(pointsB, pointsA);
            } else {
                int winsA = a.getWins();
                int winsB = b.getWins();
                if (winsA != winsB) {
                    return Integer.compare(winsB, winsA);
                } else {
                    int lossesA = a.getLosses();
                    int lossesB = b.getLosses();
                    if (lossesA != lossesB) {
                        return Integer.compare(lossesA, lossesB);
                    } else {
                        int drawsA = a.getDraws();
                        int drawsB = b.getDraws();
                        if (drawsA != drawsB) {
                            return Integer.compare(drawsB, drawsA);
                        } else {
                            return a.getTeamName().compareTo(b.getTeamName());
                        }
                    }
                }
            }
        });

        List<String> standings = new ArrayList<>();
        standings.add(String.format("%-20s%-10s%-10s%-10s%-10s", "Team", "Points", "Wins", "Losses", "Draws"));
        for (Team team : teams) {
            standings.add(String.format("%-20s%-10d%-10d%-10d%-10d",
                    team.getTeamName(), team.getPoints(), team.getWins(),
                    team.getLosses(), team.getDraws()));
        }

        return standings;
    }



}

