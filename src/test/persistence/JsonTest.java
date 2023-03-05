package persistence;

import model.Match;
import model.Team;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class JsonTest {
    protected void checkTeam(String name, int played, int wins, int draws, int losses, int points, Team team) {
        assertEquals(name, team.getTeamName());
        assertEquals(played, team.getMatchesPlayed());
        assertEquals(wins, team.getWins());
        assertEquals(draws, team.getDraws());
        assertEquals(losses, team.getLosses());
        assertEquals(points, team.getPoints());
    }

    protected void checkMatch(String homeTeam, String awayTeam, int homeGoals, int awayGoals, Match match) {
        assertEquals(homeTeam, match.getHomeTeam().getTeamName());
        assertEquals(awayTeam, match.getAwayTeam().getTeamName());
        assertEquals(homeGoals, match.getHomeGoals());
        assertEquals(awayGoals, match.getAwayGoals());
    }



}
