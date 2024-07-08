package pmf.android.sportscoresbysbm2.ui.adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.data.database.TeamEntity;
import pmf.android.sportscoresbysbm2.data.model.Country;
import pmf.android.sportscoresbysbm2.ui.viewholder.CountriesViewHolder;
import pmf.android.sportscoresbysbm2.ui.viewholder.FavouriteTeamsViewHolder;
import pmf.android.sportscoresbysbm2.util.RecyclerViewClickListenerInterface;

public class FavouriteTeamsAdapter extends RecyclerView.Adapter<FavouriteTeamsViewHolder>  {
    private Context context;
    private final RecyclerViewClickListenerInterface listener;
    private List<TeamEntity> favTeamsList;

    public FavouriteTeamsAdapter(Context context, List<TeamEntity> favTeams, RecyclerViewClickListenerInterface listener) {
        this.context = context;
        this.listener = listener;
        if(this.favTeamsList!=null){
            this.favTeamsList.clear();
            this.favTeamsList.addAll(favTeams);
        }else{
            this.favTeamsList = favTeams;
        }
       // this.tempList = countryList;

    }

    @NonNull
    @Override
    public FavouriteTeamsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.list_favourite_team, parent, false);
        return new FavouriteTeamsViewHolder(view, listener);

    }
    @Override
    public void onBindViewHolder(@NonNull FavouriteTeamsViewHolder holder, int position) {

        holder.teamName.setText(favTeamsList.get(position).getTeamName());
    }

    @Override
    public int getItemCount() {
        if (favTeamsList == null){
            return 0;
        } else {return favTeamsList.size();}
    }




    public TeamEntity getItem(int position) {
        return favTeamsList.get(position);
    }
}




