package pmf.android.sportscoresbysbm2.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.data.model.Country;
import pmf.android.sportscoresbysbm2.ui.adapters.CountriesAdapter;
import pmf.android.sportscoresbysbm2.viewmodel.CountriesResponseViewModel;

public class CountriesActivity extends AppCompatActivity {

    private CountriesResponseViewModel mCoutnriesViewModel;
    private List<Country> mCountryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_countries);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });

        mCoutnriesViewModel = new ViewModelProvider(this).get(CountriesResponseViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewCountries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CountriesAdapter(this, setCountriesResponse()));
    }


    public List<Country> setCountriesResponse() {
        List<Country> localList = new ArrayList<Country>();
        mCoutnriesViewModel.getCountriesResponse().observe(this, CountriesResponse -> {
            if(CountriesResponse == null) {
                Log.e("MainActivity", "CountriesResponse is null");
                Country country = new Country("Croatia", "HR", "https://www.countryflags.io/HR/shiny/64.png");
            } else {
                 localList.addAll(CountriesResponse.getResponse());
                Log.e("MainActivity", "CountriesResponse is not null");
                // Ovako ne radi this.CountriesResponse = CountriesResponse.getResponse();
                Log.e("Countries activity ", localList.get(67).getName());
                mCountryList.addAll(localList);
            }
        });

        return mCountryList;
    }
}