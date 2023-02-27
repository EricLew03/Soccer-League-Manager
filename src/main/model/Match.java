package model;

import javax.xml.transform.Result;

public class Match {
    private Team homeTeam;
    private Team awayTeam;
    private int homeGoals;
    private int awayGoals;
    private Result result;

    // EFFECTS: Constructs a match with a given home team and away team
    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeGoals = 0;
        this.awayGoals = 0;
        this.result = null;
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

    public int getHomeGoals() {
        return homeGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }



    // EFFECTS: Returns a string representation of the match
    @Override
    public String toString() {
        return homeTeam.getTeamName() + " " + homeGoals + " - " + awayGoals + " " + awayTeam.getTeamName();
    }
}