package model;

//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class MatchTest {
//    private Match match;
//    private Team firstTeam;
//    private Team secondTeam;
//
//
//
//
//    @BeforeEach
//    public void setUp() {
//        match = new Match(firstTeam, secondTeam);
//    }
//
//    @Test
//    public void testFirstTeamWins() {
//        match.setScore(2, 1);
//        assertEquals(2, match.getTeam1Score());
//        assertEquals(1, match.getTeam2Score());
//        assertEquals(Team.Result.WIN, match.firstTeam.getResult());
//        assertEquals(Team.Result.LOSS, match.secondTeam.getResult());
//    }
//
//    @Test
//    public void testSecondTeamWins() {
//        match.setScore(1, 2);
//        assertEquals(1, match.getTeam1Score());
//        assertEquals(2, match.getTeam2Score());
//        assertEquals(Team.Result.LOSS, match.firstTeam.getResult());
//        assertEquals(Team.Result.WIN, match.secondTeam.getResult());
//    }
//
//    @Test
//    public void testDraw() {
//        match.setScore(1, 1);
//        assertEquals(1, match.getTeam1Score());
//        assertEquals(1, match.getTeam2Score());
//        assertEquals(Team.Result.DRAW, match.firstTeam.getResult());
//        assertEquals(Team.Result.DRAW, match.secondTeam.getResult());
//
//    }
//
//}
