package pmf.android.sportscoresbysbm2.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompetitionsByCountry {

    @SerializedName("response")
    private List<Competition> competitions;

    public CompetitionsByCountry(List<Competition> competition) {
        this.competitions= competition;
    }

    public void setCompetitions(List<Competition> competition) {
        this.competitions = competition;
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }


}








