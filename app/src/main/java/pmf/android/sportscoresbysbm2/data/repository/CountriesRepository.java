package pmf.android.sportscoresbysbm2.data.repository;

import pmf.android.sportscoresbysbm2.data.model.CountryList;
import pmf.android.sportscoresbysbm2.utilities.APIFootballInterface;
import pmf.android.sportscoresbysbm2.utilities.RetrofitMaker;
import pmf.android.sportscoresbysbm2.utilities.APICredentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountriesRepository {

    private APIFootballInterface mApiFootballInterface;
    private RetrofitMaker retrofitMaker;

    public CountriesRepository() {
        mApiFootballInterface = RetrofitMaker.getRetrofit();
    }


    public void fetchCountriesList() {
        mApiFootballInterface.getCountries().enqueue(new Callback<CountryList>(){
            @Override
            public void onResponse(Call<CountryList> call, Response<CountryList> response) {
                if (response.code() == 200) {
                    CountryList countryList = response.body();
                }
            }
            @Override
            public void onFailure(Call<CountryList> call, Throwable t) {

            }
        });
    }

}
