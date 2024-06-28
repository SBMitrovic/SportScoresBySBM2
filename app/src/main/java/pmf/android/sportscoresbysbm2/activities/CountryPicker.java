package pmf.android.sportscoresbysbm2.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.model.Country;
import pmf.android.sportscoresbysbm2.model.CountryList;
import pmf.android.sportscoresbysbm2.utilities.APIInterface;
import pmf.android.sportscoresbysbm2.utilities.RetrofitMaker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryPicker extends AppCompatActivity {
    ListView l;

    String tutorials[]
            = { "Algorithms", "Data Structures",
            "Languages", "Interview Corner",
            "GATE", "ISRO CS",
            "UGC NET CS", "CS Subjects",
            "Web Technologies" };

     CountryList countryList;
    APIInterface apiInterface = RetrofitMaker.getRetrofit();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_country_picker2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        l = findViewById(R.id.list);
        ArrayAdapter<Country> arr;
        arr
                = new ArrayAdapter<Country>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                countryList.getResponse());
        l.setAdapter(arr);
    }

    private void getCountriesList() {
        apiInterface.getCountries().enqueue(new Callback<CountryList>() {
            String smh = "smh";

            @Override
            public void onResponse(Call<CountryList> call, Response<CountryList> response) {
                Log.i("infoinfo","smh");
                if (response.code() == 200) {
                    Log.i("response.body", response.body().toString());
                    Log.i("bodyToString", response.body().toString());
                    countryList = response.body();
                    TextView api_keyView = findViewById(R.id.api_keyView);
                    api_keyView.setText(countryList.getResponse().get(100).getName());
                }
            }

            @Override
            public void onFailure(Call<CountryList> call, Throwable t) {
                Log.i("onFailure", t.getMessage());
            }
        });
    }


}
