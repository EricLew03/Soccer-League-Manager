package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a league made of >= 2 teams and produce a standing table based on the number of points.
public class League implements Writable {
    private List<Team> league;

    // EFFECTS: Constructs a soccer league with an empty list of teams
    public League() {
        this.league = new ArrayList<>();
    }

    // REQUIRES: The team must not already be present in the league
    // MODIFIES: this
    // EFFECTS: Adds the given team to the league
    public void addTeam(Team team) {
        league.add(team);
    }

    // EFFECTS:  Returns a List of all teams in the league.
    public List<Team> getTeams() {
        return new ArrayList<>(league);
    }


    // EFFECTS : returns the number of teams in the league
    public Integer numTeam() {
        return league.size();
    }

    public Team getTeamByName(String teamName) {
        for (Team team : league) {
            if (team.getTeamName().equals(teamName)) {
                return team;
            }
        }
        return null; // if team with given name not found
    }


    // REQUIRES: The number of teams in the league must be at least 2
    // MODIFIES: this
    // EFFECTS: Sorts the teams in the league in descending order based on the number of points they have.
    //          If two teams have the same number of points, the team with the higher number of wins is ranked higher.
    //          If two teams have the same number of points and wins, the team with the name that comes first
    //          alphabetically is ranked higher.
    public List<String> getStandings() {
        Collections.sort(league, (a, b) -> {
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
                    return a.getTeamName().compareTo(b.getTeamName());
                }
            }
        });
        List<String> standings = new ArrayList<>();
        standings.add(String.format("%-25s%-10s%-10s%-10s%-10s%-10s", "Team", "Matches",
                "Points", "Wins", "Losses","Draws"));
        for (Team team : league) {
            standings.add(String.format("%-25s%-10d%-10d%-10d%-10d%-10d", team.getTeamName(),team.getMatchesPlayed(),
                    team.getPoints(), team.getWins(), team.getLosses(),team.getDraws()));
        }
        return standings;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("league", teamsToJson());

        return json;
    }

    // EFFECTS : returns the matches in this match records as a JSON array
    private JSONArray teamsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Team t : league) {
            jsonArray.put((t.toJson()));
        }
        return jsonArray;
    }
}


