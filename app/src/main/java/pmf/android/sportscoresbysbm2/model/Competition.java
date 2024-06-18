package pmf.android.sportscoresbysbm2.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NonNls;

import java.util.List;

@Entity(tableName = "competition")
public class Competition {
    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

        @PrimaryKey
        @NonNull
        private Long id;
        @SerializedName("league")
        private League  league;
        @SerializedName("country")
        private Country country;
        @SerializedName("seasons")
        private List<Season> seasons;

        public Competition(League league, Country country, List<Season> seasons) {
            this.id = this.league.getId();
            this.league = league;
            this.country = country;
            this.seasons = seasons;
        }

        public League getLeague() {
            return league;
        }

        public void setLeague(League league) {
            this.league = league;
        }

        public Country getCountry() {
            return country;
        }

        public void setCountry(Country country) {
            this.country = country;
        }

        public List<Season> getSeasons() {
            return seasons;
        }

        public void setSeasons(List<Season> seasons) {
            this.seasons = seasons;
        }
    }

