package pmf.android.sportscoresbysbm2.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import pmf.android.sportscoresbysbm2.data.model.CountriesResponse;
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



    public LiveData<CountriesResponse> fetchCountries() {
        final MutableLiveData<CountriesResponse> data = new MutableLiveData<>();
        mApiFootballInterface.getCountries().enqueue(new Callback<CountriesResponse>() {
            @Override
            public void onResponse(Call<CountriesResponse> call, Response<CountriesResponse> response) {
                Log.i(LOG, "Response successfull");
                data.setValue(response.body());
                Log.i("Country name[1] : ", response.body().getResponse().get(1).getName());
            }

            @Override
            public void onFailure(Call<CountriesResponse> call, Throwable throwable) {
                    Log.e(LOG, throwable.getMessage());
                    data.setValue(null);
            }
        });
        return data;
    }



}