package pmf.android.sportscoresbysbm2.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.data.model.StandingsResponse;
import pmf.android.sportscoresbysbm2.ui.viewholder.StandingsGroupViewHolder;
import pmf.android.sportscoresbysbm2.ui.viewholder.StandingsViewHolder;
import pmf.android.sportscoresbysbm2.util.RecyclerViewClickListenerInterface;

public class StandingsGroupAdapater   extends RecyclerView.Adapter<StandingsGroupViewHolder>   {

    private Context context;
    private List<List<StandingsResponse.Standing>> list;
    private final RecyclerViewClickListenerInterface listener;
    private int counter;
    public StandingsGroupAdapater(Context context, List<List<StandingsResponse.Standing>> list, RecyclerViewClickListenerInterface listener) {
        this.context = context;
        this.listener = listener;
        if(this.list!=null){
            this.list.clear();
        }else{
            this.list = list;
        }

    }
    public StandingsGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_standingsgroup, parent, false);
        return new StandingsGroupViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull StandingsGroupViewHolder holder, int position) {
             holder.standingsGroup.setText(list.get(position).get(0).getGroup());
             counter++;

    }


    @Override
    public int getItemCount() {
        if (this.list == null) {
            return 0;
        } else{
            return this.list.size();
        }}

}
