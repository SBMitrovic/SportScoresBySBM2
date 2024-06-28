package pmf.android.sportscoresbysbm2.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryList {

    @SerializedName("response")
    private List<Country> response;

    public CountryList(List<Country> response) {
        this.response = response;
    }

    public List<Country> getResponse() {
        return this.response;
    }

    public void setResponse(List<Country> response) {
        this.response = response;
    }





}


