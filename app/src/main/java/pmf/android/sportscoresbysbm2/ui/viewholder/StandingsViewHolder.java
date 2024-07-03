package pmf.android.sportscoresbysbm2.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import pmf.android.sportscoresbysbm2.R;

public class StandingsViewHolder extends RecyclerView.ViewHolder {
    public TextView teamPosition, teamName, teamPoints, matchesLost, matchesDrawn, matchesWon, matchesPlayed;
    public ImageView teamLogo;

    public StandingsViewHolder(@androidx.annotation.NonNull View itemView) {
        super(itemView);
        teamPosition = itemView.findViewById(R.id.team_position);
        teamName = itemView.findViewById(R.id.team_name);
        teamPoints = itemView.findViewById(R.id.team_points);
        matchesLost = itemView.findViewById(R.id.matches_lost);
        matchesDrawn = itemView.findViewById(R.id.matches_drawn);
        matchesWon = itemView.findViewById(R.id.matches_won);
        matchesPlayed = itemView.findViewById(R.id.matches_played);
        teamLogo = itemView.findViewById(R.id.team_logo);
    }
}
