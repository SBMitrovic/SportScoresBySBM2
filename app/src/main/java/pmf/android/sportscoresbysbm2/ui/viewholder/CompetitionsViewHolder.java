package pmf.android.sportscoresbysbm2.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.util.RecyclerViewClickListenerInterface;

public class CompetitionsViewHolder extends RecyclerView.ViewHolder {
    public TextView competitonName;
    public ImageView competitionLogo;


    public CompetitionsViewHolder(@NonNull View itemView, RecyclerViewClickListenerInterface listenerInterface) {
        super(itemView);
        this.competitonName = itemView.findViewById(R.id.competitionName);
        this.competitionLogo = itemView.findViewById(R.id.competitionLogo);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listenerInterface != null) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        listenerInterface.onItemClick(position);
                    }
                }
            }
        });
    }
}
