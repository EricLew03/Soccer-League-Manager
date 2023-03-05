package persistence;


import model.League;
import model.Match;
import model.MatchRecords;

import model.Team;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;



import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

        @Test
        void testReaderNonExistentFile() {
            JsonReader reader = new JsonReader("./data/noSuchFile.json", "./data/noSuchFile.json");
            try {
               League league = reader.readLeague();
               MatchRecords matches = reader.readMatches(league);
            } catch (IOException e) {
                // pass
            }
        }

        @Test
        void testReaderEmptyLeagueFiles() {
            JsonReader reader = new JsonReader("./data/testReaderEmptyLeague.json",
                    "./data/testReaderEmptyMatches.json");
            try {
                League league = reader.readLeague();
                MatchRecords matches = reader.readMatches(league);
                assertEquals(0, league.numTeam());
                assertEquals(0, matches.numMatches());
            } catch (IOException e) {
                fail("Couldn't read from file");
            }
        }

        @Test
        void testReaderGeneralFiles() {
            JsonReader reader = new JsonReader("./data/testReaderGeneralLeague.json",
                        "./data/testReaderGeneralMatches.json");
            try {
                League league = reader.readLeague();
                MatchRecords matches = reader.readMatches(league);
                assertEquals(3, league.numTeam());
                assertEquals(2, matches.numMatches());
                List<Team> teams = league.getTeams();
                List<Match> records = matches.getMatchRecords();
                checkTeam("aaa", 2, 0, 0, 2 , 0, teams.get(0) );
                checkTeam("bbb", 1, 1, 0 , 0, 3,teams.get(1) );
                checkTeam("ccc", 1, 1, 0, 0 , 3, teams.get(2) );
                checkMatch("aaa", "bbb", 1,2, records.get(0));
                checkMatch("ccc","aaa", 2, 1, records.get(1));
                } catch (IOException e) {
                    fail("Couldn't read from file");
                }
        }


}

