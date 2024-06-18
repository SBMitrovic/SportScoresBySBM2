package pmf.android.sportscoresbysbm2.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "league")
public class League {
    @PrimaryKey
    @NonNull
    private Long id;
    private String name;
    private String country;
    private String logo;
    private String flag;
    private Long season;
    private List<List<StandingsResponse.Standing>> standings;

    public League(Long id, String name, String country, String logo, String flag, Long season, List<List<StandingsResponse.Standing>> standings) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.logo = logo;
        this.flag = flag;
        this.season = season;
        this.standings = standings;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCountry() {
        return this.country;
    }

    public String getLogo() {
        return this.logo;
    }

    public String getFlag() {
        return this.flag;
    }

    public Long getSeason() {
        return this.season;
    }

    public List<List<StandingsResponse.Standing>> getStandings() {
        return this.standings;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setSeason(Long season) {
        this.season = season;
    }

    public void setStandings(List<List<StandingsResponse.Standing>> standings) {
        this.standings = standings;
    }
}