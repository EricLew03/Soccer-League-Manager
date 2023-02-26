package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeamRecords {
    private List<Match> matches;

    public TeamRecords() {
        this.matches = new ArrayList<>();
    }

    public void addMatch(Match match) {
        matches.add(match);
    }

    public List<Match> getMatches() {
        return Collections.unmodifiableList(matches);
    }

    public List<Match> getMatchesForTeam(Team team) {
        List<Match> teamMatches = new ArrayList<>();
        for (Match match : matches) {
            if (match.getHomeTeam().equals(team) || match.getAwayTeam().equals(team)) {
                teamMatches.add(match);
            }
        }
        return teamMatches;
    }
}
