package pmf.android.sportscoresbysbm2.utilities;

import java.util.ArrayList;
import java.util.List;

import pmf.android.sportscoresbysbm2.model.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @Headers({"x-rapidapi-key:" + APICredentials.API_KEY, "Cache-Control: public, max-age=3600"})
    @GET("leagues")
    Call<CompetitionsByCountry> getCompetitionsByCountry(@Query("country") String country);

    @Headers({"x-rapidapi-key:" + APICredentials.API_KEY, "Cache-Control: public, max-age=3600"})
    @GET("countries")
    Call<CountryList> getCountries();

    @Headers("x-rapidapi-key:"+APICredentials.API_KEY)
    @GET("standings")
    Call<StandingsResponse> getStandingsByLeague(@Query("league") String league, @Query("season") String season);

}
