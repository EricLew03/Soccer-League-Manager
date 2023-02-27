package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {
    private Team team;

    @BeforeEach
    public void setUp() {
        team = new Team("Test Team");
    }

    @Test
    public void testConstructor() {
        assertEquals("Test Team", team.getTeamName());
        assertEquals(0, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(0, team.getDraws());
        assertEquals(0, team.getLosses());
        assertEquals(0, team.getPoints());
    }

    @Test
    public void testUpdatePoints() {
        team.updatePoints(Team.Result.WIN);
        assertEquals(1, team.getMatchesPlayed());
        assertEquals(1, team.getWins());
        assertEquals(0, team.getDraws());
        assertEquals(0, team.getLosses());
        assertEquals(3, team.getPoints());

        team.updatePoints(Team.Result.DRAW);
        assertEquals(2, team.getMatchesPlayed());
        assertEquals(1, team.getWins());
        assertEquals(1, team.getDraws());
        assertEquals(0, team.getLosses());
        assertEquals(4, team.getPoints());

        team.updatePoints(Team.Result.LOSS);
        assertEquals(3, team.getMatchesPlayed());
        assertEquals(1, team.getWins());
        assertEquals(1, team.getDraws());
        assertEquals(1, team.getLosses());
        assertEquals(4, team.getPoints());
    }

    @Test
    public void testWin() {
        team.win();
        assertEquals(1, team.getWins());
        assertEquals(3, team.getPoints());
    }

    @Test
    public void testLose() {
        team.lose();
        assertEquals(1, team.getLosses());
    }

    @Test
    public void testDraw() {
        team.draw();
        assertEquals(1, team.getDraws());
        assertEquals(1, team.getPoints());
    }

    @Test
    public void testGetTeamName() {
        assertEquals("Test Team", team.getTeamName());
    }

    @Test
    public void testGetMatchesPlayed() {
        assertEquals(0, team.getMatchesPlayed());
    }

    @Test
    public void testGetPoints() {
        assertEquals(0, team.getPoints());
    }

    @Test
    public void testGetWins() {
        assertEquals(0, team.getWins());
    }

    @Test
    public void testGetLosses() {
        assertEquals(0, team.getLosses());
    }

    @Test
    public void testGetDraws() {
        assertEquals(0, team.getDraws());
    }
}
