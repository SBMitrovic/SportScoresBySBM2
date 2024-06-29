package pmf.android.sportscoresbysbm2.ui.activities;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.widget.Toast;

import java.util.List;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.SportScoresBySBM;
import pmf.android.sportscoresbysbm2.data.model.CompetitionsByCountry;
import pmf.android.sportscoresbysbm2.data.model.Country;
import pmf.android.sportscoresbysbm2.data.model.CountryList;
import pmf.android.sportscoresbysbm2.data.model.StandingsResponse;
import pmf.android.sportscoresbysbm2.data.database.ApplicationRoomDatabase;
import pmf.android.sportscoresbysbm2.data.database.TeamEntityDao;
import pmf.android.sportscoresbysbm2.util.APIFootballInterface;
import pmf.android.sportscoresbysbm2.util.RetrofitMaker;
import pmf.android.sportscoresbysbm2.viewmodel.CountryListViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private CountryListViewModel mCountryListViewModel;
    private LiveData<CountryList> mCountryList;
    int code;
    static final int PERMISSIONS_REQUEST_INTERNET = 1;
    APIFootballInterface apiInterface;
    CountryList kountryList;
    List<Country> countryList;
    CompetitionsByCountry CompetitionsByCountryList;
    StandingsResponse currentLeague;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView api_keyView = findViewById(R.id.api_keyView);
        mCountryListViewModel = new ViewModelProvider(this).get(CountryListViewModel.class);
        setCountryList();
    }



    public void setCountryList() {
        mCountryListViewModel.getCountryList().observe(this, countryList -> {
            if(countryList == null) {
                Log.e("MainActivity", "CountryList is null");
            } else {
                Log.e("MainActivity", "CountryList is not null");
                this.kountryList = countryList;
                // Ovako ne radi this.countryList = countryList.getResponse();
                Log.e("MainActivity", this.kountryList.getResponse().get(0).getName());
                TextView api_keyView = findViewById(R.id.api_keyView);
                api_keyView.setText(kountryList.getResponse().get(0).getName());
            }
        });

    }


}