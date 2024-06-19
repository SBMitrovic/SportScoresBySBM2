package pmf.android.sportscoresbysbm2.model;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NonNls;

import java.util.List;


public class Competition {


        @PrimaryKey
        @NonNull
        private Long id;
        @SerializedName("league")
        private League  league;
        @SerializedName("country")
        private Country country;
        @SerializedName("seasons")
        // Ili ovako ili embeded @TypeConverter(Season.class)
        private List<Season> seasons;



    public Competition(League league, Country country, List<Season> seasons, Long id) {
            this.id = this.league.getId();
            this.league = league;
            this.country = country;
            this.seasons = seasons;
        }


        public Competition() {
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

    @NonNull
    public Long getId() {
        return this.league.getId();
    }
}

