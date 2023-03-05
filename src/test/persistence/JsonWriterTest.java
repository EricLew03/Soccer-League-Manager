package persistence;


import model.League;
import model.Match;
import model.MatchRecords;
import model.Team;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json",
                    "./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testEmptyLeague() {
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            League league = new League();
            MatchRecords matches = new MatchRecords();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLeague.json",
                    "./data/testWriterEmptyMatches.json");
            writer.open();
            writer.writeLeague(league);
            writer.writeMatch(matches);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLeague.json",
                    "./data/testWriterEmptyMatches.json");
            League league1 = reader.readLeague();
            MatchRecords match = reader.readMatches(league1);
            assertEquals(0, league1.numTeam());
            assertEquals(0, match.numMatches());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            League league = new League();
            MatchRecords matches = new MatchRecords();
            Team teamA = new Team("AAA");
            Team teamB = new Team("BBB");
            Team teamC = new Team("CCC");
            Match match1 = new Match(teamA, teamB);
            Match match2 = (new Match(teamA, teamC));
            league.addTeam(teamA);
            league.addTeam(teamB);
            league.addTeam(teamC);
            matches.addMatch(match1);
            matches.addMatch(match2);
            match1.changeHomeScore(1);
            match2.changeHomeScore(7);
            match1.updateResult(match1.getHomeGoals(),match1.getAwayGoals());
            match2.updateResult(match2.getHomeGoals(),match2.getAwayGoals());

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLeague.json",
                    "./data/testWriterGeneralMatches.json");
            writer.open();
            writer.writeLeague(league);
            writer.writeMatch(matches);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLeague.json",
                    "./data/testWriterGeneralMatches.json");
            League league2 = reader.readLeague();
            MatchRecords matchRecords= reader.readMatches(league2);

            assertEquals(3, league2.numTeam());
            assertEquals(2, matchRecords.numMatches());
            List<Team> teams = league.getTeams();
            List<Match> records = matches.getMatchRecords();
            checkTeam("AAA", 2, 2, 0, 0 , 6, teams.get(0) );
            checkTeam("BBB", 1, 0, 0 , 1, 0,teams.get(1) );
            checkTeam("CCC", 1, 0, 0, 1 , 0, teams.get(2) );
            checkMatch("AAA", "BBB", 1,0, records.get(0));
            checkMatch("AAA","CCC", 7, 0, records.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
