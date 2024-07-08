package pmf.android.sportscoresbysbm2.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.data.database.TeamEntity;
import pmf.android.sportscoresbysbm2.ui.adapters.CompetitionAdapter;
import pmf.android.sportscoresbysbm2.ui.adapters.FavouriteTeamsAdapter;
import pmf.android.sportscoresbysbm2.util.RecyclerViewClickListenerInterface;
import pmf.android.sportscoresbysbm2.viewmodel.CountriesResponseViewModel;
import pmf.android.sportscoresbysbm2.viewmodel.SingleTeamViewModel;

public class FavouriteTeams extends AppCompatActivity implements RecyclerViewClickListenerInterface {
    private List<TeamEntity> favouriteTeams;
    private SingleTeamViewModel mSingleTeamViewModel;
    private RecyclerView recyclerViewFavourites;
    private FavouriteTeamsAdapter adapter;
    private LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_favourite_teams);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(0);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            
            switch (item.getItemId()) {
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), CountriesActivity.class));
                    finish();
                    return true;
                case R.id.favorites:
                    startActivity(new Intent(getApplicationContext(), CountriesActivity.class));
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

    public void initialization(){

        mSingleTeamViewModel = new ViewModelProvider(this).get(SingleTeamViewModel.class);

        favouriteTeams = mSingleTeamViewModel.getFavouriteTeams();



        recyclerViewFavourites = findViewById(R.id.recyclerViewFavourites);
        recyclerViewFavourites.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewFavourites.setLayoutManager(layoutManager);

        if (favouriteTeams == null) {
            Log.i("FavouriteTeams", "favouriteTeams is null");
        } else {
            Log.i("FavouriteTeams", "favouriteTeams is not null");
            adapter = new FavouriteTeamsAdapter(this, favouriteTeams,this);
            recyclerViewFavourites.setAdapter(adapter);

        }
       // adapter = new FavouriteTeamsAdapter(this, favouriteTeams,this);
    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(this, SingleTeamActivity.class);
        intent.putExtra("teamId", favouriteTeams.get(position).getTeamId());
        startActivity(intent);
    }

}