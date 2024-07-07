package pmf.android.sportscoresbysbm2.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.data.model.CountriesResponse;
import pmf.android.sportscoresbysbm2.data.model.Country;
import pmf.android.sportscoresbysbm2.data.model.StandingsResponse;
import pmf.android.sportscoresbysbm2.ui.adapters.CompetitionAdapter;
import pmf.android.sportscoresbysbm2.ui.adapters.CountriesAdapter;
import pmf.android.sportscoresbysbm2.ui.adapters.StandingsAdapter;
import pmf.android.sportscoresbysbm2.util.RecyclerViewClickListenerInterface;
import pmf.android.sportscoresbysbm2.viewmodel.CompetitionsViewModel;
import pmf.android.sportscoresbysbm2.viewmodel.StandingsResponseViewModel;

public class StandingsActivity extends AppCompatActivity implements RecyclerViewClickListenerInterface {

    private StandingsResponseViewModel mStandingsViewModel;
    private List<StandingsResponse.Standing> mStandingsList;
    public LiveData<StandingsResponse> standingsResponseLiveData;
    private LinearLayoutManager layoutManager;
    RecyclerView recyclerViewStandings;
    StandingsAdapter adapter;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_standings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), CountriesActivity.class));
                    finish();
                    return true;
                case R.id.favorites:
                    startActivity(new Intent(getApplicationContext(), MakeNotificationActivity.class));
                    finish();
                    return true;

                case R.id.notification:
                    startActivity(new Intent(getApplicationContext(), MakeNotificationActivity.class));
                    finish();
                    return true;
            }
            return false;
        });
        initialization();
    }

    private void initialization() {

        recyclerViewStandings = findViewById(R.id.recyclerViewStandings);
        recyclerViewStandings.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewStandings.setLayoutManager(layoutManager);


        //Initialize lists
        mStandingsList = new ArrayList<>();

        //Adapter
        adapter = new StandingsAdapter(this, mStandingsList, this);
        recyclerViewStandings.setAdapter(adapter);

        //ViewModel
        mStandingsViewModel = new ViewModelProvider(this).get(StandingsResponseViewModel.class);

        intent = getIntent();

        long leagueId = intent.getLongExtra("leagueId", 0);
        Log.i("Standings activity ID ", String.valueOf(leagueId));
        String season = intent.getStringExtra("seasonYear");
        getStandings(leagueId, season);


    }

    private void getStandings(long leagueId, String season) {

        mStandingsViewModel.getStandingsResponse(leagueId, season).observe(this, standingsResponse -> {
            if(standingsResponse == null) {
                Log.e("Competitions activity", "CompetitionsResponse is null");
            } else {
                mStandingsViewModel = new ViewModelProvider(this).get(StandingsResponseViewModel.class);

                mStandingsList.addAll(standingsResponse.getResponse().get(0).getLeague().getStandingsSimple());

                // Set the adapter to the RecyclerView here
                adapter = new StandingsAdapter(this, mStandingsList, this);
                recyclerViewStandings.setAdapter(adapter);

                Log.e("Standings activity", "Standings is not null");
                Log.e("Standings activity ", mStandingsList.get(1).getTeam().getName());
            }
        });
    }

    @Override
    public void onItemClick(int position) {

        intent = new Intent(this, SingleTeamActivity.class);
        intent.putExtra("teamId", mStandingsList.get(position).getTeam().getId());
        startActivity(intent);
    }
}