package pmf.android.sportscoresbysbm2.data.model;

import androidx.annotation.NonNull;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class League {
    @PrimaryKey
    private Long id;
    private String name;
    private String country;
    private String logo;
    private String flag;
    private Long season;
    private List<List<StandingsResponse.Standing>> standings;

    private int standingsInnerListSize;

    @Ignore
    public League(@NonNull Long id, String name, String country, String logo, String flag, Long season, List<List<StandingsResponse.Standing>> standings) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.logo = logo;
        this.flag = flag;
        this.season = season;
        this.standings = standings;
    }

    public League() {
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

    public List<StandingsResponse.Standing> getStandingsSimple() {
        int counter = 0;
        List<StandingsResponse.Standing> list = new java.util.ArrayList<>();

        for (int i = 0; i < standings.size(); i++) {
            for (int j = 0; j < standings.get(i).size(); j++) {
                list.add(standings.get(i).get(j));
                counter++;
            }
        }
        standingsInnerListSize = counter;
        return list;
    }

    public Map<String, List<StandingsResponse.Standing>> experiment() {
        Map<String, List<StandingsResponse.Standing>> map = new HashMap<>();
        for(int i = 0 ; i < standings.size(); i++){
            map.put("list" + i + "" , standings.get(i));
        }
        return map;
    }
    public void printSomething(Map<String, List<League>> map)
    {
        for (int i = 0 ; i < map.size(); i++){
            System.out.println(map.get("list" + i + "").get(i).getName());
        }
    }

}