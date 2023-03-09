package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TeamTest {
    private Team team;


    @BeforeEach
    public void setup() {
        team = new Team("A");
    }

    @Test
    public void testConstructor() {
        assertEquals("A", team.getTeamName());
        assertEquals(0, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(0, team.getDraws());
        assertEquals(0, team.getPoints());
    }

    @Test
    public void testOneWin() {
        assertEquals("A", team.getTeamName());
        assertEquals(0, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(0, team.getDraws());
        assertEquals(0, team.getPoints());

        team.updatePoints(Team.Result.WIN);

        assertEquals("A", team.getTeamName());
        assertEquals(1, team.getMatchesPlayed());
        assertEquals(1, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(0, team.getDraws());
        assertEquals(3, team.getPoints());

    }

    @Test
    public void testTwoWin() {
        assertEquals("A", team.getTeamName());
        assertEquals(0, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(0, team.getDraws());
        assertEquals(0, team.getPoints());

        team.updatePoints(Team.Result.WIN);
        assertEquals("A", team.getTeamName());
        assertEquals(1, team.getMatchesPlayed());
        assertEquals(1, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(0, team.getDraws());
        assertEquals(3, team.getPoints());

        team.updatePoints(Team.Result.WIN);
        assertEquals("A", team.getTeamName());
        assertEquals(2, team.getMatchesPlayed());
        assertEquals(2, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(0, team.getDraws());
        assertEquals(6, team.getPoints());

    }

    @Test
    public void testOneLose() {
        assertEquals("A", team.getTeamName());
        assertEquals(0, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(0, team.getDraws());
        assertEquals(0, team.getPoints());

        team.updatePoints(Team.Result.LOSS);

        assertEquals("A", team.getTeamName());
        assertEquals(1, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(1, team.getLosses());
        assertEquals(0, team.getDraws());
        assertEquals(0, team.getPoints());

    }

    @Test
    public void testTwoLose() {
        assertEquals("A", team.getTeamName());
        assertEquals(0, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(0, team.getDraws());
        assertEquals(0, team.getPoints());

        team.updatePoints(Team.Result.LOSS);

        assertEquals("A", team.getTeamName());
        assertEquals(1, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(1, team.getLosses());
        assertEquals(0, team.getDraws());
        assertEquals(0, team.getPoints());

        team.updatePoints(Team.Result.LOSS);

        assertEquals("A", team.getTeamName());
        assertEquals(2, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(2, team.getLosses());
        assertEquals(0, team.getDraws());
        assertEquals(0, team.getPoints());

    }

    @Test
    public void testOneDraw() {
        assertEquals("A", team.getTeamName());
        assertEquals(0, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(0, team.getDraws());
        assertEquals(0, team.getPoints());

        team.updatePoints(Team.Result.DRAW);

        assertEquals("A", team.getTeamName());
        assertEquals(1, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(1, team.getDraws());
        assertEquals(1, team.getPoints());

    }

    @Test
    public void testTwoDraw() {
        assertEquals("A", team.getTeamName());
        assertEquals(0, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(0, team.getDraws());
        assertEquals(0, team.getPoints());

        team.updatePoints(Team.Result.DRAW);

        assertEquals("A", team.getTeamName());
        assertEquals(1, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(1, team.getDraws());
        assertEquals(1, team.getPoints());

        team.updatePoints(Team.Result.DRAW);

        assertEquals("A", team.getTeamName());
        assertEquals(2, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(2, team.getDraws());
        assertEquals(2, team.getPoints());

    }

    @Test
    public void testWinDraw() {
        assertEquals("A", team.getTeamName());
        assertEquals(0, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(0, team.getDraws());
        assertEquals(0, team.getPoints());

        team.updatePoints(Team.Result.WIN);
        assertEquals("A", team.getTeamName());
        assertEquals(1, team.getMatchesPlayed());
        assertEquals(1, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(0, team.getDraws());
        assertEquals(3, team.getPoints());

        team.updatePoints(Team.Result.DRAW);
        assertEquals("A", team.getTeamName());
        assertEquals(2, team.getMatchesPlayed());
        assertEquals(1, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(1, team.getDraws());
        assertEquals(4, team.getPoints());

    }

    @Test
    public void testWinLoss() {
        assertEquals("A", team.getTeamName());
        assertEquals(0, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(0, team.getDraws());
        assertEquals(0, team.getPoints());

        team.updatePoints(Team.Result.WIN);
        assertEquals("A", team.getTeamName());
        assertEquals(1, team.getMatchesPlayed());
        assertEquals(1, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(0, team.getDraws());
        assertEquals(3, team.getPoints());

        team.updatePoints(Team.Result.LOSS);
        assertEquals("A", team.getTeamName());
        assertEquals(2, team.getMatchesPlayed());
        assertEquals(1, team.getWins());
        assertEquals(1, team.getLosses());
        assertEquals(0, team.getDraws());
        assertEquals(3, team.getPoints());

    }

    @Test
    public void testLossDraw() {
        assertEquals("A", team.getTeamName());
        assertEquals(0, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(0, team.getDraws());
        assertEquals(0, team.getPoints());

        team.updatePoints(Team.Result.LOSS);

        assertEquals("A", team.getTeamName());
        assertEquals(1, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(1, team.getLosses());
        assertEquals(0, team.getDraws());
        assertEquals(0, team.getPoints());

        team.updatePoints(Team.Result.DRAW);

        assertEquals("A", team.getTeamName());
        assertEquals(2, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(1, team.getLosses());
        assertEquals(1, team.getDraws());
        assertEquals(1, team.getPoints());

    }

    @Test
    public void testLossWin() {
        assertEquals("A", team.getTeamName());
        assertEquals(0, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(0, team.getDraws());
        assertEquals(0, team.getPoints());

        team.updatePoints(Team.Result.LOSS);

        assertEquals("A", team.getTeamName());
        assertEquals(1, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(1, team.getLosses());
        assertEquals(0, team.getDraws());
        assertEquals(0, team.getPoints());

        team.updatePoints(Team.Result.WIN);

        assertEquals("A", team.getTeamName());
        assertEquals(2, team.getMatchesPlayed());
        assertEquals(1, team.getWins());
        assertEquals(1, team.getLosses());
        assertEquals(0, team.getDraws());
        assertEquals(3, team.getPoints());

    }

    @Test
    public void testDrawWin() {
        assertEquals("A", team.getTeamName());
        assertEquals(0, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(0, team.getDraws());
        assertEquals(0, team.getPoints());

        team.updatePoints(Team.Result.DRAW);

        assertEquals("A", team.getTeamName());
        assertEquals(1, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(1, team.getDraws());
        assertEquals(1, team.getPoints());

        team.updatePoints(Team.Result.WIN);

        assertEquals("A", team.getTeamName());
        assertEquals(2, team.getMatchesPlayed());
        assertEquals(1, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(1, team.getDraws());
        assertEquals(4, team.getPoints());

    }

    @Test
    public void testDrawLoss() {
        assertEquals("A", team.getTeamName());
        assertEquals(0, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(0, team.getDraws());
        assertEquals(0, team.getPoints());

        team.updatePoints(Team.Result.DRAW);

        assertEquals("A", team.getTeamName());
        assertEquals(1, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(1, team.getDraws());
        assertEquals(1, team.getPoints());

        team.updatePoints(Team.Result.LOSS);

        assertEquals("A", team.getTeamName());
        assertEquals(2, team.getMatchesPlayed());
        assertEquals(0, team.getWins());
        assertEquals(1, team.getLosses());
        assertEquals(1, team.getDraws());
        assertEquals(1, team.getPoints());

    }


    @Test
    public void testWin() {
        team.win();
        assertEquals(3, team.getPoints());
        assertEquals(1, team.getWins());
    }

    @Test
    public void testLose() {
        team.lose();
        assertEquals(0, team.getPoints());
        assertEquals(0, team.getWins());
        assertEquals(1, team.getLosses());
    }

    @Test
    public void testDraw() {
        team.draw();
        assertEquals(1, team.getPoints());
        assertEquals(0, team.getWins());
        assertEquals(0, team.getLosses());
        assertEquals(1, team.getDraws());
    }

    @Test
    void testGetTeamName() {
        assertEquals("A", team.getTeamName());
    }

    @Test
    void testGetMatchesPlayed() {
        team.updatePoints(Team.Result.WIN);
        team.updatePoints(Team.Result.WIN);
        assertEquals(2, team.getMatchesPlayed());
    }

    @Test
    void testGetPoints() {
        team.updatePoints(Team.Result.WIN);
        team.updatePoints(Team.Result.DRAW);
        team.updatePoints(Team.Result.LOSS);
        assertEquals(4, team.getPoints());
    }

    @Test
    void testGetWins() {
        team.updatePoints(Team.Result.WIN);
        team.updatePoints(Team.Result.DRAW);
        team.updatePoints(Team.Result.LOSS);
        assertEquals(1, team.getWins());
    }

    @Test
    void testGetLosses() {
        team.updatePoints(Team.Result.WIN);
        team.updatePoints(Team.Result.DRAW);
        team.updatePoints(Team.Result.WIN);
        assertEquals(0, team.getLosses());
    }

    @Test
    void testGetDraws() {
        team.updatePoints(Team.Result.WIN);
        team.updatePoints(Team.Result.DRAW);
        team.updatePoints(Team.Result.LOSS);
        assertEquals(1, team.getDraws());
    }

    @Test
    void testUpdatePointsWithNullResult() {
        assertThrows(IllegalArgumentException.class, () -> team.updatePoints(null));
    }

    @Test
    void testUnrecognizedResult() {
        assertThrows(IllegalArgumentException.class, () -> team.updatePoints(Team.Result.UNRECOGNIZED));
    }

}


