package pmf.android.sportscoresbysbm2.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;


import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.data.database.TeamEntity;
import pmf.android.sportscoresbysbm2.data.model.SingleTeamResponse;
import pmf.android.sportscoresbysbm2.data.repository.SingleTeamRepository;
import pmf.android.sportscoresbysbm2.databinding.ActivityMapsBinding;
import pmf.android.sportscoresbysbm2.viewmodel.SingleTeamViewModel;

public class SingleTeamActivity extends AppCompatActivity implements OnMapReadyCallback{
    private Intent intent;
    private SingleTeamResponse.SingleTeam singleTeam;
    private Button favouritesButton;
    private SingleTeamRepository singleTeamRepository;
    public ImageView teamLogo;

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private final int FINE_PERMISSION_CODE = 1;
    Geocoder geocoder = new Geocoder(this);

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_single_team);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();



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
                    startActivity(new Intent(getApplicationContext(), FavouriteTeams.class));
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
        favouritesButton = findViewById(R.id.favouritesButton);
        favouritesButton.setOnClickListener(v -> {
            TeamEntity teamEntity = new TeamEntity(singleTeam.getTeam().getId(), singleTeam.getTeam().getName());
            boolean teamNotAlreadyInBase = singleTeamRepository.insertSingleTeam(teamEntity);
            if(teamNotAlreadyInBase) {
                Toast.makeText(SingleTeamActivity.this, "Team added to favourites", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SingleTeamActivity.this, "Team already in favourites", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initialization(){
        intent = getIntent();
        Long teamId = intent.getLongExtra("teamId", 0L);
        singleTeamRepository = SingleTeamRepository.getInstance();
        getSingleTeam(teamId);


    }


    public void getSingleTeam(Long teamId){
        SingleTeamViewModel mSingleTeamViewModel = new ViewModelProvider(this).get(SingleTeamViewModel.class);
        mSingleTeamViewModel.getSingleTeamResponse(teamId).observe(this, singleTeamResponse -> {
            if(singleTeamResponse == null) {
                Log.e("SingleTeamActivity", "singleTeamResponse is null");
            } else {
                Log.i("SingleTeamActivity", singleTeamResponse.getResponse().get(0).getTeam().getName());

                singleTeam = singleTeamResponse.getResponse().get(0);
                Log.i("SingleTeamActivity", singleTeamResponse.getResponse().get(0).getVenue().getAddress());
                TextView teamName = findViewById(R.id.teamName);
                ImageView imageView = findViewById(R.id.teamLogo);
                teamName.setText(singleTeam.getTeam().getName());
                ImageView teamLogo = findViewById(R.id.teamLogo);
                Picasso.get().load(singleTeam.getTeam().getLogo()).into(teamLogo);
                Log.i("SingleTeamActivity Logo", singleTeam.getTeam().getLogo());

            }
        });
    }


    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(SingleTeamActivity.this);
                }
            }
        });
    }


    public void onMapReady(GoogleMap googleMap) {
        //String stadiumUri = "geo:0,0?q=" + singleTeam.getVenue().getName();
        Log.i("Stadium address", singleTeam.getVenue().getAddress() + " " + singleTeam.getVenue().getCity());
        String stadiumUri = "geo:0,0?q=" + singleTeam.getVenue().getAddress() + " " + singleTeam.getVenue().getCity();

        Log.i("stadiumUri", stadiumUri);
        Uri gmmIntentUri = Uri.parse(stadiumUri.toString());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
        /*mMap = googleMap;

        LatLng myLocation = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        mMap.addMarker(new MarkerOptions().position(myLocation).title("My Location"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));*/
    }

}