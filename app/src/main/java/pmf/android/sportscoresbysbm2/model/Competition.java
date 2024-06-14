package pmf.android.sportscoresbysbm2.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;


public class Competition {


        @SerializedName("league")
        private League league;
        @SerializedName("country")
        private Country country;
        @SerializedName("seasons")
        private List<Season> seasons;

        public Competition(League league, Country country, List<Season> seasons) {
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

