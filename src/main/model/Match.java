package model;

public class Match {

    Team firstTeam;
    Team secondTeam;
    private int firstTeamScore;
    private int secondTeamScore;

    // REQUIRES: the 2 teams in the match must be different
    /* EFFECTS: constructs a match between 2 teams,
                with the score for both teams initialised as 0
    */
    public Match(Team firstTeam, Team secondTeam) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.firstTeamScore = 0;
        this.secondTeamScore = 0;
    }

    // REQUIRES: the score for both team are >= 0
    // MODIFIES: This
    /* EFFECTS: produce the result of the match where
                if the first team scores more goals, the result is set
                to win for the team while the result of the other team
                is set to lose vice versa for both teams
                If the score for both team is the same, the result is a
                draw for both teams
    */
    public void setScore(int team1Score, int team2Score) {
        this.firstTeamScore = team1Score;
        this.secondTeamScore = team2Score;
        if (team1Score > team2Score) {
            firstTeam.update(Team.Result.WIN);
            secondTeam.update(Team.Result.LOSS);
        } else if (team1Score < team2Score) {
            firstTeam.update(Team.Result.LOSS);
            secondTeam.update(Team.Result.WIN);
        } else {
            firstTeam.update(Team.Result.DRAW);
            secondTeam.update(Team.Result.DRAW);
        }
    }

    // EFFECTS : return the name of the team that scored more goals
    //           if the score is similar for both team, return null
    public String getWinner() {
        if (firstTeamScore > secondTeamScore) {
            return firstTeam.getTeamName();
        } else if (firstTeamScore < secondTeamScore) {
            return secondTeam.getTeamName();
        } else {
            return null;
        }
    }

    // EFFECTS : return the name of the first team
    public String getTeam1() {
        return firstTeam.getTeamName();
    }

    // EFFECTS : return the name of the second team
    public String getTeam2() {
        return secondTeam.getTeamName();
    }


    // EFFECTS : return the score of the first team
    public int getTeam1Score() {
        return firstTeamScore;
    }

    // EFFECTS : return the score of the second team
    public int getTeam2Score() {
        return secondTeamScore;
    }



}
