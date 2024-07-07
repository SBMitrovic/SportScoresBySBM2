package pmf.android.sportscoresbysbm2.ui.viewholder;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.util.RecyclerViewClickListenerInterface;

public class StandingsViewHolder extends RecyclerView.ViewHolder {
    public TextView teamPosition, teamName, teamPoints, matchesLost, matchesDrawn, matchesWon, matchesPlayed, standingsGroup;
    public ImageView teamLogo;
    public FrameLayout frameLayout;
    public StandingsViewHolder(@androidx.annotation.NonNull View itemView , RecyclerViewClickListenerInterface listener) {
        super(itemView);
        teamPosition = itemView.findViewById(R.id.team_position);
        teamName = itemView.findViewById(R.id.team_name);
        teamPoints = itemView.findViewById(R.id.team_points);
        matchesLost = itemView.findViewById(R.id.matches_lost);
        matchesDrawn = itemView.findViewById(R.id.matches_drawn);
        matchesWon = itemView.findViewById(R.id.matches_won);
        matchesPlayed = itemView.findViewById(R.id.matches_played);
        teamLogo = itemView.findViewById(R.id.team_logo);
        // standingsGroup = itemView.findViewById(R.id.standings_group);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            }
        });
    }
}
