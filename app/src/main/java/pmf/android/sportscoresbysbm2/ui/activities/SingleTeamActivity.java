package pmf.android.sportscoresbysbm2.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.data.model.SingleTeamResponse;
import pmf.android.sportscoresbysbm2.viewmodel.SingleTeamViewModel;

public class SingleTeamActivity extends AppCompatActivity {
    private Intent intent;
    private SingleTeamResponse.SingleTeam singleTeam;
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

        initialization();

    }


    private void initialization(){
        intent = getIntent();
        Long teamId = intent.getLongExtra("teamId", 0L);
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