package pmf.android.sportscoresbysbm2.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

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
import pmf.android.sportscoresbysbm2.ui.adapters.CompetitionAdapter;
import pmf.android.sportscoresbysbm2.ui.adapters.CountriesAdapter;
import pmf.android.sportscoresbysbm2.viewmodel.CompetitionsViewModel;
import pmf.android.sportscoresbysbm2.viewmodel.CountriesResponseViewModel;

public class CompetitionsActivity extends AppCompatActivity {
    private RecyclerView recyclerViewCompetitions;
    private CompetitionsViewModel mCompetitionsViewModel;
    private CompetitionAdapter adapter;
    private List<Competition> mCompetitionsList;
    private LinearLayoutManager layoutManager;


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
        getCompetitions();
    }

    private void initialization() {

        recyclerViewCompetitions = findViewById(R.id.recyclerViewCompetitions);
        recyclerViewCompetitions.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewCompetitions.setLayoutManager(layoutManager);


        //Initialize lists
        mCompetitionsList = new ArrayList<>();

        //Adapter
        adapter = new CompetitionAdapter(this, mCompetitionsList);
        recyclerViewCompetitions.setAdapter(adapter);

        //ViewModel
        mCompetitionsViewModel = new ViewModelProvider(this).get(CompetitionsViewModel.class);

        getCompetitions();


    }

    private void getCompetitions(){

        mCompetitionsViewModel.getCompetitionsResponse("Serbia").observe(this, competitionsResponse -> {
            if(competitionsResponse == null) {
                Log.e("Competitions activity", "CompetitionsResponse is null");
            } else {
                mCompetitionsViewModel = new ViewModelProvider(this).get(CompetitionsViewModel.class);

                mCompetitionsList.addAll(competitionsResponse.getCompetitions());

                // Set the adapter to the RecyclerView here
                adapter = new CompetitionAdapter(this, mCompetitionsList);
                recyclerViewCompetitions.setAdapter(adapter);

                Log.e("Countries activity", "CountriesResponse is not null");
                Log.e("Countries activity ", mCompetitionsList.get(1).getLeague().getName());
            }
        });
    }
}