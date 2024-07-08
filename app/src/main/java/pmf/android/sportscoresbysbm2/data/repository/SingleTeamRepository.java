package pmf.android.sportscoresbysbm2.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import java.util.List;

import pmf.android.sportscoresbysbm2.SportScoresBySBM;
import pmf.android.sportscoresbysbm2.data.database.ApplicationRoomDatabase;
import pmf.android.sportscoresbysbm2.data.database.TeamEntity;
import pmf.android.sportscoresbysbm2.data.model.SingleTeamResponse;
import pmf.android.sportscoresbysbm2.data.model.StandingsResponse;
import pmf.android.sportscoresbysbm2.util.APIFootballInterface;
import pmf.android.sportscoresbysbm2.util.RetrofitMaker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleTeamRepository {

    private static final String LOG = "SingleTeamRepo";
    private static final Object LOCK = new Object();
    private APIFootballInterface mApiFootballInterface;
    private RetrofitMaker retrofitMaker;
    private static SingleTeamRepository sInstance;
    private static ApplicationRoomDatabase db;

    private SingleTeamRepository() {
        mApiFootballInterface = RetrofitMaker.getRetrofit();
    }

    public synchronized static SingleTeamRepository getInstance() {
        Log.d(LOG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new SingleTeamRepository();
                Log.d(LOG, "Made new repository");
            }
        }
        db = Room.databaseBuilder(SportScoresBySBM.getInstance(),
                ApplicationRoomDatabase.class, "SportScoresBySBM_database").allowMainThreadQueries().build();
        return sInstance;
    }

    public LiveData<SingleTeamResponse> fetchSingleTeam(long teamId) {
        final MutableLiveData<SingleTeamResponse> data = new MutableLiveData<>();
        mApiFootballInterface.getSingleTeam(teamId).enqueue(new Callback<SingleTeamResponse>() {
            @Override
            public void onResponse(Call<SingleTeamResponse> call, Response<SingleTeamResponse> response) {
                Log.i(LOG, "SingleTeam Response successfull");
                data.setValue(response.body());
                if (response.body() != null) {
                    Log.i("Team name ", response.body().getResponse().get(0).getTeam().getName());
                } else {
                    Log.i("Team name ", "null");
                }
            }

            @Override
            public void onFailure(Call<SingleTeamResponse> call, Throwable throwable) {
                Log.e(LOG, throwable.getMessage());
                data.setValue(null);
                Log.e(LOG, "Failure in fetchSingleTeam");
            }
        });
        return data;
    }
    public List<TeamEntity> getAllTeams() {
        List<TeamEntity> teams = db.teamEntityDao().getAll();
        return teams;
    }
    public boolean insertSingleTeam(TeamEntity team) {
        Long id = team.getTeamId();
        List<TeamEntity> teams = db.teamEntityDao().getAll();
        for (TeamEntity t : teams) {
            if (t.getTeamId().equals(id)) {
                return false;
            }
        }
        db.teamEntityDao().insert(team);
        return true;
    }
}