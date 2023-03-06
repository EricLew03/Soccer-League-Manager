package persistence;

import model.League;
import model.MatchRecords;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Used json code as template from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.
// Represents a writer that write the league and matches from JSON data stored in file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter leagueWriter;
    private PrintWriter matchWriter;
    private String leagueDestination;
    private String matchDestination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String leagueDestination, String matchDestination) {
        this.leagueDestination = leagueDestination;
        this.matchDestination = matchDestination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        leagueWriter = new PrintWriter(new File(leagueDestination));
        matchWriter = new PrintWriter(new File(matchDestination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of league to file
    public void writeLeague(League league) {
        JSONObject json = league.toJson();
        saveLeagueToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of match records to file
    public void writeMatch(MatchRecords matches) {
        JSONObject json = matches.toJson();
        saveMatchToFile(json.toString(TAB));

    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        leagueWriter.close();
        matchWriter.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to league file
    private void saveLeagueToFile(String json) {
        leagueWriter.print(json);
    }

    // MODIFIES: this
    // EFFECTS: writes string to match file
    private void saveMatchToFile(String json) {
        matchWriter.print(json);
    }
}