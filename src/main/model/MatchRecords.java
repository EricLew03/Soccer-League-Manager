package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// represents the all the matches added
public class MatchRecords implements Writable {
    private List<Match> matchRecords;

    // EFFECTS: constructs an empty list of matches
    public MatchRecords() {
        this.matchRecords = new ArrayList<>();
    }

    // EFFECTS : return the number of matches recorded
    public Integer numMatches() {
        return matchRecords.size();
    }

    // MODIFIES: this
    // EFFECTS: adds the given match to the list of matches
    public void addMatch(Match match) {
        matchRecords.add(match);
    }

    // EFFECTS: returns the list of matches
    public List<Match> getMatchRecords() {
        return matchRecords;
    }


    // EFFECTS: returns a list of all matches involving the given team
    public List<Match> getMatchesForTeam(Team team) {
        List<Match> matchesForTeam = new ArrayList<>();
        for (Match match : matchRecords) {
            if (match.getHomeTeam().equals(team) || match.getAwayTeam().equals(team)) {
                matchesForTeam.add(match);
            }
        }
        return matchesForTeam;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("matches", matchesToJson());

        return json;
    }

    // EFFECTS : returns the matches in this match records as a JSON array
    private JSONArray matchesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Match m : matchRecords) {
            jsonArray.put((m.toJson()));
        }
        return jsonArray;
    }
}
