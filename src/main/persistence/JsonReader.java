package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.*;
import org.json.*;

// Used json code as template from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.
// Represents a reader that reads the league and matches from JSON data stored in file
public class JsonReader {
    private String leagueSource;
    private String matchSource;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String leagueSource, String matchSource) {
        this.leagueSource = leagueSource;
        this.matchSource = matchSource;
    }

    // EFFECTS: reads league from file and returns it;
    // throws IOException if an error occurs reading data from file
    public League readLeague() throws IOException {
        EventLog.getInstance().logEvent(new Event("The User loaded the saved league"));
        String jsonData = readFile(leagueSource);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLeague(jsonObject);
    }

    // EFFECTS: reads match records from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MatchRecords readMatches(League league) throws IOException {
        EventLog.getInstance().logEvent(new Event("The User loaded the saved match records"));
        String jsonData = readFile(matchSource);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMatches(jsonObject, league);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses league from JSON object and returns it
    public League parseLeague(JSONObject jsonObject) {
        League league = new League();
        addTeams(league, jsonObject);
        return league;
    }

    // MODIFIES: league
    // EFFECTS: parses teams from JSON object and adds them to league
    private void addTeams(League league, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("league");
        for (Object json : jsonArray) {
            JSONObject nextTeam = (JSONObject) json;
            addTeam(league, nextTeam);
        }
    }

    // MODIFIES: league
    // EFFECTS: parses team from JSON object and adds it to league
    private void addTeam(League league, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Integer played = jsonObject.getInt("played");
        Integer wins = jsonObject.getInt("wins");
        Integer draws = jsonObject.getInt("draws");
        Integer losses = jsonObject.getInt("losses");
        Integer points = jsonObject.getInt("points");

        Team team = new Team(name);
        team.setMatchesPlayed(played);
        team.setWins(wins);
        team.setLosses(losses);
        team.setDraws(draws);
        team.setPoints(points);
        league.addTeam(team);
    }

    // EFFECTS: parses match records from JSON object and returns it
    private MatchRecords parseMatches(JSONObject jsonObject, League league) throws IOException {
        MatchRecords matchRecords = new MatchRecords();
        addMatches(matchRecords, jsonObject, league);
        return matchRecords;
    }

    // MODIFIES: matchRecords
    // EFFECTS: parses matches from JSON object and adds them to matchRecords
    private void addMatches(MatchRecords matchRecords, JSONObject jsonObject, League league) throws IOException {
        JSONArray jsonArray = jsonObject.getJSONArray("matches");
        for (Object json : jsonArray) {
            JSONObject nextMatch = (JSONObject) json;
            addMatch(matchRecords, nextMatch, league);
        }
    }

    // MODIFIES: matches
    // EFFECTS: parses match from JSON object and adds it to matches
    private void addMatch(MatchRecords matches, JSONObject jsonObject, League league) throws IOException {
        String homeTeamName = jsonObject.getString("homeTeam");
        String awayTeamName = jsonObject.getString("awayTeam");
        Integer homeGoals = jsonObject.getInt("homeGoals");
        Integer awayGoals = jsonObject.getInt("awayGoals");

        Team home = league.getTeamByName(homeTeamName);
        Team away = league.getTeamByName(awayTeamName);

        Match match = new Match(home, away);
        match.changeHomeScore(homeGoals);
        match.changeAwayScore(awayGoals);

        matches.addMatch(match);
    }
}
