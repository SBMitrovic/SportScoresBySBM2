package pmf.android.sportscoresbysbm2.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.data.model.Competition;
import pmf.android.sportscoresbysbm2.data.model.CompetitionsResponse;
import pmf.android.sportscoresbysbm2.data.model.Country;
import pmf.android.sportscoresbysbm2.data.model.Season;
import pmf.android.sportscoresbysbm2.data.model.StandingsResponse;
import pmf.android.sportscoresbysbm2.ui.adapters.CompetitionAdapter;
import pmf.android.sportscoresbysbm2.ui.adapters.CountriesAdapter;
import pmf.android.sportscoresbysbm2.util.RecyclerViewClickListenerInterface;
import pmf.android.sportscoresbysbm2.viewmodel.CompetitionsViewModel;
import pmf.android.sportscoresbysbm2.viewmodel.CountriesResponseViewModel;

public class CompetitionsActivity extends AppCompatActivity implements RecyclerViewClickListenerInterface {
    private RecyclerView recyclerViewCompetitions;
    private CompetitionsViewModel mCompetitionsViewModel;
    private CompetitionAdapter adapter;
    private List<Competition> mCompetitionsList;
    private LinearLayoutManager layoutManager;
    private Intent intent;
    private String currentSeasonYear = "2023";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_competitions);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        initialization();

    }

    private void initialization() {

        recyclerViewCompetitions = findViewById(R.id.recyclerViewCompetitions);
        recyclerViewCompetitions.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewCompetitions.setLayoutManager(layoutManager);


        //Initialize lists
        mCompetitionsList = new ArrayList<>();

        //Adapter
        adapter = new CompetitionAdapter(this, mCompetitionsList,this);
        recyclerViewCompetitions.setAdapter(adapter);

        //ViewModel
        mCompetitionsViewModel = new ViewModelProvider(this).get(CompetitionsViewModel.class);
        intent = getIntent();
        String countryName = intent.getStringExtra("countryName");
        getCompetitions(countryName);


    }

    private void getCompetitions(String countryName){

        mCompetitionsViewModel.getCompetitionsResponse(countryName).observe(this, competitionsResponse -> {
            if(competitionsResponse == null) {
                Log.e("Competitions activity", "CompetitionsResponse is null");
            } else {
                mCompetitionsViewModel = new ViewModelProvider(this).get(CompetitionsViewModel.class);

                mCompetitionsList.addAll(competitionsResponse.getCompetitions());

                // Set the adapter to the RecyclerView here
                adapter = new CompetitionAdapter(this, mCompetitionsList,this);
                recyclerViewCompetitions.setAdapter(adapter);

                Log.e("Countries activity", "CountriesResponse is not null");
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(CompetitionsActivity.this, StandingsGroupActivity.class);
        intent.putExtra("leagueName", mCompetitionsList.get(position).getLeague().getName());
        Long id = mCompetitionsList.get(position).getLeague().getId();
        intent.putExtra("leagueId",id);
        Log.i("leagueId", String.valueOf(id));
        intent.putExtra("countryName", intent.getStringExtra("countryName"));
        intent.putExtra("seasonYear", currentSeasonYear);

        //Ovo treba negdje izmjesititi dovodilo je do pucanja programa kada
        //Je tip takmicenja bio kup jer za njega ne postoje standings
        List<Season> seasons = mCompetitionsList.get(position).getSeasons();


        if(mCompetitionsList.get(position).getSeasons().get(seasons.size()-1).getCoverage().isStandings() == true) {
            startActivity(intent);
        }else{
            Toast.makeText(this, "Standings is not available for this competition", Toast.LENGTH_SHORT).show();
            //Eventualno prikazati pobjednika ili za KUPOVE prikazivati utakmice
        }

    }
}