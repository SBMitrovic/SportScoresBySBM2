package pmf.android.sportscoresbysbm2.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import com.squareup.picasso.Picasso;

import okhttp3.Handshake;
import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.data.model.Competition;
import pmf.android.sportscoresbysbm2.ui.viewholder.CompetitionsViewHolder;
import pmf.android.sportscoresbysbm2.util.RecyclerViewClickListenerInterface;

public class CompetitionAdapter extends RecyclerView.Adapter<CompetitionsViewHolder> {

    private List<Competition> competitonsList;
    private Context context;
    private final RecyclerViewClickListenerInterface listener;

    public CompetitionAdapter(Context context, List<Competition> competitonsList, RecyclerViewClickListenerInterface listener) {
        this.context = context;
        this.competitonsList = competitonsList;
        this.listener = listener;
    }

    @Override
    public CompetitionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_competition, parent, false);
        CompetitionsViewHolder vh = new CompetitionsViewHolder(view, listener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CompetitionsViewHolder holder, int position) {

        holder.competitonName.setText(competitonsList.get(position).getLeague().getName());
        Picasso.get().load(competitonsList.get(position).getLeague().getLogo()).into(holder.competitionLogo);
    }

    @Override
    public int getItemCount() {
        return competitonsList.size();
    }


}
