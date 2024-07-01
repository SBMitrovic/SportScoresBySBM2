package pmf.android.sportscoresbysbm2.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.data.model.CountriesResponse;
import pmf.android.sportscoresbysbm2.data.model.Country;
import pmf.android.sportscoresbysbm2.ui.adapters.CountriesAdapter;
import pmf.android.sportscoresbysbm2.viewmodel.CountriesResponseViewModel;

public class CountriesActivity extends AppCompatActivity {

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
       // EdgeToEdge.enable(this);
      /*  ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recyclerViewCountries), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });*/
        initialization();
        dobaviCountries();
        //adapter.notifyDataSetChanged();

    }

    /*
    public void setCountriesResponse() {
        List<Country> localList = new ArrayList<Country>();
        mCoutnriesViewModel.getCountriesResponse().observe(this, CountriesResponse -> {
            if(CountriesResponse == null) {
                Log.e("MainActivity", "CountriesResponse is null");
                Country country = new Country("Croatia", "HR", "https://www.countryflags.io/HR/shiny/64.png");
            } else {
                mCoutnriesViewModel = new ViewModelProvider(this).get(CountriesResponseViewModel.class);
                RecyclerView recyclerView = findViewById(R.id.recyclerViewCountries);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                localList.addAll(CountriesResponse.getResponse());
                Log.e("ALB", localList.get(0).getName());
                recyclerView.setAdapter(new CountriesAdapter(this, localList));

                Log.e("MainActivity", "CountriesResponse is not null");
                // Ovako ne radi this.CountriesResponse = CountriesResponse.getResponse();
                Log.e("Countries activity ", localList.get(67).getName());
            }
        });

    }


     */
    private void initialization() {
        recyclerViewCountries = (RecyclerView) findViewById(R.id.recyclerViewCountries);


        // use a linear layout manager

        layoutManager = new LinearLayoutManager(CountriesActivity.this);

        recyclerViewCountries.setLayoutManager(layoutManager);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerViewCountries.setHasFixedSize(true);

        // adapter
        adapter = new CountriesAdapter(this, localList);
        recyclerViewCountries.setAdapter(adapter);

        // View Model


        mCoutnriesViewModel = new ViewModelProvider(this).get(CountriesResponseViewModel.class);

    }

    public void dobaviCountries(){
        mCoutnriesViewModel.getCountries().observe(this, CountriesResponse -> {
            if(CountriesResponse == null) {
                Log.e("MainActivity", "CountriesResponse is null");
                Country country = new Country("Croatia", "HR", "https://www.countryflags.io/HR/shiny/64.png");
            } else {
                mCoutnriesViewModel = new ViewModelProvider(this).get(CountriesResponseViewModel.class);

                localList.addAll(CountriesResponse.getResponse());
                adapter.notifyDataSetChanged();

                Log.e("Countries activity", "CountriesResponse is not null");
                // Ovako ne radi this.CountriesResponse = CountriesResponse.getResponse();
                Log.e("Countries activity ", localList.get(67).getName());
            }
        });

    }



/*
    public void dobaviCountries(){
        List<Country> localList = new ArrayList<Country>();
        // Log.e("value", mCoutnriesViewModel.getCountries().getValue() + "");
        mCoutnriesViewModel.getCountries().observe(this, CountriesResponse -> {
            if(CountriesResponse == null) {
                Log.e("MainActivity", "CountriesResponse is null");
                Country country = new Country("Croatia", "HR", "https://www.countryflags.io/HR/shiny/64.png");
            } else {
                mCoutnriesViewModel = new ViewModelProvider(this).get(CountriesResponseViewModel.class);
                recyclerView  = (RecyclerView) findViewById(R.id.recyclerViewCountries);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                localList.addAll(CountriesResponse.getResponse());
                Log.e("ALB", localList.get(0).getName());
                adapter = new CountriesAdapter(this, localList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                Log.e("Countries activity", "CountriesResponse is not null");
                // Ovako ne radi this.CountriesResponse = CountriesResponse.getResponse();
                Log.e("Countries activity ", localList.get(67).getName());
            }
        });

    } */
}