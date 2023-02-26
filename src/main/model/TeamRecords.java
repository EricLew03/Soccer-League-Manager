import model.Match;
import model.Team;

import java.util.ArrayList;

public class TeamRecords {
    private Team team;
    private ArrayList<Match> matchRecords;

    public TeamRecords(Team team) {
        this.team = team;
        this.matchRecords = new ArrayList<>();
    }

    public void addMatch(Match match) {
        matchRecords.add(match);
        updateTeamRecord(match);
    }

    public ArrayList<Match> getMatches() {
        return matchRecords;
    }

    public Team getTeam() {
        return team;
    }

    private void updateTeamRecord(Match match) {
        if (match.getHomeTeamName().equals(team.getTeamName())) {
            team.incrementGoalsFor(match.getHomeScore());
            team.incrementGoalsAgainst(match.getAwayScore());
            if (match.getHomeScore() > match.getAwayScore()) {
                team.incrementWins();
                team.incrementPoints(3);
            } else if (match.getHomeScore() == match.getAwayScore()) {
                team.incrementDraws();
                team.incrementPoints(1);
            } else {
                team.incrementLosses();
            }
        } else if (match.getAwayTeamName().equals(team.getTeamName())) {
            team.incrementGoalsFor(match.getAwayScore());
            team.incrementGoalsAgainst(match.getHomeScore());
            if (match.getAwayScore() > match.getHomeScore()) {
                team.incrementWins();
                team.incrementPoints(3);
            } else if (match.getAwayScore() == match.getHomeScore()) {
                team.incrementDraws();
                team.incrementPoints(1);
            } else {
                team.incrementLosses();
            }
        }
    }
}

