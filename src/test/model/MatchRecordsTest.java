package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MatchRecordsTest {
    private MatchRecords matchRecords;
    private Team team1;
    private Team team2;
    private Match match1;
    private Match match2;
    private Match match3;

    @BeforeEach
    public void setUp() {
        matchRecords = new MatchRecords();
        team1 = new Team("Team 1");
        team2 = new Team("Team 2");
        match1 = new Match(team1, team2);
        match2 = new Match(team2, team1);
        match3 = new Match(team1, team2);
    }

    @Test
    public void testAddMatch() {
        matchRecords.addMatch(match1);
        assertEquals(1, matchRecords.getMatchRecords().size());
        assertTrue(matchRecords.getMatchRecords().contains(match1));
    }

    @Test
    public void testGetMatchRecords() {
        matchRecords.addMatch(match1);
        matchRecords.addMatch(match2);
        matchRecords.addMatch(match3);
        List<Match> matches = matchRecords.getMatchRecords();
        assertEquals(3, matches.size());
        assertTrue(matches.contains(match1));
        assertTrue(matches.contains(match2));
        assertTrue(matches.contains(match3));
    }

    @Test
    public void testGetMatchesForTeam() {
        matchRecords.addMatch(match1);
        matchRecords.addMatch(match2);
        matchRecords.addMatch(match3);
        List<Match> matchesForTeam1 = matchRecords.getMatchesForTeam(team1);
        assertEquals(2, matchesForTeam1.size());
        assertTrue(matchesForTeam1.contains(match1));
        assertTrue(matchesForTeam1.contains(match3));
        List<Match> matchesForTeam2 = matchRecords.getMatchesForTeam(team2);
        assertEquals(2, matchesForTeam2.size());
        assertTrue(matchesForTeam2.contains(match1));
        assertTrue(matchesForTeam2.contains(match2));
    }
}
