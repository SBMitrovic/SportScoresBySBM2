package pmf.android.sportscoresbysbm2.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompetitionsResponse {

    @SerializedName("response")
    private List<Competition> competitions;

    public CompetitionsResponse(List<Competition> competition) {
        this.competitions= competition;
    }

    public void setCompetitions(List<Competition> competition) {
        this.competitions = competition;
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }


}








