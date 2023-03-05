package persistence;

import model.League;
import model.MatchRecords;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private PrintWriter writer2;
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
        writer = new PrintWriter(new File(leagueDestination));
        writer2 = new PrintWriter(new File(matchDestination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void writeLeague(League league) {
        JSONObject json = league.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void writeMatch(MatchRecords matches) {
        JSONObject json = matches.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
        writer2.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
        writer2.print(json);
    }
}
