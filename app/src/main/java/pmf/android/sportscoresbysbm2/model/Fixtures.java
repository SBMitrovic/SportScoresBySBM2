package pmf.android.sportscoresbysbm2.model;

import com.google.gson.annotations.SerializedName;

public class Fixtures {

        @SerializedName("events")
        private boolean events;
        @SerializedName("lineups")
        private boolean lineups;
        @SerializedName("statistics_fixtures")
        private boolean statisticsFixtures;
        @SerializedName("statistics_players")
        private boolean statisticsPlayers;

        public boolean isEvents() {
            return events;
        }

        public void setEvents(boolean events) {
            this.events = events;
        }

        public boolean isLineups() {
            return lineups;
        }

        public void setLineups(boolean lineups) {
            this.lineups = lineups;
        }

        public boolean isStatisticsFixtures() {
            return statisticsFixtures;
        }

        public void setStatisticsFixtures(boolean statisticsFixtures) {
            this.statisticsFixtures = statisticsFixtures;
        }

        public boolean isStatisticsPlayers() {
            return statisticsPlayers;
        }

        public void setStatisticsPlayers(boolean statisticsPlayers) {
            this.statisticsPlayers = statisticsPlayers;
        }
    }






