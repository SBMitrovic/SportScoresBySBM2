package pmf.android.sportscoresbysbm2.ui.activities;

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

import java.util.ArrayList;
import java.util.List;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.data.model.CountriesResponse;
import pmf.android.sportscoresbysbm2.data.model.Country;
import pmf.android.sportscoresbysbm2.data.model.StandingsResponse;
import pmf.android.sportscoresbysbm2.ui.adapters.CompetitionAdapter;
import pmf.android.sportscoresbysbm2.ui.adapters.CountriesAdapter;
import pmf.android.sportscoresbysbm2.ui.adapters.StandingsAdapter;
import pmf.android.sportscoresbysbm2.viewmodel.CompetitionsViewModel;
import pmf.android.sportscoresbysbm2.viewmodel.StandingsResponseViewModel;

public class StandingsActivity extends AppCompatActivity {

    private StandingsResponseViewModel mStandingsViewModel;
    private List<StandingsResponse.Standing> mStandingsList;
    public LiveData<StandingsResponse> standingsResponseLiveData;
    private LinearLayoutManager layoutManager;
    RecyclerView recyclerViewStandings;
    StandingsAdapter adapter;

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
        adapter = new StandingsAdapter(this, mStandingsList);
        recyclerViewStandings.setAdapter(adapter);

        //ViewModel
        mStandingsViewModel = new ViewModelProvider(this).get(StandingsResponseViewModel.class);

        getStandings();


    }

    private void getStandings(){

        mStandingsViewModel.getStandingsResponse(39, "2020").observe(this, standingsResponse -> {
            if(standingsResponse == null) {
                Log.e("Competitions activity", "CompetitionsResponse is null");
            } else {
                mStandingsViewModel = new ViewModelProvider(this).get(StandingsResponseViewModel.class);

                mStandingsList.addAll(standingsResponse.getResponse().get(0).getLeague().getStandingsSimple());

                // Set the adapter to the RecyclerView here
                adapter = new StandingsAdapter(this, mStandingsList);
                recyclerViewStandings.setAdapter(adapter);

                Log.e("Standings activity", "Standings is not null");
                Log.e("Standings activity ", mStandingsList.get(1).getTeam().getName());
            }
        });
    }
}