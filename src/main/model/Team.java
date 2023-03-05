package model;

import org.json.JSONObject;
import persistence.Writable;


// represents a team which contains the stats like matches played, wins, losses and points.
public class Team implements Writable {
    private String teamName;
    private int matchesPlayed;
    private int wins;
    private int draws;
    private int losses;
    private int points;


    public enum Result {
        WIN, LOSS, DRAW,  UNRECOGNIZED
    }

    // EFFECTS: constructs a new Team object with the given team name
    //          initializes all other fields to 0
    public Team(String n) {
        this.teamName = n;
        this.matchesPlayed = 0;
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
        this.points = 0;
    }

    // MODIFIES: this
    // EFFECTS: updates the team's record based on the given result
    public void updatePoints(Result result) throws IllegalArgumentException {
        if (result == null) {
            throw new IllegalArgumentException("Result cannot be null.");
        }
        this.matchesPlayed++;
        switch (result) {
            case WIN:
                this.win();
                break;
            case LOSS:
                this.lose();
                break;
            case DRAW:
                this.draw();
                break;
            default:
                throw new IllegalArgumentException("Result not recognized.");
        }
    }



    // MODIFIES: this
    // EFFECTS: increments the number of wins and points
    public void win() {
        wins++;
        points += 3;
    }

    // MODIFIES: this
    // EFFECTS: increments the number of losses
    public void lose() {
        losses++;
    }

    // MODIFIES: this
    // EFFECTS: increments the number of draws and points
    public void draw() {
        draws++;
        points += 1;
    }

    // MODIFIES: this
    // EFFECTS : change the number of matches played to the number input
    public void setMatchesPlayed(int played) {
        this.matchesPlayed = played;
    }

    // MODIFIES: this
    // EFFECTS : change the number of wins to the number input
    public void setWins(int wins) {
        this.wins = wins;
    }

    // MODIFIES: this
    // EFFECTS : change the number of losses to the number input
    public void setLosses(int losses) {
        this.losses = losses;
    }

    // MODIFIES: this
    // EFFECTS : change the number of draws to the number input
    public void setDraws(int draws) {
        this.draws = draws;
    }

    // MODIFIES: this
    // EFFECTS : change the number of points to the number input
    public void setPoints(int points) {
        this.points = points;
    }



    // EFFECTS: returns the team name
    public String getTeamName() {
        return teamName;
    }

    // EFFECTS: returns the number of matches played
    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    // EFFECTS: returns the number of points
    public int getPoints() {
        return points;
    }

    // EFFECTS: returns the number of wins
    public int getWins() {
        return wins;
    }

    // EFFECTS: returns the number of losses
    public int getLosses() {
        return losses;
    }

    // EFFECTS: returns the number of draws
    public int getDraws() {
        return draws;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", teamName);
        json.put("played", matchesPlayed);
        json.put("wins", wins);
        json.put("draws", draws);
        json.put("losses", losses);
        json.put("points", points);
        return json;
    }
}



