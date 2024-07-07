package pmf.android.sportscoresbysbm2.ui.activities;

import android.app.Activity;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
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
import pmf.android.sportscoresbysbm2.ui.adapters.StandingsGroupAdapater;
import pmf.android.sportscoresbysbm2.ui.fragments.StandingsFragment;
import pmf.android.sportscoresbysbm2.util.RecyclerViewClickListenerInterface;
import pmf.android.sportscoresbysbm2.viewmodel.CompetitionsViewModel;
import pmf.android.sportscoresbysbm2.viewmodel.StandingsResponseViewModel;

public class StandingsGroupActivity extends AppCompatActivity implements RecyclerViewClickListenerInterface {

    private StandingsResponseViewModel mStandingsViewModel;
    public LiveData<StandingsResponse> standingsResponseLiveData;
    private LinearLayoutManager layoutManager;
    RecyclerView recyclerViewStandings;
    StandingsGroupAdapater adapter;
    private Intent intent;
    private List<List<StandingsResponse.Standing>> list;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_standingsgroup);
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


        recyclerViewStandings = findViewById(R.id.standingsGroupRecyclerView);
        recyclerViewStandings.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewStandings.setLayoutManager(layoutManager);



        //Adapter
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

                Log.e("Competitions activity", "CompetitionsResponse is not null");
                list = standingsResponse.getResponse().get(0).getLeague().getStandings();
                adapter = new StandingsGroupAdapater(this, list, this);

                recyclerViewStandings.setAdapter(adapter);
            }

        });
    }

    @Override
    public void onItemClick(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        boolean isActive = fragmentManager.findFragmentByTag("frag"+position) != null;
        if(isActive) {
            ft.remove(fragmentManager.findFragmentByTag("frag"+position));
            ft.commit();
        }else{
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("standings", (ArrayList<? extends Parcelable>) list.get(position));
        Fragment fragment = new StandingsFragment();
        fragment.setArguments(bundle);
        ft.replace(R.id.fragment_container, fragment, "frag" + position);

        ft.commit();
        }
        /*
        FragmentTransaction ftReplace = getSupportFragmentManager().findFragmentByTag("frag" + position).getFragmentManager().beginTransaction();
        ftReplace.replace(R.id.fragment_container, fragment, "frag" + position);
        ftReplace.commit();

         */

    }

}