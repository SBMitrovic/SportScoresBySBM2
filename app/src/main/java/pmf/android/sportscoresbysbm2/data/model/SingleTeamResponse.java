package pmf.android.sportscoresbysbm2.data.model;

import java.util.List;

public class SingleTeamResponse {

    private List<SingleTeam> response;

    public void setResponse(List<SingleTeam> response) {
        this.response = response;
    }

    public List<SingleTeam> getResponse() {
        return this.response;
    }

    public class SingleTeam {
        private StandingsResponse.Team team;
        private Venue venue;

        public StandingsResponse.Team getTeam() {
            return team;
        }

        public void setTeam(StandingsResponse.Team team) {
            this.team = team;
        }

        public Venue getVenue() {
            return venue;
        }

        public void setVenue(Venue venue) {
            this.venue = venue;
        }

    }


    public class Venue {
        private Long id;
        private String name;
        private String address;
        private String city;
        private Long capacity;
        private String surface;
        private String image;

        public Venue(Long id, String name, String address, String city, Long capacity, String surface, String image) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.city = city;
            this.capacity = capacity;
            this.surface = surface;
            this.image = image;
        }

        public Long getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        public String getAddress() {
            return this.address;
        }

        public String getCity() {
            return this.city;
        }

        public Long getCapacity() {
            return this.capacity;
        }

        public String getSurface() {
            return this.surface;
        }

        public String getImage() {
            return this.image;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setCapacity(Long capacity) {
            this.capacity = capacity;
        }

        public void setSurface(String surface) {
            this.surface = surface;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }


}
