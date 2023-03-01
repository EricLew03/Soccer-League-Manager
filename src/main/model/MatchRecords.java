package model;


import java.util.ArrayList;
import java.util.List;

// represents the all the matches added
public class MatchRecords {
    private List<Match> matchRecords;

    // EFFECTS: constructs an empty list of matches
    public MatchRecords() {
        this.matchRecords = new ArrayList<>();
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
}
