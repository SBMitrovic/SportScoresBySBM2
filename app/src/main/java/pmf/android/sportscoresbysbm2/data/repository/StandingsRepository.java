package pmf.android.sportscoresbysbm2.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import pmf.android.sportscoresbysbm2.data.model.CountriesResponse;
import pmf.android.sportscoresbysbm2.data.model.StandingsResponse;
import pmf.android.sportscoresbysbm2.util.APIFootballInterface;
import pmf.android.sportscoresbysbm2.util.RetrofitMaker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StandingsRepository {

    private static final String LOG = "StandingsRepo";
    private static final Object LOCK = new Object();
    private APIFootballInterface mApiFootballInterface;
    private RetrofitMaker retrofitMaker;
    private static StandingsRepository sInstance;


    private StandingsRepository() {
        mApiFootballInterface = RetrofitMaker.getRetrofit();
    }

    public synchronized static StandingsRepository getInstance() {
        Log.d(LOG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new StandingsRepository();
                Log.d(LOG, "Made new repository");
            }
        }
        return sInstance;
    }



    public LiveData<StandingsResponse> fetchStadnings(long leagueId, String season) {
        final MutableLiveData<StandingsResponse> data = new MutableLiveData<>();
        mApiFootballInterface.getStandingsByLeague(leagueId, season).enqueue(new Callback<StandingsResponse>() {
            @Override
            public void onResponse(Call<StandingsResponse> call, Response<StandingsResponse> response) {
                Log.i(LOG, "Response successfull");
                data.setValue(response.body());
                Log.i("League name ", response.body().getResponse().get(0).getLeague().getName());
            }

            @Override
            public void onFailure(Call<StandingsResponse> call, Throwable throwable) {
                Log.e(LOG, throwable.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }

}
