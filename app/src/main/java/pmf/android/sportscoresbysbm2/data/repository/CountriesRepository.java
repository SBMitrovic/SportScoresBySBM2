package pmf.android.sportscoresbysbm2.data.repository;

import pmf.android.sportscoresbysbm2.utilities.APIFootballInterface;
import pmf.android.sportscoresbysbm2.utilities.RetrofitMaker;
import pmf.android.sportscoresbysbm2.utilities.APICredentials;

public class CountriesRepository {

    private APIFootballInterface apiInterface;
    private RetrofitMaker retrofitMaker;

    public CountriesRepository() {
        apiInterface = RetrofitMaker.getRetrofit();
    }
    

}
