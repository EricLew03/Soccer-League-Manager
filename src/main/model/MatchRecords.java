package model;

import javax.lang.model.element.Name;
import java.util.ArrayList;
import java.util.List;

public class MatchRecords {
    private List<Match> matchRecords;

    // EFFECTS : construct a list of all the match that occurred
    public MatchRecords() {
        this.matchRecords = new ArrayList<>();
    }

    // MODIFIES : this
    // EFFECTS : add a new match to the list of match
    public void addMatch(Match match) {
        matchRecords.add(match);
    }

    // EFFECTS : return the match records
    public List<Match> getMatchRecords() {
        return matchRecords;
    }

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
