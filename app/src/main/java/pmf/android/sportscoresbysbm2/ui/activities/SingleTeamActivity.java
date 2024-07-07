package pmf.android.sportscoresbysbm2.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.data.database.TeamEntity;
import pmf.android.sportscoresbysbm2.data.model.SingleTeamResponse;
import pmf.android.sportscoresbysbm2.data.repository.SingleTeamRepository;
import pmf.android.sportscoresbysbm2.viewmodel.SingleTeamViewModel;

public class SingleTeamActivity extends AppCompatActivity {
    private Intent intent;
    private SingleTeamResponse.SingleTeam singleTeam;
    private Button favouritesButton;
    private SingleTeamRepository singleTeamRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_single_team);
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
                    startActivity(new Intent(getApplicationContext(), CountriesActivity.class));

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
                Log.e("SingleTeamActivity", singleTeamResponse.getResponse().get(0).getTeam().getName());

                singleTeam = singleTeamResponse.getResponse().get(0);
                TextView teamName = findViewById(R.id.teamName);
                teamName.setText(singleTeam.getTeam().getName());
            }
        });
    }
}