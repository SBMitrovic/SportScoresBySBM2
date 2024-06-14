package pmf.android.sportscoresbysbm2.model;

import com.google.gson.annotations.SerializedName;

public class League {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("type")
    private String type;
    @SerializedName("logo")
    private String logoUrlLocation;

    @SerializedName("country")
    private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getFlagUrlLocation() {
        return flagUrlLocation;
    }

    public void setFlagUrlLocation(String flagUrlLocation) {
        this.flagUrlLocation = flagUrlLocation;
    }

    @SerializedName("flag")
    private String flagUrlLocation;

    public League(int id, String name, String type, String logoUrlLocation) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.logoUrlLocation = logoUrlLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogoUrlLocation() {
        return logoUrlLocation;
    }

    public void setLogoUrlLocation(String logoUrlLocation) {
        this.logoUrlLocation = logoUrlLocation;
    }


}
