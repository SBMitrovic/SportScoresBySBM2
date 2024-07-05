package pmf.android.sportscoresbysbm2.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.data.model.CountriesResponse;
import pmf.android.sportscoresbysbm2.data.model.Country;
import pmf.android.sportscoresbysbm2.data.repository.SingleTeamRepository;
import pmf.android.sportscoresbysbm2.ui.adapters.CountriesAdapter;
import pmf.android.sportscoresbysbm2.ui.viewholder.CountriesViewHolder;
import pmf.android.sportscoresbysbm2.util.RecyclerViewClickListenerInterface;
import pmf.android.sportscoresbysbm2.viewmodel.CountriesResponseViewModel;
import pmf.android.sportscoresbysbm2.viewmodel.SingleTeamViewModel;

public class CountriesActivity extends AppCompatActivity implements RecyclerViewClickListenerInterface {

    private CountriesResponseViewModel mCoutnriesViewModel;
    private List<Country> mCountryList;
    public LiveData<CountriesResponse> countriesResponseLiveData;
    private LinearLayoutManager layoutManager;



    List<Country> localList = new ArrayList<Country>();
    RecyclerView recyclerViewCountries;
    CountriesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);
        initialization();
    }





    private void initialization() {
        recyclerViewCountries = findViewById(R.id.recyclerViewCountries);
        recyclerViewCountries.setHasFixedSize(true);


        // use a linear layout manager
        layoutManager = new GridLayoutManager(CountriesActivity.this, 2);
        recyclerViewCountries.setLayoutManager(layoutManager);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

        // Initialize localList
        mCountryList = new ArrayList<>();

        // adapter
        adapter = new CountriesAdapter(this, mCountryList, this);
        recyclerViewCountries.setAdapter(adapter);

        // View Model
        mCoutnriesViewModel = new ViewModelProvider(this).get(CountriesResponseViewModel.class);

        // Call dobaviCountries() after the adapter is set
        getCountries();
        getSingleTeam();
    }

    public void getCountries(){

        mCoutnriesViewModel.getCountries().observe(this, CountriesResponse -> {
            if(CountriesResponse == null) {
                Log.e("MainActivity", "CountriesResponse is null");
            } else {

                mCountryList.addAll(CountriesResponse.getResponse());
                // Set the adapter to the RecyclerView here
                adapter = new CountriesAdapter(this, mCountryList, this);
                recyclerViewCountries.setAdapter(adapter);

                Log.e("Countries activity, size : ", "CountriesResponse is not null size : " + mCountryList.size());
                Log.e("Countries activity ", mCountryList.get(67).getName());



            }
        });
    }

    public void getSingleTeam(){
        SingleTeamViewModel mSingleTeamViewModel = new ViewModelProvider(this).get(SingleTeamViewModel.class);
        mSingleTeamViewModel.getSingleTeamResponse(37L).observe(this, singleTeamResponse -> {
            if(singleTeamResponse == null) {
                Log.e("SingleTeamActivity", "singleTeamResponse is null");
            } else {
                Log.e("SingleTeamActivity", singleTeamResponse.getResponse().get(0).getTeam().getName());
            }
        });
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(CountriesActivity.this, CompetitionsActivity.class);
        intent.putExtra("countryName", mCountryList.get(position).getName());
        Log.i("countryClicked", mCountryList.get(position).getName());
        startActivity(intent);
    }
}