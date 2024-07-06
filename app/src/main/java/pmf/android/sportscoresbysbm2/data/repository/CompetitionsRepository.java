package pmf.android.sportscoresbysbm2.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import pmf.android.sportscoresbysbm2.data.model.CompetitionsResponse;
import pmf.android.sportscoresbysbm2.util.APIFootballInterface;
import pmf.android.sportscoresbysbm2.util.RetrofitMaker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompetitionsRepository {

    private static final String LOG = "CompetitionsRepo";
    private static final Object LOCK = new Object();
    private APIFootballInterface mApiFootballInterface;
    private RetrofitMaker retrofitMaker;
    private static CompetitionsRepository sInstance;

    private CompetitionsRepository() {
        mApiFootballInterface = RetrofitMaker.getRetrofit();
    }


    public synchronized static CompetitionsRepository getInstance() {
        Log.d(LOG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new CompetitionsRepository();
                Log.d("CompetitionsRepo", "Made new repository");
            }
        }
        return sInstance;
    }




    public LiveData<CompetitionsResponse> fetchCompetitions(String countryName) {
        final MutableLiveData<CompetitionsResponse> data = new MutableLiveData<>();
        mApiFootballInterface.getCompetitionsByCountry(countryName).enqueue(new Callback<CompetitionsResponse>() {
            @Override
            public void onResponse(Call<CompetitionsResponse> call, Response<CompetitionsResponse> response) {
                Log.i(LOG, "Response successfull");
                data.setValue(response.body());
                Log.i("Competition on pos 0 for country: " + countryName, response.body().getCompetitions().get(0).getLeague().getName());
            }

            @Override
            public void onFailure(Call<CompetitionsResponse> call, Throwable throwable) {
                Log.e(LOG, throwable.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }

}

