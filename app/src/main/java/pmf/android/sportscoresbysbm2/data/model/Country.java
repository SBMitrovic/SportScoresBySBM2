package pmf.android.sportscoresbysbm2.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import  com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("name")
    private String name;
    @SerializedName("code")
    private String code;
    @SerializedName("flag")
    private String flagUrlLocation;

    @Ignore
    public Country(@NonNull String name, String code, String flagUrlLocation) {
        this.name = name;
        this.code = code;
        this.flagUrlLocation = flagUrlLocation;
    }

    public Country() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFlagUrlLocation() {
        return flagUrlLocation;
    }

    public void setFlagUrlLocation(String flagUrlLocation) {
        this.flagUrlLocation = flagUrlLocation;
    }



    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", flagUrlLocation='" + flagUrlLocation + '\'' +
                '}';
    }
}