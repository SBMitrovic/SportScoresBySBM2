package pmf.android.sportscoresbysbm2.utilities;

import java.util.ArrayList;

import pmf.android.sportscoresbysbm2.model.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @Headers("x-rapidapi-key:" + APICredentials.API_KEY)
    @GET("leagues")
    Call<LeaguesByCountry> getLeaguesByCountry(@Query("country") String country);

    @Headers("x-rapidapi-key:"+APICredentials.API_KEY)
    @GET("countries")
    Call<CountryList> getCountries();


}
