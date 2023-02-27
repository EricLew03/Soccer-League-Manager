import model.League;
import model.Match;
import model.MatchRecords;
import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LeagueTest {
    private League league;

    private Team chelsea;
    private Team manchesterCity;
    private Team manchesterUnited;

    @BeforeEach
    void setUp() {
        league = new League();
        chelsea = new Team("Chelsea");
        manchesterCity = new Team("Manchester City");
        manchesterUnited = new Team("Manchester United");

        league.addTeam(chelsea);
        league.addTeam(manchesterCity);
        league.addTeam(manchesterUnited);
    }

    @Test
    void testAddTeam() {
        Team arsenal = new Team("Arsenal");
        league.addTeam(arsenal);

        List<Team> teams = league.getTeams();
        assertTrue(teams.contains(arsenal));
        assertEquals(4, teams.size());
    }

    @Test
    void testGetTeams() {
        List<Team> teams = league.getTeams();

        assertTrue(teams.contains(chelsea));
        assertTrue(teams.contains(manchesterCity));
        assertTrue(teams.contains(manchesterUnited));
        assertEquals(3, teams.size());
    }

    @Test
    void testGetStandings() {
        MatchRecords matchRecords = new MatchRecords();
        Match chelseaVsManchesterCity = new Match(chelsea, manchesterCity);
        chelseaVsManchesterCity.updateResult(2, 0);
        matchRecords.addMatch(chelseaVsManchesterCity);

        Match manchesterCityVsManchesterUnited = new Match(manchesterCity, manchesterUnited);
        manchesterCityVsManchesterUnited.updateResult(2, 1);
        matchRecords.addMatch(manchesterCityVsManchesterUnited);

        Match manchesterUnitedVsChelsea = new Match(manchesterUnited, chelsea);
        manchesterUnitedVsChelsea.updateResult(0, 1);
        matchRecords.addMatch(manchesterUnitedVsChelsea);

        for (Match match : matchRecords.getMatchRecords()) {
            match.updateResult(match.getHomeGoals(), match.getAwayGoals());
        }

        List<String> standings = league.getStandings();
        assertEquals("Chelsea                 2         6         2         0        ", standings.get(1));
        assertEquals("Manchester City         2         3         1         1        ", standings.get(2));
        assertEquals("Manchester United       2         0         0         2        ", standings.get(3));
    }
}