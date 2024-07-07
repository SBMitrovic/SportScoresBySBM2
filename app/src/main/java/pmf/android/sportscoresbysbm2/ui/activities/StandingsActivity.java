package pmf.android.sportscoresbysbm2.ui.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.data.model.CountriesResponse;
import pmf.android.sportscoresbysbm2.data.model.Country;
import pmf.android.sportscoresbysbm2.data.model.StandingsResponse;
import pmf.android.sportscoresbysbm2.ui.adapters.CompetitionAdapter;
import pmf.android.sportscoresbysbm2.ui.adapters.CountriesAdapter;
import pmf.android.sportscoresbysbm2.ui.adapters.StandingsAdapter;
import pmf.android.sportscoresbysbm2.ui.fragments.StandingsFragment;
import pmf.android.sportscoresbysbm2.util.RecyclerViewClickListenerInterface;
import pmf.android.sportscoresbysbm2.viewmodel.CompetitionsViewModel;
import pmf.android.sportscoresbysbm2.viewmodel.StandingsResponseViewModel;

public class StandingsActivity extends AppCompatActivity implements RecyclerViewClickListenerInterface {
    private RecyclerView standingsRecyclerView;

    private StandingsResponseViewModel mStandingsViewModel;
    private List<StandingsResponse.Standing> mStandingsList;
    public LiveData<StandingsResponse> standingsResponseLiveData;
    private LinearLayoutManager layoutManager;
    RecyclerView recyclerViewStandings;
    StandingsAdapter adapter;
    private Intent intent;

    private Map<String, List<StandingsResponse.Standing>> standingsMap = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        standingsRecyclerView = findViewById(R.id.standingsRecyclerView);
        standingsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_standings);
        adapter = new StandingsAdapter(this, mStandingsList,this);
        standingsRecyclerView.setAdapter(adapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initialization();




    }

    private void initialization() {

        /*
        recyclerViewStandings = findViewById(R.id.recyclerViewStandings);
        recyclerViewStandings.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewStandings.setLayoutManager(layoutManager);


        //Initialize lists
        mStandingsList = new ArrayList<>();

        //Adapter
        adapter = new StandingsAdapter(this, mStandingsList, this);
        recyclerViewStandings.setAdapter(adapter);


         */
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

               // mStandingsList.addAll(standingsResponse.getResponse().get(0).getLeague().getStandingsSimple());
                standingsMap = standingsResponse.getResponse().get(0).getLeague().experiment();
                for (int i = 0; i < standingsMap.size(); i++) {
                    Log.i("Standings activity map size", String.valueOf(standingsMap.size()));
                }


                //Fragment
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                for(int i = 0 ; i < standingsMap.size(); i++) {
                    Log.i("Inside loop stand actv", String.valueOf(i));
                    Bundle bundle = new Bundle();
                    List<StandingsResponse.Standing> standings = standingsMap.get("list" + i);
                    ArrayList<StandingsResponse.Standing> standingsArrayList = new ArrayList<>(standings);

                    //Bundle
                    bundle.putParcelableArrayList("standings", standingsArrayList);
                    Log.i("Standings activity team array", String.valueOf(standingsArrayList.get(0).getTeam().getName()));


                    //Fragment

                    Fragment fragment = new StandingsFragment();
                    fragment.setArguments(bundle);
                   // ft.add(R.id.fragment_container, fragment,"standings" +  i );
                   // ft.attach(R.id.fragment_container, fragment,"standings" +  i );
                    ft.add(R.id.fragment_container, fragment,"standings" +  i );
                  //  ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                  //  ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                }

                ft.commit();


                // Set the adapter to the RecyclerView here
               // adapter = new StandingsAdapter(this, mStandingsList, this);
//                recyclerViewStandings.setAdapter(adapter);

              //  Log.e("Standings activity", "Standings is not null");
                //Log.e("Standings activity ", mStandingsList.get(1).getTeam().getName());
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