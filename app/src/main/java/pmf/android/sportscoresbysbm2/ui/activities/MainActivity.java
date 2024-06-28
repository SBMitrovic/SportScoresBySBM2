package pmf.android.sportscoresbysbm2.ui.activities;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.Manifest;
import android.widget.Toast;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.SportScoresBySBM;
import pmf.android.sportscoresbysbm2.data.model.CompetitionsByCountry;
import pmf.android.sportscoresbysbm2.data.model.CountryList;
import pmf.android.sportscoresbysbm2.data.model.StandingsResponse;
import pmf.android.sportscoresbysbm2.data.database.ApplicationRoomDatabase;
import pmf.android.sportscoresbysbm2.data.database.TeamEntityDao;
import pmf.android.sportscoresbysbm2.utilities.APIFootballInterface;
import pmf.android.sportscoresbysbm2.utilities.RetrofitMaker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    int code;
    static final int PERMISSIONS_REQUEST_INTERNET = 1;
    APIFootballInterface apiInterface;
    CountryList countryList;
    CompetitionsByCountry CompetitionsByCountryList;
    StandingsResponse currentLeague;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView api_keyView = findViewById(R.id.api_keyView);
        checkPermissions();


        apiInterface = RetrofitMaker.getRetrofit();

        getCountriesList();
        getCompetitionsByCountry("Albania");
        leagueStandings("57","2023");

        ApplicationRoomDatabase.getDatabase(SportScoresBySBM.getInstance());

        TeamEntityDao teamEntityDao = ApplicationRoomDatabase.getDatabase(getApplicationContext()).teamEntityDao();
        //TeamEntity favTeam = new TeamEntity(24L, "Bosna");
        //teamEntityDao.insert(favTeam);

        if(countryList != null) {
            api_keyView.setText("list Not null" );

        }else {
            Log.i("countries", "null");
            api_keyView.setText("list = null");
        }
    }

    private void getCountriesList() {
        apiInterface.getCountries().enqueue(new Callback<CountryList>() {

            @Override
            public void onResponse(Call<CountryList> call, Response<CountryList> response) {
                Log.i("infoinfo","smh");
                code = response.code();
                if (response.code() == 200) {
                    Log.i("response.body", response.body().toString());
                    Log.i("bodyToString", response.body().toString());
                    countryList = response.body();
                    response.body().getResponse().get(100).getName();
                    TextView api_keyView = findViewById(R.id.api_keyView);
                    api_keyView.setText(countryList.getResponse().get(100).getName());
                }
            }

            @Override
            public void onFailure(Call<CountryList> call, Throwable t) {
                Log.i("code", " " +  code);
                Log.i("onFailure", t.getMessage());
            }
        });
    }


    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    PERMISSIONS_REQUEST_INTERNET);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_INTERNET: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    private void getCompetitionsByCountry(String country) {
        apiInterface.getCompetitionsByCountry(country).enqueue(new Callback<CompetitionsByCountry>() {
            String smh = "smh";

            @Override
            public void onResponse(Call<CompetitionsByCountry> call, Response<CompetitionsByCountry> response) {
                Log.i("infoinfo","smh");
                code = response.code();
                if (response.code() == 200) {
                    Log.i("response.body", response.body().toString());
                    Log.i("bodyToString", response.body().toString());
                    CompetitionsByCountryList = response.body();
                    TextView api_keyView = findViewById(R.id.api_keyView);

                    /* Linija ispod je ubacena da bi se pokazalo kako polja koja su visak u klasi i response
                     ih ne mapira na knokretnu klasu ostaju undefined a ne null
                     sto ne dovodi do RuntimeException-a :) */
                   // api_keyView.setText(CompetitionsByCountryList.getCompetitions().get(0).getId() + ": Country - " + country);
                   // api_keyView.setText(CompetitionsByCountryList.getCompetitions().get(0).getId() + ": Country - " + country);
                }
            }

            @Override
            public void onFailure(Call<CompetitionsByCountry> call, Throwable t) {
                Log.i("code", " " +  code);
                Log.i("onFailure", t.getMessage());
            }
        });
    }

    /// 3.Standing by league and country

    private void leagueStandings(String country,String season) {
        apiInterface.getStandingsByLeague(country,season).enqueue(new Callback<StandingsResponse>() {
            String smh = "smh";

            @Override
            public void onResponse(Call<StandingsResponse> call, Response<StandingsResponse> response) {
                Log.i("infoinfo","smh");
                code = response.code();
                if (response.code() == 200) {
                    Log.i("response.bodyLeague", response.body().toString());
                    Log.i("bodyToString", response.body().toString());
                    currentLeague = response.body();
                    TextView leagueStanding = findViewById(R.id.leagueStanding);
                    leagueStanding.setText(currentLeague.getResponse().get(0).getLeague().getName());
                }
            }

            @Override
            public void onFailure(Call<StandingsResponse> call, Throwable t) {
                Log.i("code", " " +  code);
                Log.i("onFailureLeagueStanding", t.getMessage());
            }
        });
    }

}