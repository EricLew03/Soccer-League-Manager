package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchRecordsTest {
    private MatchRecords matchRecords;
    private Team teamA;
    private Team teamB;

    @BeforeEach
    public void setUp() {
        matchRecords = new MatchRecords();
        teamA = new Team("Team A");
        teamB = new Team("Team B");
    }

    @Test
    public void testAddMatch() {
        Match match = new Match(teamA, teamB);
        matchRecords.addMatch(match);
        List<Match> matches = matchRecords.getMatchRecords();
        assertEquals(1, matches.size());
        assertEquals(match, matches.get(0));
    }

    @Test
    public void testGetMatchesForTeam() {
        Match match1 = new Match(teamA, teamB);
        Match match2 = new Match(teamB, teamA);
        Match match3 = new Match(teamA, new Team("Team C"));
        matchRecords.addMatch(match1);
        matchRecords.addMatch(match2);
        matchRecords.addMatch(match3);
        List<Match> teamAMatches = matchRecords.getMatchesForTeam(teamA);
        assertEquals(3, teamAMatches.size());
        assertEquals(match1, teamAMatches.get(0));
        assertEquals(match2, teamAMatches.get(1));
        assertEquals(match3, teamAMatches.get(2));
        List<Match> teamBMatches = matchRecords.getMatchesForTeam(teamB);
        assertEquals(2, teamBMatches.size());
        assertEquals(match1, teamBMatches.get(0));
        assertEquals(match2, teamBMatches.get(1));
    }
}