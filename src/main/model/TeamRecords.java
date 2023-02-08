package model;

import java.util.ArrayList;

public class TeamRecords {
    private ArrayList<Match>  matchRecords;

    public TeamRecords() {
        this.matchRecords = new ArrayList<>();
    }

    public void addMatch(Match match) {
        matchRecords.add(match);
    }

    public ArrayList<Match> getMatches() {
        return matchRecords;
    }

}
