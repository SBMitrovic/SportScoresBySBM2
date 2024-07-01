package pmf.android.sportscoresbysbm2.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountriesResponse {

    @SerializedName("response")
    private List<Country> response;

    public CountriesResponse(List<Country> response) {
        this.response = response;
    }

    public List<Country> getResponse() {
        return this.response;
    }

    public void setResponse(List<Country> response) {
        this.response = response;
    }





}


