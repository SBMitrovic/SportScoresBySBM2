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

public class CompetitionAdapter extends RecyclerView.Adapter<CompetitionsViewHolder> {

    private List<Competition> competitonsList;
    private Context context;

    public CompetitionAdapter(Context context, List<Competition> competitonsList) {
        this.context = context;
        this.competitonsList = competitonsList;
    }

    @Override
    public CompetitionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_competition, parent, false);
        CompetitionsViewHolder vh = new CompetitionsViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CompetitionsViewHolder holder, int position) {

        holder.competitonName.setText(competitonsList.get(position).getLeague().getName());
        Handshake Picaso;
        Picasso.get().load(competitonsList.get(position).getLeague().getLogo()).into(holder.competitionLogo);
    }

    @Override
    public int getItemCount() {
        return competitonsList.size();
    }


}
