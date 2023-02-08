package model;

import javax.xml.transform.Result;

public class Team {
    private String name;
    private int matchesPlayed;
    private int wins;
    private int draws;
    private int losses;
    private int points;

    public enum Result { WIN,LOSS, DRAW }

    /* EFFECTS: construct a soccer team with name n and the number of match played,
                wins, losses, draws, points are all initialised as 0
    */

    public Team(String n) {
        this.name = n;
        this.matchesPlayed = 0;
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
        this.points = 0;
    }


    // MODIFIES: This
    /* EFFECTS: update the record of a team based on the result of its match
                where each match will increment the matches played by 1
                If the result is a win, increment wins by 1 and points by 3
                If the result is a loss, increment losses by 1 and points stay the same
                If the result is a draw, increment both draws and points by 1
     */

    public void update(Result result) {
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
        }
    }


    // EFFECTS: increase the number of wins by 1 and add 3 point to the total points of the team
    public void win() {
        wins++;
        points += 3;
    }

    // EFFECTS: increase the number of losses by 1 and point stays the same
    public void lose() {
        losses++;
    }

    // EFFECTS: increase the number of draws by 1 and add 1 point to the total points of the team
    public void draw() {
        draws++;
        points += 1;
    }

    // EFFECTS: return the name of the team
    public String getTeamName() {
        return name;
    }

    // EFFECTS: return the number of matches a team played
    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    // EFFECTS: return the number of points a team has
    public int getPoints() {
        return points;
    }

    // EFFECTS: return the number of wins that a team has
    public int getWins() {
        return wins;
    }

    // EFFECTS: return the number of losses that a team has
    public int getLosses() {
        return losses;
    }


    // EFFECTS: return the number of draws that a team has
    public int getDraws() {
        return draws;
    }

}
