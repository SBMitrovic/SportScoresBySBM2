package pmf.android.sportscoresbysbm2.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LeaguesByCountry {

    @SerializedName("response")
    private List<Competition> competition;

    public LeaguesByCountry(List<Competition> competition) {
        this.competition = competition;
    }

    public void setCompetition(List<Competition> competition) {
        this.competition = competition;
    }

    public List<Competition> getCompetition() {
        return competition;
    }


}








