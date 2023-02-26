package model;

public class Team {
    private String teamName;
    private String name;
    private int matchesPlayed;
    private int wins;
    private int draws;
    private int losses;
    private int points;

    public enum Result {
        WIN, LOSS, DRAW;
    }

    public Team(String n) {
        this.teamName = n;
        this.matchesPlayed = 0;
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
        this.points = 0;
    }

    public void updatePoints(Result result) {
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

    public void win() {
        wins++;
        points += 3;
    }

    public void lose() {
        losses++;
    }

    public void draw() {
        draws++;
        points += 1;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public int getPoints() {
        return points;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }

    // Override equals method to check if team name is the same
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Team)) {
            return false;
        }
        Team otherTeam = (Team) o;
        return this.teamName.equals(otherTeam.getTeamName());
    }

    // Override hashCode method to return a unique hash code based on team name
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + teamName.hashCode();
        return result;
    }
}