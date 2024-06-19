package pmf.android.sportscoresbysbm2.model.database.converters;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import pmf.android.sportscoresbysbm2.model.League;
import pmf.android.sportscoresbysbm2.model.StandingsResponse;

public class LeaguesWithStandings {
    @Embedded public League league;

    @Relation(parentColumn = "id", entityColumn = "league_id")
    public List<List<StandingsResponse.Standing>> standings;
}
