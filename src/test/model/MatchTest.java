package model;

import model.Match;
import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatchTest {

    private Team homeTeam;
    private Team awayTeam;
    private Match match;

    @BeforeEach
    public void setUp() {
        homeTeam = new Team("Home Team");
        awayTeam = new Team("Away Team");
        match = new Match(homeTeam, awayTeam);
    }

    @Test
    public void testUpdateResultHomeWin() {
        match.updateResult(2, 1);
        assertEquals(3, homeTeam.getPoints());
        assertEquals(0, awayTeam.getPoints());
    }

    @Test
    public void testUpdateResultAwayWin() {
        match.updateResult(1, 2);
        assertEquals(0, homeTeam.getPoints());
        assertEquals(3, awayTeam.getPoints());
    }

    @Test
    public void testUpdateResultDraw() {
        match.updateResult(1, 1);
        assertEquals(1, homeTeam.getPoints());
        assertEquals(1, awayTeam.getPoints());
    }

    @Test
    public void testGetHomeTeam() {
        assertEquals(homeTeam, match.getHomeTeam());
    }

    @Test
    public void testGetAwayTeam() {
        assertEquals(awayTeam, match.getAwayTeam());
    }

    @Test
    public void testToString() {
        match.updateResult(2, 1);
        assertEquals("Home Team 2 - 1 Away Team", match.toString());
    }
}