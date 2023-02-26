package model;

import javax.xml.transform.Result;

public class Match {
    private Team homeTeam;
    private Team awayTeam;
    private int homeGoals;
    private int awayGoals;
    private Result result;

    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeGoals = 0;
        this.awayGoals = 0;
        this.result = null;
    }

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



    public String getWinner() {
        if (homeGoals > awayGoals) {
            return homeTeam.getTeamName();
        } else if (homeGoals < awayGoals) {
            return awayTeam.getTeamName();
        } else {
            return null;
        }
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public String getHomeTeamName() {
        return homeTeam.getTeamName();
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public String getAwayTeamName() {
        return awayTeam.getTeamName();
    }

    public int getHomeScore() {
        return homeGoals;
    }

    public int getAwayScore() {
        return awayGoals;
    }

    @Override
    public String toString() {
        return homeTeam.getTeamName() + " " + homeGoals + " - " + awayGoals + " " + awayTeam.getTeamName();
    }
}