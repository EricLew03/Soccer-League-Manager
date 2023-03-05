package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.League;
import model.Match;
import model.MatchRecords;
import model.Team;
import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String leagueSource;
    private String matchSource;


    // EFFECTS: constructs reader to read from source file
    public JsonReader(String leagueSource, String matchSource) {
        this.leagueSource = leagueSource;
        this.matchSource = matchSource;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public League readLeague() throws IOException {
        String jsonData = readFile(leagueSource);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLeague(jsonObject);
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MatchRecords readMatches() throws IOException {
        String jsonData = readFile(matchSource);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMatches(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    public League parseLeague(JSONObject jsonObject) {
        League league = new League();
        addTeams(league, jsonObject);
        return league;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addTeams(League league, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("league");
        for (Object json : jsonArray) {
            JSONObject nextTeam = (JSONObject) json;
            addTeam(league, nextTeam);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
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
        team.setLosses(losses);;
        team.setDraws(draws);
        team.setPoints(points);;
        league.addTeam(team);
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private MatchRecords parseMatches(JSONObject jsonObject) throws IOException {
        MatchRecords matchRecords = new MatchRecords();
        addMatches(matchRecords, jsonObject);
        return matchRecords;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addMatches(MatchRecords matches, JSONObject jsonObject) throws IOException {
        JSONArray jsonArray = jsonObject.getJSONArray("matches");
        for (Object json : jsonArray) {
            JSONObject nextMatch = (JSONObject) json;
            addMatch(matches, nextMatch);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addMatch(MatchRecords matches, JSONObject jsonObject) throws IOException {
        String homeTeam = jsonObject.getString("homeTeam");
        String awayTeam = jsonObject.getString("awayTeam");
        Integer homeGoals = jsonObject.getInt("homeGoals");
        Integer awayGoals = jsonObject.getInt("awayGoals");

        JsonReader reader = new JsonReader(leagueSource, matchSource);
        League league = reader.readLeague();

        Team home = league.findTeam(homeTeam);
        Team away = league.findTeam(awayTeam);

        Match match = new Match(home, away);
        match.changeHomeScore(homeGoals);
        match.changeAwayScore(awayGoals);

        matches.addMatch(match);
    }

}

