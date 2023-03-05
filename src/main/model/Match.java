package model;

import org.json.JSONObject;
import persistence.Writable;

import javax.xml.transform.Result;

// represents a match between 2 teams, it contains the match score and produce the result based on the scores
public class Match implements Writable {
    private Team homeTeam;
    private Team awayTeam;
    private int homeGoals;
    private int awayGoals;
    private Result result;

    // EFFECTS: Constructs a match with a given home team and away team
    //          goals for home and away team is initialised as 0
    //          result = null
    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeGoals = 0;
        this.awayGoals = 0;
        this.result = null;
    }

    // REQUIRES: homeGoals is a non-negative integer
    // MODIFIES: this
    // EFFECTS: Updates the score of the home team
    public void changeHomeScore(int homeGoals) {
        this.homeGoals = homeGoals;
    }


    // REQUIRES: awayGoals is a non-negative integer
    // MODIFIES: this
    // EFFECTS: Updates the score of the away team
    public void changeAwayScore(int awayGoals) {
        this.awayGoals = awayGoals;
    }


    // REQUIRES: homeGoals and awayGoals are non-negative integers
    // MODIFIES: this, homeTeam, awayTeam
    // EFFECTS: Updates the result of the match and updates the points
    // of the home and away teams accordingly
    public void updateResult(int homeGoals, int awayGoals) {
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        if (homeGoals > awayGoals) {
            homeTeam.updatePoints(Team.Result.WIN);
            awayTeam.updatePoints(Team.Result.LOSS);
        } else if (homeGoals < awayGoals) {
            homeTeam.updatePoints(Team.Result.LOSS);
            awayTeam.updatePoints(Team.Result.WIN);
        } else {
            homeTeam.updatePoints(Team.Result.DRAW);
            awayTeam.updatePoints(Team.Result.DRAW);
        }
    }


    // EFFECTS : Returns the home team
    public Team getHomeTeam() {
        return homeTeam;
    }

    // EFFECTS: Returns the away team
    public Team getAwayTeam() {
        return awayTeam;
    }


    // EFFECTS : Returns the home goals
    public int getHomeGoals() {
        return homeGoals;
    }

    // EFFECTS: Returns the away goals
    public int getAwayGoals() {
        return awayGoals;
    }


    // EFFECTS: Returns a string representation of the match
    @Override
    public String toString() {
        return homeTeam.getTeamName() + " " + homeGoals + " - " + awayGoals + " " + awayTeam.getTeamName();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("homeTeam",homeTeam.getTeamName());
        json.put("awayTeam", awayTeam.getTeamName());
        json.put("homeGoals", homeGoals);
        json.put("awayGoals", awayGoals);

        return json;
    }
}
