package pmf.android.sportscoresbysbm2.model;

import com.google.gson.annotations.SerializedName;

public class Coverage {
    @SerializedName("fixtures")
    private Fixtures fixtures;
    @SerializedName("standings")
    private boolean standings;
    @SerializedName("players")
    private boolean players;
    @SerializedName("top_scorers")
    private boolean topScorers;
    @SerializedName("top_assists")
    private boolean topAssists;
    @SerializedName("injuries")
    private boolean injuries;
    @SerializedName("predictions")
    private boolean predictions;
    @SerializedName("odds")
    private boolean odds;
    @SerializedName("top_cards")
    private boolean topCards;

    public Fixtures getFixtures() {
        return fixtures;
    }

    public void setFixtures(Fixtures fixtures) {
        this.fixtures = fixtures;
    }

    public boolean isStandings() {
        return standings;
    }

    public void setStandings(boolean standings) {
        this.standings = standings;
    }

    public boolean isPlayers() {
        return players;
    }

    public void setPlayers(boolean players) {
        this.players = players;
    }

    public boolean isTopScorers() {
        return topScorers;
    }

    public void setTopScorers(boolean topScorers) {
        this.topScorers = topScorers;
    }

    public boolean isTopAssists() {
        return topAssists;
    }

    public void setTopAssists(boolean topAssists) {
        this.topAssists = topAssists;
    }

    public boolean isInjuries() {
        return injuries;
    }

    public void setInjuries(boolean injuries) {
        this.injuries = injuries;
    }

    public boolean isPredictions() {
        return predictions;
    }

    public void setPredictions(boolean predictions) {
        this.predictions = predictions;
    }

    public boolean isOdds() {
        return odds;
    }

    public void setOdds(boolean odds) {
        this.odds = odds;
    }

    public boolean isTopCards() {
        return topCards;
    }

    public void setTopCards(boolean topCards) {
        this.topCards = topCards;
    }

}
