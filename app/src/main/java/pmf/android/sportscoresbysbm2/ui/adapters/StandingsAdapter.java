package pmf.android.sportscoresbysbm2.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.data.model.League;
import pmf.android.sportscoresbysbm2.data.model.StandingsResponse;
import pmf.android.sportscoresbysbm2.ui.viewholder.StandingsViewHolder;
import pmf.android.sportscoresbysbm2.util.RecyclerViewClickListenerInterface;

public class StandingsAdapter extends RecyclerView.Adapter<StandingsViewHolder>  {
    private Context context;
    private List<StandingsResponse.Standing> standingsList;
    private final RecyclerViewClickListenerInterface listener;

    public StandingsAdapter(Context context, List<StandingsResponse.Standing> standingsList, RecyclerViewClickListenerInterface listener) {
        this.context = context;
        this.listener = listener;
        if(this.standingsList!=null){
            this.standingsList.clear();
            this.standingsList.addAll(standingsList);
        }else{
            this.standingsList = standingsList;
        }
    }
    public StandingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_item, parent, false);
        return new StandingsViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull StandingsViewHolder holder, int position) {
        holder.teamPosition.setText(standingsList.get(position).getRank().toString());
        holder.teamName.setText(standingsList.get(position).getTeam().getName());
        holder.teamPoints.setText(standingsList.get(position).getPoints().toString());
        holder.matchesLost.setText(standingsList.get(position).getAll().getLose().toString());
        holder.matchesDrawn.setText(standingsList.get(position).getAll().getDraw().toString());
        holder.matchesWon.setText(standingsList.get(position).getAll().getWin().toString());
        holder.matchesPlayed.setText(standingsList.get(position).getAll().getPlayed().toString());
        Picasso.get().load(standingsList.get(position).getTeam().getLogo()).into(holder.teamLogo);

        //If standing has groups inside (e.g Serbia,Belgium etc.)

      // if(standingsList.get(position).getGroup()!=null){
           // holder.standingsGroup.setText(standingsList.get(position).getGroup());
      //  }
    }


    @Override
    public int getItemCount() {
        return this.standingsList.size();
    }


}
