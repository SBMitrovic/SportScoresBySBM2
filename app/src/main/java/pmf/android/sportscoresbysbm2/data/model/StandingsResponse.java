package pmf.android.sportscoresbysbm2.data.model;



import androidx.room.Ignore;

import java.util.List;

public class StandingsResponse {
    private String get;
    private Long results;
    public List<Response> response;

    public StandingsResponse(String get, Long results, List<Response> response) {
        this.get = get;
        this.results = results;
        this.response = response;
    }

    public String getGet() {
        return this.get;
    }


    public Long getResults() {
        return this.results;
    }


    public List<Response> getResponse() {
        return this.response;
    }

    public void setGet(String get) {
        this.get = get;
    }


    public void setResults(Long results) {
        this.results = results;
    }


    public void setResponse(List<Response> response) {
        this.response = response;
    }


    /*
    public boolean haveStandings(){
        return this.getResponse().get(0).getLeague().getStandings().get
    }

    */

    public class Response {
        private League league;

        public Response(League league) {
            this.league = league;
        }

        public League getLeague() {
            return this.league;
        }

        public void setLeague(League league) {
            this.league = league;
        }

    }




    public class Standing {
        private Long rank;
        private Team team;
        private Long points;
        private Long goalsDiff;
        private String group;
        private String form;
        private String status;
        private String description;
        private All all;
        private Home home;
        private Away away;
        private String update;

        @Ignore
        public Standing(Long rank, Team team, Long points, Long goalsDiff, String group, String form, String status, String description, All all, Home home, Away away, String update) {
            this.rank = rank;
            this.team = team;
            this.points = points;
            this.goalsDiff = goalsDiff;
            this.group = group;
            this.form = form;
            this.status = status;
            this.description = description;
            this.all = all;
            this.home = home;
            this.away = away;
            this.update = update;
        }

        public Long getRank() {
            return this.rank;
        }

        public Team getTeam() {
            return this.team;
        }

        public Long getPoints() {
            return this.points;
        }

        public Long getGoalsDiff() {
            return this.goalsDiff;
        }

        public String getGroup() {
            return this.group;
        }

        public String getForm() {
            return this.form;
        }

        public String getStatus() {
            return this.status;
        }

        public String getDescription() {
            return this.description;
        }

        public All getAll() {
            return this.all;
        }

        public Home getHome() {
            return this.home;
        }

        public Away getAway() {
            return this.away;
        }

        public String getUpdate() {
            return this.update;
        }

        public void setRank(Long rank) {
            this.rank = rank;
        }

        public void setTeam(Team team) {
            this.team = team;
        }

        public void setPoints(Long points) {
            this.points = points;
        }

        public void setGoalsDiff(Long goalsDiff) {
            this.goalsDiff = goalsDiff;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public void setForm(String form) {
            this.form = form;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setAll(All all) {
            this.all = all;
        }

        public void setHome(Home home) {
            this.home = home;
        }

        public void setAway(Away away) {
            this.away = away;
        }

        public void setUpdate(String update) {
            this.update = update;
        }
    }

    public class Team {
        private Long id;
        private String name;
        private String logo;
        private boolean national;
        private int founded;
        private String country;

        public Team(Long id, String name, String logo, boolean national, int founded, String country) {
            this.id = id;
            this.name = name;
            this.logo = logo;
            this.national = national;
            this.founded = founded;
            this.country = country;
        }


        public Team(Long id, String name, String logo) {
            this.id = id;
            this.name = name;
            this.logo = logo;
        }

        public Long getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        public String getLogo() {
            return this.logo;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getFounded() {
            return founded;
        }

        public void setFounded(int founded) {
            this.founded = founded;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public boolean isNational() {
            return national;
        }

        public void setNational(boolean national) {
            this.national = national;
        }
    }

    public  class All {
        private Long played;
        private Long win;
        private Long draw;
        private Long lose;
        private Goals goals;

        public All(Long played, Long win, Long draw, Long lose, Goals goals) {
            this.played = played;
            this.win = win;
            this.draw = draw;
            this.lose = lose;
            this.goals = goals;
        }

        public Long getPlayed() {
            return this.played;
        }

        public Long getWin() {
            return this.win;
        }

        public Long getDraw() {
            return this.draw;
        }

        public Long getLose() {
            return this.lose;
        }

        public Goals getGoals() {
            return this.goals;
        }

        public void setPlayed(Long played) {
            this.played = played;
        }

        public void setWin(Long win) {
            this.win = win;
        }

        public void setDraw(Long draw) {
            this.draw = draw;
        }

        public void setLose(Long lose) {
            this.lose = lose;
        }

        public void setGoals(Goals goals) {
            this.goals = goals;
        }
    }

    public class Goals {
        private Long for_field;
        private Long against;

        public Goals(Long for_field, Long against) {
            this.for_field = for_field;
            this.against = against;
        }

        public Long getFor_field() {
            return this.for_field;
        }

        public Long getAgainst() {
            return this.against;
        }

        public void setFor_field(Long for_field) {
            this.for_field = for_field;
        }

        public void setAgainst(Long against) {
            this.against = against;
        }
    }

    public  class Home {
        private Long played;
        private Long win;
        private Long draw;
        private Long lose;
        private Goals2 goals;

        public Home(Long played, Long win, Long draw, Long lose, Goals2 goals) {
            this.played = played;
            this.win = win;
            this.draw = draw;
            this.lose = lose;
            this.goals = goals;
        }

        public Long getPlayed() {
            return this.played;
        }

        public Long getWin() {
            return this.win;
        }

        public Long getDraw() {
            return this.draw;
        }

        public Long getLose() {
            return this.lose;
        }

        public Goals2 getGoals() {
            return this.goals;
        }

        public void setPlayed(Long played) {
            this.played = played;
        }

        public void setWin(Long win) {
            this.win = win;
        }

        public void setDraw(Long draw) {
            this.draw = draw;
        }

        public void setLose(Long lose) {
            this.lose = lose;
        }

        public void setGoals(Goals2 goals) {
            this.goals = goals;
        }
    }

    public class Goals2 {
        private Long for_field;
        private Long against;

        public Goals2(Long for_field, Long against) {
            this.for_field = for_field;
            this.against = against;
        }

        public Long getFor_field() {
            return this.for_field;
        }

        public Long getAgainst() {
            return this.against;
        }

        public void setFor_field(Long for_field) {
            this.for_field = for_field;
        }

        public void setAgainst(Long against) {
            this.against = against;
        }
    }

    public class Away {
        private Long played;
        private Long win;
        private Long draw;
        private Long lose;
        private Goals3 goals;

        public Away(Long played, Long win, Long draw, Long lose, Goals3 goals) {
            this.played = played;
            this.win = win;
            this.draw = draw;
            this.lose = lose;
            this.goals = goals;
        }

        public Long getPlayed() {
            return this.played;
        }

        public Long getWin() {
            return this.win;
        }

        public Long getDraw() {
            return this.draw;
        }

        public Long getLose() {
            return this.lose;
        }

        public Goals3 getGoals() {
            return this.goals;
        }

        public void setPlayed(Long played) {
            this.played = played;
        }

        public void setWin(Long win) {
            this.win = win;
        }

        public void setDraw(Long draw) {
            this.draw = draw;
        }

        public void setLose(Long lose) {
            this.lose = lose;
        }

        public void setGoals(Goals3 goals) {
            this.goals = goals;
        }
    }

    public class Goals3 {
        private Long for_field;
        private Long against;

        public Goals3(Long for_field, Long against) {
            this.for_field = for_field;
            this.against = against;
        }

        public Long getFor_field() {
            return this.for_field;
        }

        public Long getAgainst() {
            return this.against;
        }

        public void setFor_field(Long for_field) {
            this.for_field = for_field;
        }

        public void setAgainst(Long against) {
            this.against = against;
        }
    }

}
