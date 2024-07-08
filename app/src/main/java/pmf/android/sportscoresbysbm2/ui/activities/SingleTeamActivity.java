package pmf.android.sportscoresbysbm2.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
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

public class SingleTeamActivity extends AppCompatActivity implements OnMapReadyCallback {
    private Intent intent;
    private SingleTeamResponse.SingleTeam singleTeam;
    private Button favouritesButton,removeFavouritesButton,testButton;
    private SingleTeamRepository singleTeamRepository;

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


        testButton = findViewById(R.id.testButton);


        if(singleTeamRepository.isFavorite(getIntent().getLongExtra("teamId", 0L))){
            testButton.setText("Remove from favorites");
        } else {
            testButton.setText("Add to favourites");


        }

        testButton.setOnClickListener(v -> {
           TeamEntity teamEntity= new TeamEntity(singleTeam.getTeam().getId(), singleTeam.getTeam().getName());
            if(singleTeamRepository.isFavorite(getIntent().getLongExtra("teamId", 0L))){
                boolean teamAlreadyInBase = singleTeamRepository.removeSingleTeam(teamEntity);
                if(teamAlreadyInBase) {
                    Toast.makeText(SingleTeamActivity.this, "Team removed from favourites", Toast.LENGTH_SHORT).show();
                    testButton.setText("Add to favourites");


                } else {
                    Toast.makeText(SingleTeamActivity.this, "Team not in favourites", Toast.LENGTH_SHORT).show();
                    testButton.setText("Add to favourites");


                }
            } else {
                boolean teamNotAlreadyInBase = singleTeamRepository.insertSingleTeam(teamEntity);
                if(teamNotAlreadyInBase) {
                    Toast.makeText(SingleTeamActivity.this, "Team added to favourites", Toast.LENGTH_SHORT).show();
                    testButton.setText("Remove from favorites");


                } else {
                    Toast.makeText(SingleTeamActivity.this, "Team already in favourites", Toast.LENGTH_SHORT).show();
                    testButton.setText("Remove from favorites");


                }
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
                TextView teamName = findViewById(R.id.teamName);
                teamName.setText(singleTeam.getTeam().getName());
                TextView teamCountry = findViewById(R.id.teamCountry);
                teamCountry.setText(singleTeam.getTeam().getCountry());

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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Check if venue information is available
        if (singleTeam != null && singleTeam.getVenue() != null) {
            String searchQuery;
            if (singleTeam.getVenue().getAddress() != null && !singleTeam.getVenue().getAddress().isEmpty()) {
                searchQuery = singleTeam.getVenue().getAddress() + " " + singleTeam.getVenue().getCity();
            } else if (singleTeam.getVenue().getCity() != null && !singleTeam.getVenue().getCity().isEmpty()) {
                searchQuery = singleTeam.getVenue().getCity();
            } else {
                searchQuery = singleTeam.getTeam().getCountry();
            }

            // Attempt to geocode the search query to a location
            List<Address> addressList = null;
            try {
                addressList = geocoder.getFromLocationName(searchQuery, 1);
                Log.i("addressList", addressList.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (addressList != null && !addressList.isEmpty()) {
                Address address = addressList.get(0);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                Log.i("latlng", latLng.toString());
                mMap.addMarker(new MarkerOptions().position(latLng).title("Stadium Location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 3));
            } else {
                // Handle case where geocoding does not find the location
                LatLng latLng = new LatLng(44.500000,-89.500000);

                mMap.addMarker(new MarkerOptions().position(latLng).title("Stadium Location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 3));
                Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Handle case where there is no venue information
            LatLng latLng = new LatLng(44.500000,-89.500000);

            mMap.addMarker(new MarkerOptions().position(latLng).title("Stadium Location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 3));
            Toast.makeText(this, "No venue information available", Toast.LENGTH_SHORT).show();
        }
    }


}

