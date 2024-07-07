package pmf.android.sportscoresbysbm2.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.data.model.StandingsResponse;
import pmf.android.sportscoresbysbm2.util.RecyclerViewClickListenerInterface;

public class StandingsAdapter extends RecyclerView.Adapter<StandingsAdapter.StandingsViewHolder> {
    private Context context;
    private List<StandingsResponse.Standing> standingsList;
    private final RecyclerViewClickListenerInterface listener;
    private FragmentManager fragmentManager;
    public StandingsAdapter(Context context, List<StandingsResponse.Standing> standingsList, RecyclerViewClickListenerInterface listener) {
        this.context = context;
        this.listener = listener;
        if(this.standingsList!=null){
            this.standingsList.clear();
            this.standingsList.addAll(standingsList);
        }else{
            this.standingsList = standingsList;
        }
        this.fragmentManager = fragmentManager;
        this.standingsList = standingsList;
    }
    public StandingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_item, parent, false);
        FrameLayout frameLayout = new FrameLayout(parent.getContext());
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new StandingsViewHolder(frameLayout);
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
        StandingItem item = standingsList.get(position);
        StandingsFragment fragment = StandingsFragment.newInstance(item.getTeamName(), item.getPoints());
        fragmentManager.beginTransaction().replace(holder.frameLayout.getId(), fragment).commit();

        //If standing has groups inside (e.g Serbia,Belgium etc.)

      // if(standingsList.get(position).getGroup()!=null){
           // holder.standingsGroup.setText(standingsList.get(position).getGroup());
      //  }
    }


    @Override
    public int getItemCount() {
        if (this.standingsList == null) {
            return 0;
        } else{
        return this.standingsList.size();
    }}

    static class StandingsViewHolder extends RecyclerView.ViewHolder {
        FrameLayout frameLayout;

        public StandingsViewHolder(@NonNull FrameLayout itemView) {
            super(itemView);
            frameLayout = itemView;
            frameLayout.setId(View.generateViewId());
        }
    }

}
