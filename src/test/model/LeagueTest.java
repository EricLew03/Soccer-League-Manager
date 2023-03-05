package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LeagueTest {
    private League league;
    private Team team1;
    private Team team2;
    private Team team3;

    @BeforeEach
    public void setUp() {
        league = new League();
        team1 = new Team("Team 1");
        team2 = new Team("Team 2");
        team3 = new Team("Team 3");
        league.addTeam(team1);
        league.addTeam(team2);
        league.addTeam(team3);
    }

    @Test
    public void testAddTeam() {
        Team team4 = new Team("Team 4");
        league.addTeam(team4);
        List<Team> teams = league.getTeams();
        assertTrue(teams.contains(team4));
    }

    @Test
    public void testGetTeams() {
        List<Team> teams = league.getTeams();
        assertEquals(3, teams.size());
        assertTrue(teams.contains(team1));
        assertTrue(teams.contains(team2));
        assertTrue(teams.contains(team3));
    }

    @Test
    public void testGetStandings() {
        Match match1 = new Match(team1, team2);
        Match match2 = new Match(team2, team3);
        Match match3 = new Match(team3, team1);

        match1.updateResult(1, 0);
        match2.updateResult(1, 1);
        match3.updateResult(0, 2);

        List<String> expectedStandings = new ArrayList<>();
        expectedStandings.add(String.format("%-25s%-10s%-10s%-10s%-10s%-10s", "Team", "Matches", "Points", "Wins",
                "Losses","Draws"));
        expectedStandings.add(String.format("%-25s%-10d%-10d%-10d%-10d%-10d", "Team 1", 2, 6, 2, 0, 0));
        expectedStandings.add(String.format("%-25s%-10d%-10d%-10d%-10d%-10d", "Team 2", 2, 1, 0, 1, 1));
        expectedStandings.add(String.format("%-25s%-10d%-10d%-10d%-10d%-10d", "Team 3", 2, 1, 0, 1, 1));

        assertEquals(expectedStandings, league.getStandings());
    }

    @Test
    public void testGetTeamByName() {
        Team testTeam = league.getTeamByName("Team 1");
        Team testTeam2 = league.getTeamByName("teeee");
        assertEquals(team1, testTeam);
        assertEquals(null, testTeam2);
    }

    @Test
    public void testNumTeam() {
        Integer A = league.numTeam() ;
        assertEquals(3,A);
    }
    @Test
    public void testSamePoints() {
        Match match1 = new Match(team1, team2);
        Match match2 = new Match(team2, team3);
        Match match3 = new Match(team3, team2);
        Match match4 = new Match(team2, team3);

        match1.updateResult(1, 0);
        match2.updateResult(1, 1);
        match3.updateResult(1, 1);
        match4.updateResult(1, 1);


        List<String> expectedStandings = new ArrayList<>();
        expectedStandings.add(String.format("%-25s%-10s%-10s%-10s%-10s%-10s", "Team", "Matches", "Points", "Wins",
                "Losses", "Draws"));
        expectedStandings.add(String.format("%-25s%-10d%-10d%-10d%-10d%-10d", "Team 1", 1, 3, 1, 0, 0));
        expectedStandings.add(String.format("%-25s%-10d%-10d%-10d%-10d%-10d", "Team 2", 4, 3, 0, 1, 3));
        expectedStandings.add(String.format("%-25s%-10d%-10d%-10d%-10d%-10d", "Team 3", 3, 3, 0, 0, 3));

        assertEquals(expectedStandings, league.getStandings());
    }

    @Test
    public void testSameWinsPoints() {
        Match match1 = new Match(team1, team2);
        Match match2 = new Match(team2, team3);
        Match match3 = new Match(team3, team1);

        match1.updateResult(0, 0);
        match2.updateResult(0, 0);
        match3.updateResult(0, 0);

        List<String> expectedStandings = new ArrayList<>();
        expectedStandings.add(String.format("%-25s%-10s%-10s%-10s%-10s%-10s", "Team", "Matches", "Points", "Wins",
                "Losses","Draws"));
        expectedStandings.add(String.format("%-25s%-10d%-10d%-10d%-10d%-10d", "Team 1", 2, 2, 0, 0, 2));
        expectedStandings.add(String.format("%-25s%-10d%-10d%-10d%-10d%-10d", "Team 2", 2, 2, 0, 0, 2));
        expectedStandings.add(String.format("%-25s%-10d%-10d%-10d%-10d%-10d", "Team 3", 2, 2, 0, 0, 2));

        assertEquals(expectedStandings, league.getStandings());
    }
}