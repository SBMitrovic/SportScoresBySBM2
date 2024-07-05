package pmf.android.sportscoresbysbm2.util;

import pmf.android.sportscoresbysbm2.data.model.CompetitionsResponse;
import pmf.android.sportscoresbysbm2.data.model.CountriesResponse;
import pmf.android.sportscoresbysbm2.data.model.SingleTeamResponse;
import pmf.android.sportscoresbysbm2.data.model.StandingsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIFootballInterface {


    @Headers({"x-rapidapi-key:" + APICredentials.API_KEY, "Cache-Control: public, max-age=3600"})
    @GET("countries")
    Call<CountriesResponse> getCountries();


    @Headers({"x-rapidapi-key:" + APICredentials.API_KEY, "Cache-Control: public, max-age=3600"})
    @GET("leagues")
    Call<CompetitionsResponse> getCompetitionsByCountry(@Query("country") String country);



    @Headers("x-rapidapi-key:"+APICredentials.API_KEY)
    @GET("standings")
    Call<StandingsResponse> getStandingsByLeague(@Query("league") long leagueId, @Query("season") String season);

    @Headers("x-rapidapi-key:"+APICredentials.API_KEY)
    @GET("teams")
    Call<SingleTeamResponse> getSingleTeam(@Query("id") long teamId);
}
