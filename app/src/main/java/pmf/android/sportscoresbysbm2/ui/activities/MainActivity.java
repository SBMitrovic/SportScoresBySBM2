package pmf.android.sportscoresbysbm2.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.data.model.CompetitionsResponse;
import pmf.android.sportscoresbysbm2.data.model.CountriesResponse;
import pmf.android.sportscoresbysbm2.data.model.Country;
import pmf.android.sportscoresbysbm2.data.model.CountriesResponse;
import pmf.android.sportscoresbysbm2.data.model.StandingsResponse;
import pmf.android.sportscoresbysbm2.data.repository.StandingsRepository;
import pmf.android.sportscoresbysbm2.util.APIFootballInterface;
import pmf.android.sportscoresbysbm2.viewmodel.CompetitionsViewModel;
import pmf.android.sportscoresbysbm2.viewmodel.CountriesResponseViewModel;
import pmf.android.sportscoresbysbm2.viewmodel.StandingsResponseViewModel;

public class MainActivity extends AppCompatActivity {

    //CountriesViewModel
    private CountriesResponseViewModel mCountriesResponseViewModel;
    private LiveData<CountriesResponse> mCountriesResponse;

    //StandingsResponseViewModel
    private StandingsResponseViewModel mStangingsViewModel;
    private LiveData<StandingsResponse> mStandingsResponse;


    //CompetitionsByCountry ViewModel
    private CompetitionsViewModel mCompetitionsViewModel;
    private LiveData<CompetitionsResponse> mCompetitionsResponse;



    int code;
    static final int PERMISSIONS_REQUEST_INTERNET = 1;
    APIFootballInterface apiInterface;
    CountriesResponse kountryList;
    List<Country> CountriesResponse;
    CompetitionsResponse competitionsResponseList;
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
        mCountriesResponseViewModel = new ViewModelProvider(this).get(CountriesResponseViewModel.class);
        setCountriesResponse();
        mStangingsViewModel = new ViewModelProvider(this).get(StandingsResponseViewModel.class);
        setStandingsResponse();
        mCompetitionsViewModel = new ViewModelProvider(this).get(CompetitionsViewModel.class);
        setCompetitions();
    }



    public void setCountriesResponse() {
        mCountriesResponseViewModel.getCountriesResponse().observe(this, CountriesResponse -> {
            if(CountriesResponse == null) {
                Log.e("MainActivity", "CountriesResponse is null");
            } else {
                Log.e("MainActivity", "CountriesResponse is not null");
                this.kountryList = CountriesResponse;
                // Ovako ne radi this.CountriesResponse = CountriesResponse.getResponse();
                Log.e("MainActivity", this.kountryList.getResponse().get(2).getName());
                TextView api_keyView = findViewById(R.id.api_keyView);
                api_keyView.setText(kountryList.getResponse().get(2).getName());
            }
        });

    }


    public void setStandingsResponse() {
        mStangingsViewModel.getStandingsResponse(22,"2023").observe(this, StandingsResponse -> {
            if(StandingsResponse == null) {
                Log.e("MainActivity", "StandingsResponse is null");
            } else {
                Log.e("MainActivityStandingsResponse", "StandingsResponse is not null");
                // Ovako ne radi this.CountriesResponse = CountriesResponse.getResponse();
                Log.e("MainActivityStandingsResponse", StandingsResponse.getResponse().get(0).getLeague().getName());
                TextView leagueStanding = findViewById(R.id.leagueStanding);
                leagueStanding.setText(StandingsResponse.getResponse().get(0).getLeague().getName());
            }
        });

    }


    public void setCompetitions() {
        mCompetitionsViewModel.getCompetitionsResponse("Serbia").observe(this, CompetitionsResponse -> {
            if(CompetitionsResponse == null) {
                Log.e("MainActivity", "CompetitionResponse is null");
            } else {
                Log.e("CompetitionResponse", "CompetitionResponse is not null");
                // Ovako ne radi this.CountriesResponse = CountriesResponse.getResponse();
                Log.e("MainActivityStandingsResponse", CompetitionsResponse.getCompetitions().get(0).getLeague().getName());
                TextView competitionOnPosZeroForCountry = findViewById(R.id.competitionOnPosZeroForCountry);
                competitionOnPosZeroForCountry.setText(CompetitionsResponse.getCompetitions().get(0).getLeague().getName());
            }
        });

    }


}