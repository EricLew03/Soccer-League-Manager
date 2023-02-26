//package model;
//
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//
//public class MatchTest {
//    private Match match;
//    Team team1 = new Team("team1");
//    Team team2 = new Team("team2");
//
//    @BeforeEach
//    public void setUp() {
//        match = new Match(team1, team2);
//    }
//    @Test
//    public void testConstructor() {
//        assertEquals(team1, match.getHomeTeam());
//        assertEquals(team2, match.getAwayTeam());
//        assertEquals(0, match.getHomeScore());
//        assertEquals(0, match.getAwayScore());
//    }
//
//    @Test
//    public void testSetScore() {
//        // Test win for team1
//        match.setScore(2, 1);
//        assertEquals("team1", match.getWinner());
//        assertEquals(Team.Result.WIN, team1.getResult());
//        assertEquals(Team.Result.LOSS, team2.getResult());
//
//        // Test win for team2
//        match.setScore(1, 2);
//        assertEquals("team2", match.getWinner());
//        assertEquals(Team.Result.LOSS, team1.getResult());
//        assertEquals(Team.Result.WIN, team2.getResult());
//
//        // Test draw
//        match.setScore(1, 1);
//        assertNull(match.getWinner());
//        assertEquals(Team.Result.DRAW, Team.Result.getResult());
//        assertEquals(Team.Result.DRAW, team2.getResult());
//        }
//    }


