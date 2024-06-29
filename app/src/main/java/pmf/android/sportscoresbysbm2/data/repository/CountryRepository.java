package pmf.android.sportscoresbysbm2.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import pmf.android.sportscoresbysbm2.data.model.CountryList;
import pmf.android.sportscoresbysbm2.util.APIFootballInterface;
import pmf.android.sportscoresbysbm2.util.RetrofitMaker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryRepository {

    private static final String LOG = "CountriesRepo";
    private static final Object LOCK = new Object();
    private APIFootballInterface mApiFootballInterface;
    private RetrofitMaker retrofitMaker;
    private static CountryRepository sInstance;


    private CountryRepository() {
        mApiFootballInterface = RetrofitMaker.getRetrofit();
    }

    public synchronized static CountryRepository getInstance() {
        Log.d(LOG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new CountryRepository();
                Log.d("CountriesRepo", "Made new repository");
            }
        }
        return sInstance;
    }



    public LiveData<CountryList> fetchCountriesList() {
        final MutableLiveData<CountryList> data = new MutableLiveData<>();
        mApiFootballInterface.getCountries().enqueue(new Callback<CountryList>() {
            @Override
            public void onResponse(Call<CountryList> call, Response<CountryList> response) {
                Log.i(LOG, "Response successfull");
                data.setValue(response.body());
                Log.i("Country name : ", response.body().getResponse().get(1).getName());
            }

            @Override
            public void onFailure(Call<CountryList> call, Throwable throwable) {
                    Log.e(LOG, throwable.getMessage());
                    data.setValue(null);
            }
        });
        return data;
    }



}